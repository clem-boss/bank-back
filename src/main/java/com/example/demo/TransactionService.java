package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.math.BigDecimal;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Transaction transfer(Account issuer, Account recipient, BigDecimal amount) {
        issuer.setBalance(issuer.getBalance().subtract(amount));
        recipient.setBalance(recipient.getBalance().add(amount));

        Transaction transaction = new Transaction(issuer, recipient, amount);
        accountRepository.save(issuer);
        accountRepository.save(recipient);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
} 