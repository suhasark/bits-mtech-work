package com.suhasa.parking.domain;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "BILLING_POLICY")
public class BillingPolicy {

    @Id
    @Column(name = "Billing_Policy_Id")
    private Integer billingPolicyId;

    private String description;
    private Integer startTimeInMinutes;
    private Integer endTimeInMinutes;
    private Double parkingRate;

    private Timestamp lastUpdatedOn;

    private String lastUpdatedBy;

    public BillingPolicy() {
    }

    public BillingPolicy(Integer billingPolicyId, String description, Integer startTimeInMinutes, Integer endTimeInMinutes, Double parkingRate, Timestamp lastUpdatedOn, String lastUpdatedBy) {
        this.billingPolicyId = billingPolicyId;
        this.description = description;
        this.startTimeInMinutes = startTimeInMinutes;
        this.endTimeInMinutes = endTimeInMinutes;
        this.parkingRate = parkingRate;
        this.lastUpdatedOn = lastUpdatedOn;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getBillingPolicyId() {
        return billingPolicyId;
    }

    public void setBillingPolicyId(Integer billingPolicyId) {
        this.billingPolicyId = billingPolicyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStartTimeInMinutes() {
        return startTimeInMinutes;
    }

    public void setStartTimeInMinutes(Integer startTimeInMinutes) {
        this.startTimeInMinutes = startTimeInMinutes;
    }

    public Integer getEndTimeInMinutes() {
        return endTimeInMinutes;
    }

    public void setEndTimeInMinutes(Integer endTimeInMinutes) {
        this.endTimeInMinutes = endTimeInMinutes;
    }

    public Double getParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(Double parkingRate) {
        this.parkingRate = parkingRate;
    }

    public Timestamp getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Timestamp lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillingPolicy that = (BillingPolicy) o;
        return Objects.equals(billingPolicyId, that.billingPolicyId);
    }


    @Override
    public int hashCode() {
        return Objects.hash(billingPolicyId);
    }

    @Override
    public String toString() {
        return "BillingPolicy{" +
                "billingPolicyId=" + billingPolicyId +
                ", description='" + description + '\'' +
                ", startTimeInMinutes=" + startTimeInMinutes +
                ", endTimeInMinutes=" + endTimeInMinutes +
                ", parkingRate=" + parkingRate +
                ", lastUpdatedOn=" + lastUpdatedOn +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }
}
