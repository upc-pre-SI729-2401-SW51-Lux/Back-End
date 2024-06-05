package com.lux.agroges.sales.Interfaces.REST.Resources;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateSalesOrderResource(Long SalesOrderId,Long FarmerProductId, String StateOfSalesOrder) {
}
