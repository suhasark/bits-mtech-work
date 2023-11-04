package com.suhasa.parking.repository;

import com.suhasa.parking.domain.Client;
import com.suhasa.parking.domain.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
}