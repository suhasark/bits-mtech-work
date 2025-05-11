package io.github.suhasark;

public class InputParams {
    public String filterCountryCode;
    public String filterCurrency;
    public String timeWeightage;
    public String costWeightage;
    public String failureWeightage;

    public InputParams(String filterCountryCode, String filterCurrency, String timeWeightage,
                       String costWeightage, String failureWeightage) {
        this.filterCountryCode = filterCountryCode;
        this.filterCurrency = filterCurrency;
        this.timeWeightage = timeWeightage;
        this.costWeightage = costWeightage;
        this.failureWeightage = failureWeightage;
    }

    public String getFilterCountryCode() {
        return filterCountryCode;
    }

    public void setFilterCountryCode(String filterCountryCode) {
        this.filterCountryCode = filterCountryCode;
    }

    public String getFilterCurrency() {
        return filterCurrency;
    }

    public void setFilterCurrency(String filterCurrency) {
        this.filterCurrency = filterCurrency;
    }

    public String getTimeWeightage() {
        return timeWeightage;
    }

    public void setTimeWeightage(String timeWeightage) {
        this.timeWeightage = timeWeightage;
    }

    public String getCostWeightage() {
        return costWeightage;
    }

    public void setCostWeightage(String costWeightage) {
        this.costWeightage = costWeightage;
    }

    public String getFailureWeightage() {
        return failureWeightage;
    }

    public void setFailureWeightage(String failureWeightage) {
        this.failureWeightage = failureWeightage;
    }
}
