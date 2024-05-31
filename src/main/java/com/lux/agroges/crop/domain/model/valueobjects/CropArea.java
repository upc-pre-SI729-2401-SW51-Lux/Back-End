package com.lux.agroges.crop.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CropArea(Long area) {
    public CropArea(){this(0L);}
    public CropArea{
        if(area == null|| area<0 ){
            throw new IllegalArgumentException("Area cannot be null or negative");
        }
    }
}
