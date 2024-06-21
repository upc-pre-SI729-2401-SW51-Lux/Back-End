package com.lux.agroges.sales.Interfaces.REST.Transform;

import com.lux.agroges.sales.Domain.Model.Commands.AddFarmerProductToSalesOrderCommand;
import com.lux.agroges.sales.Interfaces.REST.Resources.AddFarmerProductToSalesOrderResource;

public class AddFarmerProductToSalesOrderCommandFromResourceAssembler {
    public static AddFarmerProductToSalesOrderCommand toCommandFromResource(Long salesOrderId,AddFarmerProductToSalesOrderResource resource) {
        return new AddFarmerProductToSalesOrderCommand(salesOrderId, resource.farmerProductPriceId());
    }
}
