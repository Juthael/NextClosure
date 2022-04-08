package com.tregouet.next_closure.sparse_converter.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tregouet.next_closure.sparse_converter.ISparseRelationConverter;

public class SparseRelationConverter<G, M> implements ISparseRelationConverter<G, M> {

	private List<G> objects = new ArrayList<>();
	private List<M> attributes = new ArrayList<>();
	
	public SparseRelationConverter() {
	}
	
	@Override
	public boolean[][] originalRelation2Sparse(Map<G, Set<M>> binaryRelation) {
		boolean[][] sparseRelation;
		Set<M> attributeSet = new HashSet<>();
		for (G object : binaryRelation.keySet())
			objects.add(object);
		for (Set<M> attributeSubset : binaryRelation.values())
			attributeSet.addAll(attributeSubset);
		attributes.addAll(attributeSet);
		int nbOfObjects = objects.size();
		int nbOfAttributes = attributes.size();
		sparseRelation = new boolean[objects.size()][attributes.size()];
		for (int g = 0 ; g < nbOfObjects ; g++) {
			for (int m = 0 ; m < nbOfAttributes ; m++) {
				if (binaryRelation.get(objects.get(g)).contains(attributes.get(m)))
					sparseRelation[g][m] = true;
			}
		}
		return sparseRelation;
	}

	@Override
	public Map<Set<G>, Set<M>> sparseClosures2Original(Map<List<Integer>, List<Integer>> sparseClosures) {
		Map<Set<G>, Set<M>> closures = new HashMap<>();
		for (List<Integer> sparseExtent : sparseClosures.keySet()) {
			List<Integer> sparseIntent = sparseClosures.get(sparseExtent);
			Set<G> extent = new HashSet<>();
			Set<M> intent = new HashSet<>();
			for (Integer extentIdx : sparseExtent)
				extent.add(objects.get(extentIdx));
			for (Integer intentIdx : sparseIntent)
				intent.add(attributes.get(intentIdx));
			closures.put(extent, intent);
		}
		return closures;
	}

}
