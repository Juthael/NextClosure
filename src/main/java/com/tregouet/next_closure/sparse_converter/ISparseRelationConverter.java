package com.tregouet.next_closure.sparse_converter;

import java.util.Map;
import java.util.Set;

public interface ISparseRelationConverter<G, M> {
	
	boolean[][] originalRelation2Sparse(Map<G, Set<M>> binaryRelation);
	
	Set<G> objIdxes2ObjSet(Set<Integer> objIdxes);
	
	Set<M> attIdxes2AttSet(Set<Integer> attIdxes);
	
	Set<M> attVector2AttSet(boolean[] attVector);
	
}
