package com.lux.agroges.crop.Domain.Model.commands;

public record UpdateProductCommand(Long productId,Long stockProduct,String productName) {
}
