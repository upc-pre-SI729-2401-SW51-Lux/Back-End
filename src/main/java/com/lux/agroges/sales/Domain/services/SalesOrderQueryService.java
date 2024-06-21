package com.lux.agroges.sales.Domain.services;

import com.lux.agroges.sales.Domain.Model.Queries.GetAllSalesOrderQuery;

import com.lux.agroges.sales.Domain.Model.Queries.GetSalesOrderItemsBySalesOrderId;
import com.lux.agroges.sales.Domain.Model.Queries.SalesOrderByOrderIdQuery;
import com.lux.agroges.sales.Domain.Model.Queries.SalesOrdersByCustomerIdQuery;
import com.lux.agroges.sales.Domain.Model.aggregates.SalesOrder;
import com.lux.agroges.sales.Domain.Model.entities.SalesOrderItem;


import java.util.List;
import java.util.Optional;

public interface SalesOrderQueryService {
    Optional<SalesOrder> handle (SalesOrderByOrderIdQuery query);
    List<SalesOrder> handle (GetAllSalesOrderQuery query);
    List<SalesOrder> handle (SalesOrdersByCustomerIdQuery query);
    List <SalesOrderItem> handle (GetSalesOrderItemsBySalesOrderId query);


}
