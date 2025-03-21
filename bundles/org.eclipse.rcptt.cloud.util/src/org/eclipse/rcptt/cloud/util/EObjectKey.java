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
package org.eclipse.rcptt.cloud.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public abstract class EObjectKey<E extends EObject> {
	private final E object;

	public EObjectKey(E object) {
		super();
		this.object = object;
	}

	public E getObject() {
		return object;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EObjectKey) {
			EObjectKey<?> other = (EObjectKey<?>) obj;
			return EcoreUtil.equals(object, other.object);
		}
		return false;
	}


	@Override
	public int hashCode() {
		return hash(object);
	}

	protected abstract int hash(E obj);
}
