package com.lux.agroges.crop.Interfaces.REST.Transform;

import com.lux.agroges.crop.Domain.Model.commands.AddProductToCropCommand;

public class AddProductToCropCommandFromResourceAssembler {
    public static AddProductToCropCommand toCommandFromResource(Long cropId,AddProductToCropCommand resource){
        return new AddProductToCropCommand(cropId,resource.productId());
    }
}
