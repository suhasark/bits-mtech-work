package com.suhasa.parking.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "PAYMENT_TYPE")
public class PaymentType {

    @Id
    @Column(name = "Payment_Method")
    private String paymentMethod;
    private String paymentDescription;


    private Timestamp lastUpdatedOn;

    private String lastUpdatedBy;

    public PaymentType() {
    }

    public PaymentType(String paymentMethod, String paymentDescription, Timestamp lastUpdatedOn, String lastUpdatedBy) {
        this.paymentMethod = paymentMethod;
        this.paymentDescription = paymentDescription;
        this.lastUpdatedOn = lastUpdatedOn;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
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
        PaymentType that = (PaymentType) o;
        return Objects.equals(paymentMethod, that.paymentMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentMethod);
    }

    @Override
    public String toString() {
        return "PaymentType{" +
                "paymentMethod='" + paymentMethod + '\'' +
                ", paymentDescription='" + paymentDescription + '\'' +
                ", lastUpdatedOn=" + lastUpdatedOn +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }
}
