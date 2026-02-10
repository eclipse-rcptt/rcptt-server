package org.eclipse.rcptt.cloud.agent;

import static java.lang.System.currentTimeMillis;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.rcptt.cloud.common.ITestStore;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.ModelFactory;
import org.eclipse.rcptt.cloud.model.Q7Artifact;
import org.eclipse.rcptt.cloud.model.Q7ArtifactRef;
import org.eclipse.rcptt.cloud.model.RefKind;
import org.eclipse.rcptt.cloud.model.TestSuite;
import org.eclipse.rcptt.core.scenario.Scenario;
import org.eclipse.rcptt.core.scenario.ScenarioFactory;
import org.eclipse.rcptt.ecl.core.CoreFactory;
import org.eclipse.rcptt.ecl.core.Script;
import org.eclipse.rcptt.launching.Q7Launcher;
import org.eclipse.rcptt.reporting.Q7Info;
import org.eclipse.rcptt.reporting.core.ReportHelper;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import com.google.common.hash.HashCode;
import com.google.common.io.Closer;

public class TestExecutorTest {
	private static final Closer closer = Closer.create();
	private static final IProgressMonitor MONITOR = new NullProgressMonitor();
	
	@Rule
	public final MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
	

	@Mock
	private ITestStore testStore;
	private final TestSuite testSuite = ModelFactory.eINSTANCE.createTestSuite();
	private final Q7ArtifactRef testRef = ModelFactory.eINSTANCE.createQ7ArtifactRef();
	private final Q7Artifact test = ModelFactory.eINSTANCE.createQ7Artifact();
	private final Scenario scenario = ScenarioFactory.eINSTANCE.createScenario();
	private final Script script =  CoreFactory.eINSTANCE.eINSTANCE.createScript();
	
	@Before
	public void before() throws IOException {
		when(testStore.getTestSuite()).thenReturn(testSuite);
		when(testStore.getResource(any())).thenReturn(test);
		testSuite.getRefs().add(testRef);
		testRef.setKind(RefKind.SCENARIO);
		test.setContent(scenario);
		scenario.setName("ScenarioName");
		scenario.setId("ScenarioId");
		scenario.setContent(script);
		Q7Launcher.setLaunchTimeout(5);
	}

	@After
	public void after() throws IOException {
		closer.close();
	}
	
	@BeforeClass
	public static void beforeClass() throws CoreException {
		TestExecutor subject = new TestExecutor(autInfo());
		try {
			subject.deployAut(MONITOR);
		} finally {
			subject.shutdown();
		}

	}

	@Test
	public void normalExecution() throws CoreException, TimeoutException {
		TestExecutor subject = new TestExecutor(autInfo());
		closer.register(subject::shutdown);
		
		subject.deployAut(MONITOR);
		try {
			subject.startAut(MONITOR);
			Report report = subject.runTest(0, testStore, MONITOR);
			Q7Info info = ReportHelper.getInfoOnly(report.getRoot());
			assertEquals(IStatus.OK, info.getResult().getSeverity());
		} finally {
			System.out.println(subject.getOutStreamFile(Long.MAX_VALUE));
			System.err.println(subject.getErrStreamFile(Long.MAX_VALUE));
		}
	}

	@Test
	public void failedExecution() throws CoreException, TimeoutException {
		TestExecutor subject = new TestExecutor(autInfo());
		closer.register(subject::shutdown);
		
		subject.deployAut(MONITOR);
		subject.startAut(MONITOR);
		script.setContent("assert false");
		Report report = subject.runTest(0, testStore, MONITOR);
		Q7Info info = ReportHelper.getInfoOnly(report.getRoot());
		assertEquals(IStatus.ERROR, info.getResult().getSeverity());
	}
	
	@Test
	public void respectLaunchTimeout() throws CoreException, TimeoutException {
			TestExecutor subject = new TestExecutor(autInfo());
			closer.register(subject::shutdown);
			
			subject.deployAut(MONITOR);
			subject.startAut(MONITOR);
			script.setContent("wait 100000");
			
			Q7Launcher.setLaunchTimeout(1);
			long start = currentTimeMillis();
			Report report = subject.runTest(0, testStore, MONITOR);
			long stop = currentTimeMillis();
			Q7Info info = ReportHelper.getInfoOnly(report.getRoot());
			assertEquals(IStatus.ERROR, info.getResult().getSeverity());
			assertThat((stop - start)/1000., closeTo(2, 2));
			
			Q7Launcher.setLaunchTimeout(5);
			start = currentTimeMillis();
			report = subject.runTest(0, testStore, MONITOR);
			stop = currentTimeMillis();
			info = ReportHelper.getInfoOnly(report.getRoot());
			assertEquals(IStatus.ERROR, info.getResult().getSeverity());
			assertThat((stop - start)/1000., closeTo(6, 2));
	}

	
	private static AutInfo autInfo() {
		AutInfo autInfo = ModelFactory.eINSTANCE.createAutInfo();
		autInfo.getArgs().add("-consoleLog");

		switch(Platform.getOS()) {
			case Platform.OS_MACOSX -> {
				autInfo.setId("eclipse-platform-4.38-macosx-cocoa-aarch64.dmg");
				autInfo.setUri("https://ftp.yz.yamagata-u.ac.jp/pub/eclipse/eclipse/downloads/drops4/R-4.38-202512010920/eclipse-platform-4.38-macosx-cocoa-aarch64.dmg");
				autInfo.setHash(HashCode.fromString("80ed014319de547ead8f552ace8a469a0fa18a595bd316fa1cdedfcccf31315b58b5ec91232d59d6224398d930d092d5813efeac7b55a3271a3e6297bb6effad").asBytes());
			}
			case Platform.OS_LINUX -> {
				autInfo.setId("eclipse-platform-4.38-linux-gtk-x86_64.tar.gz");
				autInfo.setUri("https://ftp.yz.yamagata-u.ac.jp/pub/eclipse/eclipse/downloads/drops4/R-4.38-202512010920/eclipse-platform-4.38-linux-gtk-x86_64.tar.gz");
				autInfo.setHash(HashCode.fromString("e498da6b1203409a9902760d29d6d91f37fa0fa2622cb3b75af517c21ab8dec191439868a4da6a00e6c4829aba9c4f65ef61a6f629dd75e258f609e0de6ead7c").asBytes());
			}
			case Platform.OS_WIN32 -> {
				autInfo.setId("eclipse-platform-4.38-win32-x86_64.zip");
				autInfo.setUri("https://ftp.yz.yamagata-u.ac.jp/pub/eclipse/eclipse/downloads/drops4/R-4.38-202512010920/eclipse-platform-4.38-win32-x86_64.zip");
				autInfo.setHash(HashCode.fromString("e580253c59cf4c65e253295e6d849bc2eef6e38104d4d151bd4eb0c2b632ef6278865b1f5419b6574d5c53eeed9c16553cdee0c3d5fa8414c812f09e9417fe3c").asBytes());
			}
			default -> throw new IllegalStateException("Unknown OS: " + Platform.getOS());
		}
		return autInfo;
	}

}
