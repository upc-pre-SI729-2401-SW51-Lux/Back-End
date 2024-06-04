package com.lux.agroges.crop.domain.model.commands;

public record UpdateProductCommand(Long productId,Long stockProduct,String productName) {
}
