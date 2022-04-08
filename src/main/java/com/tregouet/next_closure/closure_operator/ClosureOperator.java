package com.tregouet.next_closure.closure_operator;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ClosureOperator implements IClosureOperator {

	private boolean[][] binaryRelation;
	private int nbOfObjects;
	private int nbOfAttributes;
	
	public ClosureOperator() {
	}
	
	@Override
	public boolean[] objects2Attributes(Collection<Integer> subset) {
		boolean[] commonAttributes = new boolean[nbOfAttributes];
		Arrays.fill(commonAttributes, true);
		for (Integer objIdx : subset) {
			boolean[] objAttributes = binaryRelation[objIdx];
			for (int m = 0 ; m < nbOfAttributes ; m++) {
				commonAttributes[m] = commonAttributes[m] && objAttributes[m];
			}
		}
		return commonAttributes;
	}
	
	@Override
	public Set<Integer> attributes2Objects(boolean[] commonAttributes) {
		Set<Integer> subset = new HashSet<>();
		for (int g = 0 ; g < nbOfObjects ; g++) {
			boolean[] objAttributes = binaryRelation[g];
			boolean compliant = true;
			int m = 0;
			while (compliant && m < nbOfAttributes) {
				if (commonAttributes[m] && !objAttributes[m])
					compliant = false;
				m++;
			}
			if (compliant)
				subset.add(g);
		}
		return subset;
	}

	@Override
	public IClosureOperator setRelation(boolean[][] binaryRelation) {
		this.binaryRelation = binaryRelation;
		nbOfObjects = binaryRelation.length;
		nbOfAttributes = binaryRelation[0].length;
		return this;
	}

	@Override
	public Collection<Integer> getClosureOf(Collection<Integer> subSet) {
		return attributes2Objects(objects2Attributes(subSet));
	}

	@Override
	public boolean[] getClosureOf(boolean[] attributes) {
		return objects2Attributes(attributes2Objects(attributes));
	}

}
