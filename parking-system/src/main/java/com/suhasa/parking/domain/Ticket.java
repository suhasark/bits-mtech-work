package com.suhasa.parking.domain;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "TICKET")
public class Ticket{
    @Id
    @GeneratedValue
    @Column(name = "Ticket_Id")
    private Long ticketId;
    private Double billingAmount;
    private Double stateGST;
    private Double centralGST;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Billing_Policy_Id", referencedColumnName = "Billing_Policy_Id")
    private BillingPolicy billingPolicy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Paid_Using", referencedColumnName = "Payment_Method")
    private PaymentType paidUsing;


    private Timestamp lastUpdatedOn;

    private String lastUpdatedBy;

    public Ticket(Long ticketId, Double billingAmount, Double stateGST, Double centralGST, BillingPolicy billingPolicy, PaymentType paidUsing, Timestamp lastUpdatedOn, String lastUpdatedBy) {
        this.ticketId = ticketId;
        this.billingAmount = billingAmount;
        this.stateGST = stateGST;
        this.centralGST = centralGST;
        this.billingPolicy = billingPolicy;
        this.paidUsing = paidUsing;
        this.lastUpdatedOn = lastUpdatedOn;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    public Ticket(Double billingAmount, Double stateGST, Double centralGST, BillingPolicy billingPolicy, PaymentType paidUsing, Timestamp lastUpdatedOn, String lastUpdatedBy) {
        this.billingAmount = billingAmount;
        this.stateGST = stateGST;
        this.centralGST = centralGST;
        this.billingPolicy = billingPolicy;
        this.paidUsing = paidUsing;
        this.lastUpdatedOn = lastUpdatedOn;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Ticket() {
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Double getBillingAmount() {
        return billingAmount;
    }

    public void setBillingAmount(Double billingAmount) {
        this.billingAmount = billingAmount;
    }

    public Double getStateGST() {
        return stateGST;
    }

    public void setStateGST(Double stateGST) {
        this.stateGST = stateGST;
    }

    public Double getCentralGST() {
        return centralGST;
    }

    public void setCentralGST(Double centralGST) {
        this.centralGST = centralGST;
    }

    public BillingPolicy getBillingPolicy() {
        return billingPolicy;
    }

    public void setBillingPolicy(BillingPolicy billingPolicy) {
        this.billingPolicy = billingPolicy;
    }

    public PaymentType getPaidUsing() {
        return paidUsing;
    }

    public void setPaidUsing(PaymentType paidUsing) {
        this.paidUsing = paidUsing;
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

    public double getFinalAmount() {
        return this.getBillingAmount() + this.getStateGST() + this.getCentralGST();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", billingAmount=" + billingAmount +
                ", stateGST=" + stateGST +
                ", centralGST=" + centralGST +
                ", billingPolicyId=" + billingPolicy +
                ", paidUsing='" + paidUsing + '\'' +
                ", lastUpdatedOn=" + lastUpdatedOn +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }
}
