package com.lux.agroges.finance.Domain.Model.valuobjects;
import jakarta.persistence.Embeddable;

@Embeddable
public record InvoiceId(String value) {
    public InvoiceId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("InvoiceId cannot be null");
        }
    }

}
