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

/** Expands sequences of symbols including groups and variations into sequences of terminal entries. Each resulting sequence is annotated by names of expanded variations. **/ 
public final class CartesianFlattener {
	private final Map<String, Entry> entriesById = new HashMap<>();
	
	public record NamedSequence(List<String> name, List<String> sequence) {
		
	}
	
	/** Group is expanded to a sequence of other entries **/
	public void putGroup(String id, String name, List<String> children) {
		Entry result = entriesById.putIfAbsent(id, new Group(name, children));
		if (result != null) {
			throw new IllegalStateException("Id " + id + " is registered multiple times");
		}
 	}
	
	/** Super expand a sequence it appears in into multiple copies, where it is replaced with one of its children
	 *  For a given sequence, the resulting set is a Cartesian product of all of its discovered supers. 
	 **/
	public void putSuper(String id, String name, List<String> children) {
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
	
	public Stream<NamedSequence> resolve(List<String> ids, String origin) {
		// TODO: add loop detection
		final List<Supplier<Stream<NamedSequence>>> list = ids.stream()
				.map(id -> requireNonNull(entriesById.get(id), origin + " is referencing an uresolved id " + id))
				.<Supplier<Stream<NamedSequence>>>map( e -> e::resolve).toList();
		return cartesianProduct(list).map(sequences -> 
			new NamedSequence(
					sequences.stream().map(NamedSequence::name).flatMap(List::stream).toList(),
					sequences.stream().map(NamedSequence::sequence).flatMap(List::stream).toList()
			)
		);
	}

	private abstract class Entry {
		public String name;
		public Entry(String name) {
			super();
			this.name = name;
		}
		abstract Stream<NamedSequence> resolve();
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
		public java.util.stream.Stream<NamedSequence> resolve() {
			//TODO add a better error message instead of ClassCastException
			return children.stream().map(this::getById).flatMap(child -> 
				child.resolve().map(sequence -> new NamedSequence(prepend(child.name, sequence.name()), sequence.sequence()))
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

	private final class Group extends Entry {
		public final List<String> children;

		public Group(String name, List<String> children) {
			super(name);
			this.children = List.copyOf(children);
		}
		
		@Override
		public java.util.stream.Stream<NamedSequence> resolve() {
			return CartesianFlattener.this.resolve(children, name);
		}
	}

	private <T> Stream<List<T>>  cartesianProduct(List<Supplier<Stream<T>>> input) {
		if (input.size() < 1) {
			throw new IllegalArgumentException(""+input.size());
		}
		if (input.size() == 1) {
			return input.getFirst().get().map(List::of);
		}
		List<Supplier<Stream<T>>> tail = input.subList(1, 0);
		return input.getFirst().get().flatMap(first -> cartesianProduct(tail).map(t ->   prepend(first, t))  );
	}

	private static <T> List<T> prepend(T first, List<T> list) {
		List<T> result = new ArrayList<>(list.size() + 1);
		result.add(first);
		result.addAll(list);
		return Collections.unmodifiableList(result);
	}

	
}
