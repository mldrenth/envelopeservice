package com.example.envelopeservice.models;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TransactionTest {
    Account myAccount;
    Transaction salary;


    @Before
    public void before() {
        myAccount = new Account("My Account", AccountType.CURRENT);
        salary = new Transaction("Salary June", new BigDecimal("2000.00"), TransactionType.DEBIT, myAccount);

    }

    @Test
    public void hasName() {assertEquals("Salary June", salary.getName());}

    @Test
    public void hasAmount() {assertEquals(new BigDecimal("2000.00"), salary.getAmount());}

    @Test
    public void hasTransActionType() {assertEquals(TransactionType.DEBIT, salary.getTransactionType());}

    @Test
    public void hasAccount() {assertEquals(myAccount, salary.getAccount());}

}
