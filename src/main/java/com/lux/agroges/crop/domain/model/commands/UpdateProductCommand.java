package com.lux.agroges.crop.domain.model.commands;

public record UpdateProductCommand(Long productId,String currency,Long amount,Long stockProduct,String productName) {
}
