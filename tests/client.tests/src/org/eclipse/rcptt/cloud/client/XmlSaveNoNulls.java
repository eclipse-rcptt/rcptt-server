/********************************************************************************
 * Copyright (c) 2025 Xored Software Inc and others
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

import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl;

public class XmlSaveNoNulls extends XMLSaveImpl {

	public XmlSaveNoNulls(Map<?, ?> options, XMLHelper helper) {
		super(options, helper, "UTF-8");
	}

	@Override
	protected void init(XMLResource resource, Map<?, ?> options) {
		super.init(resource, options);
		if (useCache) {
			throw new UnsupportedOperationException("Can't use cached Escape");
		}
		Escape old = escape;
		escape = new EscapeNoNull();
		String lineSeparator = (String) options.get(Resource.OPTION_LINE_DELIMITER);
		escape.setLineFeed(lineSeparator);
		escape.setMappingLimit(0x10FFFF); // org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl.MAX_UTF_MAPPABLE_CODEPOINT
		if (xmlVersion.equals("1.0")) {
			throw new UnsupportedOperationException("Incompatible XML version");
		}
		escape.setAllowControlCharacters(true);
		escape.setUseCDATA(Boolean.TRUE.equals(options.get(XMLResource.OPTION_ESCAPE_USING_CDATA)));
	}

	public class EscapeNoNull extends Escape {
		@Override
		public String convert(String input) {
			return super.convert(input.replace("\u0000", ""));
		}
	}

}
