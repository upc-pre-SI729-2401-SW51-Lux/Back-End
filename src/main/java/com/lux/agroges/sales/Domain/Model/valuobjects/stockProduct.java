package com.lux.agroges.sales.Domain.Model.valuobjects;

public record stockProduct(Long stockProduct) {
    public stockProduct {
        if (stockProduct == null || stockProduct < 0) {
            throw new IllegalArgumentException("stockProduct cannot be null");
        }
    }
}
