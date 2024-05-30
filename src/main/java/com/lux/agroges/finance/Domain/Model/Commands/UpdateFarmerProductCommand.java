package com.lux.agroges.finance.Domain.Model.Commands;

import java.time.LocalDateTime;

public record UpdateFarmerProductCommand(String money, Long amount, LocalDateTime start, LocalDateTime end) {
}
