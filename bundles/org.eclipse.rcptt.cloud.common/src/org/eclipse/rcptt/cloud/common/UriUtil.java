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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides utility methods for manipulating AUT URI
 * 
 * @author ivaninozemtsev
 * 
 */
public class UriUtil {
	/**
	 * Splits file name to base name and extension (with a dot) No extension
	 * gives empty string
	 * 
	 * @param fullName
	 * @return
	 */
	public static String[] nameAndExt(String fullName) {
		String extension = getFilenameExtension(fullName);
		int lastDotIndex = fullName.length() - extension.length();
		String baseName = fullName.substring(0, lastDotIndex);
		assert fullName.equals(baseName + extension) : fullName;
		return new String[] { baseName, extension };
	}

	private static String md5(String baseName) {
		return String.format("%s.md5", baseName);
	}

	public static String autZip(String url, String classifier) {
		if (!containsSubstitution(url)) {
			return url;
		}
		return substituteClassifierAndExt(url, classifier, "zip");
	}

	public static String autMd5(String url, String classifier) {
		if (!containsSubstitution(url)) {
			return md5(url);
		}
		String rv = substituteClassifierAndExt(url, classifier, "zip.md5");
		if (rv.contains("zip.md5"))
			return rv;
		return md5(rv);
	}

	private static String substituteClassifierAndExt(String url,
			String classifier, String ext) {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put(CLASSIFIER, classifier);
		vars.put(EXT, ext);
		return performSubstitution(url, vars);
	}

	private static final String CLASSIFIER = "classifier";
	private static final String EXT = "ext";
	private static final Pattern SUBST = Pattern.compile("\\[(\\w+)\\]");

	private static boolean containsSubstitution(String s) {
		return SUBST.matcher(s).find();
	}

	private static String performSubstitution(String input,
			Map<String, String> vars) {
		StringBuilder result = new StringBuilder();
		Matcher m = SUBST.matcher(input);
		int start = 0;
		while (m.find(start)) {
			result.append(input.substring(start, m.start()));
			String var = m.group(1);
			if (!vars.containsKey(var)) {
				CommonPlugin.warn(String.format(
						"Variable %s is not specified for substition of %s",
						var, input));
			} else {
				result.append(vars.get(var));
			}
			start = m.end();
		}

		if (start != input.length()) {
			result.append(input.substring(start));
		}

		return result.toString();
	}

	private static final Collection<String> INCOMPLETE_EXTENSIONS = List.of(".gz");

	public static String getFilenameExtension(String name) {
		for (String known: INCOMPLETE_EXTENSIONS) {
			if (name.endsWith(known)) {
				return getFilenameExtension(name.substring(0, name.length() - known.length())) + known;
			}
		}
		int index = name.lastIndexOf('.');
		if (index < 0) {
			return "";
		}
		return name.substring(index);
	}

}
