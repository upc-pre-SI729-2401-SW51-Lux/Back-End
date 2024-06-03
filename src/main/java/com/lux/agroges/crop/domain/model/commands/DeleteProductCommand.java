package com.lux.agroges.crop.domain.model.commands;

public record DeleteProductCommand(Long productId,String currency,Long amount,Long stockProduct,String productName) {
}
