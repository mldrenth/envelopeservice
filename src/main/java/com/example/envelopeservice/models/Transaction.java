package com.example.envelopeservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transactionType")
    private TransactionType transactionType;

    @ManyToOne
    @JsonIgnoreProperties({"transactions"})
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Transaction(String name, BigDecimal amount, TransactionType transactionType, Account account) {
        this.name = name;
        this.amount = amount;
        this.transactionType = transactionType;
        this.account = account;
    }

    Transaction(Long id, String name, BigDecimal amount, TransactionType transactionType, Account account) {
        this(name, amount, transactionType, account);
        this.id = id;
    }

    public Transaction() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
