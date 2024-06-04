package com.lux.agroges.crop.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
//Crop code is used as a key name to refer as the zone
public record CropCode(String cropCode) {
    public CropCode {
        if (cropCode == null || cropCode.isBlank()) {
            throw new IllegalArgumentException("Crop code cannot be null");
        }
    }
}
