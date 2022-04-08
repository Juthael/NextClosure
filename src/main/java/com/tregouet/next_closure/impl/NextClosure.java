package com.tregouet.next_closure.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tregouet.next_closure.IClosureFinder;
import com.tregouet.next_closure.closure_operator.ClosureOperator;
import com.tregouet.next_closure.closure_operator.IClosureOperator;
import com.tregouet.next_closure.sparse_converter.ISparseRelationConverter;
import com.tregouet.next_closure.sparse_converter.impl.SparseRelationConverter;

public class NextClosure<G, M> implements IClosureFinder<G, M> {
	
	ISparseRelationConverter<G, M> converter = new SparseRelationConverter<>();
	boolean [][] sparseRelation;
	int nbOfAttributes;
	IClosureOperator closureOperator;
	Map<Set<G>, Set<M>> closedSets = new LinkedHashMap<>();

	@Override
	public Map<Set<G>, Set<M>> apply(Map<G, Set<M>> binaryRelation) {
		sparseRelation = converter.originalRelation2Sparse(binaryRelation);
		nbOfAttributes = sparseRelation[0].length;
		closureOperator = new ClosureOperator().setRelation(sparseRelation);
	}
	
	private Set<Integer> nextClosure(Set<Integer> subsetA) {
		boolean[] attA = closureOperator.objects2Attributes(subsetA);
		for (int m = nbOfAttributes - 1 ; m >=0 ; m--) {
			if (attA[m])
				attA[m] = false;
			else {
				attA[m] = true;
				boolean attB[] = closureOperator.getClosureOf(attA);
				if (noDistinctElementBelowSpecifiedIndex(attB, attA, m)) {
					closed
				}
				
			}
		}
	}
	
	private boolean noDistinctElementBelowSpecifiedIndex(boolean[] attB, boolean[] attA, int m) {
		for (int i = 0 ; i < m ; i++) {
			if (attB[i] && !attA[i])
				return false;
		}
		return true;
	}

}
