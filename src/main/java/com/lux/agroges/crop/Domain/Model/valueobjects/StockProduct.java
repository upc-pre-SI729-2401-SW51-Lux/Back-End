package com.lux.agroges.crop.Domain.Model.valueobjects;

import jakarta.persistence.Embeddable;


@Embeddable
public record StockProduct(Long stock) {
    public StockProduct(){
        this(0L);
    }
    public StockProduct{
        if(stock==null|| stock>0){
            throw new IllegalArgumentException("ProductStock cannot be null or below 0");

        }
    }
}
