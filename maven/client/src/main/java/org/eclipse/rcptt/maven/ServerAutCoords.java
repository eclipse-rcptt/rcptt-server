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
package org.eclipse.rcptt.maven;

import org.eclipse.rcptt.maven.util.AutCoords;
import org.eclipse.rcptt.maven.util.Location;

public class ServerAutCoords extends AutCoords {

	private String uri;
	private Location[] locations;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Location[] getLocations() {
		return locations;
	}

	public void setLocationd(Location[] locations) {
		this.locations = locations;
	}

}
