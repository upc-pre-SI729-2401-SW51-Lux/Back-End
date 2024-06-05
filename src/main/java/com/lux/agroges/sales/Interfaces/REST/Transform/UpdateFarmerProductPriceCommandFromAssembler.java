package com.lux.agroges.sales.Interfaces.REST.Transform;

import com.lux.agroges.sales.Domain.Model.Commands.UpdateFarmerProductCommand;
import com.lux.agroges.sales.Interfaces.REST.Resources.UpdateFarmerProductPriceResource;

public class UpdateFarmerProductPriceCommandFromAssembler {
    public static UpdateFarmerProductCommand toCommandFromResource(UpdateFarmerProductPriceResource resource) {
        return new UpdateFarmerProductCommand(resource.id(),resource.productId(), resource.money(), resource.quantityProduct(), resource.amount(), resource.start(), resource.end());
    }
}
