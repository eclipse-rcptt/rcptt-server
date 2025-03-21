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
package org.eclipse.rcptt.cloud.model;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.rcptt.core.scenario.Scenario;

public final class Q7TestingHelper {
	public enum TestCaseState {
		pass // test marked as passed
		, fail // test marked as failed
		, failtoload // cause server to fail test with failed to load status
		, agentfailtoload // cause agent to fail test with failed to load
		// status
		, agentdisconnect // disconnect agent on test execution
		, timeout // cause agent to work as test timeout happen
		, wait20sec // agent will wait for 60 seconds for test
		, waitonCounter // agent will wait for 60 seconds for test
		, autfailure_deploy // Failure to deply AUT
		, autfailure_start // Failure to start AUT
		, clientLost // Simulate client lost
		, wait5sec, ;
	}

	public static boolean TESTING = false;

	public static boolean containsTag(EObject ct, TestCaseState state) {
		if (Q7TestingHelper.TESTING) {
			if (ct instanceof Scenario) {
				String tags = ((Scenario) ct).getTags();
				if (tags != null && tags.contains(state.name())) {
					return true;
				}
			}
		}
		return false;
	}
}
