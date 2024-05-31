package com.lux.agroges.sales.Domain.Model.Commands;

import java.time.LocalDateTime;

public record CreateFarmerProductCommand(Long FarmerProductId,Long productId, String money,Long quantityProduct, Long amount, LocalDateTime start, LocalDateTime end) {
}
