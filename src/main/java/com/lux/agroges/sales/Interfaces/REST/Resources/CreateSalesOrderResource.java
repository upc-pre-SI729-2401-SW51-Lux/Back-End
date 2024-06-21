package com.lux.agroges.sales.Interfaces.REST.Resources;

import java.time.LocalDateTime;
import java.util.List;

public record CreateSalesOrderResource(Long ruc, LocalDateTime orderTimestamp, String invoiceId) {
}
