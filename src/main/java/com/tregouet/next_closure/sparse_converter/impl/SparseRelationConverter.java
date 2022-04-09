package com.tregouet.next_closure.sparse_converter.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tregouet.next_closure.sparse_converter.ISparseRelationConverter;

public class SparseRelationConverter<G, M> implements ISparseRelationConverter<G, M> {

	private List<G> objects = new ArrayList<>();
	private List<M> attributes = new ArrayList<>();
	private int nbOfAttributes;
	
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
		nbOfAttributes = attributeSet.size();
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
	public Set<G> objIdxes2ObjSet(Set<Integer> objIdxes) {
		Set<G> objSet = new HashSet<>();
		for (Integer g : objIdxes)
			objSet.add(objects.get(g));
		return objSet;
	}

	@Override
	public Set<M> attIdxes2AttSet(Set<Integer> attIdxes) {
		Set<M> attSet = new HashSet<>();
		for (Integer m : attIdxes)
			attSet.add(attributes.get(m));
		return attSet;
	}

	@Override
	public Set<M> attVector2AttSet(boolean[] attVector) {
		Set<M> attSet = new HashSet<>();
		for (int m = 0 ; m < nbOfAttributes ; m++) {
			if (attVector[m])
				attSet.add(attributes.get(m));
		}
		return attSet;
	}

}
