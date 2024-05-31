package com.lux.agroges.sales.Domain.services;

import com.lux.agroges.sales.Domain.Model.Queries.GetAllFarmerProductPriceQuery;
import com.lux.agroges.sales.Domain.Model.Queries.GetFarmerProductByIdQuery;

import com.lux.agroges.sales.Domain.Model.Queries.GetSalesOrderItemByFarmerProductId;
import com.lux.agroges.sales.Domain.Model.aggregates.FarmerProductPrice;
import com.lux.agroges.sales.Domain.Model.entities.SalesOrderItem;

import java.util.List;
import java.util.Optional;

public interface FarmerProductQueryService {
    Optional<FarmerProductPrice> handle (GetFarmerProductByIdQuery query);
    List<FarmerProductPrice> handle(GetAllFarmerProductPriceQuery query);
    Optional<SalesOrderItem> handle (GetSalesOrderItemByFarmerProductId query);
}
