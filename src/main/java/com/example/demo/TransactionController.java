package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final AccountRepository accountRepository;

    public TransactionController(TransactionService transactionService, AccountRepository accountRepository) {
        this.transactionService = transactionService;
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(
            @RequestParam Long issuerId,
            @RequestParam Long recipientId,
            @RequestParam BigDecimal amount) {
        Account issuer = accountRepository.findById(issuerId)
            .orElseThrow(() -> new RuntimeException("Issuer account not found"));
        Account recipient = accountRepository.findById(recipientId)
            .orElseThrow(() -> new RuntimeException("Recipient account not found"));

        Transaction transaction = transactionService.transfer(issuer, recipient, amount);
        return ResponseEntity.ok(transaction);
    }
} 