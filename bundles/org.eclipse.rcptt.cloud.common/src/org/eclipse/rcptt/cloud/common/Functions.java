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
package org.eclipse.rcptt.cloud.common;

import com.google.common.base.Function;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;

public class Functions {

	public static Function<Q7ArtifactRef, String> ARTIFACT_ID = new Function<Q7ArtifactRef, String>() {

		public String apply(Q7ArtifactRef input) {
			return input.getId();
		}
	};

}
