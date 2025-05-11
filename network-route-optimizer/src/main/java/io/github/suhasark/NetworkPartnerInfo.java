package io.github.suhasark;

import java.time.LocalDateTime;

public class NetworkPartnerInfo {
    private String bankPartner;
    private String countryCode;
    private String currencySupported;
    private String paymentRail;
    private int averageCompletionTimeInSeconds;
    private int numberOfFailuresPerThousandTransactions;
    private boolean active;
    private String routingCode;
    private String operatingHours;
    private boolean isWeekend;
    private int costInUsCents;
    private int discountedCostInUsCents;
    private String discountRule;
    private LocalDateTime transactionDate;

    // Constructor
    public NetworkPartnerInfo(String bankPartner, String countryCode, String currencySupported, String paymentRail,
                              int averageCompletionTimeInSeconds, int numberOfFailuresPerThousandTransactions,
                              boolean active,
                              String routingCode, String operatingHours, boolean isWeekend, int costInUsCents,
                              int discountedCostInUsCents, String discountRule, LocalDateTime transactionDate) {
        this.bankPartner = bankPartner;
        this.countryCode = countryCode;
        this.currencySupported = currencySupported;
        this.paymentRail = paymentRail;
        this.averageCompletionTimeInSeconds = averageCompletionTimeInSeconds;
        this.numberOfFailuresPerThousandTransactions = numberOfFailuresPerThousandTransactions;
        this.active = active;
        this.routingCode = routingCode;
        this.operatingHours = operatingHours;
        this.isWeekend = isWeekend;
        this.costInUsCents = costInUsCents;
        this.discountedCostInUsCents = discountedCostInUsCents;
        this.discountRule = discountRule;
        this.transactionDate = transactionDate;
    }

    // Getters (only the ones needed for the weighted cost calculation)
    public int getAverageCompletionTimeInSeconds() {
        return averageCompletionTimeInSeconds;
    }

    public int getNumberOfFailuresPerThousandTransactions() {
        return numberOfFailuresPerThousandTransactions;
    }

    public int getCostInUsCents() {
        return costInUsCents;
    }

    public String getBankPartner() {
        return bankPartner;
    }

    public void setBankPartner(String bankPartner) {
        this.bankPartner = bankPartner;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCurrencySupported() {
        return currencySupported;
    }

    public void setCurrencySupported(String currencySupported) {
        this.currencySupported = currencySupported;
    }

    public String getPaymentRail() {
        return paymentRail;
    }

    public void setPaymentRail(String paymentRail) {
        this.paymentRail = paymentRail;
    }

    public void setAverageCompletionTimeInSeconds(int averageCompletionTimeInSeconds) {
        this.averageCompletionTimeInSeconds = averageCompletionTimeInSeconds;
    }

    public void setNumberOfFailuresPerThousandTransactions(int numberOfFailuresPerThousandTransactions) {
        this.numberOfFailuresPerThousandTransactions = numberOfFailuresPerThousandTransactions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoutingCode() {
        return routingCode;
    }

    public void setRoutingCode(String routingCode) {
        this.routingCode = routingCode;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public void setWeekend(boolean weekend) {
        isWeekend = weekend;
    }

    public void setCostInUsCents(int costInUsCents) {
        this.costInUsCents = costInUsCents;
    }

    public int getDiscountedCostInUsCents() {
        return discountedCostInUsCents;
    }

    public void setDiscountedCostInUsCents(int discountedCostInUsCents) {
        this.discountedCostInUsCents = discountedCostInUsCents;
    }

    public String getDiscountRule() {
        return discountRule;
    }

    public void setDiscountRule(String discountRule) {
        this.discountRule = discountRule;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "NetworPartnerInfo{" +
                "\n\tbankPartner='" + bankPartner + '\'' +
                ",\n\tcountryCode='" + countryCode + '\'' +
                ",\n\tcurrencySupported='" + currencySupported + '\'' +
                ",\n\tpaymentRail='" + paymentRail + '\'' +
                ",\n\taverageCompletionTimeInSeconds=" + averageCompletionTimeInSeconds +
                ",\n\tnumberOfFailuresPerThousandTransactions=" + numberOfFailuresPerThousandTransactions +
                ",\n\tactive=" + active +
                ",\n\troutingCode='" + routingCode + '\'' +
                ",\n\toperatingHours='" + operatingHours + '\'' +
                ",\n\tisWeekend=" + isWeekend +
                ",\n\tcostInUsCents=" + costInUsCents +
                ",\n\tdiscountedCostInUsCents=" + discountedCostInUsCents +
                ",\n\tdiscountRule='" + discountRule + '\'' +
                ",\n\ttransactionDate=" + transactionDate +
                "\n}";
    }
}
