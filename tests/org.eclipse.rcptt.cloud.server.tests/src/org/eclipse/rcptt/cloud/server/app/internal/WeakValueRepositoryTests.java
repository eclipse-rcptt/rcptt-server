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

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.Closeable;
import java.io.IOException;
import java.lang.ref.Reference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BooleanSupplier;
import java.util.stream.Stream;

import org.eclipse.rcptt.cloud.server.app.internal.WeakValueRepository.Entry;
import org.eclipse.rcptt.cloud.server.app.internal.WeakValueRepository.Repository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.io.Closer;

public class WeakValueRepositoryTests {

	
	// Can't use Mockito mock, as it tends to hold references to all involved object, preventing GC cleanup
	private final Repository<String, Object> repository = new RepositoryMock();
	private static final Object VALUE = new Object();
	private static final Object BAD_VALUE = new Object();
	private static final int G = 1024 * 1024 * 1024;
	private final Closer closer = Closer.create();
	
	@After
	public void after() throws IOException {
		closer.close();
	}
	
	@Test
	public void respectCapacity()  {
		WeakValueRepository<String, Object> subject = new WeakValueRepository<String, Object>(repository, 3);
		subject.putIfAbsent("x", VALUE);
		try (var c = gc()) {
			assertStays(() -> repository.get("x").isPresent());	
		}
		
		noise(subject);
		try (var c = gc()) {
			assertEventually(() -> repository.get("x").isEmpty());
		}
	}
	
	@Test
	public void rechableEntriesAreAlwaysValidPut() {
		WeakValueRepository<String, Object> subject = new WeakValueRepository<String, Object>(repository, 3);
		Entry<Object> entry = subject.putIfAbsent("x", VALUE);
		try (var c = gc()) {
			assertStays(() -> {
				noise(subject);
				return repository.get("x").isPresent();
			});
			Reference.reachabilityFence(entry);
			entry = null;
			assertEventually(() -> {
				noise(subject);
				return repository.get("x").isEmpty();
			});
		}
	}
	
	@Test
	public void rechableEntriesAreAlwaysValidGet() {
		WeakValueRepository<String, Object> subject = new WeakValueRepository<String, Object>(repository, 3);
		subject.putIfAbsent("x", VALUE);
		Entry<Object> entry = subject.get("x").get();
		try (var c = gc()) {
			assertStays(() -> {
				noise(subject);
				return repository.get("x").isPresent();
			});
			Reference.reachabilityFence(entry);
			entry = null;
			assertEventually(() -> {
				noise(subject);
				return repository.get("x").isEmpty();
			});
		}
	}
	
	@Test
	public void revivedEntryIsAlwaysValid() {
		WeakValueRepository<String, Object> subject = new WeakValueRepository<String, Object>(repository, 3);
		subject.putIfAbsent("x", VALUE);
		try (var c = gc()) {
			assertStays(() -> {
				noise(subject);
				subject.get("x").ifPresent(e -> assertEquals(VALUE, e.contents()));
				noise(subject);
				assertEquals(VALUE,	subject.putIfAbsent("x", VALUE).contents());
				noise(subject);
				return true;
			});
		}
	}
	
	@Test
	public void doNotRemoveAnythingAfterDisposal() {
		WeakValueRepository<String, Object> subject = new WeakValueRepository<String, Object>(repository, 3);
		noise(subject);
		subject.close();
		subject = null;
		try (var c = gc()) {
			assertStays(() -> repository.oldestKeys().count() == 3);
		}
	}
	
	@Test(timeout = 10000)
	public void allowParallelAccess() throws InterruptedException {
		CountDownLatch startLatch = new CountDownLatch(1);
		CountDownLatch release = new CountDownLatch(1);
		try {
			RepositoryMock repository2 = new RepositoryMock() {
				@Override
				public Entry<Object> putIfAbsent(String key, Object input) {
					if (input == BAD_VALUE) {
						try {
							startLatch.countDown();
							release.await();
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
							throw new RuntimeException(e);
						}
					}
					return super.putIfAbsent(key, input);
				}
			};
			WeakValueRepository<String, Object> subject = new WeakValueRepository<String, Object>(repository2, 3);
			CompletableFuture.runAsync(() -> subject.putIfAbsent("1", BAD_VALUE));
			startLatch.await();
			long start2 = currentTimeMillis();
			subject.putIfAbsent("x", VALUE); // If "x" is in a different bucket than "1" this will not block
			assertEquals(VALUE, subject.get("x").get().contents());
			assertTrue(currentTimeMillis() - start2 < 100);
			var f = CompletableFuture.runAsync(() -> subject.putIfAbsent("1", VALUE)); // this blocks because "1" bucket is busy
			assertStays(() -> !f.isDone());
			release.countDown();
			assertEventually(()->f.isDone());
			f.join(); // rethrow
			assertEquals("First putIfAbsent wins", BAD_VALUE, subject.get("1").get().contents());
		} finally {
			release.countDown();
		}
	}
	
	@Test
	public void doNotStoreTooLargeEntries() {
		WeakValueRepository<String, Object> subject = new WeakValueRepository<String, Object>(repository, 3);
		long VERY_LARGE = 4L * 1024 * G;
		Assert.assertThrows(IllegalArgumentException.class, () -> {
			subject.putIfAbsent("x", VERY_LARGE);
		});
		assertFalse(repository.get("x").isPresent());
	}

	private void noise(WeakValueRepository<String, Object> subject) {
		subject.putIfAbsent("1", BAD_VALUE);
		subject.putIfAbsent("2", BAD_VALUE);
		subject.putIfAbsent("3", BAD_VALUE);
	}
	
	private static void assertStays(BooleanSupplier condition) {
		long stop = currentTimeMillis() + 1000;
		while (currentTimeMillis() < stop) {
			assertTrue(condition.getAsBoolean());
			Thread.yield();
		}
	}
	
	private static void assertEventually(BooleanSupplier condition) {
		long stop = currentTimeMillis() + 1000;
		while (currentTimeMillis() < stop) {
			if (condition.getAsBoolean()) {
				return;
			}
			Thread.yield();
		}
		fail();
	}

	private static class RepositoryMock implements Repository<String, Object> {
		private final Map<String, Object> delegate = Collections.synchronizedMap(new HashMap<>());

		@Override
		public void remove(String key) {
			delegate.remove(key);
		}

		@Override
		public Entry<Object> putIfAbsent(String key, Object input) {
			delegate.put(key, input);
			return new EntryImpl(key);
		}

		@Override
		public Stream<String> oldestKeys() {
			return delegate.keySet().stream();
		}

		@Override
		public Optional<Entry<Object>> get(String key) {
			return Optional.ofNullable(delegate.get(key)).<Entry<Object>>map(ignored -> new EntryImpl(key));
		}

		final class EntryImpl implements Entry<Object> {
			private final String key;
			public EntryImpl(String key) {
				this.key = key;
			}
			@Override
			public Object contents() {
				Object result = delegate.get(key);
				if (result == null) {
					throw new AssertionError("WeakValueRepository should not remove entries while they are reachable");
				}
				return result;
			}

			@Override
			public long size() {
				Object value = delegate.getOrDefault(key, 1L);
				if (value instanceof Long l) {
					return l;
				}
				return 1;
			}
		}
	}
	private interface UncheckedCloseable extends Closeable {
		@Override
		void close();
	}
	private UncheckedCloseable gc() {
		AtomicBoolean stop = new AtomicBoolean(false);
		@SuppressWarnings("resource")
		ForkJoinTask<Object> task = ForkJoinPool.commonPool().submit(() -> {
			System.out.println("Start gc");
			while (!stop.get()) {
				System.gc();
				Thread.sleep(1); // Thread.sleep() is necessary, Thread.yield() produces false negative in revivedEntryIsAlwaysValid()
			}
			System.out.println("Stop gc");
			return null;
		});
		return closer.register(() -> {
			stop.set(true);
			task.join();
		});
	}
}
