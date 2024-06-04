package com.lux.agroges.sales.Interfaces.REST.Resources;

import java.time.LocalDateTime;

public record SalesOrderResource(Long salesOrderId, Long ruc, LocalDateTime orderTimestamp, String invoiceId) {
    
}
