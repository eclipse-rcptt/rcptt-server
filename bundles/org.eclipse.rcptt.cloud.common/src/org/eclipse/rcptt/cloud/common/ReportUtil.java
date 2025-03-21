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
package org.eclipse.rcptt.cloud.common;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.rcptt.ecl.core.ProcessStatus;
import org.eclipse.rcptt.ecl.internal.core.ProcessStatusConverter;
import org.eclipse.rcptt.internal.core.RcpttPlugin;
import org.eclipse.rcptt.reporting.ItemKind;
import org.eclipse.rcptt.reporting.Q7Info;
import org.eclipse.rcptt.reporting.ReportingFactory;
import org.eclipse.rcptt.reporting.core.IQ7ReportConstants;
import org.eclipse.rcptt.reporting.core.ReportHelper;
import org.eclipse.rcptt.reporting.core.SimpleSeverity;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Node;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.Report;
import org.eclipse.rcptt.sherlock.core.model.sherlock.report.ReportFactory;

public class ReportUtil {

	public static String toString(Throwable e) {
		StringWriter writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer, false);
		e.printStackTrace(printWriter);
		printWriter.close();
		return writer.toString();
	}

	public static void verifyReport(Report report) {
		checkNotNull("Report is empty", report.getRoot());
		checkNotNull("Report is unnamed", report.getRoot().getName());
		Q7Info info = ReportHelper.getInfo(report.getRoot());
		checkNotNull("Report has no Q7Info", info);
		checkNotNull("Report has no test id", info.getId());
	}

	public static Report generateFailedReport(String scenarioId,
			String scenarioName, Throwable e) {
		return generateReport(scenarioId, scenarioName, RcpttPlugin.createStatus(e));
	}
	public static Report generateFailedReport(String scenarioId,
			String scenarioName, String message) {
		return generateReport(scenarioId, scenarioName, RcpttPlugin.createProcessStatus(IStatus.ERROR, message));
	}

	public static Report generateSkippedReport(String scenarioId,
			String scenarioName, String message) {
		return generateReport(scenarioId, scenarioName,
				RcpttPlugin.createProcessStatus(IStatus.CANCEL, message));
	}

	public static Report generateReport(String scenarioId, String scenarioName,
			IStatus status) {
		return generateReport(scenarioId, scenarioName, ProcessStatusConverter.toProcessStatus(status));
	}

	public static Report generateReport(String scenarioId, String scenarioName,
			ProcessStatus status) {

		Report report = ReportFactory.eINSTANCE.createReport();
		Node root = ReportFactory.eINSTANCE.createNode();
		root.setName(scenarioName);
		report.setRoot(root);
		Q7Info q7info = ReportingFactory.eINSTANCE.createQ7Info();
		q7info.setId(scenarioId);
		q7info.setResult(status);
		q7info.setTags("");
		q7info.setType(ItemKind.TESTCASE);
		root.getProperties().put(IQ7ReportConstants.ROOT, q7info);

		Q7Info scenarioInfo = EcoreUtil.copy(q7info);
		scenarioInfo.setType(ItemKind.SCRIPT);
		Node scenarioNode = ReportFactory.eINSTANCE.createNode();
		scenarioNode.setName(root.getName());
		scenarioNode.getProperties().put(IQ7ReportConstants.ROOT, scenarioInfo);
		root.getChildren().add(scenarioNode);

		return report;
	}

	public static SimpleSeverity getStatus(Report report) {
		Q7Info info = ReportHelper.getInfoOnly(report.getRoot());
		if (info == null)
			return SimpleSeverity.ERROR;
		return SimpleSeverity.create(info);
	}

}
