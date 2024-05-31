package com.lux.agroges.sales.Domain.services;

import com.lux.agroges.sales.Domain.Model.aggregates.FarmerProductPrice;
import com.lux.agroges.sales.Domain.Model.aggregates.SalesOrder;

import java.util.List;

public interface SalesOrderQueryService {
    SalesOrder findById(Long salesOrderId);
    List<SalesOrder> findAll();
    List<SalesOrder> findByRucId(String rucId);
    List<FarmerProductPrice> findFarmerProductPricesBySalesOrderId(Long salesOrderId);
}
