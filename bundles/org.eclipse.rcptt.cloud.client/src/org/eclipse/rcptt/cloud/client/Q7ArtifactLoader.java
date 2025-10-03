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

import static java.util.Objects.requireNonNull;
import static java.util.function.Predicate.not;
import static org.eclipse.rcptt.cloud.util.CheckedExceptionWrapper.encode;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.rcptt.cloud.common.Hash;
import org.eclipse.rcptt.cloud.util.CheckedExceptionWrapper;
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
import org.eclipse.rcptt.core.scenario.Verification;
import org.eclipse.rcptt.core.utils.TagsUtil;
import org.eclipse.rcptt.core.workspace.RcpttCore;
import org.eclipse.rcptt.core.workspace.WorkspaceFinder;
import org.eclipse.rcptt.internal.core.model.ModelManager;
import org.eclipse.rcptt.internal.core.model.index.NamedElementCollector;
import org.eclipse.rcptt.launching.utils.TestSuiteElementCollector;
import org.eclipse.rcptt.workspace.WorkspaceContext;

import com.google.common.base.Joiner;
import com.google.common.hash.HashCode;

public final class Q7ArtifactLoader {
	private final String[] skipTags;
	
	public final class ArtifactHandle {
		public final String id, name;
		public final List<String> verifications, contexts;
		public final HashCode hash;
		public final HandleType kind;
		public final String path;

		
		private ArtifactHandle(String id, String name, List<String> verifications, List<String> contexts, IQ7NamedElement element) throws ModelException {
			super();
			this.id = requireNonNull(id);
			this.name = requireNonNull(name);
			this.verifications = List.copyOf(verifications);
			this.verifications.forEach(Objects::requireNonNull);
			this.contexts = contexts.stream().filter(not(RcpttCore.DEFAULT_WORKBENCH_CONTEXT_ID::equals)).toList();
			this.contexts.forEach(Objects::requireNonNull);
			this.element = element;
			IQ7NamedElement workingCopy = element
					.getIndexingWorkingCopy(new NullProgressMonitor());
			try {
				this.kind = workingCopy.getElementType();
				this.path = workingCopy.getPath().toPortableString();
				assert EnumSet.of(HandleType.Context, HandleType.TestCase, HandleType.Verification).contains(kind);
				this.hash = HashCode.fromBytes(Hash.hash(patch(workingCopy.getNamedElement())));
			} finally {
				workingCopy.discardWorkingCopy();
			}
		}

		public NamedElement getContent() throws ModelException {
			NamedElement result;
			IQ7NamedElement workingCopy = element
					.getIndexingWorkingCopy(new NullProgressMonitor());
			try {
				// copy the object before closing the working copy?
				result = patch(workingCopy.getNamedElement());
			} finally {
				// This might corrupt the result with some unloading procedure. Assert below may help to debug then. 
				workingCopy.discardWorkingCopy();
			}
			assert HashCode.fromBytes(Hash.hash(result)).equals(hash);
			return result;		
		}
		
		private NamedElement patch(NamedElement namedElement) throws ModelException {
			assert ! (namedElement instanceof SuperContext);
			assert ! (namedElement instanceof GroupContext);
			namedElement.setName(name);
			if (namedElement instanceof Scenario scenario) {
				scenario.setId(id);
				scenario.getContexts().clear();
				scenario.getContexts().addAll(contexts);
				scenario.getVerifications().clear();
				scenario.getVerifications().addAll(verifications);
				return scenario;
			}
			assert namedElement.getId().equals(id);
			if (namedElement instanceof WorkspaceContext el) {
				ContextType type = ((IContext)element).getType();
				if (type != null) {
					type.getMaker().makeExecutable(el, element);
				}
				return namedElement;
			}
			assert namedElement instanceof Context || namedElement instanceof Verification;
			return namedElement;
		}
		
		private final IQ7NamedElement element;
	}


	public Q7ArtifactLoader(String[] skipTags) {
		this.skipTags = skipTags;
	}
	
	public Stream<ArtifactHandle> findArtifacts(String... suites)
			throws CoreException, InterruptedException {
		ModelManager.getModelManager().getIndexManager()
		.waitUntilReady(new NullProgressMonitor());
		NamedElementCollector collector = null;
		if (suites.length == 0) {
			collector = new NamedElementCollector(HandleType.Context, HandleType.Verification, HandleType.TestCase);
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
		
		
		CartesianFlattener flattener = new CartesianFlattener();
		try {
			elements.stream()
				.filter(IContext.class::isInstance)
				.map(IContext.class::cast)
				.map(org.eclipse.rcptt.cloud.util.CheckedExceptionWrapper.encode(  IContext::getNamedElement))
				.forEach(context -> {
					String id = context.getId();
					String name = context.getName();
					if (context instanceof GroupContext g) {
						flattener.putSequence(id, name, g.getContextReferences());
					} else if (context instanceof SuperContext s) {
						flattener.putVariant(id, name, s.getContextReferences());
					} else {
						flattener.putTerminal(id, name);
					}
				}
			);
		
			return elements.parallelStream().flatMap(e -> {
				try {
					return convertToHandles(e, flattener);
				} catch (CoreException e1) {
					throw new CheckedExceptionWrapper(e1);
				}
			}).peek(element -> {assert element.hash != null; });
		} catch (CheckedExceptionWrapper e) {
			e.rethrow(InterruptedException.class);
			e.rethrow(CoreException.class);
			e.rethrowUnchecked();
			throw e;
		}
	}

	private String formatExecutionId(String id, List<String> contextVariant) {
		if (contextVariant.isEmpty()) {
			return id;
		}
		return Stream.concat(Stream.of(id), contextVariant.stream()).collect(Collectors.joining("_")).replace(":", "").replace(" ", ""); 
	}

	private String formatExecutionName(String test, List<String> contextVariant) {
		if (contextVariant.isEmpty()) {
			return test;
		}
		return test + " (" + Joiner.on(", ").join(contextVariant) + ")";
	}
	
	private Stream<ArtifactHandle> convertToHandles(IQ7NamedElement base, CartesianFlattener flattener) throws CoreException {
		IQ7NamedElement element = base
				.getIndexingWorkingCopy(new NullProgressMonitor());
		try {
			try {
				NamedElement namedElement = element.getNamedElement();
				String id = namedElement.getId();
				String name = namedElement.getName();
				if (element instanceof ITestCase
						&& isSkipExecuton((ITestCase) element)) {
					return Stream.empty();
				}
				if (element instanceof ITestCase testcase) {
					IContext[] contexts = RcpttCore.getInstance().getContexts(testcase, WorkspaceFinder.getInstance(), false);
					Stream<CartesianFlattener.NamedSequence> variants = flattener.resolve(Arrays.stream(contexts).filter(c -> ! RcpttCore.DEFAULT_WORKBENCH_CONTEXT_ID.equals(encode(c::getID).get())).map(encode(IContext::getID)).toList(), name);
					return variants.map(encode((CartesianFlattener.NamedSequence s) -> new ArtifactHandle(formatExecutionId(id, s.name()),  formatExecutionName(name, s.name()), getVerifications(testcase), s.sequence(), base)));
				} else if (element instanceof IContext) {
					if (namedElement instanceof GroupContext) {
						return Stream.empty();
					}
					if (namedElement instanceof SuperContext) {
						return Stream.empty();
					}
					return Stream.of(new ArtifactHandle(id, namedElement.getName(), List.of(), List.of(), base));
				} else if (element instanceof IVerification) {
					return Stream.of(new ArtifactHandle(id, namedElement.getName(), List.of(), List.of(), base));
				}
			} catch (CheckedExceptionWrapper e) {
				e.rethrow(CoreException.class);
				e.rethrowUnchecked();
				throw e;
			}
			
			throw new IllegalArgumentException("Unknown artifact type: " + element.getPath());
		} catch (CoreException e) {
			throw new CoreException(new MultiStatus(getClass(), 0, new IStatus[] {e.getStatus()},  "Processing " + element.getElementName(), e));
		} catch (Exception e) {
			throw new CoreException(Status.error("Can't process " + base.getPath().toPortableString(), e));
		} finally {
			element.discardWorkingCopy();
		}
	}

	
	private static List<String> getVerifications(IQ7NamedElement element) throws ModelException {
		IVerification[] verifications = RcpttCore.getInstance().getVerifications(
				(ITestCase) element,
				WorkspaceFinder.getInstance(), false);
		if (verifications != null) {
			return Arrays.stream(verifications).peek(encode(Q7ArtifactLoader::checkExists)).map(encode(IVerification::getID)).peek(Objects::requireNonNull).toList();
		}
		return List.of();
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
	

}
