package com.suhasa.parking;

import com.github.javafaker.Faker;
import com.suhasa.parking.domain.Client;
import com.suhasa.parking.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class BoostrapInitialData implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final Faker faker = new Faker(Locale.getDefault());

    public BoostrapInitialData(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) {
        for (int i = 0; i < 10; i++) {
//            clientRepository.save(new Client(faker.name().fullName(), faker.internet().emailAddress()));
        }
    }
}