package com.example.envelopeservice.models;

public enum AccountType {
    CURRENT("Current"),
    SAVINGS("Savings");

    private String typeName;

    AccountType(String typeName){
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
