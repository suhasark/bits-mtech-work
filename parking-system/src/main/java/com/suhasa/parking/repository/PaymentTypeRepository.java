package com.suhasa.parking.repository;

import com.suhasa.parking.domain.BillingPolicy;
import com.suhasa.parking.domain.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, String> {
//    @Query(value = "SELECT * FROM PAYMENT_TYPE where PAYMENT_METHOD = 'Cash'")
//    PaymentType cashPaymentMentod();
}