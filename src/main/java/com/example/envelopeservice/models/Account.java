package com.example.envelopeservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "accountType")
    private AccountType accountType;

    @JsonIgnoreProperties({"accounts"})
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private ArrayList<Envelope> envelopes;

    @JsonIgnoreProperties({"accounts"})
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private ArrayList<Transaction> transactions;

    @Column(name = "availableAmount")
    private BigDecimal availableAmount;

    public Account(String name, AccountType accountType) {
        this.name = name;
        this.accountType = accountType;
        this.envelopes = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.availableAmount = new BigDecimal("0.00");
    }

    public Account() {
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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public ArrayList<Envelope> getEnvelopes() {
        return envelopes;
    }

    public void setEnvelopes(ArrayList<Envelope> envelopes) {
        this.envelopes = envelopes;
    }

    public int getEnvelopeAmount() {
        return this.envelopes.size();
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    public void deposit(BigDecimal depositAmount) {
        this.setAvailableAmount(this.availableAmount.add(depositAmount));
    }

    public BigDecimal withdraw(BigDecimal withdrawAmount) {
        if (this.canWithdrawAmount(withdrawAmount)) {
            this.setAvailableAmount(this.availableAmount.subtract(withdrawAmount));
            return withdrawAmount;
        }
        else {
            BigDecimal newWithdrawAmount = this.availableAmount;
            this.setAvailableAmount(new BigDecimal("0.00"));
            return newWithdrawAmount;
        }
    }

    public Boolean canWithdrawAmount(BigDecimal withdrawAmount) {
        return this.availableAmount.compareTo(withdrawAmount) >= 0;
    }

    public void addEnvelope(Envelope envelope) {
        this.envelopes.add(envelope);
    }

    public void depositIntoEnvelope(BigDecimal depositAmount, Long id) {
        BigDecimal withdrawnAmount = this.withdraw(depositAmount);
        this.getEnvelopes().stream().filter(envelope -> envelope.getId().equals(id)).findAny().ifPresent(foundEnvelope -> foundEnvelope.deposit(withdrawnAmount));
    }

    public void withdrawFromEnvelope(BigDecimal withdrawAmount, Long id) {
        Envelope chosenEnvelope = this.getEnvelopes().stream().filter(envelope -> envelope.getId().equals(id)).findAny().orElse(null);
        BigDecimal withdrawnAmount = chosenEnvelope.withdraw(withdrawAmount);
        this.deposit(withdrawnAmount);

    }

    public void receiveTransaction(Transaction transaction) {
        if (transaction.getTransactionType() == TransactionType.DEBIT) {
            this.deposit(transaction.getAmount());
        }
        else {
            this.withdraw(transaction.getAmount());
        }
        this.transactions.add(transaction);
    }
}
