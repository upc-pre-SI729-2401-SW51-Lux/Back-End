package com.lux.agroges.sales.Interfaces.REST.Transform;

import com.lux.agroges.sales.Domain.Model.aggregates.FarmerProductPrice;
import com.lux.agroges.sales.Interfaces.REST.Resources.FarmerProductPriceResource;

public class FarmerProductPriceFromEntityAssembler {
    public static FarmerProductPriceResource toResourceFromEntity(FarmerProductPrice entity) {
        return new FarmerProductPriceResource(entity.getId(), entity.getProductId().productId(), entity.getPrice().money(), entity.getPrice().amount(), entity.getValidityTimeFarmerProduct().start(), entity.getValidityTimeFarmerProduct().end(),entity.getQuantityProduct());
    }
}
