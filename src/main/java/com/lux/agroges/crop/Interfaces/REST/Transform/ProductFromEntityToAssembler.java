package com.lux.agroges.crop.Interfaces.REST.Transform;

import com.lux.agroges.crop.Domain.Model.aggregates.Product;
import com.lux.agroges.crop.Interfaces.REST.Resources.ProductResource;

public class ProductFromEntityToAssembler {
    public static ProductResource toResourceFromEntity(Product entity){
        return new ProductResource(entity.getId(),entity.getProductName().name(),entity.getStockProduct().stock());
    }
}
