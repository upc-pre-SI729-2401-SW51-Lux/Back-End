package com.lux.agroges.crop.Interfaces.REST.Transform;

import com.lux.agroges.crop.Domain.Model.commands.UpdateCropCommand;
import com.lux.agroges.crop.Interfaces.REST.Resources.UpdateCropResource;

public class UpdateCropCommandFromResourceAssembler {
    public static UpdateCropCommand toCommandFromResource(Long cropId, UpdateCropResource resource){
        return new UpdateCropCommand(cropId,resource.cropCode(),resource.currency(),resource.value());
    }
}
