package com.tregouet.next_closure;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public interface IClosureFinder<G, M> extends Function<Map<G, Set<M>>, Map<Set<G>, Set<M>>> {

}
