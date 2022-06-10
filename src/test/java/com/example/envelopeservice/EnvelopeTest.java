package com.example.envelopeservice;

import com.example.envelopeservice.models.Envelope;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class EnvelopeTest {
    Envelope travel;

    @Before
    public void before(){
        travel = new Envelope("Travel");
    }

    @Test
    public void hasName() {
        assertEquals("Travel", travel.getName());
    }

    @Test
    public void hasInitialAmount0() {
        assertEquals(new BigDecimal("0.00"), travel.getAmount());
    }

    @Test
    public void hasGoalAmount0(){
        assertEquals(new BigDecimal("0.00"), travel.getGoalAmount());
    }

    @Test
    public void canChangeName(){
        travel.setName("Groceries");
        assertEquals("Groceries", travel.getName());
    }

    @Test
    public void canSetGoalAmount(){
        travel.setGoalAmount(new BigDecimal("1000.00"));
        assertEquals(new BigDecimal("1000.00"), travel.getGoalAmount());
    }

    @Test
    public void canDepositMoney(){
        travel.deposit(new BigDecimal("200"));
        assertEquals(new BigDecimal("200.00"), travel.getAmount());
    }

    @Test
    public void canWithdrawMoney(){
        travel.deposit(new BigDecimal("200"));
        travel.withdraw(new BigDecimal("100"));
        assertEquals(new BigDecimal("100.00"), travel.getAmount());
    }

    @Test
    public void canNotWithdrawNegativeAmount(){
        travel.deposit(new BigDecimal("200"));
        BigDecimal withdrawnAmount = travel.withdraw(new BigDecimal("250"));
        assertEquals(new BigDecimal("0.00"), this.travel.getAmount());
        assertEquals(new BigDecimal("200.00"), withdrawnAmount);

    }

    @Test
    public void canReachGoal(){
        travel.setGoalAmount(new BigDecimal("500"));
        travel.deposit(new BigDecimal("501.00"));
        assertEquals(true, travel.hasReachedGoal());
    }

}
