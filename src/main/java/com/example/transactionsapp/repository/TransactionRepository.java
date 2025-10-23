package com.example.transactionsapp.repository;

import org.springframework.stereotype.Repository;

import com.example.transactionsapp.model.Transaction;

import java.util.*;

@Repository
public class TransactionRepository {
    private final Map<UUID, Transaction> db = new HashMap<>();

    public Transaction save(Transaction t) {
        db.put(t.getId(), t);
        return t;
    }

    public List<Transaction> findAll() {
        return new ArrayList<>(db.values());
    }
}