package com.lux.agroges.finance.Domain.Model.Commands;

public record AddFarmerProductToSalesOrderCommand(Long salesOrderId, Long productId) {
}
