package com.lux.agroges.sales.Interfaces.REST.Resources;

import java.time.LocalDateTime;

public record CreateFarmerProductPriceResource(Long productId, String money, Long quantityProduct, Long amount, LocalDateTime start, LocalDateTime end) {
}
