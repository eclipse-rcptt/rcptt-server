package org.eclipse.rcptt.maven;

import static java.lang.String.format;
import static java.util.Arrays.asList;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.eclipse.rcptt.maven.util.Injection;
import org.eclipse.rcptt.maven.util.JavaExec;
import org.eclipse.rcptt.maven.util.Location;
import org.eclipse.rcptt.maven.util.NetUtils;
import org.eclipse.rcptt.maven.util.TestOptions;

/**
 * Executes q7 tests
 *
 * @author ivaninozemtsev
 *
 * @goal execute
 * @phase compile
 * @threadSafe
 */
public class ExecuteMojo extends AbstractQ7Mojo {

	private static final String JUNIT_REPORT = "-junitReport";
	private static final String AGENT_RESULTS_DIR = "-agentResultsDir";
	private static final String AUT_ARGS = "-autArgs";
	private static final String AUT_EXECUTION_ENVIRONMENT = "-autExecutionEnvironment";
	private static final String AUT_VM_ARGS = "-autVMArgs";
	private static final String AUT_URI = "-autUri";
	private static final String AUT_ID = "-autId";
	private static final String AUT_LOCATIONS = "-auts";
	private static final String SERVER = "-server";
	private static final String TEST_SUTE_NAME = "-testSuiteName";
	private static final String WORKSPACE = "-data";
	private static final String JAR = "-jar";
	private static final String IMPORT = "-import";
	private static final String INJECT_SITES = "-injectSites";
	private static final String IGNORE_OTHER_INJECTIONS = "-ignoreOtherInjections";
	private static final String TEST_OPTIONS = "-testOptions";
	private static final String SKIP_TAGS = "-skipTags";
	private static final String CLASSIFIERS = "-platforms";
	private static final String SUITES = "-suites";
	private static final String SHUTDOWN_LISTENER_PORT = "-shutdownListenerPort";
	private static final String METADATA = "-metadata";
	private static final String METADATA_FILE = "-metadataFile";
	private static final String LICENSE_URL = "-licenseUrl";
	private static final String CLIENT_ID = "-clientId";
	private static final String CLIENT_SECRET = "-clientSecret";
	private static final String ORGANIZATION = "-organization";

	private static final String[] DEFAULT_Q7_VM_ARGS = new String[] {
			"-Xms256m", "-Xmx1024m" };

	private static int shutdownListenerPort;

	public void execute() throws MojoExecutionException, MojoFailureException {
            if (skipTests) {
                getLog().info("Tests are skipped");
                return;
            }

		if (!projectHasTests()) {
			getLog().info("No Q7 tests found in project, skipping Q7 execution");
			return;
		}

		validatePreparation();

		Commandline cmd = new Commandline();

		JavaExec java = JavaExec.getDefault();
		cmd.setExecutable(java.getFile().getAbsolutePath());
		cmd.setWorkingDirectory(getResolvedQ7Dir());

		List<String> q7VmArgs = new ArrayList<String>();
		String[] userArgs = getQ7Coords().getVmArgs();
		if (userArgs == null || userArgs.length == 0) {
			q7VmArgs.addAll(asList(DEFAULT_Q7_VM_ARGS));
			if (java.hasPermGen()) {
				q7VmArgs.add("-XX:MaxPermSize=256m");
			}
		} else {
			q7VmArgs.addAll(asList(userArgs));
		}

		for (String arg : q7VmArgs) {
			cmd.createArg().setValue(arg);
		}

		// Equinox launcher
		cmd.createArg().setValue(JAR);
		cmd.createArg().setFile(getEquinoxJar());
		
		if (getLog().isDebugEnabled()) {
			cmd.createArg().setValue("-consoleLog");
		}
		
		// Application
		cmd.createArg().setValue("-application");
		cmd.createArg().setValue("org.eclipse.rcptt.cloud.client.headless");

		// Workspace
		cmd.createArg().setValue(WORKSPACE);
		cmd.createArg().setFile(getQ7WsDir());
		// Projects
		cmd.createArg().setValue(IMPORT);
		for(String importProject : getImports()) {
			if (!new File(importProject).isDirectory()) {
				throw new MojoFailureException(importProject + " does not exist");
			}
		    cmd.createArg().setValue(importProject);
		}
		

		// AUT location
		if (aut.getUri() != null) {
			cmd.createArg().setValue(AUT_URI);
			cmd.createArg().setValue(aut.getUri());
		}

		// AUT ID
		if (aut.getArtifactId() != null) {
			cmd.createArg().setValue(AUT_ID);
			cmd.createArg().setValue(aut.getArtifactId());
		}
		// credentials
		boolean credentialSetup = false;
		if( getQ7Coords().getClientId() != null ) {
			cmd.createArg().setValue(CLIENT_ID);
			cmd.createArg().setValue(getQ7Coords().getClientId());
			credentialSetup = true;
		}
		if( getQ7Coords().getClientSecret() != null ) {
			cmd.createArg().setValue(CLIENT_SECRET);
			cmd.createArg().setValue(getQ7Coords().getClientSecret());
			credentialSetup = true;
		}
        if( getQ7Coords().getOrganization() != null) {
            cmd.createArg().setValue(ORGANIZATION);
            cmd.createArg().setValue(getQ7Coords().getOrganization());
        }
		if( !credentialSetup ) {
			if (getQ7Coords().getLicenseUrl() != null) {
				// license url
				cmd.createArg().setValue(LICENSE_URL);
				cmd.createArg().setValue(getQ7Coords().getLicenseUrl());
			}
		}
		
		// Q7 Server location
		cmd.createArg().setValue(SERVER);
		cmd.createArg().setValue(q7server);
		
		if (aut.executionEnvironment != null) {
			cmd.createArg().setValue(AUT_EXECUTION_ENVIRONMENT);
			cmd.createArg().setValue(aut.executionEnvironment);
		}

		// AUT VM Args
		if (aut.getVmArgs() != null && aut.getVmArgs().length > 0) {
			cmd.createArg().setValue(AUT_VM_ARGS);
			cmd.createArg().setValue(createArgValue(aut.getVmArgs()));
		}
		// AUT args
		if (aut.getArgs() != null && aut.getArgs().length > 0) {
			cmd.createArg().setValue(AUT_ARGS);
			cmd.createArg().setValue(createArgValue(aut.getArgs()));

		}

		// AUT locations
		if (aut.getLocations() != null && aut.getLocations().length > 0) {
			cmd.createArg().setValue(AUT_LOCATIONS);
			for (Location location : aut.getLocations()) {
				cmd.createArg().setValue(location.getValue());
			}
		}
		// Classifiers
		if (classifiers != null && classifiers.length > 0) {
			cmd.createArg().setValue(CLASSIFIERS);
			for (String classifier : classifiers) {
				cmd.createArg().setValue(classifier);
			}
		}
		if (suites != null && suites.length > 0) {
			cmd.createArg().setValue(SUITES);
			for (String suite : suites) {
				cmd.createArg().setValue(suite);
			}
		}

		cmd.createArg().setValue(TEST_SUTE_NAME);
		cmd.createArg().setValue(
				String.format("%s-%s", project.getGroupId(),
						project.getArtifactId()));
		// Report
		cmd.createArg().setValue(JUNIT_REPORT);
		cmd.createArg().setFile(getSurefireFile());

		// Agent results
		cmd.createArg().setValue(AGENT_RESULTS_DIR);
		cmd.createArg().setFile(new File(getResultsDir(), "agentResults"));

		int shift = (int) (new Random().nextLong() % 1000);
		shutdownListenerPort = NetUtils
				.findFreePort(9000 + shift, 9999 + shift);
		if (shutdownListenerPort != -1) {
			cmd.createArg().setValue(SHUTDOWN_LISTENER_PORT);
			cmd.createArg().setValue(
					((Integer) shutdownListenerPort).toString());
		}

		if (aut.getInjections() != null && aut.getInjections().length > 0) {
			setInjections(cmd);
		}

		if (skipTags != null) {
			cmd.createArg().setValue(SKIP_TAGS);
			for (String skipTag : skipTags) {
				cmd.createArg().setValue(skipTag);
			}
		}

		cmd.createArg().setValue(METADATA);
		for (Entry<String, String> meta : getMetadata().entrySet()) {
			cmd.createArg().setValue(
					String.format("%s=%s", meta.getKey(), meta.getValue()));
		}
		if( metadataFile != null) {
			cmd.createArg().setValue(METADATA_FILE);
			cmd.createArg().setValue(metadataFile);
		}

		cmd.createArg().setValue(TEST_OPTIONS);
		for (Entry<String, String> option : getTestOptions().entrySet()) {
			cmd.createArg().setValue(
					String.format("%s=%s", option.getKey(), option.getValue()));
		}

		// whoo, almost ready to launch
		getLog().info(String.format("Q7 command line is %s", cmd.toString()));
		FileStreamConsumer outConsumer;
		FileStreamConsumer errConsumer;
		try {
			outConsumer = new FileStreamConsumer(getQ7Out(), getLog(), false);
			errConsumer = new FileStreamConsumer(getQ7Err(), getLog(), true);
		} catch (FileNotFoundException e) {
			throw new MojoExecutionException(
					"Can't write q7 out and err files", e);
		}
		try {
			int duration = TestOptions.getInt(getTestOptions(),
					TestOptions.EXEC_TIMEOUT);
			getLog().info(
					String.format("The execution timeout is set to %d seconds",
							duration));

			try {
				int code = CommandLineUtils.executeCommandLine(cmd, outConsumer, errConsumer,
					duration);
				
				if (code == 64) {
					throw new MojoExecutionException("Configuration is invalid");
				}
				if (code == 56) {
					throw new MojoFailureException("There are test failures");
				}
				if (code != 0) {
					throw new MojoExecutionException(format("Q7 client exited with code %d", code));
				}
			} finally {
				ShutdownHook.start();
			}
			outConsumer.done();
			errConsumer.done();
		} catch (CommandLineException e) {
			throw new MojoExecutionException("Failed to launch Q7", e);
		}
	}

    	private List<String> getImports() throws MojoFailureException {
	    List<String> result = new ArrayList<String>();
	    for (File file : getProjectsDir().listFiles()) {
		result.add(file.getAbsolutePath());
	    }

	    if (projects != null)
		for (String projectPath : projects)
		    result.add(projectPath);
	    return result;
	}
    
	private static final String UNIT_SEP = ";";

	private void setInjections(Commandline cmd) {
		cmd.createArg().setValue(IGNORE_OTHER_INJECTIONS);
		cmd.createArg().setValue(
				Boolean.toString(aut.isIgnoreOtherInjections()));
		cmd.createArg().setValue(INJECT_SITES);

		for (Injection injection : aut.getInjections()) {
			StringBuilder sb = new StringBuilder();
			sb.append(injection.getSite());
			if (injection.getFeatures() != null) {
				for (String feature : injection.getFeatures()) {
					sb.append(UNIT_SEP).append(feature);
				}
			}
			cmd.createArg().setValue(sb.toString());
		}
	}

	/**
	 * Validate that all necessary information is present and no one has
	 * corrupted the results of {@link PrepareMojo} by customizing lifecycle
	 *
	 * @throws MojoFailureException
	 */
	private void validatePreparation() throws MojoFailureException {
		boolean ok = true;
		ok &= getProjectsDir().exists();
		ok &= getThisProjectDir().exists();
		ok &= getQ7Dir().exists();
		if (!ok) {
			throw new MojoFailureException(
					"Cannot execute Q7 tests. Something is corrupted during prepare goal. Is lifecycle have been modified?");
		}
	}

	/**
	 * Get equinox launcher jar
	 *
	 * @return
	 * @throws MojoFailureException
	 */
	private File getEquinoxJar() throws MojoFailureException {
		File q7dir = getResolvedQ7Dir();
		File plugins = new File(q7dir, "plugins");
		if (!plugins.exists() || !plugins.isDirectory()) {
			throw new MojoFailureException("Invalid Q7 location " + q7dir);
		}
		File[] result = plugins.listFiles(new FileFilter() {

			public boolean accept(File f) {
				if (f.isDirectory())
					return false;
				if (!f.getName().endsWith(".jar"))
					return false;
				return f.getName().contains("equinox.launcher_");
			}
		});
		if (result.length == 0) {
			throw new MojoFailureException(
					"Can't find equinox launcher in Q7 dir at" + q7dir);
		}
		return result[result.length - 1];
	}

	private static final class FileStreamConsumer implements StreamConsumer {
		private Log fLog;
		private boolean fIsError;

		public FileStreamConsumer(File dest, Log log, boolean isError)
				throws FileNotFoundException {
			this.writer = new PrintWriter(new FileOutputStream(dest));
			fLog = log;
			fIsError = isError;
		}

		public void done() {
			writer.flush();
			writer.close();
		}

		private PrintWriter writer;

		public void consumeLine(String line) {
			writer.println(line);

			if (fLog != null) {
				if (fIsError) {
					fLog.error(line);
				} else {
					fLog.info(line);
				}
			}
		}

	}

	private static String createArgValue(String[] values) {
		StringBuilder builder = new StringBuilder();
		builder.append("\"");
		boolean first = true;
		for (String arg : values) {
			if (!first) {
				builder.append(' ');
			}
			first = false;
			builder.append(escape(arg));
		}
		builder.append("\"");
		return builder.toString();
	}

	private static String escape(String src) {
		StringBuilder result = new StringBuilder();
		for (int index = 0; index < src.length(); index++) {
			char symbol = src.charAt(index);
			if (symbol == '\"') {
				result.append('\\');
			}
			result.append(symbol);
		}
		return result.toString();
	}

	/**
	 * @parameter
	 *
	 */
	private String q7server;

	Thread ShutdownHook = new Thread() {
		public void run() {
			getLog().info(
					"Process terminated. Send shutdown request to Q7 runner.");
			try (Socket ignored = new Socket("127.0.0.1", shutdownListenerPort)) {
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	};
}
