package com.example.demo;

import java.util.Date;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp = new Date();

    private BigDecimal amount;

    @JsonBackReference("issuer-transactions")
    @ManyToOne
    @JoinColumn(name = "issuer_id")
    private Account issuer;

    @JsonBackReference("recipient-transactions")
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Account recipient;

    protected Transaction() {}

    public Transaction(Account issuer, Account recipient, BigDecimal amount) {
        this.issuer = issuer;
        this.recipient = recipient;
        this.amount = amount;
        this.timestamp = new Date();
    }

    public Long getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Account getIssuer() {
        return issuer;
    }

    public void setIssuer(Account issuer) {
        this.issuer = issuer;
    }

    public Account getRecipient() {
        return recipient;
    }

    public void setRecipient(Account recipient) {
        this.recipient = recipient;
    }
}
