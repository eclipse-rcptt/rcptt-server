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
package org.eclipse.rcptt.cloud.server.ecl.impl.internal;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.rcptt.ecl.core.LiteralParameter;
import org.eclipse.rcptt.ecl.runtime.IParamConverter;

import org.eclipse.rcptt.cloud.model.ModelFactory;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;

public class ArtifactRefParamConverter implements
		IParamConverter<Q7ArtifactRef> {

	public Q7ArtifactRef convert(LiteralParameter literal,
			List<String> allowedTypes) throws CoreException {
		final Q7ArtifactRef ref = ModelFactory.eINSTANCE.createQ7ArtifactRef();
		ref.setId(literal.getLiteral());
		return ref;
	}

	public Class<Q7ArtifactRef> forType() {
		return Q7ArtifactRef.class;
	}

    public String convertToCode(Object object) {
        if(object instanceof Q7ArtifactRef) {
            return ((Q7ArtifactRef) object).getId();
        }
        return object == null ? null : object.toString();
    }
}
