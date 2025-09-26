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

import static java.lang.Math.min;
import static java.lang.Math.toIntExact;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.UncheckedExecutionException;

/**
 * Removes a value from persistent storage when a given capacity is exceeded and handle is unreachable
 * 
 * @see <a href="https://softwareengineering.stackexchange.com/q/458786/106122">A passive LRU cache with locks</a>
 */
public final class WeakValueRepository<K, V> {
	public interface Entry<V> {
		V contents();
		long size();
	}
	public interface Repository<K, V> {
		Optional<Entry<V>> get(K key);
		Entry<V> putIfAbsent(K key, V input);
		void remove(K key);
		Stream<K> oldestKeys();
	}
	
	public WeakValueRepository(Repository<K, V> repository, long maxSize) {
		super();
		this.repository = repository;
		weakMap = new ConcurrentWeakValueMap<>(repository::remove);
		cache = CacheBuilder.newBuilder()
				.<K, Entry<V>>weigher((ignored, entry) -> toIntExact(min(Integer.MAX_VALUE, entry.size())))
				.maximumWeight(maxSize)
				.build();
		try (Stream<K> keys = repository.oldestKeys()) {
			// This will hash the whole cache causing slow startup 
			keys.parallel().forEachOrdered(this::get);
		}
	}

	public Entry<V> putIfAbsent(K key, V input) {
		checkClosed();
		try {
			Entry<V> result = cache.get(key, () -> {
				return weakMap.computeIfAbsent(key, k -> repository.putIfAbsent(k, input));
			});
			return result;
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	public Optional<Entry<V>> get(K key) {
		checkClosed();
		try {
			Optional<Entry<V>> result = Optional.of(cache.get(key, () -> {
				return weakMap.computeIfAbsent(key, k -> repository.get(key).orElseThrow());
			}));
			return result;
		} catch (UncheckedExecutionException | ExecutionException e) {
			if (e.getCause() instanceof NoSuchElementException) {
				return Optional.empty();
			}
			throw new RuntimeException(e);
		}
	}
	
	public void close() {
		closed.set(true);
		weakMap.clear();
	}

	private void checkClosed() {
		if (closed.get()) {
			throw new IllegalStateException("Storage is closed");
		}
	}

	private final ConcurrentWeakValueMap<K, Entry<V>> weakMap;
	private final Repository<K, V> repository;
	private final Cache<K, Entry<V>> cache;
	private final AtomicBoolean closed = new AtomicBoolean(false);
}
