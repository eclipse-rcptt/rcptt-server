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
package org.eclipse.rcptt.cloud.client;

import static java.lang.Math.addExact;
import static java.lang.Math.multiplyExact;
import static java.lang.String.format;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.cloud.client.AutTcpConnector.Aut;
import org.eclipse.rcptt.cloud.client.Q7ArtifactLoader.ArtifactHandle;
import org.eclipse.rcptt.cloud.client.Q7ServerApi.UploadResult;
import org.eclipse.rcptt.cloud.commandline.Arg;
import org.eclipse.rcptt.cloud.commandline.CommandLineApplication;
import org.eclipse.rcptt.cloud.commandline.InvalidCommandLineArgException;
import org.eclipse.rcptt.cloud.common.ReportUtil;
import org.eclipse.rcptt.cloud.common.commonCommands.AddAut;
import org.eclipse.rcptt.cloud.common.commonCommands.AddTestResource;
import org.eclipse.rcptt.cloud.common.commonCommands.AddTestSuite;
import org.eclipse.rcptt.cloud.common.commonCommands.BeginTestSuite;
import org.eclipse.rcptt.cloud.common.commonCommands.CancelSuite;
import org.eclipse.rcptt.cloud.common.commonCommands.CommonCommandsFactory;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.Envelope;
import org.eclipse.rcptt.cloud.model.ModelFactory;
import org.eclipse.rcptt.cloud.model.ModelUtil;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.RefKind;
import org.eclipse.rcptt.cloud.model.TestOptions;
import org.eclipse.rcptt.cloud.model.TestSuite;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecTestSuite;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecutionProgress;
import org.eclipse.rcptt.cloud.server.serverCommands.ExecutionState;
import org.eclipse.rcptt.cloud.server.serverCommands.ServerCommandsFactory;
import org.eclipse.rcptt.cloud.util.CheckedExceptionWrapper;
import org.eclipse.rcptt.cloud.util.HttpEclClient.ExecutionResult;
import org.eclipse.rcptt.core.internal.builder.MigrateProjectsJob;
import org.eclipse.rcptt.core.model.IQ7Element.HandleType;
import org.eclipse.rcptt.core.workspace.ProjectUtil;
import org.eclipse.rcptt.ecl.core.BoxedValue;
import org.eclipse.rcptt.ecl.core.util.ECLBinaryResourceImpl;
import org.eclipse.rcptt.ecl.runtime.BoxedValues;
import org.eclipse.rcptt.internal.core.RcpttPlugin;
import org.eclipse.rcptt.internal.core.model.ModelManager;
import org.eclipse.rcptt.launching.injection.InjectionConfiguration;
import org.eclipse.rcptt.launching.injection.InjectionFactory;
import org.eclipse.rcptt.launching.injection.util.InjectionUtil;
import org.eclipse.rcptt.reporting.ItemKind;
import org.eclipse.rcptt.reporting.Q7Info;
import org.eclipse.rcptt.reporting.ReportingFactory;
import org.eclipse.rcptt.reporting.core.IQ7ReportConstants;
import org.eclipse.rcptt.reporting.core.IReportRenderer.IContentFactory;
import org.eclipse.rcptt.reporting.core.SimpleSeverity;
import org.eclipse.rcptt.reporting.html.HtmlReporter;
import org.eclipse.rcptt.reporting.util.FileContentFactory;
import org.eclipse.rcptt.reporting.util.JUnitFileReportGenerator;
import org.eclipse.rcptt.reporting.util.Q7ReportIterator;
import org.eclipse.rcptt.reporting.util.ReportUtils;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Node;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.ReportFactory;
import org.eclipse.rcptt.sherlock.core.streams.SherlockReportIterator;
import org.eclipse.rcptt.sherlock.core.streams.SherlockReportOutputStream;
import org.eclipse.rcptt.util.FileUtil;
import org.eclipse.rcptt.util.NetworkUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class ClientApplication extends CommandLineApplication {

	@Arg
	public String server = "https://cloud.xored.com/client";

	@Arg(isRequired = true, name = "testSuiteName", description="Unique suite/project name")
	public String testSuiteName;

	@Arg(isRequired = true, argCount = -1, name = "import", description="A list of paths to test projects and their dependencies.")
	public String[] projectDirs = new String[0];

	@Arg(isRequired = false, argCount = -1, name = "injectSites")
	public String[] injectSites = new String[0];

	@Arg(isRequired = false)
	public boolean ignoreOtherInjections = false;

	@Arg(isRequired = false)
	public String autArgs;

	@Arg(isRequired = false, argCount = 1)
	public String autExecutionEnvironment;

	@Arg(isRequired = false)
	public String autVMArgs;

	@Arg(isRequired = false, argCount = -1)
	public String[] testOptions = new String[0];

	@Arg(isRequired = false, argCount = -1)
	public String[] metadata = new String[0];

	@Arg(isRequired = false)
	public String metadataFile = null;

	@Arg(isRequired = false, argCount = -1)
	public String[] platforms = new String[0];

	@Arg(isRequired = false)
	public String autUri;

	@Arg(isRequired = false, argCount = -1, name = "skipTags")
	public String[] skipTags = new String[] { "skipExecution" };

	@Arg(isRequired = false, argCount = -1, description = "classifer|path. Example:win32.win32.x86_64|target/eclipse-java-2024-12-R-win32-win32-x86_64.zip")
	public String[] auts;
	@Arg
	public String autId;

	@Arg(isRequired = false)
	public String junitReport;

	@Arg(isRequired = false)
	public String agentResultsDir;

	@Arg(isRequired = false)
	public int shutdownListenerPort = 0;

	@Arg(isRequired = false, argCount = -1)
	public String[] suites = new String[0];

	@Arg(isRequired = false)
	public int chunkSize = 10;

	@Arg(isRequired = false, description = "Disable AUT on server mirroring. INTERNAL")
	public boolean disableServerMirror = false;

	@Arg(isRequired = false, description = "Client ID for Q7 License Server")
	public String clientId = null;

	@Arg(isRequired = false, description = "Secret key for Q7 License Server")
	public String clientSecret = null;

	@Arg(isRequired = false, description = "Organization name for Q7 License Server")
	public String organization = null;

	@Arg(isRequired = false, description = "License URL for Enterprise mode")
	public String licenseUrl = null;

	private static final ILog LOG = Platform.getLog(ClientApplication.class);

	private static final boolean DEBUG = false;
	private final Map<String, ArtifactHandle> resourcesById = new HashMap<>();

	private TestSuite suite;

	private Q7ArtifactLoader loader;

	private URI serverUri;
	private String serverHost;
	private int serverPort;

	private Q7ServerApi api;

	@Override
	protected Object run() throws Exception {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceDescription description = workspace.getDescription();
		description.setAutoBuilding(false);
		workspace.setDescription(description);

		NetworkUtils.initTimeouts();
		if (shutdownListenerPort > 0) {
			createShutdownListener(shutdownListenerPort);
		}
		Runtime.getRuntime().addShutdownHook(shutdownHook);

		if (projectDirs.length == 0) {
			throw ClientAppPlugin
					.createException("No project directories is specified");
		}
		
				

		try {
			if (!server.contains("://")) {
				server = "https://" + server;
			}
			if (!server.endsWith("/")) {
				server += '/';
			}

			serverUri = new URI(server);
			serverHost = serverUri.getHost();
			serverPort = serverUri.getPort();
			if (serverPort == -1) {
				serverPort = serverUri.toURL().getDefaultPort();
			}
			if (serverPort == -1) {
				throw ClientAppPlugin
						.createException("Can not map server protocol to port number.");
			}
		} catch (Exception e) {
			throw ClientAppPlugin.createException("Invalid server URL.", e);
		}

		api = new Q7ServerApi(serverUri);

		updateAutUri();

		beginTestSuite(testSuiteName);

		configureQ7Environment();

		File[] projectDirFiles = new File[projectDirs.length];
		for (int i = 0; i < projectDirs.length; i++) {
			projectDirFiles[i] = new File(projectDirs[i]);
		}
		this.loader = new Q7ArtifactLoader(skipTags);

		workspace.run(monitor -> {
			ProjectUtil.importProjects(projectDirFiles, System.out);
			new MigrateProjectsJob(workspace.getRoot()).runSync();
		}, workspace.getRoot(), IWorkspace.AVOID_UPDATE, null);

		for (var p : ModelManager.getModelManager().getModel().getProjects()) {
			checkProjectReferences(p.getProject());
		}

		CompletableFuture<Void> autResult = CompletableFuture.runAsync(CheckedExceptionWrapper.encode(this::updateAUTs));

		long st = System.currentTimeMillis();
		logInfo("Load Artifacts");

		loadArtifactRefs();
		long ed = System.currentTimeMillis();
		
		logInfo("Ensure Integrity");
		ensureIntegrity();
		
		try {
			autResult.get();
		} catch (ExecutionException e) {
			if (e.getCause() instanceof CheckedExceptionWrapper e2) {
				e2.rethrow(CoreException.class);
				e2.rethrow(InvalidCommandLineArgException.class);
				e2.rethrowUnchecked();
				throw e2;
			}
			throw e;
		}


		logInfo("Create Test Suite");
		suite = createTestSuite();

		logInfo(String.format(
				"%d artifacts in suite, test cases - %d, contexts - %d", Integer.valueOf(suite
						.getRefs().size()),
				Integer.valueOf(ModelUtil.scenarioList(suite).size()),
				Integer.valueOf(ModelUtil.dependenciesMap(suite).size())));

		st = System.currentTimeMillis();
		logInfo("Prepare list of artifacts required by server");
		List<ArtifactHandle> missingResources = addTestSuite(suite);
		ed = System.currentTimeMillis();
		System.out.println("Add suite complete:" + (ed - st));

		int index = 0;
		long time1 = System.currentTimeMillis();
		int chunkBytes = multiplyExact(chunkSize, 1024 * 1024);
		ByteArrayOutputStream bout = new ByteArrayOutputStream(addExact(chunkBytes, 1024 * 1024));
		ZipOutputStream zout =  new ZipOutputStream(bout);
		int chunk = 0;
		System.out.println("Preparing artifacts for sending...");
		int processed = 0;
		Collection<ArtifactHandle> values = resourcesById.values();
		int total = values.size();
		int ch = 0;
		CompletableFuture<Void> upload = null;
		for (final ArtifactHandle ref : missingResources) {
			processed++;
			Q7Artifact artifact = getArtifact(ref);
			String id = ref.id;
			if (!artifact.getId().equals(ref.id)) {
				throw new AssertionError("Requested: " + id + ", received: " + artifact.getId());
			}
			
			System.out.printf("Compressing resource %s, (%s, %s), contexts: %s, verifications: %s (%d of %d)\n", ref.path, id, ref.hash, Joiner.on("; ").join(ref.contexts),Joiner.on("; ").join(ref.verifications),  processed, total);

			ECLBinaryResourceImpl r = new ECLBinaryResourceImpl();
			r.getContents().add(EcoreUtil.copy(artifact));

			ZipEntry entry = new ZipEntry(id);
			zout.putNextEntry(entry);
			r.save(zout, null);
			zout.closeEntry();
			index++;
			chunk++;
			if (bout.size() >= chunkSize * 1024 * 1024) {
				zout.close();
				waitFor(upload);
				upload = null;
				byte[] sending = bout.toByteArray();
				System.out.println("");
				logInfo(String
						.format("Sending %dMB resources chunk (%d artifacts). Processed resources %d of %d artifacts.",
								Integer.valueOf(sending.length / (1024 * 1024)), Integer.valueOf(chunk), Integer.valueOf(index),
								Integer.valueOf(missingResources.size())));
				String chunkName =  "artifacts" + ch + ".zip";
				upload = CompletableFuture.runAsync(() -> {
					try {
						URI relative = api.uploadDataAsFile(suiteID,
								sending ,chunkName,
								false);
						addTestResource(relative);
					} catch (CoreException e) {
						throw new RuntimeException(e);
					}
				});
				bout.reset();
				zout = new ZipOutputStream(bout);
				chunk = 0;
				ch++;
			}
		}
		zout.close();

		waitFor(upload);
		// Send last fragment
		if (chunk > 0) {
			System.out.println("Sending last resources chunk (" + chunk
				+ " artifacts).");
			URI uploadedRoot = api.uploadDataAsFile(suiteID,
					bout.toByteArray(), "artifacts" + ch + ".zip", false);
			addTestResource(uploadedRoot);
		}
		zout = null;
		bout = null;

		long time2 = System.currentTimeMillis();
		System.out.println("Complete uploading artifacts to server (time:"
				+ Long.toString(time2 - time1) + ")");

		IPath location = ClientAppPlugin.getDefault().getStateLocation()
				.append("session-" + ReportUtils.getID(testSuiteName));

		LaunchConfigBuilder launchBuilder = new LaunchConfigBuilder(Maps.transformValues(resourcesById, handle -> handle.path));

		logInfo("Exec Test Suite");
		execTestSuite(createTestOptions());

		File agentResDir = createAgentResultsDir();
		if (agentResDir == null) {
			agentResDir = location.append("reporting").toFile();
			agentResDir.mkdirs();
		}

		File serverReportFile = new File(agentResDir, "q7.report.server");

		logInfo("Download Q7 report from: " + reportUri);
		api.downloadFile(reportUri, serverReportFile);

		File reportFile = new File(agentResDir, "q7.report");
		if (reportFile.exists()) {
			reportFile.delete();
		}

		try (
			SherlockReportOutputStream out = new SherlockReportOutputStream(new BufferedOutputStream(
					new FileOutputStream(reportFile)))) {

			// Copy all from server report file
			if (serverReportFile.exists()) {
				SherlockReportIterator iterator = new SherlockReportIterator(
						serverReportFile);
				while (iterator.hasNext()) {
					Report next = iterator.next();
					if (next != null) {
						EObject root = next.getRoot().getProperties()
								.get(IQ7ReportConstants.ROOT);
						EObject agentID = next.getRoot().getProperties()
								.get(IQ7ReportConstants.AGENTID);
	
						if (root instanceof Q7Info && agentID instanceof BoxedValue) {
							Q7Info info = (Q7Info) root;
							launchBuilder.addLine(
									BoxedValues.toString((BoxedValue) agentID),
									info.getId());
						}
						out.write(next);
					}
				}
			}
		} catch (FileNotFoundException e1) {
			ClientAppPlugin.logErr("Failed to create final report file", e1);
			return null;
		}


		try (Q7ReportIterator summary = new Q7ReportIterator(reportFile)) {

			if (junitReport != null) {
				logInfo("Generate JUnit Report.");
				createJunitReport(summary);
			}

			logInfo("Generate per Agent launch configurations.");
			Map<String, String> launchFiles = new HashMap<>();
			for (String agentName : launchBuilder.getAgents()) {
				File launchFile = new File(agentResDir, format("%s.launch", FileUtil.getID(agentName)));
				launchBuilder.build(launchFile, agentName);
				launchFiles.put(agentName, launchFile.getName());
			}
			logInfo("Generate HTML report.");
			createHtmlReport(summary, agentResDir);
			logInfo("Execution Complete");
			int testCount = 0;
			for (Report report : summary) {
				testCount++;
				SimpleSeverity severity = ReportUtil.getStatus(report);
				if (severity.equals(SimpleSeverity.ERROR)) {
					return TEST_FAIL_EXIT_CODE;
				}
				if (severity.equals(SimpleSeverity.CANCEL)) {
					return EXIT_ILLEGAL_ARGUMENT;
				}
			}
			if (testCount <= 0) {
				logInfo("No tests were executed");
				return EXIT_ILLEGAL_ARGUMENT;
			}
		}
		return null;
	}

	private <T> T waitFor(CompletableFuture<T> upload)
			throws InterruptedException, CoreException, Exception {
		if (upload == null) {
			return null;
		}
		try {
			return upload.get();
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			if (cause != null) {
				cause = cause.getCause();
				Throwables.throwIfInstanceOf(cause, CoreException.class);
				Throwables.throwIfInstanceOf(e, InterruptedException.class);
				Throwables.throwIfInstanceOf(e, Exception.class);
			}
			Throwables.throwIfInstanceOf(e, InterruptedException.class);
			Throwables.throwIfInstanceOf(e, Exception.class);
			Throwables.throwIfUnchecked(e);
			throw new RuntimeException(cause);
		}
	}

	private void checkProjectReferences(IProject project) throws InvalidCommandLineArgException, CoreException {
		for (var p: project.getReferencedProjects()) {
			if (!p.exists()) {
				throw new InvalidCommandLineArgException(String.format("Project %s references an absent project %s. Add %2$s to -import argument.", project.getName(), p.getName()), "import");
			}
			checkProjectReferences(p);
		}
	}

	private List<AutInfo> autInformation;

	private void updateAUTs() throws CoreException, InvalidCommandLineArgException {
		autInformation = createAutInfos();
		if (disableServerMirror) {
			return;
		}
		if (autInformation != null && autInformation.size() > 0) {
			autInformation = updateAuts(autInformation);
			return;
		}
		autInformation = null;
		throw ClientAppPlugin
				.createException("Failed to upload AUT information");
	}

	private void configureQ7Environment() {
		// Q7Builder.setEnabled(false);
		// ModelManager.getModelManager().getIndexManager().disable();
	}

	private Thread shutdownHook = new Thread() {
		@Override
		public void run() {
			if (suite == null || completed) {
				// no need to cancel
				return;
			}
			CancelSuite cs = CommonCommandsFactory.eINSTANCE
					.createCancelSuite();
			cs.setSuiteId(getSuiteId());
			try {
				api.execute(cs);
			} catch ( CoreException e) {
				System.out.println("Cancel test suite failed:");
				LOG.log(e.getStatus());
			} catch (ConnectException e) {
				e.printStackTrace();
			}
		}
	};

	private File createAgentResultsDir() {
		if (agentResultsDir == null) {
			return null;
		}
		File f = new File(agentResultsDir);
		if (f.isFile()) {
			ClientAppPlugin.logErr(String.format("%s is not a directory", f),
					null);
			return null;
		}
		if (!f.exists() && !f.mkdirs()) {
			ClientAppPlugin
					.logErr(String.format("can't create dir %", f), null);
		}
		return f;
	}

	private void createJunitReport(Q7ReportIterator report) {
		final File result = new File(junitReport);
		result.getParentFile().mkdirs();
		JUnitFileReportGenerator junitGenerator = new JUnitFileReportGenerator();
		junitGenerator.generateReport(new IContentFactory() {

			@Override
			public OutputStream createFileStream(String name) {
				try {
					return new BufferedOutputStream(
							new FileOutputStream(result));
				} catch (FileNotFoundException e) {
					ClientAppPlugin.logErr("Failed to save JUnit report", e);
					return null;
				}
			}

			@Override
			public IContentFactory createFolder(String name) {
				return this;
			}

			@Override
			public List<String> matchFiles(String fname) {
				return new ArrayList<>();
			}

			@Override
			public boolean isFileExist(String fileName) {
				return false;
			}

			@Override
			public void removeFileOrFolder(String name) throws CoreException {
			}
		}, testSuiteName, report);
	}

	private void createHtmlReport(Q7ReportIterator report, File agentResDir) {
		HtmlReporter reporter = new HtmlReporter();

		FileContentFactory files = new FileContentFactory(agentResDir);
		reporter.generateReport(files, testSuiteName, report);
	}

	private Map<String, String> uploadMap = new HashMap<>();

	private URI reportUri;

	private void loadArtifactRefs() throws CoreException, InterruptedException, InvalidCommandLineArgException {
		Set<String> idsToRemove = new HashSet<>();
		List<ArtifactHandle> problemElements = new ArrayList<>();
		loader.findArtifacts(suites).forEachOrdered(entry-> {
			String id = entry.id;
			if (resourcesById.containsKey(id)) {
				idsToRemove.add(id);
				problemElements.add(entry);
				problemElements.add(resourcesById.get(id));
				return;// If already registered
			}
			resourcesById.put(id, entry);
		});
		if (problemElements.size() > 0) {
			StringBuilder builder = new StringBuilder();
			for (ArtifactHandle iq7NamedElement : problemElements) {
				String id = iq7NamedElement.id;
				builder.append(String.format("Resource %s has duplicate ID: %s\n", iq7NamedElement.path, id));
			}
			throw new InvalidCommandLineArgException(builder.toString(), "-import");
		}
		System.out.printf("Loaded %d artifacts\n", resourcesById.size());
	}

	/**
	 * based on dependants map find missing resources and exclude it's
	 * dependants
	 * @throws CoreException 
	 */
	private void ensureIntegrity() throws InvalidCommandLineArgException, CoreException {
		Set<String> resourcesToExclude = Sets.newHashSet();
		boolean found = true;
		StringBuilder errorMessage = new StringBuilder();
		while (found) {
			found = false;
			for (Entry<String, ArtifactHandle> entry : resourcesById.entrySet()) {
				ArtifactHandle ref = entry.getValue();
				String id = entry.getKey();
				assert id.equals(ref.id);
				if (resourcesToExclude.contains(id)) {
					continue;
				}
				
				List<String> subRefs = Stream.concat(ref.contexts.stream(), ref.verifications.stream()).toList();
				
				
				for (String refId : subRefs) {
					
					if (Strings.isNullOrEmpty(refId) || "null".equals(refId))
						throw new NullPointerException("Broken artifact: " + ref);
					if (!resourcesById.containsKey(refId)
							|| resourcesToExclude.contains(refId)) {
						String type = ref.kind.toString().toLowerCase();
						String location = resourcesById.get(id).path;

						errorMessage.append(format("%s '%s' refers to missing resource %s\n",
										type, location, refId));
						resourcesToExclude.add(id);
						found = true;
					}
				}
			}
		}		
		if (!resourcesToExclude.isEmpty()) {
			throw new InvalidCommandLineArgException(errorMessage.toString(), "-import");
		}
		if (DEBUG) {
			StringBuilder output = new StringBuilder();
			output.append("Following artifacts were found:\n");
			for (Map.Entry<String, ArtifactHandle> i: resourcesById.entrySet()) {
				output.append(i.getValue().path);
				output.append(" ");
				output.append(i.getKey());
				output.append("\n");
			}
			System.out.println(output);
		}
		try {
			resourcesById.values().stream().parallel().forEach(CheckedExceptionWrapper.encode(h -> {h.getContent();}));
		} catch (CheckedExceptionWrapper e) {
			e.rethrow(CoreException.class);
			e.rethrow(InvalidCommandLineArgException.class);
			e.rethrowUnchecked();
			throw e;
		}
	}

	private Q7Artifact getArtifact(ArtifactHandle ref) throws CoreException {
		Q7Artifact result = ModelFactory.eINSTANCE.createQ7Artifact();
		result.setContent(ref.getContent());
		result.setId(ref.id);
		result.getRefs().addAll(ref.contexts.stream().map(this::toContextRef).toList());
		result.getRefs().addAll(ref.verifications.stream().map(this::toVerificationRef).toList());
		return result;
	}

	private void addTestResource(URI artifactsFile) throws CoreException {
		AddTestResource cmd = CommonCommandsFactory.eINSTANCE
				.createAddTestResource();
		cmd.setResource(null);
		cmd.setSuiteId(suiteID);
		cmd.setArtifactsPath(artifactsFile.toASCIIString());
		try {
			ExecutionResult result = api.execute(cmd, Q7ServerApi.DEFAULT_TIMEOUT * 8);
			if (result.status.matches(IStatus.ERROR | IStatus.CANCEL)) {
				throw new CoreException(new MultiStatus(getClass(), result.status.getCode(), new IStatus[] {result.status}, "Failed to upload " + artifactsFile, null));
			}
		} catch (ConnectException e) {
			throw new CoreException(Status.error("Server is unavilable", e));
		}
	}

	private List<AutInfo> updateAuts(List<AutInfo> toUpdate) throws CoreException {
		List<AutInfo> results = new ArrayList<>();
		for (AutInfo autInfo : toUpdate) {
			AddAut cmd = CommonCommandsFactory.eINSTANCE.createAddAut();
			cmd.setSuiteId(suiteID);
			cmd.setAut(EcoreUtil.copy(autInfo));
			ExecutionResult result;
			try {
				result = api.execute(cmd);
			} catch (ConnectException e) {
				throw new CoreException(Status.error("Server is unavilable", e));
			}
			if (!result.status.isOK()) {
				throw new CoreException(result.status);
			}
			boolean added = false;
			for (Object o : result.results) {
				if (o instanceof AutInfo) {
					results.add((AutInfo) o);
					added = true;
					continue;
				}
			}
			if (!added) {
				// Not changed.
				results.add(autInfo);
			}
		}
		return results;
	}

	private boolean beginTestSuite(String suiteId) throws CoreException, InvalidCommandLineArgException {
		Preconditions.checkNotNull(suiteId);
		BeginTestSuite cmd = CommonCommandsFactory.eINSTANCE
				.createBeginTestSuite();
		cmd.setSuiteId(suiteId);
		try {
			ExecutionResult result = api.execute(cmd);
			if (!result.status.isOK()) {
				throw new CoreException(result.status);
			}
			for (Object o : result.results) {
				if (o instanceof BoxedValue) {
					suiteID = BoxedValues.toString((BoxedValue) o);
					return true;
				}
			}
		} catch (ConnectException e) {
			throw new InvalidCommandLineArgException("Can't connect to server " + serverUri, "-server");
		}
		return false;
	}

	private void execTestSuite(TestOptions options)
			throws CoreException, InterruptedException {
		Preconditions.checkNotNull(autInformation);
		ExecTestSuite cmd = ServerCommandsFactory.eINSTANCE
				.createExecTestSuite();

		cmd.setSuiteId(suiteID);
		cmd.getAuts().addAll(autInformation);
		cmd.setOptions(options);
		applyMetadata(cmd.getMetadata()::put);
		ExecutionResult result;
		try {
			result = api.execute(cmd);
		} catch (ConnectException e) {
			throw new CoreException(Status.error("Server is unavailable", e));
		}
		if (!result.status.isOK()) {
			throw new CoreException(result.status);
		}

		for (Object o : result.results) {
			if (o instanceof BoxedValue) {
				reportUri = URI.create(BoxedValues.toString((BoxedValue) o));
				if (reportUri == null)
					throw new NullPointerException("Invalid report URI: " + o);
			}
		}
		if (reportUri == null)
			throw new NullPointerException("Server sent no report URI");
		long start = System.currentTimeMillis();
		waitComplete(suiteID);
		long cur = (System.currentTimeMillis() - start) / 1000;
		System.out.println("Test execution done: "
				+ String.format(" %d:%02d mins.", Long.valueOf(cur / 60), Long.valueOf(cur % 60)));
	}

	private boolean completed = false;

	private void waitComplete(String suiteIDArg)
			throws CoreException, InterruptedException {

		List<Q7ArtifactRef> scenarious = ModelUtil.scenarioList(suite);
		int total = 0;
		for (Q7ArtifactRef q7ArtifactRef : scenarious) {
			if (resourcesById.containsKey(q7ArtifactRef.getId())) {
				total++;
			}
		}
		if (platforms.length > 1) {
			total = total * platforms.length;
		}

		Map<String, Integer> froms = new HashMap<>();
		int processed = 0;
		long lastSuccess = System.currentTimeMillis();
		while (!completed) {
			ExecutionProgress command = ServerCommandsFactory.eINSTANCE
					.createExecutionProgress();
			command.setSuiteId(suiteIDArg);
			ExecutionResult result = null;
			try {
				result = api.execute(command, Q7ServerApi.DEFAULT_TIMEOUT);
				lastSuccess = System.currentTimeMillis();
			} catch (SocketException e) {
				LOG.error("Server is unavailable", e);
				Thread.sleep(60);
				if (System.currentTimeMillis() - lastSuccess > 20 * 60 * 1000) { // >=
					// 20
					// minutes
					throw ClientAppPlugin
							.createException(
									"Failed to wait for server, since it is not available for timeout (20 minutes.",
									e);
				}

				continue; // try one more time.
			}

			for (Object answer : result.results) {
				if (answer instanceof Envelope) {
					Envelope envelope = (Envelope) answer;
					processed++;

					String message = envelope.getMessage();

					if (!envelope.getFrom().equals("no_agent")) {
						if (!froms.containsKey(envelope.getFrom())) {
							logInfo(format("New agent: %s_%s", envelope.getFrom(), envelope.getArch()));
							froms.put(envelope.getFrom(), Integer.valueOf(1));
						} else {
							Integer val = froms.get(envelope.getFrom());
							if (val != null) {
								froms.put(envelope.getFrom(),
										Integer.valueOf(val.intValue() + 1));
							}
						}
					}
					logInfo(message);
					StringBuilder b = new StringBuilder();
					if (processed % 20 == 0) {
						for (Map.Entry<String, Integer> k : froms.entrySet()) {
							b.append(
									"Agent info:" + k.getKey() + " tests:"
											+ k.getValue()).append("\n");
						}
						logInfo(b.toString());
					}
				} else if (answer instanceof ExecutionState s) {
					completed = true;
					if (s.getTotalTestCount() < 1) {
						throw new CoreException(Status.error("No tests found"));
					}
				}
			}
			Thread.sleep(1000);
		}
		StringBuilder b = new StringBuilder();
		for (Map.Entry<String, Integer> k : froms.entrySet()) {
			b.append("Agent info:" + k.getKey() + " tests:" + k.getValue())
					.append("\n");
		}
		logInfo(b.toString());
	}

	private TestOptions createTestOptions() {
		TestOptions result = ModelFactory.eINSTANCE.createTestOptions();
		for (String optionString : testOptions) {
			int eqIndex = optionString.indexOf('=');
			if (eqIndex == -1) {
				logInfo(String
						.format("Invalid test option %s, format should be 'key = value'",
								optionString));
			}
			result.getValues().put(optionString.substring(0, eqIndex).trim(),
					optionString.substring(eqIndex + 1).trim());
		}
		return result;
	}

	private void applyMetadata(BiConsumer<String, String> metadataOutput) {
		if (metadataFile != null) {
			// try to load metdata file if specified
			Properties props = new Properties();
			File mf = new File(metadataFile);
			if (!mf.exists()) {
				logInfo("Metadata file is not exists: " + metadataFile);
				throw new RuntimeException("Please correct metadata file: "
						+ metadataFile);
			}
			try (BufferedInputStream in = new BufferedInputStream(
						new FileInputStream(mf))) {
				props.load(in);
				in.close();
			} catch (IOException e) {
				logInfo("Metadata file load problem: " + metadataFile
						+ " cause: " + e.getMessage(), e);
				throw new RuntimeException("Please correct metadata file: "
						+ metadataFile, e);
			}
			Set<Entry<Object, Object>> eSet = props.entrySet();
			for (Entry<Object, Object> e : eSet) {
				if (e.getKey() instanceof String
						&& e.getValue() instanceof String) {
					metadataOutput.accept((String) e.getKey(),
							(String) e.getValue());
				}
			}

		}
		for (String optionString : metadata) {
			int eqIndex = optionString.indexOf('=');
			if (eqIndex == -1) {
				logInfo(String
						.format("Invalid test option %s, format should be 'key = value'",
								optionString));
				continue; // to avoid IndexOutOfBounds on next line
			}
			metadataOutput.accept(optionString.substring(0, eqIndex).trim(),
					optionString.substring(eqIndex + 1).trim());
		}
	}

	private List<AutInfo> createAutInfos() throws CoreException, InvalidCommandLineArgException {
		List<AutInfo> rv = new ArrayList<>();
		if (autUri != null) {
			if (platforms == null || platforms.length == 0) {
				rv.add(createAutInfo(autUri, null));
			}
			for (String platform : platforms) {
				AutInfo aut = createAutInfo(autUri, platform);
				rv.add(aut);
			}
		} else if (autsData != null) {

			// Upload each local archive to specified host
			for (Aut autInfo : autsData) {
				String zipPath = autInfo.zipPath;
				if (!new File(zipPath).exists()) {
					throw new InvalidCommandLineArgException(
							"Failed to upload non existing aut archive: "
							+ zipPath + " for architecture: "
							+ autInfo.classifier, "-auts");
				} else {
					UploadResult uploadResult = api.uploadHashedFile(Path.of(zipPath));
					String autURI = getServerPathURI(uploadResult.serverPath().toASCIIString());
					AutInfo autInfo2 = createAutInfo(autURI, autInfo.classifier);
					autInfo2.setHash(uploadResult.hash());
					rv.add(autInfo2);
					System.out.println("Uploaded aut:" + zipPath + " to: "
							+ autURI);
					System.out.flush();
				}
			}
		}
		if (rv.size() == 0) {
			logInfo("No AUTs specified");
			return null;
		}
		return rv;
	}

	private AutInfo createAutInfo(String uri, String platform) throws CoreException {
		AutInfo aut = ModelFactory.eINSTANCE.createAutInfo();
		aut.setUri(deQuote(uri));
		aut.setClassifier(deQuote(platform));
		if (autId == null) {
			autId = testSuiteName;
		}
		aut.setId(autId);

		InjectionConfiguration injection = InjectionFactory.eINSTANCE.createInjectionConfiguration();
		if (injectSites != null && injectSites.length > 0) {
			for (String siteInfo : injectSites) {
				if (siteInfo.startsWith("file:/")) {
					addLocalInjection(siteInfo, injection);
				} else {
					String unquoted = deQuote(siteInfo);
					if ("null".equals(unquoted)) {
						throw new IllegalArgumentException("Invalid injection site location: " + siteInfo);
					}
					injection.getEntries().add(InjectionUtil.siteFromString(unquoted));
				}
			}
		}
		if (!injection.getEntries().isEmpty()) {
			aut.setInjection(injection);
			aut.setIgnoreOtherInjections(ignoreOtherInjections);
		}

		aut.getArgs().addAll(createArgs(autArgs));
		aut.getVmArgs().addAll(createArgs(autVMArgs));
		aut.setExecutionEnvironment(autExecutionEnvironment);
		return aut;
	}

	private void addLocalInjection(String siteInfo, InjectionConfiguration injection) throws CoreException {
		// Splits siteInfo which should looked like: <uri>[;feature1;feature2...]
		String[] split = siteInfo.split(";", 2);
		URI siteUri;
		try {
			siteUri = new URI(split[0]);
		} catch (URISyntaxException e1) {
			throw new IllegalArgumentException("Invalid injection site location: " + siteInfo, e1);
		}
		String zipPath = siteUri.getSchemeSpecificPart();
		String postfix = (split.length > 1) ? ";" + split[1] : "";
		File dirFile = new File(zipPath);
		if (!dirFile.exists()) {
			throw new IllegalArgumentException("Invalid local injection: \"" + zipPath + "\" not found");
		}
		if (dirFile.isFile() && !zipPath.endsWith(".zip")) {
			throw new IllegalArgumentException("Invalid local injection: \"" + zipPath + "\" should be zip archive");
		}

		File toDelete = null;
		try {
			// Makes a zipped version if directory
			if (dirFile.isDirectory()) {
				try {
					toDelete = File.createTempFile("q7-update-site-temp-file", ".zip");
					FileUtil.zipFolder(zipPath, toDelete.getAbsolutePath());
					zipPath = toDelete.getAbsolutePath();
				} catch (IOException e) {
					logInfo("Failed to create temporary upload file: " + e.getMessage());
					throw ClientAppPlugin.createException(e.getMessage(), e);
				}
			}

			// Tries to upload and add
			String uri = uploadMap.get(zipPath);
			if (uri == null) {
				String serverPath = api.uploadFile(suiteID, zipPath, FileUtil.getID(split[0]), true);
				if (serverPath != null) {
					uri = getServerPathURI("artifacts/" +serverPath);
					uploadMap.put(zipPath, uri);
				}
			}
			if (uri != null) {
				System.out.println("Use update site hosted on server: " + uri);
				injection.getEntries().add(InjectionUtil.siteFromString(uri + postfix));
			} else {
				throw ClientAppPlugin.createException("Failed to upload updatesite to server: " + zipPath);
			}
		} finally {
			if (toDelete != null) {
				toDelete.delete();
			}
		}
	}

	private String getServerPathURI(String serverPath) {
		String uri = "server://" + serverHost + ":" + serverPort
				+ "/" + serverPath;
		return uri;
	}

	private List<String> createArgs(String line) {
		List<String> args = Lists.newArrayList();
		if (line == null) {
			return args;
		}
		line = deQuote(line);
		line = deQuote2(line);

		boolean quote = false;
		StringBuilder arg = new StringBuilder();
		for (int index = 0; index < line.length(); index++) {
			char symbol = line.charAt(index);
			switch (symbol) {
			case '"':
				quote = !quote;
				break;
			case ' ':
			case '\t':
			case '\n':
			case '\r':
			case '\f':
				if (quote) {
					arg.append(symbol);
				} else if (arg.length() > 0) {
					args.add(arg.toString());
					arg = new StringBuilder();
				}
				break;
			default:
				arg.append(symbol);
			}
		}

		if (arg.length() > 0) {
			args.add(arg.toString());
		}
		return args;
	}

	private static String deQuote(String line) {
		if (line != null) {
			if (line.startsWith("'") && line.endsWith("'")) {
				line = line.substring(1, line.length() - 1);
			}
		}
		return line;
	}

	private static String deQuote2(String line) {
		if (line != null) {
			if (line.startsWith("\"") && line.endsWith("\"")) {
				line = line.substring(1, line.length() - 1);
			}
		}
		return line;
	}

	private List<ArtifactHandle> addTestSuite(TestSuite newSuite) throws IOException,
			UnknownHostException, CoreException, InterruptedException {
		AddTestSuite addTestSuite = CommonCommandsFactory.eINSTANCE
				.createAddTestSuite();
		addTestSuite.setSuite(newSuite);
		addTestSuite.setSuiteId(suiteID);

		ExecutionResult result = api.execute(addTestSuite);
		if (!result.status.isOK()) {
			throw new RuntimeException("Failed to launch suite:"
					+ result.status.getMessage());
		}
		return Arrays.stream(result.results).map(Q7ArtifactRef.class::cast).map(Q7ArtifactRef::getId).map(resourcesById::get).peek(Objects::requireNonNull).toList();
	}

	private TestSuite createTestSuite() throws CoreException {
		TestSuite rv = ModelFactory.eINSTANCE.createTestSuite();
		rv.setId(testSuiteName);
		ModelUtil.setRefs(rv, resourcesById.values().stream().map(this::toRef).toList()  );
		return rv;
	}
	
	private Q7ArtifactRef toRef(ArtifactHandle handle) {
		Q7ArtifactRef result = ModelFactory.eINSTANCE.createQ7ArtifactRef();
		result.setHash(handle.hash.asBytes());
		result.setId(handle.id);
		result.setKind(toModel(handle.kind));
		result.getRefs().addAll(handle.contexts);
		result.getRefs().addAll(handle.verifications);
		return result;
	}

	private RefKind toModel(HandleType kind) {
		return switch (kind) {
			case Context -> RefKind.CONTEXT;
			case TestCase -> RefKind.SCENARIO;
			case Verification -> RefKind.VERIFICATION;
			default -> throw new IllegalArgumentException("Unexpected value: " + kind);
		};
	}

	private List<Aut> autsData;

	private String suiteID;

	private void updateAutUri() throws CoreException,
			UnsupportedEncodingException, InvalidCommandLineArgException {
		if (autUri == null && autsData == null) {

			if (auts == null || auts.length == 0) {
				throw new InvalidCommandLineArgException (
						"One from 'autUri' or 'auts' parameters must be specified.", "auts");
			}

			autsData = new ArrayList<>();
			for (String aut : auts) {
				aut = deQuote(aut);
				if (aut.startsWith("\"") && aut.endsWith("\"")) {
					aut = aut.substring(1, aut.length() - 1);
				}
				String[] data = aut.split("[|]");
				if (data.length != 2) {
					throw new IllegalArgumentException(
							"Invalid 'auts' parameter.");
				}
				autsData.add(new Aut(data[0], data[1]));
			}
		}
	}

	private static void logInfo(String message) {
		LOG.info(message);
		System.out.println(message);
		System.out.flush();
	}

	private static void logInfo(String message, Throwable e) {
		LOG.error(message, e);
		System.out.println(message);
		if (e != null) {
			e.printStackTrace();
		}
		System.out.flush();
	}

	private static void createShutdownListener(final int port) {
		Thread ShutdownListenerThread = new Thread() {

			@Override
			public void run() {
				// listen only for local connections
				try (ServerSocket serverSocket = new ServerSocket(port, 50,
							InetAddress.getByName(null))) {
					serverSocket.accept().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("ClientApplication terminated.");
			}
		};
		ShutdownListenerThread.setDaemon(true);
		ShutdownListenerThread.start();
	}

	private String getSuiteId() {
		return suiteID;
	}

	public static Report generateSkippedReport(String scenarioId,
			String scenarioName, String message) {

		Report report = ReportFactory.eINSTANCE.createReport();
		Node root = ReportFactory.eINSTANCE.createNode();
		root.setName(scenarioName);
		report.setRoot(root);
		Q7Info q7info = ReportingFactory.eINSTANCE.createQ7Info();
		q7info.setId(scenarioId);
		q7info.setResult(RcpttPlugin.createProcessStatus(IStatus.ERROR, message));
		q7info.setType(ItemKind.TESTCASE);
		root.getProperties().put(IQ7ReportConstants.ROOT, q7info);

		Q7Info scenarioInfo = EcoreUtil.copy(q7info);
		scenarioInfo.setType(ItemKind.SCRIPT);
		Node scenarioNode = ReportFactory.eINSTANCE.createNode();
		scenarioNode.setName(root.getName());
		scenarioNode.getProperties().put(IQ7ReportConstants.ROOT, scenarioInfo);
		root.getChildren().add(scenarioNode);

		return report;
	}

	private Q7ArtifactRef toContextRef(String id) {
		Q7ArtifactRef result = ModelFactory.eINSTANCE.createQ7ArtifactRef();
		result.setId(id);
		final ArtifactHandle context = resourcesById.get(id);
		if (context == null) {
			throw new IllegalArgumentException("Context wih ID " + id + " is not found");
		}
		assert context.contexts.isEmpty();
		assert context.verifications.isEmpty();
		if (context.kind != HandleType.Context) {
			throw new IllegalArgumentException("Object wih ID " + id + " is not a context");
		}
		result.setHash(context.hash.asBytes());
		result.setKind(RefKind.CONTEXT);
		return result;
	}
	
	private Q7ArtifactRef toVerificationRef(String id) {
		Q7ArtifactRef result = ModelFactory.eINSTANCE.createQ7ArtifactRef();
		result.setId(id);
		final ArtifactHandle verification = resourcesById.get(id);
		if (verification == null) {
			throw new IllegalArgumentException("Verification wih ID " + id + " is not found");
		}
		assert verification.contexts.isEmpty();
		assert verification.verifications.isEmpty();
		if (verification.kind != HandleType.Verification) {
			throw new IllegalArgumentException("Object wih ID " + id + " is not a verification");
		}
		result.setHash(verification.hash.asBytes());
		result.setKind(RefKind.VERIFICATION);
		return result;
	}

}
