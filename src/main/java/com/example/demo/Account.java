package com.example.demo;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private BigDecimal balance;

    @OneToMany(mappedBy = "issuer")
    private List<Transaction> outgoingTransactions = new ArrayList<>();
    
    @OneToMany(mappedBy = "recipient")
    private List<Transaction> incomingTransactions = new ArrayList<>();

    protected Account() {}

    public Account(String name, Number balance) {
        this.name = name;
        this.balance = new BigDecimal(balance.toString());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Number balance) {
        this.balance = new BigDecimal(balance.toString());
    }  
}
