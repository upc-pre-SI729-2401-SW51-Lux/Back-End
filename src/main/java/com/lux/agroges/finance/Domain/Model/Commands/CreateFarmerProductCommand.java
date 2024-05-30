package com.lux.agroges.finance.Domain.Model.Commands;

public record CreateFarmerProductCommand(String productName,String productDescription,Long Price) {
}
