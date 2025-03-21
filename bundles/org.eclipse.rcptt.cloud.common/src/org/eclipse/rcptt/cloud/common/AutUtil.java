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

import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.util.EObjectKey;

public class AutUtil {

	public static String getName(AutInfo aut) {
		return aut.getId();
	}

	public static EObjectKey<AutInfo> getAutKey(AutInfo info) {
		return new EObjectKey<AutInfo>(info) {

			@Override
			protected int hash(AutInfo obj) {
				int result = obj.getId().hashCode();
				result = result * 31 + obj.getUri().hashCode();
				result = result * 31 + obj.getClassifier().hashCode();
				return result;
			}
		};
	}

}
