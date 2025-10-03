package org.eclipse.rcptt.cloud.client;

import static java.util.Objects.requireNonNull;
import static org.eclipse.core.runtime.Platform.getLog;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ICoreRunnable;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.junit.rules.ExternalResource;
import org.osgi.framework.Bundle;

public final class ResourcesRule extends ExternalResource {
	public final IWorkspace workspace = ResourcesPlugin.getWorkspace();
	
	public void importProject(Bundle bundle, String path) throws CoreException, IOException {
		if (path.startsWith("/")) {
			throw new IllegalArgumentException("Path shoulfd be realtive: " + path);
		}
		URL descriptionUrl = bundle.getEntry(path+"/.project");
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
		workspace.run((ICoreRunnable)(monitor -> {
				project.create(description, null);
				project.open(null);
				System.out.println(project.getName());
				String prefixToRemove = "/" + path;
				for (URL entry: (Iterable<URL>)(bundle.findEntries(path+"/", "*", true)::asIterator)) {
					System.out.println(entry);
					if (entry.equals(descriptionUrl)) {
						continue;
					}
					String entryPath = entry.getPath();
					if (!entryPath.startsWith(prefixToRemove)) {
						throw new AssertionError("Unexpected search result: " + entry);
					}
					entryPath = entryPath.substring(prefixToRemove.length());
					System.out.println(entryPath);
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
			}),
			workspace.getRuleFactory().createRule(project),
			0,
			null
		);
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
	}
	
	@Override
	protected void after() {
		clear();
		super.after();
	}
	
	private void clear() {
		for (IProject i: workspace.getRoot().getProjects()) {
			try {
				i.delete(true,  true, null);
			} catch (CoreException e) {
				LOG.log(e.getStatus());
				throw new AssertionError(e);
			}
		}
	}
	private static final ILog LOG = getLog(ResourcesRule.class);

}
