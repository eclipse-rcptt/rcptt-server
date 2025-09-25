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
package org.eclipse.rcptt.cloud.client;

import static org.eclipse.core.runtime.Status.error;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URI;
import java.nio.file.Files;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.AbstractVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.SystemDefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.rcptt.cloud.common.UriUtil;
import org.eclipse.rcptt.cloud.util.CheckedExceptionWrapper;
import org.eclipse.rcptt.cloud.util.HttpEclClient;
import org.eclipse.rcptt.cloud.util.HttpEclClient.ExecutionResult;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.util.FileUtil;

import com.google.common.base.Preconditions;
import com.google.common.hash.HashCode;

public class Q7ServerApi {

	public static final int ONE_WEEK = 1000 * 60 * 60 * 24 * 7;
	public static final int DEFAULT_TIMEOUT = 60 * 1000;
	private static final String HASH_TYPE = "SHA-256";

	private URI url;

	private DefaultHttpClient client;
	private SSLSocketFactory socketFactory;
	private HttpEclClient eclClient;

	public Q7ServerApi(URI url) {
		this.url = url;
		this.client = new SystemDefaultHttpClient();
		ClientConnectionManager connManager = client.getConnectionManager();
		if (connManager instanceof PoolingClientConnectionManager) {
			((PoolingClientConnectionManager) connManager).setDefaultMaxPerRoute(8);
			((PoolingClientConnectionManager) connManager).setMaxTotal(32);
		}
		HttpParams params = this.client.getParams();
		// that is just for reading, in fact
		params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, DEFAULT_TIMEOUT);
		params.setIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE,
				16 * 1024);
		params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
				30 * 1000);

		this.client.setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
			public long getKeepAliveDuration(HttpResponse arg0, HttpContext arg1) {
				return ONE_WEEK;
			}
		});

		try {
			this.socketFactory = new SSLSocketFactory(new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					return true;
				}
			}, new AbstractVerifier() {

				public void verify(String host, String[] cns,
						String[] subjectAlts) throws SSLException {
					if (host.equals("localhost") || host.equals("xored.com")
							|| host.endsWith(".xored.com")) {
						return;
					}
					super.verify(host, cns, subjectAlts, true);
				}

			});
		} catch (Exception e) {
			throw new CheckedExceptionWrapper(e);
		}

		client.getConnectionManager().getSchemeRegistry()
				.register(new Scheme("https", 443, socketFactory));

		eclClient = new HttpEclClient(url + "api/exec", client);
	}

	public HttpPost makePost(String subUrl) {
		return new HttpPost(url.resolve(subUrl));
	}

	public HttpGet makeGet(String subUrl) {
		return new HttpGet(url.resolve(subUrl));
	}
	
	public HttpResponse execute(HttpPost post) throws ClientProtocolException,
			IOException {
		return client.execute(post);
	}

	public HttpResponse execute(HttpGet get) throws ClientProtocolException,
			IOException {
		return client.execute(get);
	}

	public record UploadResult(byte[] hash, URI serverPath) {} 
	public UploadResult uploadHashedFile(java.nio.file.Path file) throws CoreException {
		client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT,
				DEFAULT_TIMEOUT);
		try {
			MessageDigest md = MessageDigest.getInstance(HASH_TYPE);
			try (DigestInputStream is = new DigestInputStream(Files.newInputStream(file), md);
					OutputStream os = OutputStream.nullOutputStream()) {
				is.transferTo(os);
			}
			byte[] byteHash = md.digest();
			String hash = HashCode.fromBytes(byteHash).toString();
			URI uri = URI.create("api/cache/" + hash + "/" + file.getFileName().toString());
			
			HttpPut request = new HttpPut(url.resolve(uri));
			request.setEntity(new FileEntity(file.toFile(), ContentType.APPLICATION_OCTET_STREAM));
			request.addHeader("Expect", "100-continue");
			
			try (CloseableHttpResponse response = client.execute(request)) {
				int status = response.getStatusLine().getStatusCode();
				if (status == HttpStatus.SC_OK || status == HttpStatus.SC_CONFLICT) {
					return new UploadResult(byteHash, uri);
				}
				throw new CoreException(error("Failed to upload " + file + ". HTTP status code: " + status));
			}
		} catch (Exception e) {
			throw new CoreException(error("Failed to upload " + file, e));
		}
	}
	
	
	public String uploadFile(String suiteID, String zipPath,
			String artifactName, boolean unzip) throws CoreException {
		client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT,
				DEFAULT_TIMEOUT);

		Path path = new Path(artifactName);
		String fileName = FileUtil.getID(path.lastSegment()) + UriUtil.getFilenameExtension(path.lastSegment());

		try {
			HttpPost post = makePost("api/upload");

			post.setEntity(new FileEntity(new File(zipPath), ContentType.
					create("application/q7-filedata")));
			post.addHeader("unzip", unzip ? "true" : "false");
			post.addHeader("filename", fileName);
			post.addHeader("suiteid", suiteID);

			HttpResponse response = execute(post);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new CoreException(ClientAppPlugin.createErrorStatus(
						response.getStatusLine().toString(), null));
			}

			HttpEntity resEntity = response.getEntity();

			if (resEntity != null) {
				return EntityUtils.toString(resEntity);
			} else {
				throw new CoreException(ClientAppPlugin.createErrorStatus(
						"null response received", null));
			}

		} catch (Exception e) {
			throw new CoreException(ClientAppPlugin.createErrorStatus(
					"AUT upload failed", e));
		}
	}

	public void downloadFile(URI path, File toFile) throws CoreException {
		Preconditions.checkNotNull(path);
		client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT,
				DEFAULT_TIMEOUT);

		try {
			HttpGet post = makeGet("/artifacts/" + path);

			HttpResponse response = execute(post);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new CoreException(ClientAppPlugin.createErrorStatus(
						response.getStatusLine().toString(), null));
			}

			HttpEntity resEntity = response.getEntity();

			if (resEntity != null) {
				InputStream content = resEntity.getContent();
				FileUtil.copy(content, new BufferedOutputStream(
						new FileOutputStream(toFile)));
			} else {
				throw new CoreException(ClientAppPlugin.createErrorStatus(
						"null response received", null));
			}

		} catch (Exception e) {
			throw new CoreException(ClientAppPlugin.createErrorStatus(
					"Failed to downoad file: " + path, e));
		}
	}

	/**
	 * @return URI pointing to resource on server relative to resource root 
	 */
	public URI uploadDataAsFile(String suiteID, byte[] data,
			String artifactName, boolean unzip) throws CoreException {
		client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT,
				DEFAULT_TIMEOUT);

		String fileName = FileUtil.getID(new Path(artifactName).lastSegment());

		try {
			HttpPost post = makePost("api/upload");

			ByteArrayEntity entity = new ByteArrayEntity(data);
			entity.setContentType("application/q7-filedata");
			post.setEntity(entity);
			post.addHeader("unzip", unzip ? "true" : "false");
			post.addHeader("filename", fileName);
			post.addHeader("suiteid", suiteID);

			HttpEntity resEntity = execute(post).getEntity();

			if (resEntity != null) {
				String serverUri = EntityUtils.toString(resEntity);
				return URI.create(serverUri);
			} else {
				throw new CoreException(ClientAppPlugin.createErrorStatus(
						"null response received", null));
			}

		} catch (Exception e) {
			throw new CoreException(ClientAppPlugin.createErrorStatus(
					"AUT upload failed", e));
		}
	}

	public ExecutionResult execute(Command command) throws CoreException, ConnectException {
		return eclClient.execute(command, DEFAULT_TIMEOUT);
	}

	public ExecutionResult execute(Command command, int timeout)
			throws CoreException, ConnectException {
		return eclClient.execute(command, timeout);
	}

}
