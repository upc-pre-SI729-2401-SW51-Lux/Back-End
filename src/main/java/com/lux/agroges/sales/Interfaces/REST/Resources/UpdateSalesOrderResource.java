package com.lux.agroges.sales.Interfaces.REST.Resources;


import java.time.LocalDateTime;

public record UpdateSalesOrderResource(Long ruc, LocalDateTime orderTimestamp, String invoiceId) {
}
