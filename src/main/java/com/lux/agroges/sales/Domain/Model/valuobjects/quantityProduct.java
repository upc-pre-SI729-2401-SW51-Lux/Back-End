package com.lux.agroges.sales.Domain.Model.valuobjects;
import jakarta.persistence.Embeddable;

@Embeddable
public record quantityProduct(Long quantityProduct) {
    public quantityProduct(){
        this(0L);
    }
    public quantityProduct {
        if (quantityProduct == null || quantityProduct < 0) {
            throw new IllegalArgumentException("quantityProduct cannot be null");
        }
    }
}
