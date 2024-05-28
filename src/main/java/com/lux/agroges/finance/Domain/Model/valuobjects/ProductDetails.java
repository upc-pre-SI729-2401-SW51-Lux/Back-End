package com.lux.agroges.finance.Domain.Model.valuobjects;
import jakarta.persistence.Embeddable;


@Embeddable
public record ProductDetails(String productName,String productDescription) {
    public ProductDetails{
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name must not be empty");
        }
        if (productDescription == null || productDescription.isBlank()) {
            throw new IllegalArgumentException("Product description must not be empty");
        }

    }
}
