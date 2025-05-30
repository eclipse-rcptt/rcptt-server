/********************************************************************************
 * Copyright (c) 2011 Xored Software Inc and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Xored Software Inc - initial API and implementation
 ********************************************************************************/
package org.eclipse.rcptt.cloud.client;

import static org.eclipse.rcptt.cloud.util.internal.UtilPlugin.createException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ICoreRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.cloud.client.SuperContextSupport.ContextConfiguration;
import org.eclipse.rcptt.cloud.model.ModelFactory;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.RefKind;
import org.eclipse.rcptt.core.ContextType;
import org.eclipse.rcptt.core.model.IContext;
import org.eclipse.rcptt.core.model.IQ7Element.HandleType;
import org.eclipse.rcptt.core.model.IQ7NamedElement;
import org.eclipse.rcptt.core.model.ITestCase;
import org.eclipse.rcptt.core.model.IVerification;
import org.eclipse.rcptt.core.model.ModelException;
import org.eclipse.rcptt.core.model.Q7Status;
import org.eclipse.rcptt.core.scenario.Context;
import org.eclipse.rcptt.core.scenario.GroupContext;
import org.eclipse.rcptt.core.scenario.NamedElement;
import org.eclipse.rcptt.core.scenario.Scenario;
import org.eclipse.rcptt.core.scenario.SuperContext;
import org.eclipse.rcptt.core.utils.TagsUtil;
import org.eclipse.rcptt.core.workspace.RcpttCore;
import org.eclipse.rcptt.core.workspace.WorkspaceFinder;
import org.eclipse.rcptt.internal.core.model.ModelManager;
import org.eclipse.rcptt.internal.core.model.Q7Element;
import org.eclipse.rcptt.internal.core.model.index.NamedElementCollector;
import org.eclipse.rcptt.launching.CheckedExceptionWrapper;
import org.eclipse.rcptt.launching.utils.TestSuiteElementCollector;
import org.eclipse.rcptt.workspace.WorkspaceContext;

import com.google.common.base.Strings;

public class Q7ArtifactLoader {
	private static final boolean USE_MD5 = false;
	private final String[] skipTags;

	public Q7ArtifactLoader(String[] skipTags) {
		this.skipTags = skipTags;
	}
	
	public record ElementAndReferences(IQ7NamedElement element, Q7ArtifactRef references) {
		public ElementAndReferences {
			Objects.requireNonNull(element);
			Objects.requireNonNull(references);
		}
	};

	public Stream<ElementAndReferences> artifactRefs(String... suites)
			throws CoreException, InterruptedException {
		NamedElementCollector collector = null;
		if (suites.length == 0) {
			collector = new NamedElementCollector();
		} else {
			collector = new TestSuiteElementCollector(Arrays.asList(suites), true);
		}
		ModelManager.getModelManager().getModel().accept(collector);
		List<IQ7NamedElement> elements = collector.getElements();

		if (collector instanceof TestSuiteElementCollector suiteCollector) {
			Set<String> absent = suiteCollector.getAbsentSuites();
			if (!absent.isEmpty()) {
				throw new CoreException(Status.error("Following suites are not found: " + absent));
			}
			// Collect also all contexts
			NamedElementCollector contexts = new NamedElementCollector(
					HandleType.Context, HandleType.Verification);
			ModelManager.getModelManager().getModel().accept(contexts);
			elements.addAll(contexts.getElements());
		}



		// ModelManager.getModelManager().getIndexManager().waitUntilReady();
			class ProgressJob extends Job {
				private final AtomicInteger count = new AtomicInteger(0);
				public ProgressJob() {
					super("Report progress");
				}

				@Override
				protected IStatus run(IProgressMonitor monitor) {
					while (!monitor.isCanceled()) {
						System.out.println("Processing resources (" + count.get() + " of " + elements.size() + ")");
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							return Status.OK_STATUS;
						}
					}
					System.out.println(count.get() + " resources processed");
					return Status.OK_STATUS;
				}
			}
			ProgressJob progressJob = new ProgressJob();
			progressJob.schedule(10000);
			try {
				return elements.parallelStream().flatMap(e -> {
					try {
						return collectArtifactsRefs(e);
					} catch (CoreException e1) {
						throw new CheckedExceptionWrapper(e1);
					}
				}).peek(ignored -> progressJob.count.getAndIncrement()).onClose(progressJob::cancel);
			} catch (CheckedExceptionWrapper e) {
				e.rethrow(InterruptedException.class);
				e.rethrow(CoreException.class);
				e.rethrowUnchecked();
				throw e;
			} finally {
				progressJob.cancel();
			}
	}
	
	interface ArtifactReferenceById {
		Q7ArtifactRef apply(String id) throws CoreException;
	}

	public static Q7Artifact getArtifact(IQ7NamedElement base,
			ArtifactReferenceById refs) throws CoreException {

			try {
			if (base instanceof Q7VariantTestCase) {
				Q7Artifact result = ModelFactory.eINSTANCE.createQ7Artifact();
				Q7VariantTestCase content = (Q7VariantTestCase) base;
				result.setId(content.getID());
				Scenario el = EcoreUtil.copy(content.getNamedElement());
				result.setContent(el);
				for (String contextId : el.getContexts()) {
					result.getRefs().add(refs.apply(contextId));
				}
				return result;
			}
	
			IQ7NamedElement content = base.getWorkingCopy(new NullProgressMonitor());
			try {
				Q7Artifact result = ModelFactory.eINSTANCE.createQ7Artifact();
				result.setId(content.getID());
				NamedElement el = EcoreUtil.copy(content.getNamedElement());
				result.setContent(el);
				if (el instanceof Context) {
					IContext ctx = (IContext) content;
					if (result.getContent() instanceof WorkspaceContext) {
						ContextType type = ctx.getType();
						if (type != null) {
							type.getMaker().makeExecutable((Context) el, content);
						}
					}
					if (el instanceof GroupContext) {
						for (String contextId : ((GroupContext) el)
								.getContextReferences()) {
							result.getRefs().add(
									EcoreUtil.copy(refs.apply(contextId)));
						}
					}
				}
				if (el instanceof Scenario) {
					Scenario scenario = (Scenario) el;
					int ind = 0;
					for (String contextRef : refs.apply(scenario.getId()).getRefs()) {
						assert contextRef != null;
						if (
							!scenario.getContexts().contains(contextRef) &&
							refs.apply(contextRef).getKind() == RefKind.CONTEXT 
						) {
							scenario.getContexts().add(ind, contextRef);
							ind++;
						}
					}
					for (String contextId : scenario.getContexts()) {
						result.getRefs().add(refs.apply(contextId));
					}
				}
	
				return result;
			} finally {
				content.discardWorkingCopy();
			}
		} catch (CoreException e) {
			throw new CoreException(new MultiStatus(Q7ArtifactLoader.class, 0, new IStatus[]{e.getStatus()}, "Failed to load " + base.getPath(), e));
		} catch (Exception e) {
			throw new CoreException(Status.error("Failed to load " + base.getPath(), e));
		}
	}
	
	public static byte[] md5(IQ7NamedElement obj) throws CoreException {
		if (USE_MD5) {
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("md5");
			} catch (NoSuchAlgorithmException e) {
				throw createException("Can't calc md5 hash", e);
			}
			md.reset();
			md.update(md5simple(obj));
			return md.digest();
		}
		return null;
	}

	private static byte[] md5simple(IQ7NamedElement obj) throws CoreException {
		IFile resource = (IFile) obj.getResource();
		if (resource.exists()) {
			// byte[] bs = md5cache.get(resource);
			// if (bs != null) {
			// return bs;
			// }
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("md5");
			} catch (NoSuchAlgorithmException e) {
				throw createException("Can't calc md5 hash", e);
			}
			try (InputStream contents = new BufferedInputStream(resource.getContents())) {
				byte[] buffer = new byte[4096];
				int len = 0;
				while ((len = contents.read(buffer)) > 0) {
					md.update(buffer, 0, len);
				}
			} catch (IOException e) {
				createException("Failed to calculate md5", e);
			}
			byte[] dg = md.digest();
			// md5cache.put(resource, dg);
			return dg;
		}
		return new byte[0];
	}

	private Stream<ElementAndReferences> collectArtifactsRefs(IQ7NamedElement base) throws CoreException {
		IQ7NamedElement element = base
				.getIndexingWorkingCopy(new NullProgressMonitor());
		try {
			if (element instanceof ITestCase
					&& isSkipExecuton((ITestCase) element)) {
				return Stream.empty();
			}
			Q7ArtifactRef result = ModelFactory.eINSTANCE
					.createQ7ArtifactRef();
			result.setId(element.getID());
			result.setHash(md5(element));

			if (element instanceof ITestCase) {
				// Add Contexts
				IContext[] contexts = RcpttCore.getInstance().getContexts(
						(ITestCase) element,
						WorkspaceFinder.getInstance(), false);
				List<IContext> superContexts = SuperContextSupport
						.findSuperContexts(contexts);
				if (superContexts == null || superContexts.isEmpty()) {
					result.setKind(RefKind.SCENARIO);
					addContextReferences(result, contexts);

					// Add verifications
					addVerifications(element, result);

					return Stream.of(new ElementAndReferences(base, result));
				} else {
					List<ContextConfiguration> variants = SuperContextSupport
							.findContextVariants(superContexts, contexts);
					try {
						return variants.stream().map(CheckedExceptionWrapper.wrap(contextConfiguration -> {
							Q7ArtifactRef result2 = ModelFactory.eINSTANCE
									.createQ7ArtifactRef();
							result2.setHash(md5(element));
							result2.setKind(RefKind.SCENARIO);
	
							Q7VariantTestCase varian = new Q7VariantTestCase((Q7Element) element.getParent(),
									(ITestCase) base, contextConfiguration);
							result2.setId(varian.getID());
	
							IContext[] configContexts = contextConfiguration
									.getContexts();
	
							addContextReferences(result2, configContexts);
							// Add verifications
							addVerifications(element, result2);
	
							return new ElementAndReferences(varian, result2);
						}));
					} catch (CheckedExceptionWrapper e) {
						e.rethrow(CoreException.class);
						e.rethrowUnchecked();
						throw e;
					}
				}
			} else if (element instanceof IContext) {
				NamedElement namedElement = element.getNamedElement();
				if (namedElement instanceof GroupContext) {
					EList<String> refs = ((GroupContext) namedElement)
							.getContextReferences();
					refs.forEach(Q7ArtifactLoader::checkNotNull);
					result.getRefs().addAll(new ArrayList<>(refs));
				}
				if (namedElement instanceof SuperContext) {
					EList<String> refs = ((SuperContext) namedElement)
							.getContextReferences();
					result.getRefs().addAll(new ArrayList<>(refs));
				}
				result.setKind(RefKind.CONTEXT);
				return Stream.of(new ElementAndReferences(base, result));
			} else if (element instanceof IVerification) {
				result.setKind(RefKind.VERIFICATION);
				return Stream.of(new ElementAndReferences(base, result));
			}
			return Stream.empty();
		} catch (CoreException e) {
			throw new CoreException(new MultiStatus(getClass(), 0, new IStatus[] {e.getStatus()},  "Processing " + element.getElementName(), e));
		} finally {
			element.discardWorkingCopy();
		}
	}

	private static void addVerifications(IQ7NamedElement element, Q7ArtifactRef result) throws ModelException {
		IVerification[] verifications = RcpttCore.getInstance().getVerifications(
				(ITestCase) element,
				WorkspaceFinder.getInstance(), false);
		if (verifications != null) {
			for (IVerification v : verifications) {
				checkExists(v);
				String id = v.getID();
				checkNotNull(id);
				result.getRefs().add(id);
			}
		}
	}

	private static void addContextReferences(Q7ArtifactRef result,
			IContext[] configContexts) throws ModelException {
		for (IContext ctx : configContexts) {
			checkExists(ctx);
			String ctxID = ctx.getID();
			if (!RcpttCore.DEFAULT_WORKBENCH_CONTEXT_ID.equals(ctxID)) {
				checkNotNull(ctxID);
				result.getRefs().add(ctxID);
			}
		}
	}
	
	private static void checkExists(IQ7NamedElement element) throws ModelException {
		if (!element.exists()) {
			throw new ModelException(new Q7Status(0, String.format("Element %s does not exist", element.getID())));
		}
	}

	private boolean isSkipExecuton(ITestCase testCase) {
		try {
			return TagsUtil.hasAny(testCase, skipTags);
		} catch (ModelException e) {
			ClientAppPlugin.logWarn("Failed to get tags for testcase:"
					+ testCase.getPath().toString(), e);
		}
		return false;
	}
	
	
	private static void checkNotNull(String id) {
		if (Strings.isNullOrEmpty(id) || "null".contentEquals(id))
			throw new NullPointerException();
	}

}
