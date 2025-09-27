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
package org.eclipse.rcptt.cloud.server.app.internal;

import java.lang.ref.Cleaner;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

final class ConcurrentWeakValueMap<K, V> {
	public ConcurrentWeakValueMap(Consumer<K> handleRemoval) {
		this.handleRemoval = Objects.requireNonNull(handleRemoval);
	}
	
	public V put(K key, V value) {
		WeakReference<V> ref = map.put(key, new WeakReference<V>(value));
		if (ref != null) {
			return ref.get();
		}
		return null;
	}
	
	public V computeIfAbsent(K key, Function<K, V> mapping) {
		AtomicReference<V> result = new AtomicReference<>();
		map.compute(key, (k, v) -> {
			if (v != null) {
				result.set(v.get());
				if (result.get() != null) {
					return v;
				}
			}
			result.set(mapping.apply(key));
			cleaner.register(result.get(), new Cleanup(k));
			return new WeakReference<V>(result.get());
		});
		return result.get();
	}
	
	/** Cancel cleanup of entries added so far */
	public void clear() {
		map.clear();
	}

	private final class Cleanup implements Runnable {
		private final K key;
		
		public Cleanup(K key) {
			this.key = Objects.requireNonNull(key);
		}

		@Override
		public void run() {
			map.computeIfPresent(key, (k, v) -> {
				if (v.refersTo(null)) {
					handleRemoval.accept(k);
					return null;
				}
				return v;
			});
		}
		
	}
	private final Consumer<K> handleRemoval;
	private final Map<K, WeakReference<V>> map = new ConcurrentHashMap<>();
	private final Cleaner cleaner = Cleaner.create();	
}
