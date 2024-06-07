package com.lux.agroges.crop.Interfaces.REST.Transform;

import com.lux.agroges.crop.Domain.Model.commands.CreateCropCommand;
import com.lux.agroges.crop.Interfaces.REST.Resources.CreateCropResource;
public class CreateCropCommandFromResourceAssembler {
    public static CreateCropCommand toCommandFromResource(CreateCropResource resource){
        return new CreateCropCommand(resource.cropId(),resource.cropCode(),resource.currency(),resource.value());
    }

}
