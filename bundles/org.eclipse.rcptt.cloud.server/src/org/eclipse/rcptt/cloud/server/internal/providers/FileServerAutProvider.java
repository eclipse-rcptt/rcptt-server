package org.eclipse.rcptt.cloud.server.internal.providers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.eclipse.rcptt.cloud.common.UriUtil;
import org.eclipse.rcptt.cloud.model.AutInfo;
import org.eclipse.rcptt.cloud.util.IOUtil.HttpSrc;
import org.eclipse.rcptt.cloud.util.IOUtil.ISrcFactory;
import org.eclipse.rcptt.util.Base64;


public class FileServerAutProvider extends BaseServerAutProvider {

	private static final String SCHEME_HTTP = "http";
	private static final String SCHEME_HTTPS = "https";


	public boolean isSupported(AutInfo aut) {
		return isSupported(aut, "file");
	}

	private boolean isSupported(AutInfo aut, String scheme) {
		try {
			return aut.getUri().startsWith(scheme + ":/");
		} catch (Exception ex) {
			return false;
		}
	}


	@Override
	protected ISrcFactory getMd5SourceFactory(AutInfo aut, String classifier) {
		String uri = UriUtil.autZip(aut.getUri(), classifier);
		return new ISrcFactory() {
			
			@Override
			public InputStream reopen() throws IOException {
				Path path;
				try {
					path = Path.of(URI.create(uri));
				} catch (IllegalArgumentException e) {
					throw new IllegalArgumentException(uri, e);
				}
				MessageDigest md = null;
				try {
					md = MessageDigest.getInstance("md5");
				} catch (NoSuchAlgorithmException e) {
					throw new IllegalStateException(e);
				}
				try (InputStream is = Files.newInputStream(path) ) {
					byte[] buffer = new byte[1_000_000];
					for(int length = is.read(buffer); length >=0; length = is.read(buffer)) {
						md.digest(buffer, 0, length);
					}
				} catch (DigestException e) {
					throw new IllegalStateException(e);
				}
				byte[] result = Base64.encode(md.digest()).getBytes();
				return new ByteArrayInputStream(result);
			}
			
			@Override
			public void done() {
				// all resources are closed in reopen()
			}
			
			@Override
			public String toString() {
				return uri;
			}
		};
	}


	@Override
	protected ISrcFactory getZipSourceFactory(AutInfo aut, String classifier) {
		String uri = UriUtil.autZip(aut.getUri(), classifier);
		return new ISrcFactory() {
			
			@Override
			public InputStream reopen() throws IOException {
				return Files.newInputStream(Path.of(URI.create(uri)));
			}
			
			@Override
			public void done() {
				// org.eclipse.rcptt.cloud.util.IOUtil.downloadSafe(ISrcFactory, IDstFactory, DownloadOptions, IProgressMonitor, MessageDigest, IDownloadMonitor) should close result of reopen()	
			}
			
			@Override
			public String toString() {
				return uri;
			}
		};
	}

}
