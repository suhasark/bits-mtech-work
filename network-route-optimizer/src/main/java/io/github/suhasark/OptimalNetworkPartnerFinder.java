package io.github.suhasark;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class OptimalNetworkPartnerFinder {

    public static final String CSV_FILE = "D:\\\\projects\\\\bits-mtech-work\\\\network-route-optimizer\\\\files" +
            "\\\\payment_rails_enhanced_corrected.csv";

    public static void main(String[] args) {
        InputParams result = parseInputParams();
        new OptimalNetworkPartnerFinder().process(result);
    }

    private static InputParams parseInputParams() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the country code to filter: ");
        String filterCountryCode = scanner.nextLine().trim().toUpperCase();
        System.out.print("Enter the currency code to filter: ");
        String filterCurrency = scanner.nextLine().trim().toUpperCase();
        System.out.print("Enter the weightage to assign for time taken [Int]: ");
        String timeWeightage = scanner.nextLine().trim().toUpperCase();
        System.out.print("Enter the weightage to assign for cost [Int]: ");
        String costWeightage = scanner.nextLine().trim().toUpperCase();
        System.out.print("Enter the weightage to assign for failures [Int]: ");
        String failureWeightage = scanner.nextLine().trim().toUpperCase();
        scanner.close();
        return new InputParams(filterCountryCode, filterCurrency, timeWeightage, costWeightage,
                failureWeightage);
    }

    private static List<NetworkPartnerInfo> parseAndCollectData(String filterCountryCode, String filterCurrency) {
        String line;
        String csvSplitBy = ",";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<NetworkPartnerInfo> eligibleNetworkPartnerInfos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            // Read the header row
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                if (data.length == 14) {
                    try {
                        String countryCode = data[1].trim().toUpperCase();
                        String currencySupported = data[2].trim().toUpperCase();
                        boolean active = Boolean.parseBoolean(data[6].trim());

                        if (countryCode.equals(filterCountryCode) && currencySupported.equals(filterCurrency) && active) {
                            NetworkPartnerInfo networkPartnerInfo = new NetworkPartnerInfo(
                                    data[0].trim(),
                                    countryCode,
                                    currencySupported,
                                    data[3].trim(),
                                    Integer.parseInt(data[4].trim()),
                                    Integer.parseInt(data[5].trim()),
                                    active,
                                    data[7].trim(),
                                    data[8].trim(),
                                    Boolean.parseBoolean(data[9].trim()),
                                    Integer.parseInt(data[10].trim()),
                                    Integer.parseInt(data[11].trim()),
                                    data[12].trim(),
                                    LocalDateTime.parse(data[13].trim(), formatter)
                            );
                            eligibleNetworkPartnerInfos.add(networkPartnerInfo);
                        }
                    } catch (NumberFormatException | java.time.format.DateTimeParseException |
                             ArrayIndexOutOfBoundsException e) {
                        System.err.println("Error parsing line: " + line + " - " + e.getMessage());
                    }
                }
                else {
                    System.err.println("Skipping invalid line: " + line + " - Incorrect number of columns");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return eligibleNetworkPartnerInfos;
    }

    public NetworkPartnerInfo process(InputParams result) {
        List<NetworkPartnerInfo> eligibleNetworkPartnerInfos = parseAndCollectData(result.filterCountryCode,
                result.filterCurrency);
        NetworkPartnerInfo networkPartnerInfo = null;
        if (eligibleNetworkPartnerInfos == null) return null;


        if (!eligibleNetworkPartnerInfos.isEmpty()) {
            Model model = new Model("Optimal Network Partner");
            int numRails = eligibleNetworkPartnerInfos.size();

            IntVar[] weightedCosts = new IntVar[numRails];
            for (int i = 0; i < numRails; i++) {
                NetworkPartnerInfo partnerInfo = eligibleNetworkPartnerInfos.get(i);
                weightedCosts[i] = model.intVar("weightedCost_" + i,
                        Integer.parseInt(result.timeWeightage) * partnerInfo.getAverageCompletionTimeInSeconds() +
                                Integer.parseInt(result.failureWeightage) * partnerInfo.getNumberOfFailuresPerThousandTransactions() +
                                Integer.parseInt(result.costWeightage) * partnerInfo.getCostInUsCents());
            }

            IntVar minWeightedCostVar = model.intVar("minWeightedCost", -1, 10000);
            model.min(minWeightedCostVar, weightedCosts).post();

            IntVar cheapestIndex = model.intVar("cheapestIndex", 0, numRails - 1);

            for (int i = 0; i < numRails; i++) {
                model.ifThen(model.arithm(weightedCosts[i], "=", minWeightedCostVar),
                        model.arithm(cheapestIndex, "=", i));
            }

            model.setObjective(Model.MINIMIZE, minWeightedCostVar);

            Solution solution = model.getSolver().findSolution();

            if (solution != null) {
                int bestIndex = solution.getIntVal(cheapestIndex);
                int minCostFound = solution.getIntVal(minWeightedCostVar);
                System.out.println("\nCheapest Payment Rail (based on weighted cost):");
                networkPartnerInfo = eligibleNetworkPartnerInfos.get(bestIndex);
                System.out.println(networkPartnerInfo);
                System.out.println("Weighted Cost: " + minCostFound);

            }
            else {
                System.out.println("No solution found.");
            }

        }
        else {
            System.out.println("No eligible payment rails found for the given criteria.");
        }
        return networkPartnerInfo;
    }

}
