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

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.cloud.client.SuperContextSupport.ContextConfiguration;
import org.eclipse.rcptt.cloud.common.Hash;
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
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.hash.HashCode;

public class Q7ArtifactLoader {
	private final String[] skipTags;
	private final ListMultimap<String, String> superContexts = MultimapBuilder.hashKeys().arrayListValues().build();
	private final ListMultimap<String, String> groupContexts = MultimapBuilder.hashKeys().arrayListValues().build();
	private final Map<String, String> idToName = new HashMap<>();
	
	public final class ArtifactHandle {
		public final String id;
		public final List<String> verifications, contexts;
		public final HashCode hash;
		
		
		private ArtifactHandle(List<String> verifications, List<String> contexts, IQ7NamedElement element) throws ModelException {
			super();
			this.verifications = List.copyOf(verifications);
			this.contexts = List.copyOf(contexts);
			this.element = element;
			IQ7NamedElement workingCopy = element
					.getIndexingWorkingCopy(new NullProgressMonitor());
			try {
				this.id = workingCopy.getID();
				this.hash = HashCode.fromBytes(Hash.hash(patch(workingCopy.getNamedElement())));; 
			} finally {
				workingCopy.discardWorkingCopy();
			}
		}

		public NamedElement getContent() throws ModelException {
			NamedElement result;
			IQ7NamedElement workingCopy = element
					.getIndexingWorkingCopy(new NullProgressMonitor());
			try {
				result = patch(workingCopy.getNamedElement());
			} finally {
				// This might corrupt the result with some unloading procedure. Assert below may help to debug then. 
				workingCopy.discardWorkingCopy();
			}
			assert HashCode.fromBytes(Hash.hash(result)).equals(hash);
			return result;		
		}
		
		private NamedElement patch(NamedElement namedElement) {
		}
		
		private final IQ7NamedElement element;
	}


	public Q7ArtifactLoader(String[] skipTags) {
		this.skipTags = skipTags;
	}
	
	public record ElementAndReferences(IQ7NamedElement element, Q7ArtifactRef references) {
		public ElementAndReferences {
			Objects.requireNonNull(element);
			Objects.requireNonNull(references);
		}
	};

	public Stream<ArtifactHandle> artifactRefs(String... suites)
			throws CoreException, InterruptedException {
		ModelManager.getModelManager().getIndexManager()
		.waitUntilReady(new NullProgressMonitor());
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
		
		elements.stream()
			.filter(IContext.class::isInstance)
			.map(IContext.class::cast)
			.map(org.eclipse.rcptt.cloud.util.CheckedExceptionWrapper.encode(  IContext::getNamedElement))
			.forEach(context -> {
				String id = g.getId();
				idToName.put(id, suiteCollector)
			if (context instanceof GroupContext g) {
				groupContexts.putAll(id,  g.getContextReferences());
			} else if (context instanceof SuperContext s) {
				superContexts.putAll(id, s.getContextReferences());
			}
		});
		
		try {
			return elements.parallelStream().flatMap(e -> {
				try {
					return collectArtifactsRefs(e);
				} catch (CoreException e1) {
					throw new CheckedExceptionWrapper(e1);
				}
			}).peek(element -> {assert element.references().getHash() != null; });
		} catch (CheckedExceptionWrapper e) {
			e.rethrow(InterruptedException.class);
			e.rethrow(CoreException.class);
			e.rethrowUnchecked();
			throw e;
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
				Scenario scenario = EcoreUtil.copy(content.getNamedElement());
				result.setContent(scenario);
				for (String contextId : scenario.getContexts()) {
					result.getRefs().add(refs.apply(contextId));
				}
				
				// Handle default verifications
				int ind = 0;
				for (String ref : refs.apply(scenario.getId()).getRefs()) {
					assert ref != null;
					if (
						!scenario.getVerifications().contains(ref) &&
						refs.apply(ref).getKind() == RefKind.VERIFICATION 
					) {
						scenario.getVerifications().add(ind, ref);
						ind++;
					}
				}
				
				for (String id : scenario.getVerifications()) {
					result.getRefs().add(refs.apply(id));
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
					// Handle default contexts
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
					// Handle default verifications
					ind = 0;
					for (String ref : refs.apply(scenario.getId()).getRefs()) {
						assert ref != null;
						if (
							!scenario.getVerifications().contains(ref) &&
							refs.apply(ref).getKind() == RefKind.VERIFICATION 
						) {
							scenario.getVerifications().add(ind, ref);
							ind++;
						}
					}
					
					for (String contextId : scenario.getContexts()) {
						result.getRefs().add(refs.apply(contextId));
					}
					
					for (String id : scenario.getVerifications()) {
						result.getRefs().add(refs.apply(id));
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

	private static byte[] hash(IQ7NamedElement obj) throws CoreException {
		return Hash.hash(obj.getNamedElement());
	}

	private Stream<ArtifactHandle> collectArtifactsRefs(IQ7NamedElement base) throws CoreException {
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

			if (element instanceof ITestCase testcase) {
				Stream<List<String>> contextVariants = resolveContexts(testcase);
				for (List<String> contexts: (Iterable<List<String>>)contextVariants::iterator) {
					
				}
				
				// Add Contexts
				IContext[] contexts = RcpttCore.getInstance().getContexts(
						(ITestCase) element,
						WorkspaceFinder.getInstance(), false);
				// Add verifications
				addVerifications(element, result);


			} else if (element instanceof IContext) {
				NamedElement namedElement = element.getNamedElement();
				if (namedElement instanceof GroupContext) {
					EList<String> refs = ((GroupContext) namedElement)
							.getContextReferences();
					refs.forEach(Q7ArtifactLoader::checkNotNull);
					result.getRefs().addAll(new ArrayList<>(refs));
				}
				if (namedElement instanceof SuperContext) {
					return Stream.empty();
				}
				result.setKind(RefKind.CONTEXT);
				result.setHash(hash(element));
				return Stream.of(new ElementAndReferences(base, result));
			} else if (element instanceof IVerification) {
				result.setKind(RefKind.VERIFICATION);
				result.setHash(hash(element));
				return Stream.of(new ElementAndReferences(base, result));
			}
			return Stream.empty();
		} catch (CoreException e) {
			throw new CoreException(new MultiStatus(getClass(), 0, new IStatus[] {e.getStatus()},  "Processing " + element.getElementName(), e));
		} finally {
			element.discardWorkingCopy();
		}
	}

	private record NamedContextList(String name, List<String> contexts) {}
	private Stream<NamedContextList> resolveSuperContexts(ITestCase testcase) {
		IContext[] contexts = RcpttCore.getInstance().getContexts(
				(ITestCase) testcase,
				WorkspaceFinder.getInstance(), false);
	
		
		List<Set<String>> combinations  Arrays.stream(contexts).map(org.eclipse.rcptt.cloud.util.CheckedExceptionWrapper.encode(IContext::getID)).toList();
		List<IContext> superContexts = SuperContextSupport.findSuperContexts(contexts);
		if (superContexts == null || superContexts.isEmpty()) {
			result.setKind(RefKind.SCENARIO);
			addContextReferences(result, contexts);
			return Stream.of(new ElementAndReferences(base, result));
		} else {
			List<ContextConfiguration> variants = SuperContextSupport
					.findContextVariants(superContexts, contexts);
			try {
				return variants.stream().map(CheckedExceptionWrapper.wrap(contextConfiguration -> {
					Q7ArtifactRef result2 = ModelFactory.eINSTANCE
							.createQ7ArtifactRef();
					result2.setKind(RefKind.SCENARIO);

					Q7VariantTestCase varian = new Q7VariantTestCase((Q7Element) element.getParent(),
							(ITestCase) base, contextConfiguration);
					result2.setId(varian.getID());

					IContext[] configContexts = contextConfiguration
							.getContexts();

					addContextReferences(result2, configContexts);

					return new ElementAndReferences(varian, result2);
				}));
			} catch (CheckedExceptionWrapper e) {
				e.rethrow(CoreException.class);
				e.rethrowUnchecked();
				throw e;
			}
		}
	}
	
	private Strr

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
