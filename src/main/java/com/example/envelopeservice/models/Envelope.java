package com.example.envelopeservice.models;

import java.math.BigDecimal;

public class Envelope {

    private String name;
    private BigDecimal amount;
    private BigDecimal goalAmount;

    public Envelope(String name) {
        this.name = name;
        this.amount = new BigDecimal("0.00");
        this.goalAmount = new BigDecimal("0.00");
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
