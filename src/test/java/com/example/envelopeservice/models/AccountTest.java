package com.example.envelopeservice.models;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AccountTest {
    Account myAccount;
    Envelope travel;
    Envelope groceries;


    @Before
    public void before() {
        myAccount = new Account("My Account", AccountType.CURRENT);
        travel = new Envelope(1L,"Travel", myAccount);
        groceries = new Envelope("Groceries", myAccount);
    }

    @Test
    public void hasName() {
        assertEquals("My Account", myAccount.getName());
    }

    @Test
    public void hasAccountType() {
        assertEquals("Current", myAccount.getAccountType().getTypeName());
    }

    @Test
    public void hasEmptyEnvelopeList() {
        assertEquals(0, myAccount.getEnvelopeAmount());
    }

    @Test
    public void canAddEnvelope() {
        myAccount.addEnvelope(travel);
        myAccount.addEnvelope(groceries);
        assertEquals(2, myAccount.getEnvelopeAmount());
    }

    @Test
    public void hasAvailableAmount0() {
        assertEquals(new BigDecimal("0.00"), myAccount.getAvailableAmount());
    }

    @Test
    public void canDepositIntoAvailableAmount() {
        myAccount.deposit(new BigDecimal("600.00"));
        assertEquals(new BigDecimal("600.00"), myAccount.getAvailableAmount());
    }

    @Test
    public void canWithdrawMoney() {
        myAccount.deposit(new BigDecimal("200"));
        myAccount.withdraw(new BigDecimal("100"));
        assertEquals(new BigDecimal("100.00"), myAccount.getAvailableAmount());

    }

    @Test
    public void canNotWithdrawNegativeAmount() {
        myAccount.deposit(new BigDecimal("200"));
        BigDecimal withdrawnAmount = myAccount.withdraw(new BigDecimal("250"));
        assertEquals(new BigDecimal("0.00"), this.myAccount.getAvailableAmount());
        assertEquals(new BigDecimal("200.00"), withdrawnAmount);
    }

    @Test
    public void canAddToEnvelope() {
        myAccount.addEnvelope(travel);
        myAccount.deposit(new BigDecimal("200.00"));
        myAccount.depositIntoEnvelope(new BigDecimal("100.00"), travel.getId());
        assertEquals(new BigDecimal("100.00"), myAccount.getAvailableAmount());
        assertEquals(new BigDecimal("100.00"), myAccount.getEnvelopes().get(0).getAmount());
    }

}
