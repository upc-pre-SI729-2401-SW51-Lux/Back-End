package com.lux.agroges.sales.Domain.services;

import com.lux.agroges.sales.Domain.Model.Commands.*;
import com.lux.agroges.sales.Domain.Model.aggregates.SalesOrder;

import java.util.Optional;

public interface SalesOrderCommandService {
    Optional<SalesOrder> handle(CreateSalesOrderCommand command);
    void handle(UpdateSalesOrderCommand command);
    void handle(CancelSalesOrderCommand command);
    void handle(AddFarmerProductToSalesOrderCommand command);
    void handle(RemoveProductFromSalesOrderCommand command);

}
