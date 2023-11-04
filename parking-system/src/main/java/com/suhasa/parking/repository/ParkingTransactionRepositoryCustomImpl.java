package com.suhasa.parking.repository;

import com.suhasa.parking.domain.ParkingTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ParkingTransactionRepositoryCustomImpl implements ParkingTransactionRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<ParkingTransaction> findAllOpenTransactions() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ParkingTransaction> query = cb.createQuery(ParkingTransaction.class);
        Root<ParkingTransaction> txn = query.from(ParkingTransaction.class);


        Predicate nullTicketPredicate = cb.isNull(txn.get("vehicleOutTime"));
        query.where(nullTicketPredicate);

        return entityManager.createQuery(query)
                .getResultList();
    }
}
