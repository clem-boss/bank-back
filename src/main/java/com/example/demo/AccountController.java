package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Account not found")));
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestParam String name, @RequestParam Number initialBalance) {
        Account account = new Account(name, initialBalance);
        return ResponseEntity.ok(accountRepository.save(account));
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Number> getBalance(@PathVariable Long id) {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Account not found"));
        return ResponseEntity.ok(account.getBalance());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Account not found");
        }
        accountRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
} 