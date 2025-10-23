package com.example.transactionsapp.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private String description;
    private LocalDate date;
    private BigDecimal amountUSD;

    public Transaction() {}

    public Transaction(String description, LocalDate date, BigDecimal amountUSD) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.date = date;
        this.amountUSD = amountUSD;
    }

    public UUID getId() { return id; }
    public String getDescription() { return description; }
    public LocalDate getDate() { return date; }
    public BigDecimal getAmountUSD() { return amountUSD; }
}