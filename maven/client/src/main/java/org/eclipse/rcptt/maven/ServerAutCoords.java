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
