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
package org.eclipse.rcptt.cloud.server.rest.stub;

import java.io.File;

import org.eclipse.rcptt.cloud.server.ism.internal.ISMHandle;
import org.eclipse.rcptt.cloud.server.ism.stats.Execution;
import org.eclipse.rcptt.cloud.server.ism.stats.StatsPackage;

public class InMemHandle extends ISMHandle<Execution> {
	private Node<Execution> node;

	public InMemHandle(Execution object) {
		super(File.listRoots()[0], StatsPackage.Literals.EXECUTION);
		this.node = new Node<Execution>();
		this.node.object = object;
	}

	@Override
	public synchronized Node<Execution> use() {
		return node;
	}

}
