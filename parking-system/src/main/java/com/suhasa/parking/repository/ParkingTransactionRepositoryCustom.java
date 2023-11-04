package com.suhasa.parking.repository;

import com.suhasa.parking.domain.ParkingTransaction;

import java.util.List;

public interface ParkingTransactionRepositoryCustom {
    List<ParkingTransaction> findAllOpenTransactions();
}
