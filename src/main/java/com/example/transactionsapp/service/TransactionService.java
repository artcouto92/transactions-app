package com.example.transactionsapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.transactionsapp.model.Transaction;
import com.example.transactionsapp.repository.TransactionRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

@Service
public class TransactionService {

    private final TransactionRepository repo;
    private final RestTemplate restTemplate = new RestTemplate();

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public Transaction addTransaction(String description, LocalDate date, BigDecimal amount) {
        if (description.length() > 50 || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid transaction data");
        }
        Transaction t = new Transaction(description, date, amount.setScale(2, RoundingMode.HALF_UP));
        return repo.save(t);
    }

    public List<Map<String, Object>> getTransactionsConverted(String currency) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Transaction t : repo.findAll()) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", t.getId());
            map.put("description", t.getDescription());
            map.put("date", t.getDate());
            map.put("usdAmount", t.getAmountUSD());

            BigDecimal rate = getExchangeRate(currency, t.getDate());
            if (rate == null) {
                map.put("error", "No exchange rate within 6 months");
            } else {
                map.put("exchangeRate", rate);
                map.put("convertedAmount", t.getAmountUSD().multiply(rate).setScale(2, RoundingMode.HALF_UP));
            }
            result.add(map);
        }
        return result;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private BigDecimal getExchangeRate(String currency, LocalDate date) {
        try {
            String url = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange?filter=record_date:lte:" + date + ",country_currency_desc:eq:" + currency.toUpperCase() + "&sort=-record_date";
            Map resp = restTemplate.getForObject(url, Map.class);
            List<Map<String, String>> data = (List<Map<String, String>>) resp.get("data");
            if (data == null || data.isEmpty()) return null;
            Map<String, String> entry = data.get(0);
            return new BigDecimal(entry.get("exchange_rate"));
        } catch (Exception e) {
            return null;
        }
    }
}
