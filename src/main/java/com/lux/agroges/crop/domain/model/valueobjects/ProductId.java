package com.lux.agroges.crop.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProductId(Long productId) {
    public ProductId(){
        this(0L);
    }
    public ProductId {
        if (productId == null || productId < 0) {
            throw new IllegalArgumentException("Product Id cannot be null or negative");
        }

    }
}
//Creo que hay que hacer que este record esté en shared
