package com.tregouet.next_closure;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public interface IClosedSetsFinder<G, M> extends Function<Map<G, Set<M>>, LinkedHashMap<Set<G>, Set<M>>> {

}
