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
import static org.eclipse.core.runtime.Platform.getLog;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Stream;

import org.eclipse.core.runtime.ILog;
import org.eclipse.rcptt.cloud.util.CheckedExceptionWrapper;

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
		// This will hash the whole cache causing slow startup
		CompletableFuture.runAsync(() -> {
			try {
				try (Stream<K> keys = repository.oldestKeys()) {
					keys.takeWhile(ignored -> !closed.get()).forEachOrdered(this::get);
				}
			} catch (Exception e) {
				LOG.error("Failed to index offline artifact storage", e);
			}
		});
	}

	public Entry<V> putIfAbsent(K key, V input) {
		checkClosed();
		try {
			return computeCached(key, k -> repository.putIfAbsent(k, input));
		} catch (ExecutionException e) {
			throw new CheckedExceptionWrapper(e);
		}
	}

	public Optional<Entry<V>> get(K key) {
		checkClosed();
		try {
			return Optional.of(computeCached(key, k -> repository.get(key).orElseThrow()));
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
	
	private Entry<V> computeCached(K key, Function<K, Entry<V>> mapper) throws ExecutionException {
		return cache.get(key, () -> {
			return weakMap.computeIfAbsent(key, mapper);
		});
	}

	private static final ILog LOG = getLog(WeakValueRepository.class);
	private final ConcurrentWeakValueMap<K, Entry<V>> weakMap;
	private final Repository<K, V> repository;
	private final Cache<K, Entry<V>> cache;
	private final AtomicBoolean closed = new AtomicBoolean(false);
}
