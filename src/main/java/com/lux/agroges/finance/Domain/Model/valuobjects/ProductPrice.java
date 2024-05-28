package com.lux.agroges.finance.Domain.Model.valuobjects;
import jakarta.persistence.Embeddable;

@Embeddable
public record ProductPrice(Long Price) {
    public ProductPrice {
        if (Price == null || Price < 0) {
            throw new IllegalArgumentException("ProductPrice cannot be null");
        }
    }
}
