package com.lux.agroges.sales.Interfaces.REST.Transform;

import com.lux.agroges.sales.Domain.Model.entities.SalesOrderItem;
import com.lux.agroges.sales.Interfaces.REST.Resources.SalesOrderItemResource;

public class SalesOrderItemFromResourceFromEntityAssembler {
    public static SalesOrderItemResource toResourceFromEntity(SalesOrderItem entity) {
        return new SalesOrderItemResource(entity.getId(), entity.getFarmerProductPrice().getId());
    }


}
