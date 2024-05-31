package com.lux.agroges.sales.Domain.Model.Commands;

import java.time.LocalDateTime;

public record UpdateFarmerProductCommand(Long FarmerProductPriceId,Long productId, String money,Long quantityProduct, Long amount, LocalDateTime start, LocalDateTime end) {

}
