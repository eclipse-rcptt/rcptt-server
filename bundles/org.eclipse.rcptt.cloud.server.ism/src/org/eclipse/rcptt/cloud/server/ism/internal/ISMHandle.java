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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.rcptt.util.FileUtil;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xored.emfjson.Emf2Json;

public class ISMHandle<T extends EObject> {
	private final File file;
	private final EClass eclass;

	protected static class Node<T> {
		public Node() {
		}

		public Resource resource = null;
		public T object = null;
	}

	private WeakReference<Node<T>> weakNode = null;

	public ISMHandle(File file, EClass eclass) {
		this.file = file;
		this.eclass = eclass;
		file.mkdirs();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eclass == null) ? 0 : eclass.hashCode());
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ISMHandle<?> other = (ISMHandle<?>) obj;
		if (eclass == null) {
			if (other.eclass != null) {
				return false;
			}
		} else if (!eclass.equals(other.eclass)) {
			return false;
		}
		if (file == null) {
			if (other.file != null) {
				return false;
			}
		} else if (!file.equals(other.file)) {
			return false;
		}
		return true;
	}

	private ReadWriteLock rwLock = new ReentrantReadWriteLock();

	public <R> R apply(Function<T, R> handle) {
		rwLock.readLock().lock();
		try {
			Node<T> used = use();
			return handle.apply(used.object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			rwLock.readLock().unlock();
		}
	}

	public <R> R commit(Function<T, R> handle) {
		rwLock.writeLock().lock();
		Node<T> used = null;
		try {
			used = use();
			R result = handle.apply(used.object);
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (used != null) {
				save(used);
			}
			rwLock.writeLock().unlock();
		}
	}

	@SuppressWarnings("unchecked")
	protected synchronized Node<T> use() {
		Node<T> node = null;
		if (weakNode != null) {
			node = weakNode.get();
		}
		if (node == null) {
			node = new Node<T>();
			node.resource = new XMIResourceImpl(
					URI.createFileURI(getContentFile().getAbsolutePath()));
			if (getContentFile().exists()) {
				loadFromFile(node);
			}
		}

		if (node.object == null) {
			node.object = (T) eclass.getEPackage().getEFactoryInstance()
					.create(eclass);
			node.resource.getContents().add(node.object);
			node.resource.setTrackingModification(true);
			node.resource.setModified(false);
		}
		weakNode = new WeakReference<ISMHandle.Node<T>>(node);
		return node;
	}

	private File getContentFile() {
		return new File(file, ".content.json");
	}

	@SuppressWarnings("unchecked")
	private synchronized void loadFromFile(Node<T> node) {
		try {
			Emf2Json emfjson = new Emf2Json(eclass.getEPackage());

			byte[] bytes = FileUtil.getStreamContent(new BufferedInputStream(
					new FileInputStream(getContentFile())));

			JsonElement element = new JsonParser().parse(new String(bytes));
			if (element.isJsonObject()) {
				EObject deserialize = emfjson.deserialize(element
						.getAsJsonObject());
				if (deserialize != null && eclass.isInstance(deserialize)) {
					node.object = (T) deserialize;
				}
			}
			if (node.object != null) {
				node.resource.getContents().clear();
				node.resource.getContents().add(node.object);
			}
			node.resource.setTrackingModification(true);
			node.resource.setModified(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized boolean hasChanges(Node<T> node) {
		if (node == null || node.object == null) {
			return false;
		}
		if (node.resource == null) {
			return true;
		}
		return node.resource.isModified();
	}

	private synchronized void save(Node<T> node) {
		weakNode = new WeakReference<ISMHandle.Node<T>>(node);
		if (hasChanges(node)) {
			Emf2Json emfjson = new Emf2Json(node.object.eClass()
					.getEPackage());
			JsonObject jsonObject = emfjson.serialize(node.object);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String content = gson.toJson(jsonObject);

			File contentFile = getContentFile();
			contentFile.getParentFile().mkdirs();
			try {
				Files.write(content, contentFile, Charsets.UTF_8);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public String getFileName() {
		return file.getName();
	}

	public synchronized boolean exists() {
		Node<T> node = null;
		if (weakNode != null) {
			node = weakNode.get();
		}
		if (node != null && node.object != null) {
			return true;
		}
		if (file.exists()) {
			return true;
		}
		return false;
	}

	public File getFileRoot() {
		return file;
	}

	public void remove() {
		FileUtil.deleteFiles(file.listFiles());
		file.delete();
	}
}
