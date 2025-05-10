package com.suhasark.customrouteoptimizer;

import java.util.List;
import java.util.Map;

public class MinimizeObjectiveConstraint implements Constraint {
    private final List<NetworkRoute> routes;
    private final int maxCost;

    public MinimizeObjectiveConstraint(List<NetworkRoute> routes, int maxCost) {
        this.routes = routes;
        this.maxCost = maxCost;
    }

    @Override
    public boolean isSatisfied(Map<String, Integer> assignment) {
        if (!assignment.containsKey("routeIndex")) return true;
        int index = assignment.get("routeIndex");
        if (index >= routes.size()) return false;

        NetworkRoute r = routes.get(index);
        int score = r.cost * 5 + r.time * 3 + r.hops * 2 + r.failureRate * 4;
        return score <= maxCost;
    }
}
