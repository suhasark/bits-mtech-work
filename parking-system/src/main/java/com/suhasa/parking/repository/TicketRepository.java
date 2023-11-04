package com.suhasa.parking.repository;

import com.suhasa.parking.domain.ParkingSlot;
import com.suhasa.parking.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}