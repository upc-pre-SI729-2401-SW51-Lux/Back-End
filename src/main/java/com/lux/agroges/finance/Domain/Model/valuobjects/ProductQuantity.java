package com.lux.agroges.finance.Domain.Model.valuobjects;
import jakarta.persistence.Embeddable;

@Embeddable
public record ProductQuantity(Integer quantity) {
    public ProductQuantity {
        if (quantity == null || quantity < 0) {
            throw new IllegalArgumentException("ProductQuantity cannot be null");
        }
    }
}
