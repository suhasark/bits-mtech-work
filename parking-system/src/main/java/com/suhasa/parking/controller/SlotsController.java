package com.suhasa.parking.controller;

import com.suhasa.parking.domain.Client;
import com.suhasa.parking.domain.ParkingSlot;
import com.suhasa.parking.repository.ClientRepository;
import com.suhasa.parking.repository.ParkingSlotRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/slots")
public class SlotsController {

    private final ParkingSlotRepository repository;

    public SlotsController(ParkingSlotRepository clientRepository) {
        this.repository = clientRepository;
    }

    @GetMapping
    public List<ParkingSlot> getClients() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ParkingSlot getClient(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody ParkingSlot client) throws URISyntaxException {
        ParkingSlot savedClient = repository.save(client);
        return ResponseEntity.created(new URI("/slots/" + savedClient.getSlotNumber())).body(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody ParkingSlot slot) {
        ParkingSlot currentClient = repository.findById(id).orElseThrow(RuntimeException::new);
        currentClient.setOccupancyStatus(slot.getOccupancyStatus());
        currentClient = repository.save(slot);

        return ResponseEntity.ok(currentClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
