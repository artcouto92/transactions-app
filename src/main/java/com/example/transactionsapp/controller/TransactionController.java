package com.example.transactionsapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.transactionsapp.model.Transaction;
import com.example.transactionsapp.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    // Cria uma nova transação
    @PostMapping
    public ResponseEntity<?> create(
            @RequestParam String description,
            @RequestParam String date,
            @RequestParam BigDecimal amount) {
        try {
            Transaction transaction = service.addTransaction(description, LocalDate.parse(date), amount);
            return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
        } catch (IllegalArgumentException e) {
            // Se os dados forem inválidos, devolve 400 (Bad Request)
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            // Erros inesperados devolvem 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Unexpected error occurred"));
        }
    }

    // Lista transações convertidas para uma moeda
    @GetMapping("/{currency}")
    public ResponseEntity<List<Map<String, Object>>> list(@PathVariable String currency) {
        List<Map<String, Object>> result = service.getTransactionsConverted(currency);
        return ResponseEntity.ok(result);
    }
}