package com.lux.agroges.crop.Interfaces.REST.Transform;

import com.lux.agroges.crop.Domain.Model.commands.CreateProductCommand;
import com.lux.agroges.crop.Interfaces.REST.Resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommandFromResource(CreateProductResource resource){
        return new CreateProductCommand(resource.id(),resource.name(),resource.stockProduct());
    }
}
