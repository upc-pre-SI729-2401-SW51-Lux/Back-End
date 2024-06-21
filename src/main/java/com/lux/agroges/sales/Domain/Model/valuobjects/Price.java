package com.lux.agroges.sales.Domain.Model.valuobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Price(String money,Long amount) {
    public Price {
        if (money == null || money.isBlank()) {
            throw new IllegalArgumentException("Money cannot be null or empty");
        }
        if (amount == null || amount < 0) {
            throw new IllegalArgumentException("Price value cannot be null or negative");
        }
    }
    public Price(){
        this("USD",0L);
    }
}
