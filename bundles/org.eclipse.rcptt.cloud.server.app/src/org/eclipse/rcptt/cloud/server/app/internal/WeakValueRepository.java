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

import java.lang.ref.Cleaner;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalNotification;
import com.google.common.util.concurrent.UncheckedExecutionException;

/**
 * Prevents removal of an element from a repository while its handle is still reachable.
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
	
	public WeakValueRepository(Repository<K, V> repository) {
		super();
		this.repository = repository;
		try (Stream<K> keys = repository.oldestKeys()) {
			// This will hash the whole cache causing slow startup 
			keys.parallel().forEachOrdered(this::get);
		}
	}

	public Optional<Entry<V>> get(K key) {
		try {
			Optional<Entry<V>> result = Optional.of(cache.get(key, () -> repository.get(key).orElseThrow()));
			return result;
		} catch (UncheckedExecutionException | ExecutionException e) {
			if (e.getCause() instanceof NoSuchElementException) {
				return Optional.empty();
			}
			throw new RuntimeException(e);
		}
	}

	public Entry<V> putIfAbsent(K key, V input) {
		try {
			return cache.get(key, () -> repository.putIfAbsent(key, input));
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	private void onRemove(RemovalNotification<K, Entry<V>> notification) {
		assert notification.getKey() != null;
		assert notification.getValue() != null;
		K key = notification.getKey();
		cleaner.register(notification.getValue(), () -> {
			if (cache.getIfPresent(key) == null) {
				// TODO: fix race with get()
				// The following line may remove the entry after it has been returned by a query
				repository.remove(key);
			}
		});
	}

	
	private final Cleaner cleaner = Cleaner.create();
		
	private final Repository<K, V> repository;
	private final Cache<K, Entry<V>> cache = CacheBuilder.newBuilder()
			.removalListener(this::onRemove)
			.weigher((ignored, entry) -> toIntExact(min(Integer.MAX_VALUE, entry.size())))
			.maximumWeight(Long.getLong(getClass().getName() + ".cache_bytes",  10_000_000_000L))
			.build();
}
