package com.tregouet.next_closure.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.tregouet.next_closure.IClosedSetsFinder;
import com.tregouet.next_closure.closure_operator.IClosureOperator;
import com.tregouet.next_closure.closure_operator.impl.ClosureOperator;
import com.tregouet.next_closure.sparse_converter.ISparseRelationConverter;
import com.tregouet.next_closure.sparse_converter.impl.SparseRelationConverter;

/**
 * Implementation of algorithm in Ganter, B., Obiedkov, S. (2016, pp. 44-47)
 * 
 * @author Gael Tregouet
 *
 * @param <G>
 * @param <M>
 */
public class NextClosure<G, M> implements IClosedSetsFinder<G, M> {
	
	ISparseRelationConverter<G, M> converter = new SparseRelationConverter<>();
	boolean [][] sparseRelation;
	int nbOfAttributes;
	IClosureOperator closureOperator;
	LinkedHashMap<Set<G>, Set<M>> lecticallyOrderedClosedSets = new LinkedHashMap<>();
	
	public NextClosure() {
	}

	@Override
	public LinkedHashMap<Set<G>, Set<M>> apply(Map<G, Set<M>> binaryRelation) {
		sparseRelation = converter.originalRelation2Sparse(binaryRelation);
		nbOfAttributes = sparseRelation[0].length;
		closureOperator = new ClosureOperator().setRelation(sparseRelation);
		boolean[] lastClosure = firstClosure();
		while (lastClosure != null)
			lastClosure = nextClosure(lastClosure);
		return lecticallyOrderedClosedSets;
	}
	
	private boolean[] nextClosure(boolean[] a) {
		for (int m = nbOfAttributes - 1 ; m >= 0 ; m--) {
			if (a[m])
				a[m] = false;
			else {
				boolean b[] = closureOperator.getClosureOf(addNewAttribute(a, m));
				boolean isLecticallyNextClosedSet = noDistinctElementBelowSpecifiedIndex(b, a, m);
				if (isLecticallyNextClosedSet) {
					Set<Integer> objIdxes = closureOperator.attributes2Objects(b);
					lecticallyOrderedClosedSets.put(converter.objIdxes2ObjSet(objIdxes), converter.attVector2AttSet(b));
					return b;
				}
			}
		}
		return null;
	}
	
	private boolean[] firstClosure() {
		boolean[] emptyAttSet = new boolean[nbOfAttributes];
		Set<Integer> firstClosedSet = closureOperator.attributes2Objects(emptyAttSet);
		lecticallyOrderedClosedSets.put(converter.objIdxes2ObjSet(firstClosedSet), converter.attVector2AttSet(emptyAttSet));
		return emptyAttSet;
	}
	
	private boolean noDistinctElementBelowSpecifiedIndex(boolean[] b, boolean[] a, int m) {
		for (int i = 0 ; i < m ; i++) {
			if (b[i] && !a[i])
				return false;
		}
		return true;
	}
	
	private boolean[] addNewAttribute (boolean[] attVector, int newAttIdx) {
		boolean[] newAttVector = new boolean[nbOfAttributes];
		System.arraycopy(attVector, 0, newAttVector, 0, nbOfAttributes);
		newAttVector[newAttIdx] = true;
		return newAttVector;
	}

}
