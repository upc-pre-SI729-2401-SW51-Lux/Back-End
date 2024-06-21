package com.lux.agroges.sales.Interfaces.REST.Transform;

import com.lux.agroges.sales.Domain.Model.Commands.CreateFarmerProductCommand;
import com.lux.agroges.sales.Interfaces.REST.Resources.CreateFarmerProductPriceResource;

public class CreateFarmerProductPriceCommandFromResourceAssembler {
    public static CreateFarmerProductCommand toCommandFromResource(CreateFarmerProductPriceResource resource) {
        return new CreateFarmerProductCommand( resource.productId(), resource.money(), resource.amount(), resource.start(), resource.end(), resource.quantityProduct())  ;
    }
}
