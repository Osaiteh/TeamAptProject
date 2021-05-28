package com.glory.teamaptproject.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public class TransactionRequest {

    private Long destinationAccountNumber;

    private BigDecimal amount;

    private UUID reference;

    private String description;

    public Long getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(Long destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UUID getReference() {
        return reference;
    }

    public void setReference(UUID reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
