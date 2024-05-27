package com.lux.agroges.crop.domain.model.valueobjects;

public record CropId(String idCrop) {
    public CropId(){
        this(null);
    }

    public CropId{
        if(idCrop ==null || idCrop.isEmpty()){
            throw new IllegalArgumentException("IdCrop cannot be null or empty");
        }
    }
    public String getID(){
        return idCrop;
    }
}