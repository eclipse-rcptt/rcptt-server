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
package org.eclipse.rcptt.cloud.agent.autManager.impl;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessBuilder.Redirect;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.StreamSupport;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathNodes;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

final class DmgExtract {

	private static final XPathExpression KEYS;
	static {
		try {
			KEYS = XPathFactory.newInstance().newXPath()
					.compile("plist/dict/array/dict/key[text()=\"mount-point\"]/following-sibling::string[1]");
		} catch (XPathExpressionException e) {
			throw new AssertionError(e);
		}
	}

	// See https://stackoverflow.com/a/78068659/125562
	public static void extract(Path dmgPath, Path target) throws IOException, InterruptedException {
		assert Files.isRegularFile(dmgPath);
		assert Files.isDirectory(target);
		ProcessBuilder processBuilder = new ProcessBuilder("hdiutil", "attach", "-nobrowse", "-readonly", "-plist",
				dmgPath.toString());
		processBuilder.redirectError(Redirect.INHERIT);
		Process process = processBuilder.start();
		List<String> mountpoints;
		try (InputStream output = process.getInputStream()) {
			mountpoints = parseMountPoints(output);
			completeProcess(process, "" + processBuilder.command());
		} finally {
			process.destroy();
		}
		processBuilder.redirectOutput(Redirect.INHERIT);

		for (String point : mountpoints) {
			try {
				copyFolder(Paths.get(point), target, NOFOLLOW_LINKS);
			} finally {
				processBuilder.command("hdiutil", "detach", point);
				completeProcess(processBuilder.start(), "" + processBuilder.command());
			}
		}

	}

	public static void copyFolder(Path source, Path target, CopyOption... options) throws IOException {
		Files.walkFileTree(source, new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				Files.createDirectories(target.resolve(source.relativize(dir).toString()));
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.copy(file, target.resolve(source.relativize(file).toString()), options);
				return FileVisitResult.CONTINUE;
			}
		});
	}

	private static void completeProcess(Process process, String description) throws IOException, InterruptedException {
		try {
			if (!process.waitFor(100, TimeUnit.SECONDS)) {
				throw new IOException(description + " has timed out");
			}
			if (process.exitValue() != 0) {
				throw new IOException(description + " has exited with code " + process.exitValue());
			}
		} finally {
			process.destroy();
		}
	}

	private static List<String> parseMountPoints(InputStream plist) throws IOException {
		try {
			Document dom = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder().parse(plist);
			XPathNodes result = KEYS.evaluateExpression(dom, XPathNodes.class);
			return StreamSupport.stream(result.spliterator(), false).map(Node::getTextContent).toList();
		} catch (SAXException | ParserConfigurationException | XPathExpressionException e) {
			throw new IOException(e);
		}
	}

//	@Test
	public void parsePlist() throws IOException {
		String data = """
				<?xml version="1.0" encoding="UTF-8"?>
				<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
				<plist version="1.0">
				<dict>
					<key>system-entities</key>
					<array>
						<dict>
							<key>content-hint</key>
							<string>Apple_HFS</string>
							<key>dev-entry</key>
							<string>/dev/disk4s1</string>
							<key>mount-point</key>
							<string>/Volumes/Eclipse</string>
							<key>potentially-mountable</key>
							<true/>
							<key>unmapped-content-hint</key>
							<string>48465300-0000-11AA-AA11-00306543ECAC</string>
							<key>volume-kind</key>
							<string>hfs</string>
						</dict>
						<dict>
							<key>content-hint</key>
							<string>GUID_partition_scheme</string>
							<key>dev-entry</key>
							<string>/dev/disk4</string>
							<key>potentially-mountable</key>
							<false/>
							<key>unmapped-content-hint</key>
							<string>GUID_partition_scheme</string>
						</dict>
						<dict>
							<key>mount-point</key>
							<string>/Volumes/Eclipse2</string>
						</dict>
					</array>
				</dict>
				</plist>
				""";

		ByteArrayInputStream stream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
		List<String> list = parseMountPoints(stream);
		System.out.println(list);
		if (!List.of("/Volumes/Eclipse", "/Volumes/Eclipse2").equals(list)) {
			throw new AssertionError();
		}
	}
}
