package com.tregouet.next_closure.closure_operator;

import java.util.Collection;
import java.util.Set;

public interface IClosureOperator {
	
	IClosureOperator setRelation(boolean[][] binaryRelation);
	
	Collection<Integer> getClosureOf(Collection<Integer> subSet);
	
	boolean[] getClosureOf(boolean[] attributes);
	
	boolean[] objects2Attributes(Collection<Integer> subset);
	
	Set<Integer> attributes2Objects(boolean[] commonAttributes);

}
