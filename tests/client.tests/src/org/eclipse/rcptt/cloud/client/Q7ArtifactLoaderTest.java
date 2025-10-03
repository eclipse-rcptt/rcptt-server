package org.eclipse.rcptt.cloud.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.rcptt.cloud.client.Q7ArtifactLoader.ArtifactHandle;
import org.eclipse.rcptt.core.scenario.Scenario;
import org.junit.Rule;
import org.junit.Test;
import org.osgi.framework.FrameworkUtil;

public class Q7ArtifactLoaderTest {
	
	@Rule
	public final ResourcesRule resources = new ResourcesRule(); 

	@Test
	public void propagateProjectMetadata() throws CoreException, IOException, InterruptedException {
		resources.importProject(FrameworkUtil.getBundle(getClass()), "projects/defaultContext");
		Q7ArtifactLoader subject = createSubject();
		ArtifactHandle handle = subject.findArtifacts().filter(h -> "_4kgawJ65EfCKA7dMQkd_6w".equals(h.id)).findFirst().get();
		final Scenario scenario = (Scenario)handle.getContent();
		assertTrue(handle.contexts.contains("_BdU1kJ66EfCKA7dMQkd_6w"));
		assertTrue(scenario.getContexts().contains("_BdU1kJ66EfCKA7dMQkd_6w"));
		assertTrue(handle.verifications.contains("_sEEQUKAqEfCJSZhz2-miOg"));
		assertTrue(scenario.getVerifications().contains("_sEEQUKAqEfCJSZhz2-miOg"));
	}
	
	@Test
	public void superContexts() throws CoreException, IOException, InterruptedException {
		resources.importProject(FrameworkUtil.getBundle(getClass()), "projects/superCOntexts");
		Q7ArtifactLoader subject = createSubject();
		List<ArtifactHandle> handles = subject.findArtifacts().filter(h -> h.id.startsWith("_Oxf8IJ-QEfCxSNB-5UOilw")).toList();
		assertEquals(3, handles.size());
		handles = subject.findArtifacts().filter(h -> h.id.startsWith("_go_-oJ-PEfCxSNB-5UOilw")).toList();
		assertEquals(4, handles.size());
	}
	

	private Q7ArtifactLoader createSubject() {
		return new Q7ArtifactLoader(new String[0]);
	}
	
	

}
