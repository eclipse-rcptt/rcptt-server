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

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class LaunchConfigBuilder {
	public LaunchConfigBuilder(Map<String, String> pathById)
			throws ParserConfigurationException {
		this.pathById = pathById;
		this.builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		this.agents = new ArrayList<String>();
		this.agentsfileList = new HashMap<String, List<String>>();
	}

	private final DocumentBuilder builder;
	private final Map<String, String> pathById;

	private final List<String> agents;
	private final Map<String, List<String>> agentsfileList;

	public void addLine(String agentName, String artifactId) {
		List<String> files = agentsfileList.get(agentName);
		if (files == null) {
			agents.add(agentName);
			files = new ArrayList<String>();
			agentsfileList.put(agentName, files);
		}
		if (pathById.containsKey(artifactId)) {
			files.add(pathById.get(artifactId));
		}
	}

	public List<String> getAgents() {
		return agents;
	}

	public void build(File file, String agentName) throws TransformerException {
		List<String> files = agentsfileList.get(agentName);
		if (files == null || files.size() == 0) {
			return;
		}
		List<String> types = new ArrayList<String>();
		for (int i = 0; i < files.size(); i++) {
			types.add("1");
		}

		Document doc = newDocument();
		Element root = createRoot(doc, "launchConfiguration");
		root.setAttribute("type", "com.xored.q7.launching.scenarios");
		setLaunchAttr(doc, root, "intAttribute",
				"com.xored.q7.launching.ECL_PORT_ATTR", "5378");
		setLaunchAttr(doc, root, "stringAttribute",
				"com.xored.q7.launching.HOST", "localhost");
		setLaunchAttr(doc, root, "booleanAttribute",
				"com.xored.q7.launching.INCLUDE_CONTEXT_ATTR", "false");
		setLaunchAttr(doc, root, "intAttribute",
				"com.xored.q7.launching.TESLA_PORT_ATTR", "7926");
		setLaunchAttr(doc, root, "booleanAttribute",
				"com.xored.q7.launching.NO_SORT", "true");

		setListAttr(doc, root, "org.eclipse.debug.core.MAPPED_RESOURCE_PATHS",
				files);
		setListAttr(doc, root, "org.eclipse.debug.core.MAPPED_RESOURCE_TYPES",
				types);

		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		StreamResult sr = new StreamResult(file);
		transformer.transform(source, sr);
	}

	private void setListAttr(Document doc, Element parent, String key,
			Collection<String> values) {
		Element list = doc.createElement("listAttribute");
		list.setAttribute("key", key);
		parent.appendChild(list);
		for (String val : values) {
			Element elem = doc.createElement("listEntry");
			elem.setAttribute("value", val);
			list.appendChild(elem);
		}
	}

	private void setLaunchAttr(Document doc, Element parent, String type,
			String key, String value) {
		Element child = doc.createElement(type);
		parent.appendChild(child);
		child.setAttribute("key", key);
		child.setAttribute("value", value);
	}

	private Element createRoot(Document doc, String name) {
		Element result = doc.createElement(name);
		doc.appendChild(result);
		return result;
	}

	private Document newDocument() {
		return builder.newDocument();
	}
}
