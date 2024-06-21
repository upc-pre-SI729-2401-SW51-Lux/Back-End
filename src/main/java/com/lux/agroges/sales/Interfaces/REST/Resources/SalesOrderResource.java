package com.lux.agroges.sales.Interfaces.REST.Resources;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record SalesOrderResource(Long id,Long ruc, LocalDateTime orderTimestamp, String invoiceId, List<Long> farmerProductPriceIds) {
        public SalesOrderResource(Long id, Long ruc, LocalDateTime orderTimestamp, String invoiceId) {
        this(id, ruc, orderTimestamp, invoiceId, new ArrayList<>());

    }
    
}
