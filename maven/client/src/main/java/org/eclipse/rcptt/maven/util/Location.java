package org.eclipse.rcptt.maven.util;

public class Location {
	private String classifier;
	private String path;
	
	public String getClassifier() {
		return classifier;
	}
	
	public void setClassifier(String classifier) {
		this.classifier = classifier;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public String getValue() {
		return String.format("\"%s|%s\"", getClassifier(), getPath());
	}
}
