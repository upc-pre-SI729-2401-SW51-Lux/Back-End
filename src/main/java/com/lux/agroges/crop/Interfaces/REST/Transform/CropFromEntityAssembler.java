package com.lux.agroges.crop.Interfaces.REST.Transform;

import com.lux.agroges.crop.Domain.Model.aggregates.Crop;
import com.lux.agroges.crop.Interfaces.REST.Resources.CropResource;

import java.util.List;

public class CropFromEntityAssembler {
    public static CropResource toResourceFromEntity(Crop entity){
        List<Long> productIds=entity.getCropItems().stream()
                .map(CropItem->CropItem.getProduct().getId())
                .toList();
        return new CropResource(entity.getId(),entity.getCropCode().cropCode(),entity.getCropCost().currency(),entity.getCropCost().amount(),productIds);
    }
}
