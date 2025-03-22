package com.xored.q7.maven;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Packages q7 project as artifact
 *
 * @author ivaninozemtsev
 *
 * @goal package
 * @threadSafe
 * @phase package
 */
public class PackageMojo extends AbstractQ7Mojo {

	public void execute() throws MojoExecutionException, MojoFailureException {
		createMainArtifact();
                if (skipTests) {
                    return;
                }
		if(!projectHasTests()) {
			getLog().info("No Q7 tests found in project, skipping result artifact packing");
			return;
		}
	}

	private void createMainArtifact() throws MojoFailureException,
			MojoExecutionException {
		File projectDir = getThisProjectDir();
		File destination = new File(projectDir.getParent(), String.format("%s-%s.zip", project.getArtifactId(), project.getVersion()));
		getArchiveUtil().compressDir(projectDir, destination);
		//main artifact
		project.getArtifact().setFile(destination);
	}

}
