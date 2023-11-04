package com.suhasa.parking.controller;

import com.suhasa.parking.domain.ParkingTransaction;
import com.suhasa.parking.repository.ParkingTransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class ParkingTransactionController {

    private final ParkingTransactionRepository repository;

    public ParkingTransactionController(ParkingTransactionRepository clientRepository) {
        this.repository = clientRepository;
    }

    @GetMapping
    public List<ParkingTransaction> getClients() {
        return repository.findAllOpenTransactions();
    }

    @GetMapping("/{id}")
    public ParkingTransaction getClient(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody ParkingTransaction client) throws URISyntaxException {
        ParkingTransaction savedClient = repository.save(client);
        return ResponseEntity.created(new URI("/transactions/" + savedClient.getParkingTransactionId())).body(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody ParkingTransaction parkingTransaction) {
        ParkingTransaction txn = repository.findById(id).orElseThrow(RuntimeException::new);
        txn.setVehicle(parkingTransaction.getVehicle());
        txn = repository.save(txn);

        return ResponseEntity.ok(txn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
