package org.eclipse.rcptt.maven;

import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.FileUtils;
import org.eclipse.aether.repository.RemoteRepository;

import org.eclipse.rcptt.maven.util.CoordResolver;

/**
 * Prepares resources for q7 launching:
 *   - copies project to target dir
 *   - unpacks all dependencies to target dir
 *   - resolves and unpacks AUT
 *   - resolves and unpacks q7 launcher
 * @author ivaninozemtsev
 *
 * @goal resources
 * @threadSafe
 * @phase generate-resources
 * @requiresDependencyResolution compile
 */
public class PrepareMojo extends AbstractQ7Mojo {
	public void execute() throws MojoExecutionException, MojoFailureException {
		int feature = Runtime.version().feature();
		int required = 21;
		if (feature < required) {
			throw new MojoExecutionException(format("Java version is %d, required %d", feature, required));
		}
		if (skipTests) {
			return;
		}

		unpackApps();
		copyProjectToTarget();
		unpackDependencies();
	}

	/**
	 * Copy project contents (except for the target dir) to target
	 * @throws MojoFailureException
	 * @throws MojoExecutionException
	 */
	private void copyProjectToTarget() throws MojoFailureException, MojoExecutionException {
		getLog().info("Copying project contents to target directory...");
		File destination = getThisProjectDir();
		try {
			for(File file : project.getBasedir().listFiles()) {
				if(file.equals(outDir)) {
					continue; //skip 'target' dir
				}
				if(file.isFile()) {
					FileUtils.copyFileToDirectory(file, destination);
				} else if(file.isDirectory()) {
					FileUtils.copyDirectoryStructure(file, new File(destination, file.getName()));
				}
			}
		} catch(IOException e) {
			throw new MojoExecutionException("Failed to copy project to target directory", e);
		}
	}

	/**
	 * Resolve and extract Q7 and AUT to target directory
	 * @throws MojoFailureException
	 * @throws MojoExecutionException
	 */
	private void unpackApps() throws MojoFailureException, MojoExecutionException {
		if(!projectHasTests()) {
			getLog().info("No Q7 tests found in project, skipping AUT and Q7 resolution");
			return;
		}
		CoordResolver resolver = getCoordResolver();
		File q7Archive = resolver.resolve("Q7 client", getQ7Coords(), remotePluginRepos);
		File q7Dir = getQ7Dir();
		getLog().info(String.format("Extracting Q7 client to %s", q7Dir));
		getArchiveUtil().extract(q7Archive, getQ7Dir());
	}

	/**
	 * Unpack all transitive dependencies to target/projects
	 * @throws MojoExecutionException
	 * @throws MojoFailureException
	 */
	private void unpackDependencies() throws MojoExecutionException, MojoFailureException {
		if(!projectHasTests()) {
			getLog().info("No Q7 tests found in project, skipping dependency unpacking");
			return;
		}

		for(org.apache.maven.artifact.Artifact a : project.getArtifacts()) {
			if(!Q7_TYPE.equals(a.getType())) {
				continue;
			}
			File artifact = a.getFile();

			//So that we won't get collision in rare case when
			//artifact ids are matching while group ids are different
			File destination = new File(getProjectsDir(),
					String.format("%s.%s", a.getGroupId(), a.getArtifactId()));
			getLog().info(String.format("Unpacking %s to %s", a, destination));
			getArchiveUtil().extract(artifact, destination);
		}
	}


	/**
	 * Remote repositories of current plugin, used to resolve q7 client
	 *
	 * @parameter default-value="${project.remotePluginRepositories}"
	 * @readonly
	 */
	private List<RemoteRepository> remotePluginRepos;
}
