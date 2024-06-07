package com.lux.agroges.crop.Domain.Model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProductName(String name) {
    public ProductName(){this(null);}
    public ProductName{
        if(name==null ||name.isBlank()){
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
    }
}
