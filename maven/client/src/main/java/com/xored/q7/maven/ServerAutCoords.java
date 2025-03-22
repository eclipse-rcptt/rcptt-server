package com.xored.q7.maven;

import org.eclipse.rcptt.maven.util.AutCoords;
import com.xored.q7.maven.util.Location;

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
