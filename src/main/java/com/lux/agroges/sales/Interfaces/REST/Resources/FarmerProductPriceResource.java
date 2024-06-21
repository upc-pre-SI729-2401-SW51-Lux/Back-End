package com.lux.agroges.sales.Interfaces.REST.Resources;

import java.time.LocalDateTime;

public record FarmerProductPriceResource(Long id, Long productId, String money, Long amount, LocalDateTime start, LocalDateTime end, Long quantityProduct) {
}
