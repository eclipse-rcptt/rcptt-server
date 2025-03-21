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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.core.ContextType;
import org.eclipse.rcptt.core.model.IContext;
import org.eclipse.rcptt.core.model.IQ7Element.HandleType;
import org.eclipse.rcptt.core.model.IQ7NamedElement;
import org.eclipse.rcptt.core.model.ITestCase;
import org.eclipse.rcptt.core.model.IVerification;
import org.eclipse.rcptt.core.model.ModelException;
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
import org.eclipse.rcptt.internal.core.model.index.TestSuiteElementCollector;
import org.eclipse.rcptt.workspace.WorkspaceContext;

import com.google.common.base.Strings;
import org.eclipse.rcptt.cloud.client.SuperContextSupport.ContextConfiguration;
import org.eclipse.rcptt.cloud.model.ModelFactory;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.RefKind;

public class Q7ArtifactLoader {
	private static final boolean USE_MD5 = false;
	private final String[] skipTags;

	public Q7ArtifactLoader(String[] skipTags) {
		this.skipTags = skipTags;
	}

	public Map<Q7ArtifactRef, IQ7NamedElement> artifactRefs(String... suites)
			throws CoreException {
		NamedElementCollector collector = null;
		if (suites.length == 0) {
			collector = new NamedElementCollector();
		} else {
			collector = new TestSuiteElementCollector(Arrays.asList(suites), true);
		}
		ModelManager.getModelManager().getModel().accept(collector);

		if (suites.length > 0) {
			List<IQ7NamedElement> elements = collector.getElements();
			if (elements.size() < suites.length) {
				// print not found suites
				Set<String> names = new HashSet<>();
				for (IQ7NamedElement el : elements) {
					names.add(el.getElementName());
				}
				List<String> missingNames = Arrays.asList(suites);
				missingNames.remove(names);
				if (missingNames.size() > 0) {
					System.out
							.println("ERROR: Failed to locate testsuites by name:\n"
									+ Arrays.toString(missingNames.toArray()));

				}
			}
		}

		final Map<Q7ArtifactRef, IQ7NamedElement> result = new HashMap<>();
		List<IQ7NamedElement> elements = collector.getElements();

		if (suites.length > 0) {
			// Collect also all contexts
			NamedElementCollector contexts = new NamedElementCollector(
					HandleType.Context, HandleType.Verification);
			ModelManager.getModelManager().getModel().accept(contexts);
			elements.addAll(contexts.getElements());
		}

		// ModelManager.getModelManager().getIndexManager().waitUntilReady();
		ExecutorService executor = Executors.newFixedThreadPool(Runtime
				.getRuntime().availableProcessors() + 1);
		final int[] count = { 0 };
		System.out.println("Processing resources (" + count[0] + " of "
				+ elements.size() + ")");
		for (final IQ7NamedElement e : elements) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						int c = collectArtifactsRefs(e, result);
						if (c >= 1) {
							count[0]++;
						}
					} catch (Throwable e1) {
						ClientAppPlugin.logErr(e1.getMessage(), e1);
					}
				}
			});
		}
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Processing resources (" + count[0] + " of "
					+ elements.size() + ")");
		}
		System.out.println(elements.size() + " resources processed");
		return result;
	}

	public static Q7Artifact getArtifact(IQ7NamedElement base,
			Map<String, Q7ArtifactRef> refs) throws CoreException {

		if (base instanceof Q7VariantTestCase) {
			Q7Artifact result = ModelFactory.eINSTANCE.createQ7Artifact();
			Q7VariantTestCase content = (Q7VariantTestCase) base;
			result.setId(content.getID());
			Scenario el = EcoreUtil.copy(content.getNamedElement());
			result.setContent(el);
			for (String contextId : el.getContexts()) {
				if (!refs.containsKey(contextId)) {
					throw ClientAppPlugin.createException(String.format(
							"Inconsistent workspace - context %s is not found",
							contextId));
				}
				result.getRefs().add(EcoreUtil.copy(refs.get(contextId)));
			}
			return result;
		}

		IQ7NamedElement content = base
				.getWorkingCopy(new NullProgressMonitor());
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
						if (!refs.containsKey(contextId)) {
							throw ClientAppPlugin
									.createException(String
											.format("Inconsistent workspace - context %s is not found",
													contextId));
						}
						result.getRefs().add(
								EcoreUtil.copy(refs.get(contextId)));
					}
				}
			}
			if (el instanceof Scenario) {
				Scenario scenario = (Scenario) el;
				if (refs.containsKey(scenario.getId())) {
					int ind = 0;
					for (String contextRef : refs.get(scenario.getId()).getRefs()) {
						assert contextRef != null;
						if (
							!scenario.getContexts().contains(contextRef) &&
							refs.get(contextRef).getKind() == RefKind.CONTEXT 
						) {
							scenario.getContexts().add(ind, contextRef);
							ind++;
						}
					}
				}
				for (String contextId : scenario.getContexts()) {
					if (!refs.containsKey(contextId)) {
						throw ClientAppPlugin
								.createException(String
										.format("Inconsistent workspace - context %s is not found",
												contextId));
					}
					result.getRefs().add(EcoreUtil.copy(refs.get(contextId)));
				}
			}

			return result;
		} finally {
			content.discardWorkingCopy();
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
			InputStream contents = new BufferedInputStream(
					resource.getContents());
			byte[] buffer = new byte[4096];
			int len = 0;
			try {
				while ((len = contents.read(buffer)) > 0) {
					md.update(buffer, 0, len);
				}
				contents.close();
			} catch (IOException e) {
				createException("Failed to calculate md5", e);
			}
			byte[] dg = md.digest();
			// md5cache.put(resource, dg);
			return dg;
		}
		return new byte[0];
	}

	private int collectArtifactsRefs(IQ7NamedElement base,
			Map<Q7ArtifactRef, IQ7NamedElement> results) throws CoreException {
		IQ7NamedElement element = base
				.getWorkingCopy(new NullProgressMonitor());
		try {
			int count = 0;
			if (element instanceof ITestCase
					&& isSkipExecuton((ITestCase) element)) {
				return 0;
			}
			if (element instanceof ITestCase) {
				// Add Contexts
				IContext[] contexts = RcpttCore.getInstance().getContexts(
						(ITestCase) element,
						WorkspaceFinder.getInstance(), false);
				List<IContext> superContexts = SuperContextSupport
						.findSuperContexts(contexts);
				if (superContexts == null || superContexts.isEmpty()) {
					Q7ArtifactRef result = ModelFactory.eINSTANCE
							.createQ7ArtifactRef();
					result.setId(element.getID());
					result.setHash(md5(element));
					result.setKind(RefKind.SCENARIO);
					addContextReferences(result, contexts);

					// Add verifications
					addVerifications(element, result);

					synchronized (results) {
						results.put(result, element);
						count++;
					}
				} else {
					List<ContextConfiguration> variants = SuperContextSupport
							.findContextVariants(superContexts, contexts);
					for (ContextConfiguration contextConfiguration : variants) {
						Q7ArtifactRef result = ModelFactory.eINSTANCE
								.createQ7ArtifactRef();

						result.setHash(md5(element));
						result.setKind(RefKind.SCENARIO);

						Q7VariantTestCase varian = new Q7VariantTestCase((Q7Element) element.getParent(),
								(ITestCase) element, contextConfiguration);
						result.setId(varian.getID());

						IContext[] configContexts = contextConfiguration
								.getContexts();

						addContextReferences(result, configContexts);
						// Add verifications
						addVerifications(element, result);

						synchronized (results) {
							results.put(result, varian);
							count++;
						}
					}
				}
			} else if (element instanceof IContext) {
				Q7ArtifactRef result = ModelFactory.eINSTANCE
						.createQ7ArtifactRef();
				result.setId(element.getID());
				result.setHash(md5(element));
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
				synchronized (results) {
					results.put(result, element);
					count++;
				}
			} else if (element instanceof IVerification) {
				Q7ArtifactRef result = ModelFactory.eINSTANCE
						.createQ7ArtifactRef();
				result.setId(element.getID());
				result.setHash(md5(element));
				result.setKind(RefKind.VERIFICATION);
				synchronized (results) {
					results.put(result, element);
					count++;
				}
			}
			return count;
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
				String id = v.getID();
				checkNotNull(id);
				result.getRefs().add(id);
			}
		}
	}

	private static void addContextReferences(Q7ArtifactRef result,
			IContext[] configContexts) throws ModelException {
		for (IContext ctx : configContexts) {
			String ctxID = ctx.getID();
			if (!RcpttCore.DEFAULT_WORKBENCH_CONTEXT_ID.equals(ctxID)) {
				checkNotNull(ctxID);
				result.getRefs().add(ctxID);
			}
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
