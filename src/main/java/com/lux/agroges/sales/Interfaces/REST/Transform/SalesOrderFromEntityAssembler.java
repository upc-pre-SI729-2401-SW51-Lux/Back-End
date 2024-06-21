package com.lux.agroges.sales.Interfaces.REST.Transform;

import com.lux.agroges.sales.Domain.Model.aggregates.SalesOrder;
import com.lux.agroges.sales.Interfaces.REST.Resources.SalesOrderResource;

import java.util.List;
import java.util.stream.Collectors;

public class SalesOrderFromEntityAssembler {
    public static SalesOrderResource toResourceFromEntity(SalesOrder entity) {
                List<Long> farmerProductPriceIds = entity.getSalesOrderItems().stream()
                .map(SalesOrderItem-> SalesOrderItem.getFarmerProductPrice().getId())
                .toList();
        return new SalesOrderResource(entity.getId(), entity.getRuc().ruc(), entity.getOrderTimestamp().dateTime(), entity.getInvoiceId().InvoiceId(),farmerProductPriceIds);

    }
}
