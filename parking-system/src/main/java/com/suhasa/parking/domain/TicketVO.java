package com.suhasa.parking.domain;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class TicketVO implements Serializable {

    private Ticket ticket;

    private double finalAmount;
    public TicketVO(Ticket ticket){
        this.ticket = ticket;
    }


    public TicketVO() {
    }

    public double getFinalAmount() {
        return this.ticket.getBillingAmount() + this.ticket.getStateGST() + this.ticket.getCentralGST();
    }

    public Long getTicketId() {
        return ticket.getTicketId();
    }


    public Double getBillingAmount() {
        return ticket.getBillingAmount();
    }


    public Double getStateGST() {
        return ticket.getStateGST();
    }


    public Double getCentralGST() {
        return ticket.getCentralGST();
    }


    public BillingPolicy getBillingPolicy() {
        return ticket.getBillingPolicy();
    }


    public PaymentType getPaidUsing() {
        return ticket.getPaidUsing();
    }


    public Timestamp getLastUpdatedOn() {
        return ticket.getLastUpdatedOn();
    }


    public String getLastUpdatedBy() {
        return ticket.getLastUpdatedBy();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketVO ticket = (TicketVO) o;
        return Objects.equals(ticket.ticket, ticket.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket);
    }

    @Override
    public String toString() {
        return "TicketVO{" +
                "ticket=" + ticket +
                '}';
    }
}
