package com.lux.agroges.sales.Domain.Model.Commands;

import java.time.LocalDateTime;

public record CreateSalesOrderCommand(Long ruc, LocalDateTime orderTimestamp, String invoiceId) {


}
