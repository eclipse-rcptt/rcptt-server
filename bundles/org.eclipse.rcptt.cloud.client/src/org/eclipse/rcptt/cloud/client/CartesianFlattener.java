/********************************************************************************
 * Copyright (c) 2025 Xored Software Inc and others
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

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

/** 
 *  Expands a sequence of symbols producing a sample for every combination of variants.
 *  Expands sequences of symbols including sub and variations into sequences of terminal entries.
 *  Each resulting sequence is annotated by names of expanded variations.
 * 
 **/ 
public final class CartesianFlattener {
	private final Map<String, Entry> entriesById = new HashMap<>();
	
	/** @param name a list of super selections - for each expanded records a name of an element expanded to **/ 
	public record NamedSequence(List<String> name, List<String> sequence) {
		
	}
	
	/** Group is expanded to a sequence of other entries **/
	public void putSequence(String id, String name, List<String> children) {
		Entry result = entriesById.putIfAbsent(id, new Sequence(name, children));
		if (result != null) {
			throw new IllegalStateException("Id " + id + " is registered multiple times");
		}
 	}
	
	/** Variant expands a sequence it appears in into multiple copies, where it is replaced with one of its children
	 *  For a given sequence, the resulting set is a Cartesian product of all of its discovered supers. 
	 **/
	public void putVariant(String id, String name, List<String> children) {
		Entry result = entriesById.putIfAbsent(id, new Super(name, children));
		if (result != null) {
			throw new IllegalStateException("Id " + id + " is registered multiple times");
		}
	}
	
	/** Trivial entry does not expand, it is a terminal symbol. **/
	public void putTerminal(String id, String name) {
		Entry result = entriesById.putIfAbsent(id, new Terminal(name, id));
		if (result != null) {
			throw new IllegalStateException("Id " + id + " is registered multiple times");
		}
	}
	
	/**
	 * @param ids - a sequence of symbols to expand
	 * @param origin - caller's name for error messages
	 * @return a sequence of terminal symbols, labeled with variant choice, if no variants are present, only one result is returned. 
	 */
	public Stream<NamedSequence> resolve(List<String> ids, String origin) {
		// TODO: add loop detection
		try {
			final List<Supplier<Stream<NamedSequence>>> list = ids.stream()
					.map(id -> requireNonNull(entriesById.get(id), origin + " is referencing an uresolved id " + id))
					.<Supplier<Stream<NamedSequence>>>map( e -> e::resolve).toList();
			return cartesian(list).map(sequences -> 
				new NamedSequence(
						sequences.stream().map(NamedSequence::name).flatMap(List::stream).toList(),
						sequences.stream().map(NamedSequence::sequence).flatMap(List::stream).toList()
				)
			);
		} catch (Exception e) {
			throw new IllegalStateException("Unable to resolve " + ids.toString() + " for " + origin, e);
		}
	}

	private abstract class Entry {
		public String name;
		public Entry(String name) {
			super();
			this.name = name;
		}
		abstract Stream<NamedSequence> resolve();
		@Override
		public String toString() {
			return name;
		}
	}
	
	private final class Terminal extends Entry {
		private final NamedSequence result;
		
		public Terminal(String name, String id) {
			super(name);
			this.result = new NamedSequence(List.of(), List.of(id));
		}

		@Override
		public java.util.stream.Stream<NamedSequence> resolve() {
			return Stream.of(result);
		}
	}


	private final class Super extends Entry {
		public final List<String> children;

		public Super(String name, List<String> children) {
			super(name);
			this.children = List.copyOf(children);
		}
		
		@Override
		public Stream<NamedSequence> resolve() {
			return children.stream().map(this::getById).flatMap(child -> 
				child.resolve().map(sequence -> new NamedSequence(withHead(child.name, sequence.name()), sequence.sequence()))
			);
		}
		
		public final Entry getById(String id) {
			Entry result = entriesById.get(id);
			if (result == null) {
				throw new IllegalStateException("Entry " + name + " is referencing an unresolved id: " + id);
			}
			return result;
		}

	}

	private final class Sequence extends Entry {
		public final List<String> children;

		public Sequence(String name, List<String> children) {
			super(name);
			this.children = List.copyOf(children);
		}
		
		@Override
		public java.util.stream.Stream<NamedSequence> resolve() {
			return CartesianFlattener.this.resolve(children, name);
		}
	}

	private static <T> Stream<List<T>>  cartesian(List<Supplier<Stream<T>>> streams) {
		if (streams.size() < 1) {
			return Stream.of(List.of());
		}
		if (streams.size() == 1) {
			return streams.getFirst().get().map(List::of);
		}
		List<Supplier<Stream<T>>> rest = streams.subList(1, streams.size());
		return streams.getFirst().get().flatMap(first -> cartesian(rest).map(t ->   withHead(first, t))  );
	}

	private static <T> List<T> withHead(T head, List<T> rest) {
		List<T> result = new ArrayList<>(rest.size() + 1);
		result.add(head);
		result.addAll(rest);
		return Collections.unmodifiableList(result);
	}

	
}
