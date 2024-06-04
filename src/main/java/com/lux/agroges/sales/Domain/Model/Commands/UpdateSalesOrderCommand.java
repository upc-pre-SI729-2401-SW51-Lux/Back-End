package com.lux.agroges.sales.Domain.Model.Commands;

public record UpdateSalesOrderCommand(Long SalesOrderId,Long FarmerProductId, String StateOfSalesOrder ) {
}
