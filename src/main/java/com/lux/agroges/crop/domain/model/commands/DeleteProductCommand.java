package com.lux.agroges.crop.domain.model.commands;

public record DeleteProductCommand(Long productId,Long stockProduct,String productName) {
}
