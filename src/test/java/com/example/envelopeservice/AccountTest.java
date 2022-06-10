package com.example.envelopeservice;

import com.example.envelopeservice.models.Account;
import com.example.envelopeservice.models.AccountType;
import com.example.envelopeservice.models.Envelope;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {
    Account myAccount;
    Envelope travel;
    Envelope groceries;


    @Before
    public void before() {
        myAccount = new Account("My Account", AccountType.CURRENT);
        travel = new Envelope("Travel");
        groceries = new Envelope("Groceries");
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

}
