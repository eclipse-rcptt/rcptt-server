/*******************************************************************************
 * Copyright (c) 2026 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.cloud.server;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.rcptt.cloud.util.CheckedExceptionWrapper;
import org.eclipse.rcptt.reporting.util.IndexedExecutionReport;
import org.eclipse.rcptt.reporting.util.ReportEntry;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;

import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalNotification;

public class IndexedExecutionReports implements Closeable, IReports {
	private static final ILog LOG = Platform.getLog(IndexedExecutionReports.class);
	private final AtomicBoolean closed = new AtomicBoolean(false);
	private final LoadingCache<Path, IndexedExecutionReport> reports;
	{
		var loader = new CacheLoader<Path, IndexedExecutionReport>() {
			@Override
			public IndexedExecutionReport load(Path key) throws Exception {
				if (closed.get()) {
					throw new IllegalStateException("Closed");
				}
				return new IndexedExecutionReport(key);
			}
		};
		reports = CacheBuilder.newBuilder().expireAfterAccess(Duration.ofHours(1)).maximumSize(10).removalListener(this::onRemove).build(loader);
	}
	
	public void close(Path path) throws IOException {
		reports.asMap().keySet().removeIf(k -> k.startsWith(path));
	}
	
	@Override
	public void close() throws IOException {
		closed.set(true);
		reports.invalidateAll();
	}
	
	private void onRemove(RemovalNotification<Path, IndexedExecutionReport> notification) {
		try {
			notification.getValue().close();
		} catch (IOException e) {
			LOG.error("Can't close " + notification.getKey(), e);
		}
	}

	@SuppressWarnings("resource")
	@Override
	public Optional<Report> getReportById(Path archive, String id) throws IOException {
		try {
			return Optional.of(reports.get(archive).getById(id).getReport());
		} catch (NoSuchElementException e) {
			return Optional.empty();
		} catch (ExecutionException e) {
			if (e.getCause() instanceof FileNotFoundException) {
				return Optional.empty();
			}
			Throwables.throwIfInstanceOf(e.getCause(), IOException.class);
			throw new IOException(e);
		}
	}

	@SuppressWarnings("resource")
	@Override
	public Stream<ReportEntry> readReports(Path archive) {
		try {
			return reports.get(archive).read().map(t -> {
				try {
					return t.getEntry();
				} catch (IOException e) {
					throw new UncheckedIOException(e);
				}
			});
		} catch (ExecutionException e) {
			throw new CheckedExceptionWrapper((Exception) e.getCause());
		}
	}
}
