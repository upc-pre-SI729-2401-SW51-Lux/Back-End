package com.lux.agroges.crop.Interfaces.REST.Transform;

import com.lux.agroges.crop.Domain.Model.commands.UpdateCropCommand;
import com.lux.agroges.crop.Interfaces.REST.Resources.UpdateCropResource;

public class UpdateCropCommandFromResourceAssembler {
    public static UpdateCropCommand toCommandFromResource(Long Id, UpdateCropResource resource){
        return new UpdateCropCommand(Id,resource.cropCode(),resource.currency(),resource.value());
    }
}
