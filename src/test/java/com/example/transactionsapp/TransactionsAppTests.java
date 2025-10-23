package com.example.transactionsapp;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.transactionsapp.service.TransactionService;

@SpringBootTest
class TransactionsAppTests {

    @Autowired
    private TransactionService service;

    @Test
    void testCreateTransaction() {
        service.addTransaction("Laptop purchase", LocalDate.now(), new BigDecimal("1200.50"));
    }

}
