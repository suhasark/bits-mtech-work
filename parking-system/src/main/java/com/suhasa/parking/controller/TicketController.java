package com.suhasa.parking.controller;

import com.suhasa.parking.domain.BillingPolicy;
import com.suhasa.parking.domain.ParkingTransaction;
import com.suhasa.parking.domain.PaymentType;
import com.suhasa.parking.domain.Ticket;
import com.suhasa.parking.repository.BillingPolicyRepository;
import com.suhasa.parking.repository.ParkingTransactionRepository;
import com.suhasa.parking.repository.PaymentTypeRepository;
import com.suhasa.parking.repository.TicketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository repository;
    private final ParkingTransactionRepository transactionRepository;
    private final BillingPolicyRepository billingPolicyRepository;
    private final PaymentTypeRepository paymentTypeRepository;

    public TicketController(TicketRepository clientRepository,ParkingTransactionRepository transactionRepository,BillingPolicyRepository billingPolicyRepository,PaymentTypeRepository paymentTypeRepository) {
        this.repository = clientRepository;
        this.transactionRepository = transactionRepository;
        this.billingPolicyRepository = billingPolicyRepository;
        this.paymentTypeRepository = paymentTypeRepository;
    }

    @GetMapping
    public List<Ticket> getClients() {
        return repository.findAll();
    }

    @GetMapping("/{parkingTransactionId}")
    public Ticket getClient(@PathVariable Long parkingTransactionId) {
        Optional<ParkingTransaction> transactionRepositoryById = transactionRepository.findById(parkingTransactionId);
        ParkingTransaction parkingTransaction = transactionRepositoryById.get();
        Timestamp outTime = new Timestamp(System.currentTimeMillis());
        long occupiedTime = compareTwoTimeStamps(outTime, parkingTransaction.getVehicleInTime());
        BillingPolicy billingPolicy = this.billingPolicyRepository.findById(100).get();
        double centralGst = billingPolicy.getParkingRate() * .05;
        double stateGst = billingPolicy.getParkingRate() * .05;
        Ticket ticket = repository.save(new Ticket(billingPolicy.getParkingRate(), centralGst, stateGst, billingPolicy, paymentTypeRepository.findById("Cash").get(), new Timestamp(System.currentTimeMillis()), "Admin"));
        parkingTransaction.setTicket(ticket);
        parkingTransaction.setVehicleOutTime(outTime);
        transactionRepository.save(parkingTransaction);
        return ticket;
    }

    public static long compareTwoTimeStamps(java.sql.Timestamp currentTime, java.sql.Timestamp oldTime)
    {
        long milliseconds1 = oldTime.getTime();
        long milliseconds2 = currentTime.getTime();

        long diff = milliseconds2 - milliseconds1;
        long diffSeconds = diff / 1000;
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffMinutes;
    }
    @PostMapping
    public ResponseEntity createClient(@RequestBody Ticket client) throws URISyntaxException {
        Ticket savedClient = repository.save(client);
        return ResponseEntity.created(new URI("/slots/" + savedClient.getTicketId())).body(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody Ticket ticket) {
        Ticket currentClient = repository.findById(id).orElseThrow(RuntimeException::new);
        currentClient.setBillingAmount(ticket.getBillingAmount());
        currentClient.setCentralGST(ticket.getCentralGST());
        currentClient.setStateGST(ticket.getStateGST());
        currentClient.setBillingPolicy(ticket.getBillingPolicy());
        currentClient.setPaidUsing(ticket.getPaidUsing());
        currentClient = repository.save(ticket);

        return ResponseEntity.ok(currentClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
