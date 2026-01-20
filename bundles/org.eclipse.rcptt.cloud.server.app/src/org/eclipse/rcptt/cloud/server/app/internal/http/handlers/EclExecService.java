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
package org.eclipse.rcptt.cloud.server.app.internal.http.handlers;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.rcptt.cloud.server.app.internal.ServerAppPlugin;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.core.CoreFactory;
import org.eclipse.rcptt.ecl.core.ProcessStatus;
import org.eclipse.rcptt.ecl.core.util.ECLBinaryResourceImpl.EObjectInputStream;
import org.eclipse.rcptt.ecl.core.util.ECLBinaryResourceImpl.EObjectOutputStream;
import org.eclipse.rcptt.ecl.core.util.ECLBinaryResourceImpl.EObjectOutputStream.Check;
import org.eclipse.rcptt.ecl.internal.core.ProcessStatusConverter;
import org.eclipse.rcptt.ecl.runtime.EclRuntime;
import org.eclipse.rcptt.ecl.runtime.IProcess;
import org.eclipse.rcptt.ecl.runtime.ISession;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EclExecService extends HttpServlet {

	private static final long serialVersionUID = 5315841660939612772L;
	private final ProcessStatusConverter statusConverter = new ProcessStatusConverter();
	private final Map<String, Object> sessionProperties;
	
	public EclExecService(Map<String, Object> sessionProperties) {
		super();
		this.sessionProperties = Map.copyOf(sessionProperties);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		AsyncContext async = req.startAsync();
		async.setTimeout(Duration.of(7, ChronoUnit.DAYS).toMillis());
		async.start(() -> {
			try {
				ServletInputStream in = req.getInputStream();
				EObjectInputStream ein = new EObjectInputStream(in,
						new HashMap<Object, Object>());
				
				ServletOutputStream out = resp.getOutputStream();
				EObjectOutputStream eout = new EObjectOutputStream(out,
						new HashMap<Object, Object>());
				
				Object eobject = ein.loadEObject();
				if (!(eobject instanceof Command)) {
					error(eout, "Received entity is not a command.");
					return;
				}
				
				ISession session = EclRuntime.createSession();
				try {
					sessionProperties.forEach(session::putProperty);
					IProcess process = session.execute((Command) eobject);
					IStatus status = process.waitFor();
					
					if (!status.isOK()) {
						throw new CoreException(status);
					}
					
					write(eout, Status.OK_STATUS);
					
					InternalEList<InternalEObject> results = new BasicInternalEList<InternalEObject>(
							InternalEObject.class);
					
					while (true) {
						Object o = process.getOutput().take(1000);
						if (o == null) {
							break;
						}
						
						if (o instanceof IStatus) {
							results.add((InternalEObject) statusConverter
									.toEObject((IStatus) o));
							break;
						}
						
						if (o instanceof InternalEObject) {
							results.add((InternalEObject) o);
						}
					}
					
					eout.saveEObjects(results, Check.DIRECT_RESOURCE);
					
				} catch (CoreException e) {
					error(eout, e.getStatus());
				} catch (InterruptedException e) {
					error(eout, "Unable to complete the command.");
				}
			} catch (IOException e) {
				throw new IllegalStateException(e);
			} finally {
				async.complete();
			}
		});
		

	}

	private void error(EObjectOutputStream eout, String message)
			throws IOException {
		error(eout,
				new Status(IStatus.ERROR, ServerAppPlugin.PLUGIN_ID, message));
	}

	private void error(EObjectOutputStream eout, IStatus status)
			throws IOException {
		InternalEObject obj; 
		try {
			obj = (InternalEObject) statusConverter.toEObject(status);
		} catch (CoreException e) {
			try {
				obj = (InternalEObject) statusConverter.toEObject(e.getStatus());
			} catch (CoreException e1) {
				ProcessStatus rv = CoreFactory.eINSTANCE.createProcessStatus();
				rv.setMessage("Failed to convert error status");
				obj = (InternalEObject) rv;
			}
		}
		eout.saveEObject(obj,
				Check.DIRECT_RESOURCE);

		InternalEList<InternalEObject> result = new BasicInternalEList<InternalEObject>(
				InternalEObject.class);
		eout.saveEObjects(result, Check.DIRECT_RESOURCE);
	}

	private void write(EObjectOutputStream eout, IStatus status)
			throws IOException, CoreException {
		eout.saveEObject((InternalEObject) statusConverter.toEObject(status),
				Check.DIRECT_RESOURCE);
	}

}
