package com.lux.agroges.sales.Interfaces.REST.Transform;

import com.lux.agroges.sales.Domain.Model.aggregates.SalesOrder;
import com.lux.agroges.sales.Interfaces.REST.Resources.SalesOrderResource;

public class SalesOrderFromEntityAssembler {
    public static SalesOrderResource toResourceFromEntity(SalesOrder entity) {
        return new SalesOrderResource(entity.getId(), entity.getRuc().ruc(), entity.getOrderTimestamp().dateTime(), entity.getInvoiceId().InvoiceId());
    }
}
