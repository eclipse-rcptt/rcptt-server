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
package org.eclipse.rcptt.cloud.agent.autManager.impl;

import static java.nio.file.Files.exists;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.rcptt.cloud.common.UriUtil;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.model.ModelFactory;
import org.eclipse.rcptt.cloud.util.IOUtil.ISrcFactory;
import org.eclipse.rcptt.util.FileUtil;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class BaseAutProviderTest {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();
	
	@Test
	public void cleanupOldAuts() throws URISyntaxException, IOException, CoreException {
		Path aut1 = archiveResource("resources/aut_1", "aut_1-win32.win32.x86_64.zip");
		Path aut2 = archiveResource("resources/aut_2", "aut_2-win32.win32.x86_64.zip");
		try (BaseAutProvider subject = new Subject()) {
			AutInfo autInfo = createAutInfo();
			autInfo.setUri(aut1.toUri().toString());
			File result = subject.download(autInfo, "win32.win32.x86_64", new NullProgressMonitor());
			Assert.assertTrue(result.exists());
			autInfo.setUri(aut2.toUri().toString());
			autInfo.setId("aut2");
			subject.download(autInfo, "win32.win32.x86_64", new NullProgressMonitor());
			Assert.assertFalse(result.exists());
		}
	}
	
	@Test
	public void untar() throws IOException, CoreException {
		try (BaseAutProvider subject = new Subject()) {
			AutInfo autInfo = createAutInfo();
			File file;
			try (InputStream is = getClass().getResourceAsStream("/resources/example.tar.gz")) {
				file = temporaryFolder.newFile("example-linux.gtk.x86_64.tar.gz");
				Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
			try (OutputStream os = Files.newOutputStream(file.toPath(), StandardOpenOption.APPEND)) {
				os.write(new byte[1024*1024]);
			}
			autInfo.setUri(file.toURI().toASCIIString());
			File result = subject.download(autInfo, "linux.gtk.x86_64", new NullProgressMonitor());
			
			assertTrue(result.toPath().endsWith("test.txt"));
			assertTrue(exists(result.toPath()));
		}
	}
	
	private Path archiveResource(String resource, String name) throws IOException {
		Bundle bundle = FrameworkUtil.getBundle(getClass());
		Path path;
		try {
			path = Path.of(
					FileLocator.toFileURL(FileLocator.find(bundle, org.eclipse.core.runtime.Path.fromPortableString(resource))).toURI());
		} catch (URISyntaxException e) {
			throw new IOException(e);
		}
		File tempDir = temporaryFolder.newFolder();
		FileUtil.copyFiles(path.toFile(), tempDir);
		Path random_data_file = tempDir.toPath().resolve("random_data.bin");
		writeRandom(random_data_file);
		File aut1 = temporaryFolder.newFile(name);
		archive(tempDir.toPath(), aut1);
		return aut1.toPath();
	}

	private void writeRandom(Path random_data_file) throws IOException {
		try (OutputStream output = Files.newOutputStream(random_data_file)) {
			Random random = new Random();
			byte[] bytes = new byte[Long.BYTES];
			for (int i = 0; i < 1024*1024; i++) {
				long datum = random.nextLong();
		        for (int j = 0; j < bytes.length; j++) {
		        	bytes[j] = (byte) (datum & 0xFF);
		        	datum >>= 8;
		        }
				output.write(bytes);
			}
		}
	}
	
	private AutInfo createAutInfo() {
		AutInfo aut = ModelFactory.eINSTANCE.createAutInfo();
		aut.setClassifier("win32.win32.x86_64");
		aut.setUri("a.com");
		aut.setId("aut1");
		return aut;
	}

	private void archive(Path path, File aut1) throws IOException {
		try (ZipOutputStream zip = new ZipOutputStream(Files.newOutputStream(aut1.toPath()))) {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					String entryName = StreamSupport.stream(path.relativize(file).spliterator(), false)
							.map(Path::toString).collect(Collectors.joining("/"));
					ZipEntry enrty = new ZipEntry(entryName);
					zip.putNextEntry(enrty);
					Files.copy(file, zip);
					zip.closeEntry();
					return super.visitFile(file, attrs);
				}
			});
		}
	}

	private static final class Subject extends BaseAutProvider {

		public Subject() throws IOException {
			super();
		}

		@Override
		public boolean isSupport(AutInfo aut) {
			return true;
		}

		@Override
		protected ISrcFactory getMd5SourceFactory(AutInfo aut, String classifier) {
			return new UrlSrcFactory(URI.create(UriUtil.autMd5(aut.getUri(), classifier)));
		}

		@Override
		protected ISrcFactory getZipSourceFactory(AutInfo aut, String classifier) {
			return new UrlSrcFactory(URI.create(UriUtil.autZip(aut.getUri(), classifier)));
		}

	}

	private static final class UrlSrcFactory implements ISrcFactory {
		private final URI uri;

		public UrlSrcFactory(URI uri) {
			super();
			this.uri = Objects.requireNonNull(uri);
		}

		@Override
		public InputStream reopen() throws IOException {
			return uri.toURL().openStream();
		}

		@Override
		public void done() {
		}

	}

}
