package com.lux.agroges.crop.domain.model.valueobjects;

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
