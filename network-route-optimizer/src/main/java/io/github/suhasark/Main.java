package io.github.suhasark;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<NetworkRoute> routes = List.of(
                new NetworkRoute(20, 5, 2, 3),
                new NetworkRoute(25, 3, 1, 5),
                new NetworkRoute(15, 8, 3, 2)
        );

        Set<Integer> indices = new HashSet<>();
        for (int i = 0; i < routes.size(); i++) {
            indices.add(i);
        }

        Variable routeVar = new Variable("routeIndex", indices);
        MinimizeObjectiveConstraint constraint = new MinimizeObjectiveConstraint(routes, Integer.MAX_VALUE);

        Solver solver = new Solver(List.of(routeVar), List.of(constraint));

        Map<String, Integer> result = solver.backtrack(new HashMap<>());

        if (result != null) {
            int idx = result.get("routeIndex");
            System.out.println("Best route index: " + idx);
            System.out.println("Score: " + (routes.get(idx).cost * 5 + routes.get(idx).time * 3 +
                    routes.get(idx).hops * 2 + routes.get(idx).failureRate * 4));
        } else {
            System.out.println("No valid route found.");
        }
    }
}
