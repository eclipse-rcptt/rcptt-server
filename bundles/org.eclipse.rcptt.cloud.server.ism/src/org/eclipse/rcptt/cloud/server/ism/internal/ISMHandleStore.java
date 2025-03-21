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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.rcptt.cloud.server.ism.stats.Execution;

public class ISMHandleStore<T extends EObject> {
	private File base;
	private Map<String, ISMHandle<T>> handles = new LinkedHashMap<String, ISMHandle<T>>();
	private EClass eclass;

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
		if (handles.containsKey(name)) {
			return handles.get(name);
		}
		File newHandleFile = new File(this.base, name);
		ISMHandle<T> newHandle = new ISMHandle<T>(newHandleFile, eclass);
		handles.put(name, newHandle);
		return newHandle;
	}

	public synchronized List<ISMHandle<T>> getHandles() {
		return new ArrayList<ISMHandle<T>>(handles.values());
	}

	public synchronized boolean containsHandle(String id) {
		return handles.containsKey(id);
	}

	public synchronized void remove(ISMHandle<Execution> ismHandle) {
		handles.remove(ismHandle);
	}
}
