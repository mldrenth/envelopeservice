package com.example.envelopeservice.models;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Account {

    private String name;
    private AccountType accountType;
    private ArrayList<Envelope> envelopes;
    private BigDecimal availableAmount;

    public Account(String name, AccountType accountType) {
        this.name = name;
        this.accountType = accountType;
        this.envelopes = new ArrayList<>();
        this.availableAmount = new BigDecimal("0.00");
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

    public void depositIntoEnvelope(BigDecimal depositAmount, int index) {
        BigDecimal withdrawnAmount = this.withdraw(depositAmount);
        this.getEnvelopes().get(index).deposit(withdrawnAmount);
    }
}
