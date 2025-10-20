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

import static java.util.Objects.requireNonNull;
import static org.eclipse.core.runtime.Platform.getLog;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ICoreRunnable;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.junit.rules.ExternalResource;
import org.osgi.framework.Bundle;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterators;

public final class ResourcesRule extends ExternalResource {
	public final IWorkspace workspace = ResourcesPlugin.getWorkspace();

	public void disableAutoBuilding() {
		IWorkspaceDescription description = workspace.getDescription();
		description.setAutoBuilding(false);
		try {
			workspace.setDescription(description);
		} catch (CoreException e) {
			throw new AssertionError(e);
		}
	}
	
	public void copyProject(Bundle bundle, String path) throws CoreException, IOException {
		if (path.startsWith("/")) {
			throw new IllegalArgumentException("Path should be realtive: " + path);
		}
		URL descriptionUrl = Iterators.getOnlyElement(bundle.findEntries(path + "/", ".project", true).asIterator());
		requireNonNull(descriptionUrl, "Failed to resolve bundle resource " + path + "/.project");
		IProject project;
		IProjectDescription description;
		{
			try (final InputStream is = descriptionUrl.openStream()) {
				description = workspace.loadProjectDescription(is);
			}
			String name = description.getName();
			project = workspace.getRoot().getProject(name);
		}
		workspace.run((ICoreRunnable) (monitor -> {
			project.create(description, null);
			project.open(null);
			String prefixToRemove = "/" + path;
			for (URL entry : (Iterable<URL>) (bundle.findEntries(path + "/", "*", true)::asIterator)) {
				if (entry.equals(descriptionUrl)) {
					continue;
				}
				String entryPath = entry.getPath();
				if (!entryPath.startsWith(prefixToRemove)) {
					throw new AssertionError("Unexpected search result: " + entry);
				}
				entryPath = entryPath.substring(prefixToRemove.length());
				// TODO: support creation of missing parent folders
				if (entryPath.endsWith("/")) {
					IFolder resource = project.getFolder(entryPath);
					create(resource);
				} else {
					IFile resource = project.getFile(entryPath);
					create(resource.getParent());
					try (final InputStream is = entry.openStream()) {
						if (resource.exists()) {
							resource.setContents(is, 0, null);
						} else {
							resource.create(is, 0, null);
						}
					} catch (IOException e) {
						throw new AssertionError(e);
					}
				}
			}
		}), workspace.getRuleFactory().createRule(project), 0, null);
	}

	public void importProject(Path projectRoot) {
		IProject project;
		IProjectDescription description;
		try {
			final Path projectDescriptionFile = projectRoot.resolve(".project");
			try (InputStream is = Files.newInputStream(projectDescriptionFile)) {
				description = workspace.loadProjectDescription(is);
				project = workspace.getRoot().getProject(description.getName());
			}
			description.setLocation(IPath.fromOSString(projectRoot.toString()));
			workspace.run((ICoreRunnable) (monitor -> {
				project.create(description, null);
				project.open(null);
				assert Path.of(project.getLocationURI()).equals(projectRoot);
			}), workspace.getRuleFactory().createRule(project), IWorkspace.AVOID_UPDATE, null);
		} catch (IOException e) {
			throw new AssertionError(e);
		} catch (CoreException e) {
			throw new AssertionError(e);
		}
	}

	public void copyProject(Path projectRoot) {
		IProject project;
		IProjectDescription description;
		try {
			final Path projectDescriptionFile = projectRoot.resolve(".project");
			try (InputStream is = Files.newInputStream(projectDescriptionFile)) {
				description = workspace.loadProjectDescription(is);
				project = workspace.getRoot().getProject(description.getName());
			}
			workspace.run((ICoreRunnable) (monitor -> {
				project.create(description, null);
				project.open(null);
				@SuppressWarnings("resource")
				Path EMPTY = projectRoot.getFileSystem().getPath("");
				try {
					Files.walkFileTree(projectRoot, new SimpleFileVisitor<Path>() {
						@Override
						public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
							final Path relativePath = projectRoot.relativize(dir);
							if (EMPTY.equals(relativePath)) {
								return FileVisitResult.CONTINUE;
							}
							IFolder resource = project.getFolder(Joiner.on('/').join(segments(relativePath)));
							try {
								create(resource);
							} catch (CoreException e) {
								throw new AssertionError(e);
							}
							return FileVisitResult.CONTINUE;
						}

						@Override
						public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
							if (file.equals(projectDescriptionFile)) {
								return FileVisitResult.SKIP_SUBTREE;
							}
							final Path relativePath = projectRoot.relativize(file);
							IFile resource = project.getFile(Joiner.on('/').join(segments(relativePath)));
							try (InputStream is = Files.newInputStream(file)) {
								try {
									if (resource.exists()) {
										// Overwrite automatically generated .settings directory
										resource.setContents(is, 0, null);
									} else {
										resource.create(is, 0, null);
									}
								} catch (CoreException e) {
									throw new AssertionError(e);
								}
							}
							return FileVisitResult.CONTINUE;
						}
					});
				} catch (IOException e) {
					throw new AssertionError(e);
				}
			}), workspace.getRuleFactory().createRule(project), IWorkspace.AVOID_UPDATE, null);
		} catch (IOException e) {
			throw new AssertionError(e);
		} catch (CoreException e) {
			throw new AssertionError(e);
		}

	}

	private void create(IContainer container) throws CoreException {
		if (container.exists()) {
			return;
		}
		final IContainer parent = container.getParent();
		create(parent);
		parent.getFolder(IPath.fromPortableString(container.getName())).create(false, false, null);
	}

	@Override
	protected void before() throws Throwable {
		super.before();
		clear();
	}

	@Override
	protected void after() {
		clear();
		super.after();
	}

	public void clear() {
		try {
			workspace.run((ICoreRunnable) (monitor -> {
				for (IProject i : workspace.getRoot().getProjects()) {
					try {
						boolean isInWorkspaceDirectory = workspace.getRoot().getLocation().isPrefixOf(i.getLocation());
						i.delete(isInWorkspaceDirectory, true, null);
					} catch (CoreException e) {
						LOG.log(e.getStatus());
						throw new AssertionError(e);
					}
				}
			}), workspace.getRuleFactory().modifyRule(workspace.getRoot()), IWorkspace.AVOID_UPDATE, null);
		} catch (CoreException e) {
			throw new AssertionError(e);
		}
	}

	private String[] segments(Path relativePath) {
		String[] results = new String[relativePath.getNameCount()];
		for (int i = 0; i < results.length; i++ ) {
			results[i] = relativePath.getName(i).toString();
		}
		return results;
	}


	private static final ILog LOG = getLog(ResourcesRule.class);

}
