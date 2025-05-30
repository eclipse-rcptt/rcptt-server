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
package org.eclipse.rcptt.cloud.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.ConnectException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.core.ProcessStatus;
import org.eclipse.rcptt.ecl.core.util.ECLBinaryResourceImpl.EObjectInputStream;
import org.eclipse.rcptt.ecl.core.util.ECLBinaryResourceImpl.EObjectOutputStream;
import org.eclipse.rcptt.ecl.core.util.ECLBinaryResourceImpl.EObjectOutputStream.Check;
import org.eclipse.rcptt.ecl.internal.core.ProcessStatusConverter;

import org.eclipse.rcptt.cloud.util.internal.UtilPlugin;

public class HttpEclClient {

	private final ProcessStatusConverter statusConverter = new ProcessStatusConverter();

	private final HttpClient client;
	private final String url;

	public HttpEclClient(String url, HttpClient client) {
		this.client = client;
		this.url = url;
	}

	public ExecutionResult execute(Command command, int timeout)
			throws CoreException, ConnectException {
		HttpResponse response = null;
		byte[] bs = null;
		try {
			client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT,
					timeout);

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			EObjectOutputStream eout = new EObjectOutputStream(out,
					new HashMap<Object, Object>());

			eout.saveEObject((InternalEObject) command, Check.DIRECT_RESOURCE);

			HttpPost post = new HttpPost(url);

			ByteArrayEntity entity = new ByteArrayEntity(out.toByteArray());
			entity.setContentType("application/q7-eclcommand");
			post.setEntity(entity);

			response = client.execute(post);
			HttpEntity responseEntity = response.getEntity();

			InputStream responseContent = responseEntity.getContent();
			bs = IOUtil.getStreamContent(responseContent);
			EObjectInputStream ein = new EObjectInputStream(
					new ByteArrayInputStream(bs), new HashMap<Object, Object>());
			IStatus status = statusConverter.fromEObject((ProcessStatus) ein
					.loadEObject());
			InternalEList<InternalEObject> results = new BasicInternalEList<InternalEObject>(
					InternalEObject.class);
			ein.loadEObjects(results);

			EntityUtils.consume(responseEntity);

			return new ExecutionResult(status, results.toArray());
		} catch (ConnectException e) {
			throw e;
		} catch (Exception e) {
			UtilPlugin def = UtilPlugin.getDefault();
			if (def != null && def.getLog() != null) {
				def.getLog().log(
						UtilPlugin.createStatus("Response from: " + url
								+ "command: " + command.getClass().getName()
								+ (bs != null ? new String(bs) : ""), e));
			}
			throw UtilPlugin.createException(e.getMessage());
		}
	}

	public static class ExecutionResult {

		public final IStatus status;
		public final Object[] results;

		public ExecutionResult(IStatus status, Object[] results) {
			this.status = status;
			this.results = results;
		}
	}
}
