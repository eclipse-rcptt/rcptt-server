/********************************************************************************
 * Copyright (c) 2025 Xored Software Inc and others
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

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.rcptt.cloud.client.Q7ArtifactLoader.ArtifactHandle;
import org.eclipse.rcptt.cloud.common.Hash;
import org.eclipse.rcptt.core.model.ModelException;
import org.eclipse.rcptt.core.scenario.NamedElement;
import org.eclipse.rcptt.core.scenario.Scenario;
import org.eclipse.rcptt.ecl.core.util.ECLBinaryResourceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.osgi.framework.FrameworkUtil;

import com.google.common.hash.HashCode;

public class Q7ArtifactLoaderTest {

	@Rule
	public final ResourcesRule resources = new ResourcesRule();
	@Rule
	public final TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Before
	public void before() {
		resources.disableAutoBuilding();
		temporaryFolder.getRoot().deleteOnExit();
	}

	@Test
	public void propagateProjectMetadata() throws CoreException, IOException, InterruptedException {
		resources.copyProject(FrameworkUtil.getBundle(getClass()), "projects/defaultContext");
		Q7ArtifactLoader subject = createSubject();
		ArtifactHandle handle = subject.findArtifacts().filter(h -> "_4kgawJ65EfCKA7dMQkd_6w".equals(h.id)).findFirst()
				.get();
		final Scenario scenario = (Scenario) handle.getContent();
		assertTrue(handle.contexts.contains("_BdU1kJ66EfCKA7dMQkd_6w"));
		assertTrue(scenario.getContexts().contains("_BdU1kJ66EfCKA7dMQkd_6w"));
		assertTrue(handle.verifications.contains("_sEEQUKAqEfCJSZhz2-miOg"));
		assertTrue(scenario.getVerifications().contains("_sEEQUKAqEfCJSZhz2-miOg"));
	}

	@Test
	public void superContexts() throws CoreException, IOException, InterruptedException {
		resources.copyProject(FrameworkUtil.getBundle(getClass()), "projects/superContexts");
		Q7ArtifactLoader subject = createSubject();
		List<ArtifactHandle> handles = subject.findArtifacts().filter(h -> h.id.startsWith("_Oxf8IJ-QEfCxSNB-5UOilw"))
				.toList();
		assertEquals(3, handles.size());
		handles = subject.findArtifacts().filter(h -> h.id.startsWith("_go_-oJ-PEfCxSNB-5UOilw")).toList();
		assertEquals(4, handles.size());
	}

//	@Test
	public void dumpBin() throws IOException {
		binToXml("old");
		binToXml("new");
	}
	
	private void binToXml(String name) throws IOException {
		Resource resource = new ECLBinaryResourceImpl();

		resource.setURI(URI.createFileURI("/tmp/"+name+".bin"));
		resource.load(null);
		
		resource = toXmlResource(resource.getContents().get(0));
		resource.setURI(URI.createFileURI("/tmp/"+name+".xml"));
		resource.save(null);
		
	}

	@Test
	public void contentIsTemporallyConsistent() throws CoreException, IOException, InterruptedException {
		importLargeProject();
		List<ArtifactHandle> artifacts = getArtifacts();
		artifacts.stream().parallel().forEach(h -> {
			try {
				final NamedElement content = h.getContentInternal();
				Resource resource = toResource(h, content);
				MessageDigest md = Hash.createDigest();
				try (DigestOutputStream os = new DigestOutputStream(Files.newOutputStream(toTempPath(h)), md)) {
					resource.save(os, null);
				}
				assertEquals(h.hash, HashCode.fromBytes(md.digest()));
				assertEquals(h.hash, hash(content));
				assertEquals(h.hash, hash(resource.getContents().get(0)));
			} catch (Error | Exception e) {
				throw new AssertionError(h.path, e);
			}

		});
		Map<String, HashCode> originalHashes = computeAllHashes(artifacts);
		for (long stop = currentTimeMillis() + 1000; currentTimeMillis() < stop;) {
			importLargeProject();
			assertEquals(originalHashes, computeAllHashes(artifacts));
		}
	}

	private Resource toResource(ArtifactHandle h, NamedElement content) throws ModelException {
		Resource resource = new ECLBinaryResourceImpl();
		resource.getContents().add(EcoreUtil.copy(content));

		return resource;
	}

	private URI toTempUri(ArtifactHandle h) {
		return URI.createFileURI(toTempPath(h).toAbsolutePath().toString());
	}

	private Path toTempPath(ArtifactHandle h) {
		return temporaryFolder.getRoot().toPath().resolve(h.id);
	}

	private void importLargeProject() throws CoreException, IOException {
		resources.clear();
		resources.copyProject(FrameworkUtil.getBundle(getClass()), "projects/superContexts");

////		resources.importProject(Path.of(System.getProperty("user.home")).resolve("git/dt-products-rcptt-tests/functional/PreparatoryActions"));
	}

	private Map<String, HashCode> computeAllHashes(List<ArtifactHandle> list)
			throws CoreException, IOException, InterruptedException {
		list.stream().parallel().forEach(h -> {
			try {
				final NamedElement newContent = h.getContentInternal();
				if (!h.hash.equals(hash(newContent))) {
					Files.copy(toTempPath(h), Path.of("/tmp/old.bin"), StandardCopyOption.REPLACE_EXISTING);
					Resource resource = toResource(h, newContent);
					try (OutputStream os = Files.newOutputStream(Path.of("/tmp/new.bin"), StandardOpenOption.CREATE)) {
						resource.save(os, null);
					}
					throw new AssertionError(h.path + " hash does not match");
				}
			} catch (IOException | ModelException e) {
				throw new AssertionError(h.path, e);
			}
		});
		Map<String, HashCode> result = list.stream().collect(Collectors.toMap(h -> h.id, h -> h.hash));
		return result;
	}

	private HashCode hash(final EObject newContent) {
		return Hash.hash(newContent);
	}

	private List<ArtifactHandle> getArtifacts() throws CoreException, InterruptedException {
		Q7ArtifactLoader subject = createSubject();
		List<ArtifactHandle> list = subject.findArtifacts().parallel().toList();
		return list;
	}

	private Resource toXmlResource(EObject content) {
		XMLResourceImpl resource = new XMLResourceImpl() {
			@Override
			protected XMLSave createXMLSave(Map<?, ?> options) {
				return new XmlSaveNoNulls(options != null ? options : Map.of(), createXMLHelper());
			}
		};
		resource.getContents().add(EcoreUtil.copy(content));
		resource.setXMLVersion("1.1");
		resource.setEncoding("UTF-8");
		return resource;
	}

	private Q7ArtifactLoader createSubject() {
		return new Q7ArtifactLoader(new String[0]);
	}

}
