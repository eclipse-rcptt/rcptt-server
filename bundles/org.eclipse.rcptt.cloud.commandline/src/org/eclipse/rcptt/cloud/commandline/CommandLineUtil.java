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
package org.eclipse.rcptt.cloud.commandline;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CommandLineUtil {

	public static void processCommandLine(Object obj, String[] cmd) throws InvalidCommandLineArgException {
		Map<Field, Arg> args = getArgs(obj.getClass());
		Map<Field, String[]> vals = parseArgs(cmd, args);
		setArgs(vals, obj);
	}

	public static void printUsage(Object obj) {
		printUsage(getArgs(obj.getClass()));
	}

	private static String argName(Entry<Field, Arg> entry) {
		return String.format("-%s",
				"".equals(entry.getValue().name()) ?
						entry.getKey().getName()
						: entry.getValue().name()
				);
	}
	static void printUsage(Map<Field, Arg> args) {
		TreeMap<String, Arg> sortedArgs = new TreeMap<String, Arg>();
		for(Entry<Field, Arg> entry : args.entrySet()) {
			sortedArgs.put(argName(entry), entry.getValue());
		}
		for(Map.Entry<String, Arg> entry : sortedArgs.entrySet()) {
			StringBuilder line = new StringBuilder();
			line.append("    ").append(entry.getKey());
			Arg arg = entry.getValue();
			if(!arg.isRequired()) {
				line.append(" (optional)");
			}
			line.append(" - ");
			line.append(arg.description());
			System.out.println(line);
		}
	}
	static Map<Field, Arg> getArgs(Class<? extends Object> clazz) {
		Map<Field, Arg> result = new HashMap<Field, Arg>();
		for(Field field : getFields(clazz)) {

			Arg arg = field.getAnnotation(Arg.class);
			if(arg != null) {
				result.put(field, arg);
			}
		}
		return result;
	}

	static void setArgs(Map<Field, String[]> valsByField, Object obj) throws IllegalArgumentException {
		try {
			for(Entry<Field, String[]> entry : valsByField.entrySet()) {
				Field field = entry.getKey();
				setField(field, entry.getValue(), obj);
			}
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}

	static void setField(Field field, String[] vals, Object obj) throws IllegalArgumentException, IllegalAccessException {
		if(vals.length > 1 && !field.getType().isArray()) {
			throw new IllegalArgumentException(String.format("Value count mismatched for field %s", field.getName()));
		}

		Class<?> fieldClazz = field.getType();
		Object val = null;
		if(fieldClazz.isArray()) {
			Class<?> elemClass = fieldClazz.getComponentType();
			val = Array.newInstance(elemClass, vals.length);
			for(int i = 0; i < vals.length; i++) {
				Array.set(val, i, convert(vals[i], elemClass));
			}
		} else {
			val = convert(vals[0], fieldClazz);
		}
		field.set(obj, val);
	}

	static Object convert(String val, Class<?> clazz) {
		if(clazz.equals(int.class)) {
			return Integer.parseInt(val);
		} else if(clazz.equals(String.class)) {
			return val;
		} else if(clazz.equals(Boolean.class) || clazz.getName().equals("boolean")) {
			return Boolean.parseBoolean(val);
		}
		throw new IllegalArgumentException(String.format("Class %s is not supported", clazz.getSimpleName()));
	}

	static Map<Field, String[]> parseArgs(String[] vals, Map<Field, Arg> args) throws InvalidCommandLineArgException {
		Map<Field, String[]> result = new HashMap<Field, String[]>();
		Map<String, Field> fieldsByArgName = new HashMap<String, Field>();
		List<Arg> unsetRequiredArgs = new ArrayList<Arg>();
		for(Entry<Field, Arg> entry : args.entrySet()) {
			Arg arg = entry.getValue();
			fieldsByArgName.put(argName(entry), entry.getKey());
			if(arg.isRequired()) {
				unsetRequiredArgs.add(arg);
			}
		}

		Walker<String> valIterator = new Walker<String>(vals);
		while(valIterator.hasNext()) {
			String name = valIterator.next();
			if(!fieldsByArgName.containsKey(name)) {
				throw new InvalidCommandLineArgException(String.format("Unknown arg %s", name), name);
			}

			Field field = fieldsByArgName.get(name);
			Arg arg = args.get(field);
			String[] argVals = readValues(valIterator, arg, fieldsByArgName.keySet());
			result.put(field, argVals);
			unsetRequiredArgs.remove(arg);
		}

		if(!unsetRequiredArgs.isEmpty()) {
			throw new IllegalArgumentException(String.format("Required arguments are not set - %s", unsetRequiredArgs));
		}
		return result;
	}

	private static String[] readValues(Walker<String> vals, Arg arg, Collection<String> argNames) {
		List<String> result = new ArrayList<String>();
		int count = arg.argCount();
		if(count == -1) {
			while(vals.hasNext()) {
				String nextVal = vals.next();
				if(argNames.contains(nextVal)) {
					//next arg started
					vals.back();
					return result.toArray(new String[result.size()]);
				}
				result.add(nextVal);
			}
			return result.toArray(new String[result.size()]);
		}

		while(count-- > 0 && vals.hasNext()) {
			result.add(vals.next());
		}
		if(count != -1) {
			throw new IllegalArgumentException(String.format("Not enough values for argument %s", arg.name()));
		}
		return result.toArray(new String[result.size()]);
	}
	private static Field[] getFields(Class<? extends Object> clazz) {
		List<Field> result = new ArrayList<Field>();
		result.addAll(Arrays.asList(clazz.getDeclaredFields()));
		if(!clazz.getSuperclass().equals(Object.class)) {
			result.addAll(Arrays.asList(getFields(clazz.getSuperclass())));
		}
		return result.toArray(new Field[result.size()]);
	}

	private static final class Walker<T>
	{
		T[] array;
		int index;
		Walker(T[] array) {
			this.array = array;
			reset();
		}

		void reset() {
			index = 0;
		}

		boolean hasNext() {
			return index < array.length;
		}

		T next() {
			return array[index++];
		}

		void back() { index--; }
	}
}
