package com.lux.agroges.sales.Domain.Model.Commands;

public record RemoveProductFromSalesOrderCommand(Long salesOrderId, Long farmerProductPriceId) {

}
