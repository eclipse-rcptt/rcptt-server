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
package org.eclipse.rcptt.cloud.server.app.internal.http;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Supplier;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.http.MimeTypes.Mutable;
import org.eclipse.jetty.http.MimeTypes.Type;
import org.eclipse.jetty.server.ForwardedRequestCustomizer;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.CrossOriginHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.Attributes;
import org.eclipse.rcptt.cloud.server.ExecutionIndex;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;
import org.eclipse.rcptt.cloud.server.IServerContext;
import org.eclipse.rcptt.cloud.server.app.internal.HashedFileRepository;
import org.eclipse.rcptt.cloud.server.app.internal.ServerAppPlugin;
import org.eclipse.rcptt.cloud.server.app.internal.WeakValueRepository;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.AgentInfoHandler;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.ArtifactServlet;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.EclExecService;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.IndexHandler;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.Q7SessionUploadService;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.SessionLogsHandler;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.TerminateSessionHandle;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.TestSuitesHandler;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.stats.ExecutionByAgentsHandler;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.stats.ExecutionHandler;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.stats.SuiteHandler;
import org.eclipse.rcptt.cloud.server.app.internal.http.handlers.stats.TestHandler;
import org.eclipse.rcptt.cloud.server.ecl.impl.internal.GetHTTPServerInfoService;
import org.eclipse.rcptt.cloud.server.ism.ISMCore;
import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandleStore;
import org.eclipse.rcptt.cloud.server.ism.stats.SuiteStats;
import org.eclipse.rcptt.logging.Q7LoggingManager;

import com.google.common.hash.HashCode;

public class Q7HttpServer {
	private static final ILog LOG = Platform.getLog(Q7HttpServer.class);
	private static final Path cacheRoot = Path.of(ServerAppPlugin.getDefault().getStateLocation().toOSString()).resolve("cache");
	private final HashedFileRepository HASHED_REPOSITORY;
	{
		try {
			HASHED_REPOSITORY = new HashedFileRepository(cacheRoot);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private final Server server = new Server();
	private ServerConnector connector;
	private URI serverFileUriPrefix;
	private URI serverCacheUriPrefix;
	private final WeakValueRepository<String, InputStream> cache = new WeakValueRepository<String, InputStream>(HASHED_REPOSITORY);
	private final ExecutionRegistry executions = new ExecutionRegistry(new ExecutionRegistryRepositoryCacheAdapter());
	ExecutionIndex execIndex = new ExecutionIndex(null, executions);
	
	private final IServerContext serverContext = new IServerContext() {

		@Override
		public ExecutionRegistry getExecutionRegistry() {
			return executions;
		}

		@Override
		public URI toUri(File file) {
			return  serverFileUriPrefix.resolve(executions.makeRelativePath(file));
		}

		@Override
		public Optional<URI> toUri(byte[] hash, String name) {
			URI uri = serverCacheUriPrefix.resolve(HashCode.fromBytes(hash).toString()).resolve(name);
			return getDataByHash(hash).
					map(supplier -> {gcMonitor.put(uri, supplier); return uri; });
		}

		@Override
		public ExecutionIndex getExecutionIndex() {
			 return execIndex; 
		}

		private final WeakHashMap<Object, Object> gcMonitor = new WeakHashMap<>();
	};

	public void start(int httpPort, String sitesDir, int keepSessions, int keepAUTArtifacts, String hostname)
			throws IOException {
		URI serverUri = URI.create("server://" + hostname + ":" + httpPort);
		serverFileUriPrefix = URI.create(".");
		Map<String, Object> sessionProperties = Collections.singletonMap(IServerContext.ID, serverContext);
		sessionProperties.forEach(server::setAttribute);

		ISMHandleStore<SuiteStats> suiteStore = ISMCore.getInstance().getSuiteStore();
		MultiStatus status = new MultiStatus(getClass(), 0, "Previous run has been aborted");
		suiteStore.getHandles().stream().map(s -> executions.onStart(s)).forEach(status::add);
		if (!status.isOK()) {
			LOG.log(status);
		}
		executions.addNewSuiteHook(() -> {
			executions.removeOldExecutions(suiteStore.getHandles(), keepAUTArtifacts, keepSessions);
		});

		GetHTTPServerInfoService.setPort(httpPort);
		configureConnector(httpPort);
		server.setAttribute("jakarta.servlet.context.tempdir", executions.getTempDir().toString());
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

		context.setContextPath("/");
		context.addServlet(new Q7SessionUploadService(serverContext), "/api/upload");
		context.addServlet(new EclExecService(sessionProperties), "/api/exec");
		final ArtifactServlet.Repository repository = new ArtifactServlet.Repository() {
			@Override
			public void putIfAbsent(String hash, InputStream data) {
				cache.putIfAbsent(hash, data);
			}
			@Override
			public Optional<ArtifactServlet.Entry> get(String hash) {
				return cache.get(hash).map(ServletEntryForWeakValue::new);
			}
		};
		context.addServlet(new ArtifactServlet(repository), "/api/cache/*");
		serverCacheUriPrefix = serverUri.resolve("api/cache");

		setHandlers(sitesDir, context);

		try {
			server.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static ExecutionRegistry getExecutionRegistry(Attributes server) {
		return requireNonNull(((IServerContext) server.getAttribute(IServerContext.ID)).getExecutionRegistry());
	}
	
	public static ExecutionIndex getExecutionIndex(Attributes server2) {
		return requireNonNull(((IServerContext) server2.getAttribute(IServerContext.ID)).getExecutionIndex());
	}
	
	public IServerContext getContext() {
		return serverContext;
	}

	private final class ExecutionRegistryRepositoryCacheAdapter implements ExecutionRegistry.Repository {
		
		@Override
		public URI toServerUri(HashCode hash, String filename) {
			return serverContext.toUri(hash.asBytes(), filename).get();
		}
		
		@Override
		public Entry put(HashCode hash, InputStream data) {
			return cache.putIfAbsent(hash.toString(), data)::contents;
		}
		
		@Override
		public Optional<Entry> get(HashCode hash) {
			return cache.get(hash.toString()).map(e -> e::contents);
		}
		
	}

	private static final class ServletEntryForWeakValue implements ArtifactServlet.Entry {
		private final WeakValueRepository.Entry<InputStream> delegate; 

		public ServletEntryForWeakValue(WeakValueRepository.Entry<InputStream> delegate) {
			super();
			this.delegate = requireNonNull(delegate);
		}

		@Override
		public long size() {
			return delegate.size();
		}
		
		@Override
		public InputStream getContents() {
			return delegate.contents();
		}
	}
	
	private void configureConnector(int httpPort) {
		HttpConfiguration configuration = new HttpConfiguration();
		configuration.setOutputBufferSize(64 * 1024);
		configuration.setRequestHeaderSize(64 * 1024);
		configuration.setResponseHeaderSize(64 * 1024);
		configuration.addCustomizer(new ForwardedRequestCustomizer());
		connector = new ServerConnector(server, new HttpConnectionFactory(configuration));
		connector.setIdleTimeout(1000 * 60 * 60 * 24 * 7); // one week
		connector.setPort(httpPort);

		server.addConnector(connector);
	}

	private void setHandlers(String sitesDir, ServletContextHandler context) {
		List<Handler> allHandlers = loadExtensionHandlers();
		// https://jetty.org/docs/jetty/12/programming-guide/server/http.html#handler
		CrossOriginHandler crossOriginHandler = new CrossOriginHandler();
		crossOriginHandler.setAllowedOriginPatterns(Set.of(server.getURI().toString()));
		crossOriginHandler.setAllowCredentials(true);
		crossOriginHandler.setHandler(context);

		Handler[] builtinHandlers = new Handler[] {
				wrapInContext(IndexHandler.URI, new IndexHandler()),
				wrapInContext(SessionLogsHandler.URI, new SessionLogsHandler()),
				wrapInContext(AgentInfoHandler.URI, new AgentInfoHandler()),
				wrapInContext(TestHandler.URI, new TestHandler()),
				wrapInContext(ExecutionByAgentsHandler.URI,
						new ExecutionByAgentsHandler()),
				wrapInContext(ExecutionHandler.URI, new ExecutionHandler()),
				wrapInContext(SuiteHandler.URI, new SuiteHandler()),
				wrapInContext(TestSuitesHandler.URI, new TestSuitesHandler()),
				wrapInContext(TerminateSessionHandle.URI,
						new TerminateSessionHandle()),
				wrapInContext("/session/logs", initializeLogFileStore()),
				wrapInContext("/sites", initializeSitesStore(sitesDir)),
				wrapInContext("/artifacts", initializeArtifactsFileStore()),
				initializeRootFileStore(), crossOriginHandler, new DefaultHandler() };
		allHandlers.addAll(Arrays.asList(builtinHandlers));
		Handler.Sequence handlers = new Handler.Sequence(allHandlers);
		server.setHandler(handlers);
	}

	private static List<Handler> loadExtensionHandlers() {
		List<Handler> result = new ArrayList<>();
		for (Entry<String, Handler> entry : ServerAppPlugin.extensionHandlers()
				.entrySet()) {
			result.add(wrapInContext(entry.getKey(), entry.getValue()));
		}
		return result;
	}

	private static ResourceHandler initializeSitesStore(String sitesDir) {
		ResourceHandler rh = new ResourceHandler();
		rh.setDirAllowed(true);
		rh.setWelcomeFiles(new String[] { "index.html" });
		File dir = new File(sitesDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		rh.setBaseResourceAsString(dir.getAbsolutePath());
		return rh;
	}

	
	private static MimeTypes createMimeTypes() {
		Mutable rv = new MimeTypes.Mutable();
		rv.addMimeMapping("log", Type.TEXT_PLAIN_UTF_8.asString());
		return rv;
	}
	
	private static ResourceHandler initializeLogFileStore() {
		ResourceHandler resource_handler = new ResourceHandler();
		resource_handler.setDirAllowed(true);
		resource_handler.setWelcomeFiles(new String[] { "index.html" });
		resource_handler.setMimeTypes(createMimeTypes());
		resource_handler.setBaseResourceAsString(Q7LoggingManager.getManager()
				.getLogsFile().toOSString());
		return resource_handler;
	}

	private ResourceHandler initializeArtifactsFileStore() {
		ResourceHandler resource_handler = new ResourceHandler();
		resource_handler.setDirAllowed(true);
		resource_handler.setWelcomeFiles(new String[] { "index.html" });
		resource_handler.setBaseResourceAsString(executions.getRoot().toString());
		return resource_handler;
	}

	private static ResourceHandler initializeRootFileStore() {
		URL entry = ServerAppPlugin.getDefault().getBundle()
				.getEntry("webroot");
		try {
			URL url = FileLocator.resolve(entry);

			ResourceHandler resource_handler = new ResourceHandler();
			resource_handler.setDirAllowed(false);
			resource_handler.setWelcomeFiles(new String[] { "/" });
			resource_handler.setBaseResourceAsString(url.toString());
			return resource_handler;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Handler wrapInContext(String path, Handler handler) {
		ContextHandler context = new ContextHandler();
		context.setContextPath(path);
		context.setBaseResourceAsString(".");
		context.setClassLoader(Thread.currentThread().getContextClassLoader());
		context.setHandler(handler);
		return context;
	}

	public void stop() {
		try {
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Optional<Supplier<InputStream>> getDataByHash(byte[] hash) {
		String hashString = HashCode.fromBytes(hash).toString();
		return cache.get(hashString).map(e -> e::contents);
	}

}
