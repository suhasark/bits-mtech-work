package com.suhasa.parking.domain;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "PARKING_SLOT")
public class ParkingSlot {

    @Id
    @Column(name = "Slot_Number")
    private Integer slotNumber;

    @Enumerated(EnumType.STRING)
    private OccupancyStatus occupancyStatus;
    private Integer floorNumber;

    private Timestamp lastUpdatedOn;

    private String lastUpdatedBy;

    public ParkingSlot() {
    }

    public ParkingSlot(Integer slotNumber, OccupancyStatus occupancyStatus, Integer floorNumber, Timestamp lastUpdatedOn, String lastUpdatedBy) {
        this.slotNumber = slotNumber;
        this.occupancyStatus = occupancyStatus;
        this.floorNumber = floorNumber;
        this.lastUpdatedOn = lastUpdatedOn;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public OccupancyStatus getOccupancyStatus() {
        return occupancyStatus;
    }

    public void setOccupancyStatus(OccupancyStatus occupancyStatus) {
        this.occupancyStatus = occupancyStatus;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
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
        ParkingSlot that = (ParkingSlot) o;
        return Objects.equals(slotNumber, that.slotNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotNumber);
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "slotNumber=" + slotNumber +
                ", occupancyStatus='" + occupancyStatus + '\'' +
                ", floorNumber=" + floorNumber +
                ", lastUpdatedOn=" + lastUpdatedOn +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }
}
