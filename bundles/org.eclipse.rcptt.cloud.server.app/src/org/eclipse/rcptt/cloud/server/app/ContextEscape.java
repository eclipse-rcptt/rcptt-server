package org.eclipse.rcptt.cloud.server.app;

import org.eclipse.jetty.util.Attributes;
import org.eclipse.rcptt.cloud.server.ExecutionIndex;
import org.eclipse.rcptt.cloud.server.ExecutionRegistry;
import org.eclipse.rcptt.cloud.server.app.internal.http.Q7HttpServer;

public final class ContextEscape {
	private ContextEscape() {
	}
	
	public static ExecutionRegistry getExecutionRegistry(Attributes server) {
		return Q7HttpServer.getExecutionRegistry(server);
	}

	public static ExecutionIndex getExecutionIndex(Attributes server) {
		return Q7HttpServer.getExecutionIndex(server);
	}
}
