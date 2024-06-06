package com.lux.agroges.crop.Domain.Model.commands;

public record DeleteProductCommand(Long productId,Long stockProduct,String productName) {
}
