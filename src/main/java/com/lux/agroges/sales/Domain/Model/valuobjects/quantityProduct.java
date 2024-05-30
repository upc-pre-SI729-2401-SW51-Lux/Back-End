package com.lux.agroges.sales.Domain.Model.valuobjects;
import jakarta.persistence.Embeddable;

@Embeddable
public record quantityProduct(Long quantity) {
    public quantityProduct {
        if (quantity == null || quantity < 0) {
            throw new IllegalArgumentException("quantityProduct cannot be null");
        }
    }
}
