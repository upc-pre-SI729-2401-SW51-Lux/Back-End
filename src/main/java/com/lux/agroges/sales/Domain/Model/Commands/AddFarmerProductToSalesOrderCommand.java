package com.lux.agroges.sales.Domain.Model.Commands;

public record AddFarmerProductToSalesOrderCommand(Long salesOrderId, Long farmerProductPriceId) {
}
