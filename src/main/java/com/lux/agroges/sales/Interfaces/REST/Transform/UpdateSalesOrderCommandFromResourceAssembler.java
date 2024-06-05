package com.lux.agroges.sales.Interfaces.REST.Transform;

import com.lux.agroges.sales.Domain.Model.Commands.UpdateSalesOrderCommand;
import com.lux.agroges.sales.Interfaces.REST.Resources.UpdateSalesOrderResource;

public class UpdateSalesOrderCommandFromResourceAssembler {
    public static UpdateSalesOrderCommand toCommandFromResource(UpdateSalesOrderResource resource) {
        return new UpdateSalesOrderCommand(resource.SalesOrderId(),resource.FarmerProductId(), resource.StateOfSalesOrder());
    }
}
