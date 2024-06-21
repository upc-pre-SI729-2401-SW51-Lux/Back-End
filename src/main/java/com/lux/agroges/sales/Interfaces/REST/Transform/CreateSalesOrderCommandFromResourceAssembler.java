package com.lux.agroges.sales.Interfaces.REST.Transform;

import com.lux.agroges.sales.Domain.Model.Commands.CreateSalesOrderCommand;
import com.lux.agroges.sales.Interfaces.REST.Resources.CreateSalesOrderResource;

public class CreateSalesOrderCommandFromResourceAssembler {
    public static CreateSalesOrderCommand toCommandFromResource(CreateSalesOrderResource resource) {
        return new CreateSalesOrderCommand(resource.ruc(),resource.orderTimestamp(), resource.invoiceId());
    }
}
