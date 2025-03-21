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
package com.xored.emfjson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

// Copied and adapted from  https://github.com/xored/synergy/blob/2c475ac8853d4d5bc3ece2247e3d5c0d35df3efa/emfjson/com.xored.emfjson/src/com/xored/emfjson/Emf2Json.java
public class Emf2Json {

	private final String CLASS_ATTRIBUTE = EClass.class.getName();
	private final Map<String, EClass> classNamesToEClass = new HashMap<String, EClass>();
	private final List<DefferedLinkInfo> defferedLinks = new ArrayList<Emf2Json.DefferedLinkInfo>();

	public Emf2Json(EPackage... packages) {
		for (EPackage ePackage : packages) {
			for (EClassifier classifier : ePackage.getEClassifiers()) {
				if (classifier instanceof EClass) {
					classNamesToEClass.put(getClassName((EClass) classifier),
							(EClass) classifier);
				}
			}
		}
	}

	public JsonObject serialize(EObject object) {
		return toJsonObject(object, true);
	}

	public JsonObject serialize(EObject object, boolean saveClassInfo) {
		return toJsonObject(object, saveClassInfo);
	}

	public JsonObject toJsonObject(EObject object, boolean saveClass) {

		EClass eClass = object.eClass();
		JsonObject jsonObject = new JsonObject();
		if (saveClass) {
			jsonObject.addProperty(CLASS_ATTRIBUTE, getClassName(eClass));
		}

		for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
			if (feature.isTransient()) {
				continue;
			}
			Object value = object.eGet(feature);

			if (feature.isMany()) {
				JsonArray jsonArray = new JsonArray();

				if (value != null) {
					List<?> list = (List<?>) value;
					for (Object item : list) {
						jsonArray.add(handleFeature(feature, item));
					}
					jsonObject.add(feature.getName(), jsonArray);
				}
			} else {
				jsonObject
						.add(feature.getName(), handleFeature(feature, value));
			}
		}
		return jsonObject;
	}

	private String getClassName(EClass eClass) {
		return eClass.getEPackage().getName() + "." + eClass.getName();
	}

	private static final JsonElement JSON_NULL = new JsonNull();

	private JsonElement handleFeature(EStructuralFeature feature, Object value) {
		if (feature instanceof EAttribute) {
			return adaptAttribute((EAttribute) feature, value);
		} else if (feature instanceof EReference) {
			if (value == null) {
				return JSON_NULL;
			}
			EReference ref = (EReference) feature;

			EObject eValue = (EObject) value;

			if (ref.isContainment()) {
				return toJsonObject(eValue,
						!eValue.eClass().equals(ref.getEReferenceType()));
			} else {
				return new JsonPrimitive(generateURI(eValue));
			}
		} else {
			return JSON_NULL;
		}
	}

	private String generateURI(EObject eValue) {
		List<PathLocation> path = new ArrayList<PathLocation>();
		generatePath(eValue, path);

		Collections.reverse(path);

		Iterator<PathLocation> it = path.iterator();

		StringBuilder link = new StringBuilder("@//");

		if (it.hasNext()) {
			link.append(it.next());
			while (it.hasNext()) {
				link.append("/").append(it.next());
			}
		}
		return link.toString();
	}

	private void generatePath(EObject eValue, List<PathLocation> path) {
		EReference ref = eValue.eContainmentFeature();
		EObject container = eValue.eContainer();
		if (ref != null) {
			int index;
			if (ref.isMany()) {
				index = ((EList<?>) container.eGet(ref)).indexOf(eValue);
			} else {
				index = 0;
			}
			EReference containmentFeature = eValue.eContainmentFeature();
			path.add(new PathLocation(containmentFeature.getName(), index));
			generatePath(container, path);
		}
	}

	private JsonElement adaptAttribute(EAttribute attr, Object value) {
		if (value == null) {
			return JSON_NULL;
		}

		EDataType dataType = attr.getEAttributeType();
		if (useStringConvertation(dataType)) {
			return new JsonPrimitive(EcoreUtil.convertToString(dataType, value));
		}
		if (value instanceof Boolean) {
			return new JsonPrimitive((Boolean) value);
		} else if (value instanceof Number) {
			return new JsonPrimitive((Number) value);
		} else if (value instanceof String) {
			return new JsonPrimitive((String) value);
		} else if (value instanceof Character) {
			return new JsonPrimitive((Character) value);
		} else
			return new JsonPrimitive(value.toString());
	}

	public EObject deserialize(JsonObject jsonObject) {
		return deserialize(jsonObject, (EClass) null);
	}

	public <T extends EObject> T deserialize(JsonObject jsonObject,
			Class<T> instanceClass) {
		EClass eClass = classNamesToEClass.get(instanceClass.getName());
		if (eClass == null) {
			throw new RuntimeException(
					String.format(
							"Can't find EMF class for instance class '%s'. Please register EMF package",
							instanceClass.getName()));
		}
		return instanceClass.cast(deserialize(jsonObject, eClass));
	}

	public EObject deserialize(JsonObject jsonObject, EClass eClass) {
		defferedLinks.clear();
		EObject result = fromJsonObject(jsonObject, eClass);
		setDefferedLinks(result);
		return result;
	}

	private EObject fromJsonObject(JsonObject jsonObject, EClass eClass) {
		if (jsonObject.has(CLASS_ATTRIBUTE)) {
			String explicitClassName = jsonObject.getAsJsonPrimitive(
					CLASS_ATTRIBUTE).getAsString();
			EClass eClassExplicit = classNamesToEClass.get(explicitClassName);
			if (eClassExplicit != null) {
				eClass = eClassExplicit;
			} else {
				if (eClass == null) {
					throw new RuntimeException(
							String.format(
									"Can't determinate explicit EClass '%s' for JSON object ",
									explicitClassName));
				} else {
					log("[WARNING] json.deserialize: JSON object has explicit eclass information '%s', but this class can not be found in any registred package.",
							explicitClassName);
				}

			}
		}
		if (eClass == null) {
			throw new RuntimeException(
					"EClass is null and JSON object has not contain explicit class info.");
		}
		EObject object = EcoreUtil.create(eClass);
		fromJsonObject(jsonObject, object);
		return object;
	}

	private void fromJsonObject(JsonObject jsonObject, EObject object) {

		EClass eClass = object.eClass();

		for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
			if (feature.isTransient()) {
				continue;
			}
			if (!jsonObject.has(feature.getName())) {
				continue;
			}
			if (isLink(feature)) {
				if (((EReference) feature).getEOpposite() == null) {
					defferLinkSet((EReference) feature, object,
							jsonObject.get(feature.getName()));
				} else {
					log("Skip not contaiment opposite " + feature);
				}
			} else {
				if (feature.isMany()) {
					@SuppressWarnings("unchecked")
					List<Object> list = (List<Object>) object.eGet(feature);
					JsonArray jsonArray = jsonObject.getAsJsonArray(feature
							.getName());
					for (Object jsonValue : jsonArray) {
						if (!jsonValue.equals(JSON_NULL)) { // TODO: is it
															// always JsonNull?
							list.add(handleJson(feature, jsonValue));
						}
					}
				} else {
					object.eSet(
							feature,
							handleJson(feature,
									jsonObject.get(feature.getName())));
				}
			}
		}
	}

	private void defferLinkSet(EReference reference, EObject eOject,
			Object value) {
		defferedLinks.add(new DefferedLinkInfo(reference, eOject, value));
	}

	private void setDefferedLinks(EObject rootObject) {
		Map<String, EObject> linkCache = new HashMap<>();

		for (DefferedLinkInfo di : defferedLinks) {

			if (di.value.equals(JSON_NULL)) { // TODO: is it always JsonNull?
				di.eOject.eSet(di.reference, null);
			} else if (di.value instanceof JsonPrimitive
					&& ((JsonPrimitive) di.value).isString()) {
				EObject toSet = linkCache.computeIfAbsent(((JsonPrimitive) di.value)
						.getAsString(), k -> resolveLink(rootObject, k));
				di.eOject.eSet(di.reference, toSet);
			} else if (di.value instanceof JsonArray) {
				JsonArray array = (JsonArray) di.value;
				if (!di.reference.isMany()) {
					throw new RuntimeException(
							String.format(
									"Can't handle reference link '%s'. Not multiplicity(isMany) reference for JsonArray",
									di.reference));
				}
				@SuppressWarnings("unchecked")
				List<Object> list = (List<Object>) di.eOject.eGet(di.reference);
				for (Object obj : array) {
					if (obj instanceof String) {
						list.add(linkCache.computeIfAbsent((String) obj, k -> resolveLink(rootObject, k)));
					} else {
						throwIllegalLinkType(di.value);
					}
				}
			} else {
				throwIllegalLinkType(di.value);
			}
		}
	}

	private void throwIllegalLinkType(Object value) {
		throw new RuntimeException(
				String.format(
						"Link for not contaiment feature should represented as string value. Can't parse link value '%s'",
						value));
	}

	private static EObject resolveLink(EObject rootObject, String strValue) {
				if (!strValue.startsWith("@//")) {
					throwIllegalURILink(strValue);
				}

				if (strValue.length() == 3) { // equals "@//"
					return rootObject;
				}

				strValue = strValue.substring(3); // remove "@//" substring

				try {
					String[] parts = strValue.split("\\/");

					EObject curObject = rootObject;

					for (String part : parts) {
						String[] split = part.split("\\.");
						String name = split[0];
						int index = Integer.valueOf(split[1]);

						EStructuralFeature feature = curObject.eClass()
								.getEStructuralFeature(name);

						if (feature == null) {
							throw new RuntimeException(
									"Can't find feature by name " + name);
						}

						if (!feature.isMany()) {
							curObject = (EObject) curObject.eGet(feature);
						} else {
							List<?> list = (List<?>) curObject.eGet(feature);
							curObject = (EObject) list.get(index);
						}
					}
					return curObject;
				} catch (Throwable th) {
					throw new RuntimeException(String.format(
							"Can't parse URI for link '%s'", strValue), th);
				}
			}

	private static void throwIllegalURILink(String strValue) {
		throw new RuntimeException("Ivalid URI link format " + strValue);
	}

	private boolean isLink(EStructuralFeature feature) {
		return feature instanceof EReference
				&& !((EReference) feature).isContainment();
	}

	private Object handleJson(EStructuralFeature feature, Object value) {
		if (feature instanceof EAttribute) {
			return adaptJson((EAttribute) feature, value);
		} else if (feature instanceof EReference) {
			if (value.equals(JSON_NULL)) { // TODO: is it always JsonNull?
				return null;
			}
			EReference ref = (EReference) feature;

			if (ref.isContainment()) {
				return fromJsonObject((JsonObject) value,
						ref.getEReferenceType());
			} else {
				// we already check by isLink()
				throw new AssertionError();
			}
		} else {
			return feature.getDefaultValue();
		}
	}

	private Object adaptJson(EAttribute attr, Object value) {
		if (value.equals(JSON_NULL)) { // TODO: is it always JsonNull?
			return null;
		}
		EDataType dataType = attr.getEAttributeType();
		if (useStringConvertation(dataType)) {
			return EcoreUtil.createFromString(dataType,
					((JsonPrimitive) value).getAsString());
		}
		return coerce(dataType.getInstanceClass(), value);
	}

	private boolean useStringConvertation(EDataType dataType) {
		return dataType instanceof EEnum
				|| dataType.equals(EcorePackage.Literals.EDATE);
	}

	private void log(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	private static Object coerce(Class<?> type, Object value) {
		if (value instanceof JsonPrimitive) {
			JsonPrimitive p = (JsonPrimitive) value;
			if (type == int.class || type == Integer.class) {
				return p.getAsInt();
			} else if (type == long.class || type == Long.class) {
				return p.getAsLong();
			} else if (type == short.class || type == Short.class) {
				return p.getAsShort();
			} else if (type == byte.class || type == Byte.class) {
				return p.getAsByte();
			} else if (type == float.class || type == Float.class) {
				return p.getAsFloat();
			} else if (type == double.class || type == Double.class) {
				return p.getAsDouble();
			} else if (type == boolean.class || type == Boolean.class) {
				return p.getAsBoolean();
			} else if (type == String.class) {
				return p.getAsString();
			} else if (type == char.class || type == Character.class) {
				return p.getAsCharacter();
			}
		}

		throw new RuntimeException(String.format("Illegal value: '%s'", value));
	}

	private static class PathLocation {
		private final String name;
		private final int index;

		public PathLocation(String name, int index) {
			this.name = name;
			this.index = index;
		}

		@Override
		public String toString() {
			return name + "." + index;
		}
	}

	private static class DefferedLinkInfo {
		private final EReference reference;
		private final EObject eOject;
		private final Object value;

		public DefferedLinkInfo(EReference reference, EObject eOject,
				Object value) {
			this.reference = reference;
			this.eOject = eOject;
			this.value = value;
		}
	}
}