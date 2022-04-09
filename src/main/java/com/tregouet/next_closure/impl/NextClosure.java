package com.tregouet.next_closure.impl;

import java.util.Arrays;
import java.util.HashSet;
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
				a[m] = true;
				boolean b[] = closureOperator.getClosureOf(a);
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
		Set<Integer> emptySet = new HashSet<>();
		Set<Integer> firstClosedSet = closureOperator.getClosureOf(emptySet);
		boolean[] firstAttVector = new boolean[nbOfAttributes];
		Arrays.fill(firstAttVector, true);
		lecticallyOrderedClosedSets.put(converter.objIdxes2ObjSet(firstClosedSet), converter.attVector2AttSet(firstAttVector));
		return firstAttVector;
	}
	
	private boolean noDistinctElementBelowSpecifiedIndex(boolean[] b, boolean[] a, int m) {
		for (int i = 0 ; i < m ; i++) {
			if (b[i] && !a[i])
				return false;
		}
		return true;
	}

}
