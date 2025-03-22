package org.eclipse.rcptt.maven.util;

public class Q7Coords extends Coords {
	private String clientId = null;
	private String clientSecret = null;
    private String organization = null;
	private String licenseFile = null;
	private String licenseUrl = null;
	private String plugins[];

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getLicenseFile() {
		return licenseFile;
	}

	public void setLicenseFile(String licenseFile) {
		this.licenseFile = licenseFile;
	}
	
	public String getLicenseUrl() {
		return licenseUrl;
	}

	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}

	public String[] getPlugins() {
		return plugins;
	}

	public void setPlugins(String[] extensions) {
		this.plugins = extensions;
	}
}