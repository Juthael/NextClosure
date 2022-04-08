package com.tregouet.next_closure.sparse_converter;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ISparseRelationConverter<G, M> {
	
	boolean[][] originalRelation2Sparse(Map<G, Set<M>> binaryRelation);
	
	Map<Set<G>, Set<M>> sparseClosures2Original(Map<List<Integer>, List<Integer>> sparseClosures);
}
