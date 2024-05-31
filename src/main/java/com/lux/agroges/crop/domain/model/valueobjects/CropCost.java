package com.lux.agroges.crop.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CropCost(String currency,Long value) {
    //currency hace referencia a la moneda, en este caso dolares
    //consideramos implementar este value object en sales pero iría en contra de las practicas del DDD

    public CropCost(){this("USD",0L);}
    public CropCost{
        if(currency==null || currency.isBlank()){
            throw new IllegalArgumentException("The currency cannot be null or empty");
        }
        if(value==null || value<0){
            throw new IllegalArgumentException("The value cannot be null or negative");
        }
    }
}
