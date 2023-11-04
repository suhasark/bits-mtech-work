package com.suhasa.parking.repository;

import com.suhasa.parking.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}