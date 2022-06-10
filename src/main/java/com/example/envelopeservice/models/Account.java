package com.example.envelopeservice.models;

import java.util.ArrayList;

public class Account {

    private String name;
    private AccountType accountType;
    private ArrayList<Envelope> envelopes;

    public Account(String name, AccountType accountType) {
        this.name = name;
        this.accountType = accountType;
        this.envelopes = new ArrayList<>();
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

    public void addEnvelope(Envelope envelope) {
        this.envelopes.add(envelope);
    }
}
