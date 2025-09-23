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

import static java.util.Objects.requireNonNull;

import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.eclipse.rcptt.cloud.common.Hash;
import org.eclipse.rcptt.cloud.server.app.internal.WeakValueRepository.Repository;

import com.google.common.base.Preconditions;
import com.google.common.hash.HashCode;
import com.google.common.util.concurrent.Striped;

public class HashedFileRepository implements Repository<String, InputStream> {
	private final Path hashedDir;
	private final Set<HashCode> validHashes = Collections.synchronizedSet(new HashSet<>());
	private final Striped<ReadWriteLock> locks = Striped.readWriteLock(1000);
	private final Path temporaryDir;

	public HashedFileRepository(Path root) throws IOException {
		super();
		this.hashedDir = root.resolve("hashed");
		this.temporaryDir = root.resolve("tmp");
		Files.createDirectories(this.hashedDir);
		Files.createDirectories(this.temporaryDir);
		try (DirectoryStream<Path> directory = Files.newDirectoryStream(this.temporaryDir)) {
			for (Path i : directory) {
				Files.delete(i);
			}
		}
	}

	@Override
	public Optional<WeakValueRepository.Entry<InputStream>> get(String hashString) {
		HashCode hash = HashCode.fromString(hashString);
		Lock lock = locks.get(hash).readLock();
		lock.lock();
		try {
			if (!validate(hash)) {
				return Optional.<WeakValueRepository.Entry<InputStream>>empty();
			}
			return Optional.of(new EntryImplementation(hash, lock));
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void putIfAbsent(String hashString, InputStream data) {
		Preconditions.checkArgument(!hashString.isEmpty(), "Hash can not be empty");
		HashCode hash = HashCode.fromString(hashString);
		assert hashString.equals(hash.toString());
		exclusively(hash, () -> {
			if (validate(hash)) {
				return null;
			}
			try {
				// Temporary files have to be on the same FS for efficient move
				Path file = Files.createTempFile(temporaryDir, hashString, "");
				try {
					MessageDigest md = Hash.createDigest();
					try (DigestInputStream is = new DigestInputStream(data, md);
							OutputStream os = Files.newOutputStream(file, StandardOpenOption.WRITE)) {
						is.transferTo(os);
					}

					String dataHash = toString(md);
					if (!hash.equals(dataHash)) {
						throw new IllegalArgumentException(
								String.format("Data hash %s does not match hash argument %s", dataHash, hash));
					}
					Files.move(file, hashedDir.resolve(hashString));
					validHashes.add(hash);
				} finally {
					if (Files.exists(file)) {
						Files.delete(file);
					}
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return null;
		});
	}

	@Override
	public void remove(String key) {
		HashCode hash = HashCode.fromString(key);
		exclusively(hash, () -> {
			validHashes.remove(hash);
			Path file = hashedDir.resolve(key);
			if (!Files.exists(file)) {
				return null;
			}
			try {
				Files.delete(file);
			} catch (FileNotFoundException e) {
				return null;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return null;
		});
	}

	/**
	 * Useful for LRU cache.
	 * @return keys in order of last access.
	 */
	@SuppressWarnings("resource")
	@Override
	public Stream<String> oldestKeys() {
		try {
			return Files.walk(hashedDir, 1).filter(p -> p.getNameCount() > hashedDir.getNameCount())
					.sorted(Comparator.comparing(t -> {
						try {
							return Files.getLastModifiedTime(t);
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					}))
					.map(Path::getFileName)
					.map(Path::toString)
					.filter(i -> get(i).isPresent());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private final class EntryImplementation implements WeakValueRepository.Entry<InputStream> {
		private final HashCode hash;
		private final Lock lock;

		private EntryImplementation(HashCode hash, Lock lock) {
			this.hash = hash;
			this.lock = lock;
		}

		@Override
		public InputStream contents() {
			lock.lock();
			try {
				if (!validate(hash)) {
					// If wrapped in WeakValueRepository this should not happen
					// It prevents removal of entries while they are reachable 
					throw new FileNotFoundException(hash.toString());
				}
				Path file = hashedDir.resolve(hash.toString());
				// See oldestKeys()
				Files.setLastModifiedTime(file, FileTime.fromMillis(System.currentTimeMillis()));
				// Reentrant lock is locked second time in LockingInputStream. This is intentional.
				// On one hand file should be open in a lock, on the other the lock should not be released until the result is closed.
				FilterInputStream result = new LockingInputStream(Files.newInputStream(file), lock);
				return result;
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				lock.unlock();
			}
		}

		@Override
		public long size() {
			lock.lock();
			try {
				if (!validate(hash)) {
					// If wrapped in WeakValueRepository this should not happen
					// It prevents removal of entries while they are reachable 
					throw new FileNotFoundException(hash.toString());
				}
				return Files.size(hashedDir.resolve(hash.toString()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				lock.unlock();
			}
		}
	}

	private static final class LockingInputStream extends FilterInputStream {
		private final Lock lock;
		public LockingInputStream(InputStream delegate, Lock lock) {
			super(delegate);
			this.lock = requireNonNull(lock);
			lock.lock();
		}
		
		@Override
		public void close() throws IOException {
			try {
				super.close();
			} finally {
				lock.unlock();
			}
		}
	}
	
	private boolean validate(HashCode hash) {
		Path file = hashedDir.resolve(hash.toString());
		try {
			if (validHashes.contains(hash)) {
				if (Files.exists(file)) {
					return true;
				}
			}
			if (!hash.equals(hash(file))) {
				Files.delete(file);
				return false;
			}
		} catch (IOException e) {
			if (!Files.exists(file)) {
				return false;
			}
			throw new IllegalStateException("Can't read " + file, e);
		}
		validHashes.add(hash);
		return true;
	}

	private HashCode hash(Path file) throws IOException {
		return HashCode.fromBytes(Hash.hash(file));
	}

	private <T> T exclusively(HashCode hash, Supplier<T> runnable) {
		Lock lock = locks.get(hash).writeLock();
		lock.lock();
		try {
			return runnable.get();
		} finally {
			lock.unlock();
		}
	}

	private String toString(MessageDigest md) {
		return HashCode.fromBytes(md.digest()).toString();
	}

}
