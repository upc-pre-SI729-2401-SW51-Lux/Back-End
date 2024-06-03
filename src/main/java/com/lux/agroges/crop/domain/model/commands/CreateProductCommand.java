package com.lux.agroges.crop.domain.model.commands;

public record CreateProductCommand(Long productId,String currency,Long amount,Long stockProduct,String productName) {
}
