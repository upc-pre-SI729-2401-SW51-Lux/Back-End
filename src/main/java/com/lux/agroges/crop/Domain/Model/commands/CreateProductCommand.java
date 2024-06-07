package com.lux.agroges.crop.Domain.Model.commands;

public record CreateProductCommand(Long productId,String productName,Long stockProduct) {
}
