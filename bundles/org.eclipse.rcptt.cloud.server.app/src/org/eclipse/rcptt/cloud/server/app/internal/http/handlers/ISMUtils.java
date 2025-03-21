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
package org.eclipse.rcptt.cloud.server.app.internal.http.handlers;

import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.base.Function;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;

public class ISMUtils {
	public static Function<SuiteStats, SuiteStats> getStatsCopy = new Function<SuiteStats, SuiteStats>() {
		public SuiteStats apply(SuiteStats input) {
			return EcoreUtil.copy(input);
		}
	};
	public static Function<Execution, Execution> getExecutionCopy = new Function<Execution, Execution>() {
		public Execution apply(Execution input) {
			return EcoreUtil.copy(input);
		}
	};
}
