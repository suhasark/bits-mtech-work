package com.suhasa.parking.repository;

import com.suhasa.parking.domain.BillingPolicy;
import com.suhasa.parking.domain.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillingPolicyRepository extends JpaRepository<BillingPolicy, Integer> {

//    @Query("SELECT * FROM BILLING_POLICY where START_TIME_IN_MINUTES <= :minutes AND END_TIME_IN_MINUTES >= :minutes")
//    BillingPolicy applicablePolicyForTime(long minutes);
}