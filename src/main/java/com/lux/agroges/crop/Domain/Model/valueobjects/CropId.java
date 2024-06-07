package com.lux.agroges.crop.Domain.Model.valueobjects;

public record CropId(Long cropId) {
    public CropId {
        if (cropId == null || cropId < 0) {
            throw new IllegalArgumentException("Crop id cannot be null or below 0");
        }
    }
    public CropId(){this(0L);}
}
