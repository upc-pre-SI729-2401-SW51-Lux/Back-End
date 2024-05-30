package com.lux.agroges.finance.Domain.Model.Commands;

import java.time.LocalDateTime;

public record CreateFarmerProductCommand(Long productId, String money, Long amount, LocalDateTime start, LocalDateTime end) {
}
