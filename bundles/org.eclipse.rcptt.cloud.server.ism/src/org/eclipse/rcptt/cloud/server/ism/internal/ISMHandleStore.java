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
package org.eclipse.rcptt.cloud.server.ism.internal;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class ISMHandleStore<T extends EObject> {
	private final File base;
	private final Map<String, ISMHandle<T>> handles = new LinkedHashMap<String, ISMHandle<T>>();
	private final EClass eclass;

	public ISMHandleStore(File baseDir, EClass eclass) {
		this.base = baseDir;
		this.base.mkdirs();
		this.eclass = eclass;
		loadContent();
	}

	public File getBase() {
		return base;
	}

	private synchronized void loadContent() {
		File[] files = this.base.listFiles();
		Arrays.sort(files);
		for (File file : files) {
			if (file.isDirectory() && !file.getName().startsWith(".")) {
				if (!handles.containsKey(file.getName())) {
					handles.put(file.getName(), new ISMHandle<T>(file, eclass));
				}
			}
		}
	}

	public synchronized ISMHandle<T> getHandle(String name) {
		return handles.computeIfAbsent(name, name2 -> 
			new ISMHandle<T>(new File(this.base, name2), eclass)
		);
	}

	public synchronized List<ISMHandle<T>> getHandles() {
		return handles.values().stream().filter(ISMHandle::exists).collect(Collectors.toUnmodifiableList());
	}

	public synchronized boolean containsHandle(String id) {
		return handles.containsKey(id);
	}
}
