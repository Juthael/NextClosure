package com.tregouet.next_closure.impl;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tregouet.next_closure.IClosedSetsFinder;

public class NextClosureTest {
	
	//From an example by Ganter, B., Obiedkov, S. (2016, pp. 48, 51)
	static Map<Integer, Set<Character>> input = new HashMap<>();
	static LinkedHashMap<Set<Integer>, Set<Character>> expectedOutput = new LinkedHashMap<>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//input
		input.put(1, new HashSet<>(Arrays.asList(new Character[] {'a', 'd', 'e', 'g', 'h'})));
		input.put(2, new HashSet<>(Arrays.asList(new Character[] {'b', 'd', 'e', 'g', 'h'})));
		input.put(3, new HashSet<>(Arrays.asList(new Character[] {'c', 'd', 'e', 'g', 'h'})));
		input.put(4, new HashSet<>(Arrays.asList(new Character[] {'c', 'e', 'f', 'h'})));
		input.put(5, new HashSet<>(Arrays.asList(new Character[] {'c', 'e', 'g'})));
		input.put(6, new HashSet<>(Arrays.asList(new Character[] {'c', 'g'})));
		//expected output
		expectedOutput.put(
				new HashSet<>(), 
				new HashSet<>(Arrays.asList(new Character[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'})));
		expectedOutput.put(
				new HashSet<>(Arrays.asList(new Integer[] {1})), 
				new HashSet<>(Arrays.asList(new Character[] {'a', 'd', 'e', 'g', 'h'})));
		expectedOutput.put(
				new HashSet<>(Arrays.asList(new Integer[] {2})), 
				new HashSet<>(Arrays.asList(new Character[] {'b', 'd', 'e', 'g', 'h'})));
		expectedOutput.put(
				new HashSet<>(Arrays.asList(new Integer[] {3})), 
				new HashSet<>(Arrays.asList(new Character[] {'c', 'd', 'e', 'g', 'h'})));
		expectedOutput.put(
				new HashSet<>(Arrays.asList(new Integer[] {4})), 
				new HashSet<>(Arrays.asList(new Character[] {'c', 'e', 'f', 'h'})));
		expectedOutput.put(
				new HashSet<>(Arrays.asList(new Integer[] {3, 5})), 
				new HashSet<>(Arrays.asList(new Character[] {'c', 'e', 'g'})));
		expectedOutput.put(
				new HashSet<>(Arrays.asList(new Integer[] {3, 4})), 
				new HashSet<>(Arrays.asList(new Character[] {'c', 'e', 'h'})));
		expectedOutput.put(
				new HashSet<>(Arrays.asList(new Integer[] {3, 4, 5})), 
				new HashSet<>(Arrays.asList(new Character[] {'c', 'e'})));
		expectedOutput.put(
				new HashSet<>(Arrays.asList(new Integer[] {3, 5, 6})), 
				new HashSet<>(Arrays.asList(new Character[] {'c', 'g'})));
		expectedOutput.put(
				new HashSet<>(Arrays.asList(new Integer[] {3, 4, 5, 6})), 
				new HashSet<>(Arrays.asList(new Character[] {'c'})));
		expectedOutput.put(
				new HashSet<>(Arrays.asList(new Integer[] {1, 2, 3})), 
				new HashSet<>(Arrays.asList(new Character[] {'d', 'e', 'g', 'h'})));
		expectedOutput.put(
				new HashSet<>(Arrays.asList(new Integer[] {1, 2, 3, 5})), 
				new HashSet<>(Arrays.asList(new Character[] {'e', 'g'})));
		expectedOutput.put(
				new HashSet<>(Arrays.asList(new Integer[] {1, 2, 3, 4})), 
				new HashSet<>(Arrays.asList(new Character[] {'e', 'h'})));
		expectedOutput.put(
				new HashSet<>(Arrays.asList(new Integer[] {1, 2, 3, 4, 5})), 
				new HashSet<>(Arrays.asList(new Character[] {'e'})));
		expectedOutput.put(
				new HashSet<>(Arrays.asList(new Integer[] {1, 2, 3, 5, 6})), 
				new HashSet<>(Arrays.asList(new Character[] {'g'})));
		expectedOutput.put(
				new HashSet<>(Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6})), 
				new HashSet<>());
	}

	@Test
	public void whenAppliedToInputThenExpectedOutput() {
		IClosedSetsFinder<Integer, Character> finder = new NextClosure<>();
		LinkedHashMap<Set<Integer>, Set<Character>> output = finder.apply(input);
		assertTrue(output.equals(expectedOutput));
	}

}
