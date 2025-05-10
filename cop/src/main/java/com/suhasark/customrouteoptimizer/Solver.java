package com.suhasark.customrouteoptimizer;

import java.util.*;

public class Solver {
    private final List<Variable> variables;
    private final List<Constraint> constraints;

    public Solver(List<Variable> variables, List<Constraint> constraints) {
        this.variables = variables;
        this.constraints = constraints;
    }

    public Map<String, Integer> backtrack(Map<String, Integer> assignment) {
        if (assignment.size() == variables.size()) return assignment;

        Variable unassigned = variables.stream()
                .filter(v -> !assignment.containsKey(v.name))
                .findFirst().orElse(null);

        if (unassigned == null) return null;

        for (int value : unassigned.domain) {
            assignment.put(unassigned.name, value);
            if (isConsistent(assignment)) {
                Map<String, Integer> result = backtrack(new HashMap<>(assignment));
                if (result != null) return result;
            }
            assignment.remove(unassigned.name);
        }

        return null;
    }

    private boolean isConsistent(Map<String, Integer> assignment) {
        for (Constraint constraint : constraints) {
            if (!constraint.isSatisfied(assignment)) {
                return false;
            }
        }
        return true;
    }
}
