package com.example.envelopeservice.models;

public enum TransactionType {
    DEBIT("Debit"),
    CREDIT("Credit");

    private String typeName;

    TransactionType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }


}
