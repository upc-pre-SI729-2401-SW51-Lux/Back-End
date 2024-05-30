package com.lux.agroges.finance.Domain.Model.valuobjects;

public record Price(String money,Long pricedValue) {
    public Price {
        if (money == null || money.isBlank()) {
            throw new IllegalArgumentException("Money cannot be null or empty");
        }
        if (pricedValue == null || pricedValue < 0) {
            throw new IllegalArgumentException("Price value cannot be null or negative");
        }
    }
    public Price(){
        this("USD",0L);
    }
}
