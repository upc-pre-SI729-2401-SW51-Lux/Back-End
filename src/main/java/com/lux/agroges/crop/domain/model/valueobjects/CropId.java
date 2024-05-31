package com.lux.agroges.crop.domain.model.valueobjects;

public record CropId(Long id) {
    public CropId {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Crop id cannot be null or below 0");
        }
    }
    public CropId(){this(0L);}
}
