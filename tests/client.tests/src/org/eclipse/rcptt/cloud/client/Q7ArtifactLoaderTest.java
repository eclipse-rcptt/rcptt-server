package org.eclipse.rcptt.cloud.client;

import java.io.IOException;
import java.util.stream.Stream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.rcptt.cloud.client.Q7ArtifactLoader.ElementAndReferences;
import org.junit.Rule;
import org.junit.Test;
import org.osgi.framework.FrameworkUtil;

public class Q7ArtifactLoaderTest {
	
	@Rule
	public final ResourcesRule resources = new ResourcesRule(); 

	@Test
	public void test() throws CoreException, IOException, InterruptedException {
		resources.importProject(FrameworkUtil.getBundle(getClass()), "projects/defaultContext");
		Q7ArtifactLoader subject = new Q7ArtifactLoader(new String[0]);
		Stream<ElementAndReferences> references = subject.artifactRefs();
		
	}

	private Q7ArtifactLoader createSubject() {
		return new Q7ArtifactLoader(new String[0]);
	}

}
