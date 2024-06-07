package com.lux.agroges.crop.Interfaces.REST.Transform;


import com.lux.agroges.crop.Domain.Model.aggregates.Crop;
import com.lux.agroges.crop.Domain.Model.entities.CropItem;
import com.lux.agroges.crop.Interfaces.REST.Resources.CropItemResource;

public class CropItemFromResourceToEntityAssembler {
    public static CropItemResource toResourceFromEntity(CropItem entity){
        return new CropItemResource(entity.getEid(),entity.getProduct().getId());
    }
}
