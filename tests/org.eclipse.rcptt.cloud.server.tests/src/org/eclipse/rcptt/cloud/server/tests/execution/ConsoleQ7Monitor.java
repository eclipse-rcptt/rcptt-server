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
package org.eclipse.rcptt.cloud.server.tests.execution;

import java.io.File;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.rcptt.logging.IQ7Monitor;

public class ConsoleQ7Monitor implements IQ7Monitor {


	@Override
	public void log(String message, Throwable e) {
		System.out.println(message);
		if (e != null) {
			e.printStackTrace();
		}
	}

	@Override
	public void log(String message) {
		log(message, null);
	}


	@Override
	public String getId() {
		return "console";
	}


	@Override
	public String getPrefix() {
		return "console";
	}


	@Override
	public void close() {
	}


	@Override
	public void putChild(IQ7Monitor result) {
	}


	@Override
	public String getFile() {
		return null;
	}


	@Override
	public File getRootFolder() {
		return null;
	}

	@Override
	public void addListener(IQ7LogListener listener) {

	}

	@Override
	public void removeListener(IQ7LogListener listener) {

	}

	@Override
	public void log(IStatus status) {
		log(status.toString());
	}
}
