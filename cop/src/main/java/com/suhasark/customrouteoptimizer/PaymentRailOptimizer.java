package com.suhasark.customrouteoptimizer;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.RealVar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentRailOptimizer {

    static class PaymentRail {
        String bankPartner;
        String country;
        String currencySupported;
        String paymentRail;
        int averageCompletionTime;
        int numberOfFailures;
        boolean active;
        int discountedCost;

        public PaymentRail(String[] data) {
            this.bankPartner = data[0];
            this.country = data[1];
            this.currencySupported = data[2];
            this.paymentRail = data[3];
            this.averageCompletionTime = Integer.parseInt(data[4]);
            this.numberOfFailures = Integer.parseInt(data[5]);
            this.active = Boolean.parseBoolean(data[6]);
            this.discountedCost = Integer.parseInt(data[11]);
        }

        @Override
        public String toString() {
            return "PaymentRail{" +
                    "bankPartner='" + bankPartner + '\'' +
                    ", country='" + country + '\'' +
                    ", currencySupported='" + currencySupported + '\'' +
                    ", paymentRail='" + paymentRail + '\'' +
                    ", averageCompletionTime=" + averageCompletionTime +
                    ", numberOfFailures=" + numberOfFailures +
                    ", active=" + active +
                    ", discountedCost=" + discountedCost +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
//        constraintOptimization();
//        workingSimple();
        debugModel();
    }


    private static void debugModel() throws Exception{
        List<PaymentRail> rails = loadData("D:\\projects\\cop\\files\\payment_rails_enhanced.csv");

        // Create a test model with YOUR constraints but simplified data
        Model debugModel = new Model("Debug Model");

// Use only first 3 active rails for testing
        List<PaymentRail> testRails = rails.stream().filter(r -> r.active && r.country.equalsIgnoreCase("IN") && r.currencySupported.equalsIgnoreCase("INR")).collect(Collectors.toList());
//        List<PaymentRail> testRails = rails.stream()
//                .filter(r -> r.active)
//                .limit(3)
//                .collect(Collectors.toList());
        testRails.forEach(System.out::println);
        BoolVar[] useRail = new BoolVar[testRails.size()];
        IntVar[] times = new IntVar[testRails.size()];

        for (int i = 0; i < testRails.size(); i++) {
            useRail[i] = debugModel.boolVar("use_rail_" + i);
            times[i] = debugModel.intVar(testRails.get(i).discountedCost);
        }

// Basic constraints
        debugModel.sum(useRail, "=", 1).post();  // Select one

// Try solving without objective first
        System.out.println("Trying to find ANY solution...");
        boolean satisfiable = debugModel.getSolver().solve();
        System.out.println("Model satisfiable: " + satisfiable);

        if (satisfiable) {
            // Now try with objective
            IntVar totalTime = debugModel.intVar("total_time", 0, 100000);
            for (int i = 0; i < testRails.size(); i++) {
                debugModel.ifThen(
                        debugModel.arithm(useRail[i], "=", 1),
                        debugModel.arithm(totalTime, "=", times[i])
                );
            }
            debugModel.setObjective(Model.MINIMIZE, totalTime);

            Solution solution = debugModel.getSolver().findOptimalSolution(totalTime, Model.MINIMIZE);
            System.out.println("Optimal solution found: " + ((solution != null) ? solution.getIntVal(totalTime) : null));
        }
    }
    private static void workingSimple() throws Exception{
        List<PaymentRail> rails = loadData("D:\\projects\\cop\\files\\payment_rails_enhanced.csv");

        Model model = new Model("Working Example");
        List<PaymentRail> activeRails = rails.stream().filter(r -> r.active).collect(Collectors.toList());
        activeRails.forEach(r -> System.out.println(
                r.bankPartner + " | Active: " + r.active + " | Time: " + r.averageCompletionTime
        ));
        BoolVar[] useRail = new BoolVar[activeRails.size()];
        IntVar[] times = new IntVar[activeRails.size()];

        for (int i = 0; i < activeRails.size(); i++) {
            useRail[i] = model.boolVar("use_rail_" + i);
            times[i] = model.intVar(activeRails.get(i).averageCompletionTime);
        }

        model.sum(useRail, "=", 1).post(); // Select exactly one

        IntVar totalTime = model.intVar("total_time", 0, IntVar.MAX_INT_BOUND);

// Create a sum constraint where totalTime equals the selected rail's time
        model.sum(
                Arrays.stream(times)
                        .map(t -> model.intVar(t.getLB(), t.getUB()))
                        .toArray(IntVar[]::new),
                "=",
                totalTime
        ).post();

// Ensure exactly one rail is selected
        model.sum(useRail, "=", 1).post();

// Channeling between selection and time
        for (int i = 0; i < useRail.length; i++) {
            model.ifThen(
                    model.arithm(useRail[i], "=", 1),
                    model.arithm(totalTime, "=", times[i])
            );
        }

        model.setObjective(Model.MINIMIZE, totalTime);



//        Solution solution = model.getSolver().findOptimalSolution(totalTime, Model.MINIMIZE);
//        if (solution != null) {
//            System.out.println("Optimal time: " + solution.getIntVal(totalTime));
//        } else {
//            System.out.println("No solution found - check your data");
//            System.out.println("Active rails times: " +
//                    activeRails.stream().mapToInt(r -> r.averageCompletionTime).boxed()
//                            .collect(Collectors.toList()));
//        }

        if (model.getSolver().solve()) {
            System.out.println("Found a solution with time: " + totalTime.getValue());
        }
    }

    private static void constraintOptimization() throws Exception {
        // Load data from CSV
        List<PaymentRail> rails = loadData("D:\\projects\\cop\\files\\payment_rails_enhanced.csv");

        // Create optimization model
        Model model = new Model("Payment Rail Optimization");

        // 1. Create decision variables (whether to use each rail or not)
        BoolVar[] useRail = new BoolVar[rails.size()];
        for (int i = 0; i < rails.size(); i++) {
            useRail[i] = model.boolVar("use_rail_" + i);

            // Constraint: Can only use active rails
            if (!rails.get(i).active) {
                model.arithm(useRail[i], "=", 0).post();
            }
            if (!rails.get(i).currencySupported.equals("USD")) {
                model.arithm(useRail[i], "=", 0).post();
            } //USD
        }

        // 2. Create variables for objectives we want to optimize
        int maxTime = rails.stream()
                .filter(r -> r.active)
                .mapToInt(r -> r.averageCompletionTime)
                .max().orElse(86400); // default to 1 day if no active rails

        int maxFailures = rails.stream()
                .filter(r -> r.active)
                .mapToInt(r -> r.numberOfFailures)
                .max().orElse(1000); // default to 1000 if no active rails

        double maxCost = rails.stream()
                .filter(r -> r.active)
                .mapToDouble(r -> r.discountedCost)
                .max().orElse(10000.0); // default to $10,000 if no active rails

        // Total cost (sum of discounted costs of selected rails)
        RealVar totalCost = model.realVar("total_cost", 0, maxCost, 0.01);

        // Total completion time (sum of times of selected rails)
        IntVar totalTime = model.intVar("total_time", 0, maxTime);

        // Total failures (sum of failures of selected rails - lower is better)
        IntVar totalFailures = model.intVar("total_failures", 0, maxFailures);

        // 3. Add constraints to calculate these totals
        model.sum(useRail, "=", 1).post(); // Select exactly one rail

        // Calculate total cost
        RealVar zero = model.realVar(0.0, 0.0, 0.01);
        RealVar sum = zero;
        for (int i = 0; i < rails.size(); i++) {

            double railCost = Math.max(0, rails.get(i).discountedCost); // Ensure non-negative
            RealVar term = model.realVar(0, railCost, 0.01);
            model.realIbexGenericConstraint("{0}={1}*{2}",
                    term,
                    useRail[i],
                    model.realVar(railCost, railCost, 0.01)).post();
            RealVar newSum = model.realVar(0, Double.MAX_VALUE, 0.01);
            model.realIbexGenericConstraint("{0}={1}+{2}", newSum, sum, term).post();
            sum = newSum;
        }
        model.realIbexGenericConstraint("{0}={1}", totalCost, sum).post();

        // Calculate total time
        IntVar[] timeTerms = new IntVar[rails.size()];
        for (int i = 0; i < rails.size(); i++) {
            timeTerms[i] = model.intVar(rails.get(i).averageCompletionTime);
            model.times(useRail[i], timeTerms[i],
                    model.intVar(0, rails.get(i).averageCompletionTime)).post();
        }
        model.sum(timeTerms, "=", totalTime).post();

        // Calculate total failures (availability metric)
        IntVar[] failureTerms = new IntVar[rails.size()];
        for (int i = 0; i < rails.size(); i++) {
            failureTerms[i] = model.intVar(rails.get(i).numberOfFailures);
            model.times(useRail[i], failureTerms[i],
                    model.intVar(0, rails.get(i).numberOfFailures)).post();
        }
        model.sum(failureTerms, "=", totalFailures).post();

        // 4. Define optimization strategies
        // Weights for each dimension (adjust based on business priorities)
        int costWeight = 50;    // Importance of cost (integer weights)
        int timeWeight = 20;    // Importance of speed
        int availWeight = 30;   // Importance of availability (lower failures)

        // Scaling factors to bring all metrics to similar scales
        int costScale = 10;     // Scale cost to integer
        int timeScale = 100;    // Scale time (seconds to 0.1s units)
        int failScale = 1;      // Scale failures

        // Calculate maximum possible scaled cost
        double maxPossibleCost = rails.stream()
                .filter(r -> r.active)
                .mapToDouble(r -> r.discountedCost)
                .max()
                .orElse(1000.0); // Default to $1000 if no active rails

        int maxScaledCost = Math.min((int) Math.ceil(maxPossibleCost * 100 * 2), 1_000_000);
        // Create integer version of cost (scaled by 100 to maintain 2 decimal places)
        IntVar scaledCost = model.intVar("scaled_cost", 0, maxScaledCost);

        model.realIbexGenericConstraint("{0}={1}*100",
                model.intOffsetView(scaledCost, 0),
                totalCost).post();

        // Combined objective as IntVar (weighted sum of scaled metrics)
        int maxObjective = maxScaledCost +
                (maxTime * timeWeight * timeScale) +
                (maxFailures * availWeight * failScale);
        IntVar combinedObjective = model.intVar("combined_obj", 0, maxObjective);

        // Calculate weighted sum: cost*weight + time*weight + failures*weight


        // Calculate maximum possible values
        int maxCostComponent = maxScaledCost * costWeight;
        int maxTimeComponent = maxTime * timeWeight * timeScale;
        int maxFailComponent = maxFailures * availWeight * failScale;

// Create bounded variables
        IntVar costComponent = model.intVar("cost_component", 0, maxCostComponent);
        IntVar timeComponent = model.intVar("time_component", 0, maxTimeComponent);
        IntVar failComponent = model.intVar("fail_component", 0, maxFailComponent);

// Create constraints
        model.times(scaledCost, costWeight, costComponent).post();
        model.times(totalTime, timeWeight * timeScale, timeComponent).post();
        model.times(totalFailures, availWeight * failScale, failComponent).post();


        // Sum all components
        model.sum(new IntVar[]{costComponent, timeComponent, failComponent}, "=", combinedObjective).post();
// 4. Define optimization strategies
        // Option 1: Minimize cost
        // model.setObjective(Model.MINIMIZE, totalCost);

        // Option 2: Minimize time
        model.setObjective(Model.MINIMIZE, totalTime);

//        model.setObjective(Model.MINIMIZE, combinedObjective);

        // 5. Solve the problem
        Solver solver = model.getSolver();
        Solution solution = solver.findOptimalSolution(combinedObjective, Model.MINIMIZE);

        // 6. Print results
        if (solution != null) {
            System.out.println("Optimal solution found:");
            for (int i = 0; i < useRail.length; i++) {
                if (solution.getIntVal(useRail[i]) == 1) {
                    PaymentRail rail = rails.get(i);
                    System.out.println("\nSelected Rail:");
                    System.out.println("  Bank: " + rail.bankPartner);
                    System.out.println("  Country: " + rail.country);
                    System.out.println("  Currency: " + rail.currencySupported);
                    System.out.println("  Payment Rail: " + rail.paymentRail);
                    System.out.println("  Time: " + rail.averageCompletionTime + " seconds");
                    System.out.println("  Cost: $" + rail.discountedCost);
                    System.out.println("  Failures: " + rail.numberOfFailures + " per 1000 transactions");
                    System.out.println("  Availability Score: " + (100 - rail.numberOfFailures*10) + "%");

                    System.out.println("\nObjective Values:");
                    System.out.println("  Total Cost: $" + solution.getRealBounds(totalCost)[0] + "," + solution.getRealBounds(totalCost)[1]);
                    System.out.println("  Total Time: " + solution.getIntVal(totalTime) + " seconds");
                    System.out.println("  Total Failures: " + solution.getIntVal(totalFailures) + " per 1000");
                    System.out.println("  Combined Objective: " + solution.getIntVal(combinedObjective));

                    // Calculate normalized weights for interpretation
                    double normalizedCost = solution.getRealBounds(totalCost)[0] * costWeight / costScale;
                    double normalizedTime = solution.getIntVal(totalTime) * timeWeight / timeScale;
                    double normalizedFail = solution.getIntVal(totalFailures) * availWeight / failScale;
                    System.out.printf("  Weighted Components [Cost: %.1f, Time: %.1f, Failures: %.1f]\n",
                            normalizedCost, normalizedTime, normalizedFail);
                }
            }
        } else {
            System.out.println("No solution found");
        }
    }

    private static List<PaymentRail> loadData(String filename) throws Exception {
        List<PaymentRail> rails = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        boolean firstLine = true;

        while ((line = br.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue; // Skip header
            }
            String[] data = line.split(",");
            rails.add(new PaymentRail(data));
        }
        br.close();
        return rails;
    }
}