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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.rcptt.core.model.IContext;
import org.eclipse.rcptt.core.model.ModelException;
import org.eclipse.rcptt.core.scenario.NamedElement;
import org.eclipse.rcptt.core.scenario.SuperContext;
import org.eclipse.rcptt.core.workspace.IWorkspaceFinder;
import org.eclipse.rcptt.core.workspace.RcpttCore;
import org.eclipse.rcptt.core.workspace.WorkspaceFinder;
import org.eclipse.rcptt.internal.core.RcpttPlugin;

public class SuperContextSupport {
	public static class ContextConfiguration {
		List<String> name;
		List<IContext> contexts;

		public IContext[] getContexts() {
			if (contexts == null) {
				return new IContext[0];
			}
			return contexts.toArray(new IContext[contexts.size()]);
		}

		public List<String> getVariantName() {
			return name;
		}
	}

	private static class Variant {
		IContext context;
		List<IContext> childs;
	}

	public static List<ContextConfiguration> findContextVariants(
			List<IContext> superContexts, IContext[] orderedContexts) {
		List<ContextConfiguration> configs = new ArrayList<SuperContextSupport.ContextConfiguration>();
		List<Variant> variants = extractVariants(superContexts);
		List<IContext> orderedList = new ArrayList<IContext>(Arrays.asList(orderedContexts));
		int[] currentConfig = new int[variants.size()];

		for (int i = 0; i < currentConfig.length; i++) {
			currentConfig[i] = 0;
		}
		while (true) {
			try {
				ContextConfiguration cfg = new ContextConfiguration();
				cfg.contexts = new ArrayList<IContext>(orderedList);
				cfg.name = new ArrayList<String>();
				for (int i = 0; i < currentConfig.length; i++) {
					Variant var = variants.get(i);
					int ind = cfg.contexts.indexOf(var.context);
					IContext varContext = var.childs.get(currentConfig[i]);
					cfg.contexts.set(ind, varContext);
					cfg.name.add(varContext.getElementName());
				}
				configs.add(cfg);
			} catch (ModelException e) {
				RcpttPlugin.log(e);
			}
			if (!increaseConfig(currentConfig, variants)) {
				break;
			}
		}

		return configs;
	}

	public static boolean increaseConfig(int[] currentConfig,
			List<Variant> variants) {
		for (int i = 0; i < currentConfig.length; i++) {
			if (currentConfig[i] + 1 < variants.get(i).childs.size()) {
				currentConfig[i]++;
				return true;
			}
			currentConfig[i] = 0;
		}

		return false;
	}

	private static List<Variant> extractVariants(List<IContext> superContexts) {
		List<Variant> variants = new ArrayList<Variant>();
		IWorkspaceFinder finder = WorkspaceFinder.getInstance();
		for (IContext superCtx : superContexts) {
			try {
				SuperContext sElement = (SuperContext) superCtx
						.getNamedElement();
				EList<String> list = sElement.getContextReferences();
				List<IContext> contextChilds = new ArrayList<IContext>();
				for (String s : list) {
					IContext result = RcpttCore.getInstance().findContext(
							superCtx, false, s, finder);
					if (result != null) {
						contextChilds.add(result);
					}
				}
				if (contextChilds.size() > 0) {
					Variant var = new Variant();
					var.context = superCtx;
					var.childs = contextChilds;
					variants.add(var);
				}

			} catch (ModelException e) {
				RcpttPlugin.log(e);
			}
		}
		return variants;
	}

	public static List<IContext> findSuperContexts(IContext[] contexts) {
		List<IContext> ctxs = new ArrayList<IContext>();
		for (IContext iContext : contexts) {
			try {
				NamedElement element = iContext.getNamedElement();
				if (element instanceof SuperContext) {
					ctxs.add(iContext);
				}
			} catch (Exception e) {
				RcpttPlugin.log(e);
			}

		}
		return ctxs;
	}
}
