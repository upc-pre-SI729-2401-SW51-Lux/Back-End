package com.lux.agroges.crop.domain.model.valueobjects;

public record ProductPrice(String currency,Long amount) {
    public ProductPrice{
        if(currency==null||currency.isBlank()){
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }
        if(amount==null||amount<0){
            throw new IllegalArgumentException("Amount cannot be null or below 0");
        }
    }
    public ProductPrice(){this(null,0L);}

    public String getPrice(){
        return String.format("%s %s",currency,amount);
    }
}
