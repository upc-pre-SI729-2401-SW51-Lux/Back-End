package com.lux.agroges.sales.Domain.services;

import com.lux.agroges.sales.Domain.Model.Commands.*;
import com.lux.agroges.sales.Domain.Model.aggregates.SalesOrder;
import com.lux.agroges.sales.Domain.Model.entities.SalesOrderItem;

import java.util.List;
import java.util.Optional;

public interface SalesOrderCommandService {
    Optional<SalesOrder> handle(CreateSalesOrderCommand command);
    Optional<SalesOrder> handle(UpdateSalesOrderCommand command);
    void handle(CancelSalesOrderCommand command);
    void handle(AddFarmerProductToSalesOrderCommand command);
    void handle(RemoveProductFromSalesOrderCommand command);
}
