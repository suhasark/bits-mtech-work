package com.suhasa.parking.repository;

import com.suhasa.parking.domain.ParkingSlot;
import com.suhasa.parking.domain.ParkingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingTransactionRepository extends JpaRepository<ParkingTransaction, Long>,ParkingTransactionRepositoryCustom {
}