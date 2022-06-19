package com.example.envelopeservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "envelopes")
public class Envelope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "goalAmount")
    private BigDecimal goalAmount;

    @ManyToOne
    @JsonIgnoreProperties({"envelopes"})
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Envelope(String name, Account account) {
        this.name = name;
        this.amount = new BigDecimal("0.00");
        this.goalAmount = new BigDecimal("0.00");
        this.account = account;
    }

    Envelope(Long id, String name, Account account) {
        this(name, account);
        this.id = id;
    }

    public Envelope() {

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

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getGoalAmount() {
        return goalAmount;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setAmount(BigDecimal newAmount) {
        this.amount = newAmount;
    }

    public void setGoalAmount(BigDecimal newGoalAmount) {
        this.goalAmount = newGoalAmount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void deposit(BigDecimal depositAmount){
        this.setAmount(this.amount.add(depositAmount));
    }

    public BigDecimal withdraw(BigDecimal withdrawAmount) {
        if (this.canWithdrawAmount(withdrawAmount)) {
            this.setAmount(this.amount.subtract(withdrawAmount));
            return this.amount.subtract(withdrawAmount);
        }
        else {
            BigDecimal newWithdrawAmount = this.amount;
            this.setAmount(new BigDecimal("0.00"));
            return newWithdrawAmount;

        }
    }

    public Boolean canWithdrawAmount(BigDecimal withdrawAmount) {
        return this.amount.compareTo(withdrawAmount) >= 0;
    }

    public Boolean hasReachedGoal() {
        return this.amount.compareTo(this.goalAmount) >= 0;
    }
}
