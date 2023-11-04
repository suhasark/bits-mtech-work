package com.suhasa.parking.domain;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "PARKING_TRANSACTION")
public class ParkingTransaction {
    @Id
    @GeneratedValue
    private Long parkingTransactionId;
    private Timestamp vehicleInTime;
    private Timestamp vehicleOutTime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Occupied_Slot_Number", referencedColumnName = "Slot_Number")
    private ParkingSlot slot;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Vehicle_Registration_Number", referencedColumnName = "Registration_Number")
    private Vehicle vehicle;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Ticket_Id", referencedColumnName = "Ticket_Id", nullable = true)
    private Ticket ticket;
    private Timestamp lastUpdatedOn;

    private String lastUpdatedBy;

    public ParkingTransaction() {
    }

    public ParkingTransaction(Long parkingTransactionId, Timestamp vehicleInTime, Timestamp vehicleOutTime, ParkingSlot slot, Vehicle vehicle, Ticket ticket, Timestamp lastUpdatedOn, String lastUpdatedBy) {
        this.parkingTransactionId = parkingTransactionId;
        this.vehicleInTime = vehicleInTime;
        this.vehicleOutTime = vehicleOutTime;
        this.slot = slot;
        this.vehicle = vehicle;
        this.ticket = ticket;
        this.lastUpdatedOn = lastUpdatedOn;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getRegistrationNumber(){
        return this.vehicle == null ? " " : this.vehicle.getRegistrationNumber();
    }
    public Integer getSlotNumber(){
        return this.slot == null ? 0 : this.slot.getSlotNumber();
    }

    public Long getParkingTransactionId() {
        return parkingTransactionId;
    }

    public void setParkingTransactionId(Long parkingTransactionId) {
        this.parkingTransactionId = parkingTransactionId;
    }

    public Timestamp getVehicleInTime() {
        return vehicleInTime;
    }

    public void setVehicleInTime(Timestamp vehicleInTime) {
        this.vehicleInTime = vehicleInTime;
    }

    public Timestamp getVehicleOutTime() {
        return vehicleOutTime;
    }

    public void setVehicleOutTime(Timestamp vehicleOutTime) {
        this.vehicleOutTime = vehicleOutTime;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public void setSlot(ParkingSlot slot) {
        this.slot = slot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicleRegistrationNumber) {
        this.vehicle = vehicleRegistrationNumber;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
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

    public long getTicketId(){
        return this.ticket == null ? -1 : this.ticket.getTicketId();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingTransaction that = (ParkingTransaction) o;
        return Objects.equals(parkingTransactionId, that.parkingTransactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingTransactionId);
    }

    @Override
    public String toString() {
        return "ParkingTransaction{" +
                "parkingTransactionId=" + parkingTransactionId +
                ", vehicleInTime=" + vehicleInTime +
                ", vehicleOutTime=" + vehicleOutTime +
                ", slotNumber=" + slot +
                ", vehicleRegistrationNumber='" + vehicle + '\'' +
                ", ticketId=" + ticket +
                ", lastUpdatedOn=" + lastUpdatedOn +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }
}
