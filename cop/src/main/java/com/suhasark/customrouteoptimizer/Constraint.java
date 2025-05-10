package com.suhasark.customrouteoptimizer;

import java.util.Map;

public interface Constraint {
    boolean isSatisfied(Map<String, Integer> assignment);
}
