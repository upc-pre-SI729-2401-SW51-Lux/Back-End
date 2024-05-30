package com.lux.agroges.crop.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CropCode(String code) {
    public CropCode(){this(null);}
}
/**
 * @Summary this record makes a
 * **/