package com.lux.agroges.finance.Domain.Model.Commands;

public record UpdateFarmerProductCommand(Long FarmerProductId,String productName,String productDescription,Long Price) {
}
