package com.glory.teamaptproject.dto.response;

import java.math.BigDecimal;

public class TransactionResponse {
    private BigDecimal amount;
    private String reference;
    private String description;
    private BigDecimal newBalance;

    public TransactionResponse(BigDecimal amount, String reference, String description, BigDecimal newBalance) {
        this.amount = amount;
        this.reference = reference;
        this.description = description;
        this.newBalance = newBalance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }
}
