package com.xored.q7.maven;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.archiver.manager.ArchiverManager;
import org.codehaus.plexus.util.FileUtils;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;

import org.eclipse.rcptt.maven.util.ArchiveUtil;
import org.eclipse.rcptt.maven.util.CoordResolver;
import com.xored.q7.maven.util.Q7Coords;
import org.eclipse.rcptt.maven.util.TestOptions;
import org.eclipse.rcptt.maven.util.RCPTTMetadata;

public abstract class AbstractQ7Mojo extends AbstractMojo {
	public static final String HAS_TESTS_KEY = "hasq7tests";
	public static final String Q7_TYPE = "rcpttTest";
	public static final String[] SCENARIO_EXTENSIONS = new String[] {
			"scenario", "test" };
	/**
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	protected MavenProject project;

	/**
	 * @parameter
	 * @required
	 */
	protected ServerAutCoords aut;

	/**
	 * @parameter
	 */
	protected String[] classifiers;

	/**
	 * @parameter
	 */
	protected String metadataFile;

    /**
     * @parameter
     *   expression="${maven.test.failure.ignore}"
     *   default-value="false"
     */
    protected boolean testFailureIgnore = false;

    /**
     * @parameter
     *   expression="${skipTests}"
     *   default-value="false"
     */
    protected boolean skipTests = false;

	/**
	 * @parameter
	 */
	private Map<String, String> metadata;

	protected Map<String, String> getMetadata() {
		if (metadata == null) {
			metadata = RCPTTMetadata.createDefaults(); // defaults
		}
		return metadata;
	}

	/**
	 * @parameter
	 */
	protected String[] suites;

	/**
	 * @parameter
	 */
	private Map<String, String> testOptions;

	protected Map<String, String> getTestOptions() {
		if (testOptions == null) {
			testOptions = TestOptions.createDefaults(); // defaults
		}
		return testOptions;
	}

	/**
	 * @parameter
	 */
	private Q7Coords q7client;

	private static final String Q7_GROUP_ID = "com.xored.q7.cloud";
	private static final String Q7_ARTIFACT_ID = "client";

	protected Q7Coords getQ7Coords() {
		if (q7client == null) {
			q7client = new Q7Coords();
		}
		q7client.setClassifier("");
		if (q7client.getArtifactId() == null) {
			q7client.setArtifactId(Q7_ARTIFACT_ID);
		}
		if (q7client.getGroupId() == null) {
			q7client.setGroupId(Q7_GROUP_ID);
		}
		return q7client;
	}

	/**
	 * Target directory.
	 *
	 * @parameter expression="${project.build.directory}"
	 * @required
	 */
	protected File outDir;

	private File projectsDir;

	/**
	 * Returns directory where we should unpack projects
	 *
	 * @return
	 * @throws MojoFailureException
	 *             when directory can't be created
	 */
	protected File getProjectsDir() throws MojoFailureException {
		if (projectsDir == null) {
			projectsDir = new File(outDir, "projects");
			if (!projectsDir.exists() && !projectsDir.mkdirs()) {
				throw new MojoFailureException("Can't create directory "
						+ projectsDir);
			}
		}
		return projectsDir;
	}

       
	/**
	 * @parameter
	 */
	protected String[] projects;
       
	/**
	 * @parameter
	 */
	protected String[] skipTags;

	private File q7WsDir;

	protected File getQ7WsDir() throws MojoFailureException {
		if (q7WsDir == null) {
			q7WsDir = new File(outDir, "q7workspace");
			if (!q7WsDir.exists() && !q7WsDir.mkdirs()) {
				throw new MojoFailureException("Can't create directory "
						+ projectsDir);
			}
		}
		return q7WsDir;
	}

	protected File getMetadataDir(File ws) {
		return new File(ws, ".metadata");
	}

	protected File getResolvedRcpLocation(File baseDir)
			throws MojoFailureException {
		if (!baseDir.exists() || !baseDir.isDirectory()) {
			throw new MojoFailureException(String.format(
					"Invalid RCP location %s", baseDir));
		}

		File result = baseDir;
		while (result.list().length == 1) {
			result = new File(result, result.list()[0]);
		}
		List<String> resChildren = Arrays.asList(result.list());
		if (!resChildren.contains("plugins")) {
			throw new MojoFailureException(String.format(
					"Invalid RCP location %s", baseDir));
		}
		return result;
	}

	private File q7location;

	protected final File getQ7Dir() {
		if (q7location == null) {
			q7location = new File(outDir, "q7");
		}
		return q7location;
	}

	protected File getResolvedQ7Dir() throws MojoFailureException {
		return getResolvedRcpLocation(getQ7Dir());
	}

	private File autWorkspacePrefix;

	protected File getAutWorkspacePrefix() {
		if (autWorkspacePrefix == null) {
			autWorkspacePrefix = new File(outDir, "aut-ws-");
		}
		return autWorkspacePrefix;
	}

	private File surefireDir;

	public File getSurefireDir() throws MojoFailureException {
		if (surefireDir == null) {
			surefireDir = new File(outDir, "surefire-reports");
			if (!surefireDir.exists() && !surefireDir.mkdirs()) {
				throw new MojoFailureException("Can't create directory "
						+ surefireDir);
			}
		}
		return surefireDir;
	}

	private File surefireFile;

	public File getSurefireFile() throws MojoFailureException {
		if (surefireFile == null) {
			surefireFile = new File(getSurefireDir(), String.format(
					"TEST-%s.xml", project.getName()));
		}
		return surefireFile;
	}

	private File resultsDir;

	protected File getResultsDir() throws MojoFailureException {
		if (resultsDir == null) {
			resultsDir = new File(outDir, "results");
			if (!resultsDir.exists() && !resultsDir.mkdirs()) {
				throw new MojoFailureException("Can't create directory "
						+ resultsDir);
			}
		}
		return resultsDir;
	}

	private File resultsFile;

	protected File getQ7ReportFile() throws MojoFailureException {
		if (resultsFile == null) {
			resultsFile = new File(getResultsDir(), "q7report.xml");
		}
		return resultsFile;
	}

	private File autConsolePrefix;

	protected File getAutConsolePrefix() throws MojoFailureException {
		if (autConsolePrefix == null) {
			autConsolePrefix = new File(getResultsDir(), "aut-console-");
		}
		return autConsolePrefix;
	}

	protected File getAutConsoleOut(int num) throws MojoFailureException {
		return new File(getAutConsolePrefix().getParent(), String.format(
				"%s%d_console.log", getAutConsolePrefix().getName(), num));
	}

	private File thisProjectDir;

	/**
	 * Returns the destination directory of current project in target so that we
	 * can copy it to this dir for further packaging as artifact
	 *
	 * @return
	 * @throws MojoFailureException
	 */
	protected File getThisProjectDir() throws MojoFailureException {
		if (thisProjectDir == null) {
			thisProjectDir = new File(getProjectsDir(), project.getArtifactId());
			if (!thisProjectDir.exists() && !thisProjectDir.mkdirs()) {
				throw new MojoFailureException("Can't create directory "
						+ thisProjectDir);
			}
		}
		return thisProjectDir;
	}

	private File q7Out;

	protected File getQ7Out() throws MojoFailureException {
		if (q7Out == null) {
			q7Out = new File(getResultsDir(), "q7out.txt");
		}
		return q7Out;
	}

	private File q7Err;

	public File getQ7Err() throws MojoFailureException {
		if (q7Err == null) {
			q7Err = new File(getResultsDir(), "q7err.txt");
		}
		return q7Err;
	}

	protected boolean projectHasTests() {
	    if(projects != null && projects.length > 0) {
		return true;
	    }
		if (!project.getProperties().containsKey(HAS_TESTS_KEY)) {
			project.getProperties()
					.put(HAS_TESTS_KEY,
							FileUtils.getFilesFromExtension(project
									.getBasedir().getAbsolutePath(),
									SCENARIO_EXTENSIONS).length > 0);
		}
		return (Boolean) project.getProperties().get(HAS_TESTS_KEY);
	}

	/**
	 * To look up Archiver/UnArchiver implementations
	 *
	 * @component
	 */
	private ArchiverManager archiverManager;
	private ArchiveUtil archiveUtil;

	protected ArchiveUtil getArchiveUtil() {
		if (archiveUtil == null) {
			archiveUtil = new ArchiveUtil(archiverManager);
		}
		return archiveUtil;
	}

	private CoordResolver resolver;

	protected CoordResolver getCoordResolver() {
		if (resolver == null) {
			resolver = new CoordResolver(project.getBasedir(), outDir,
					repoSystem, repoSession, getLog());
		}
		return resolver;
	}

	/**
	 * The entry point to Aether, i.e. the component doing all the work.
	 *
	 * @component
	 */
	private RepositorySystem repoSystem;

	/**
	 * The current repository/network configuration of Maven.
	 *
	 * @parameter default-value="${repositorySystemSession}"
	 * @readonly
	 */
	private RepositorySystemSession repoSession;

}
