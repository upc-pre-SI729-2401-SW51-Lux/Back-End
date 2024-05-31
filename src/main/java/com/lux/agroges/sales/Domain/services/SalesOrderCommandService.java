package com.lux.agroges.sales.Domain.services;

import com.lux.agroges.sales.Domain.Model.Commands.*;

public interface SalesOrderCommandService {
    Long handle(CreateSalesOrderCommand command);
    void handle(UpdateSalesOrderCommand command);
    void handle(CancelSalesOrderCommand command);
    void handle(AddFarmerProductToSalesOrderCommand command);
    void handle(RemoveProductFromSalesOrderCommand command);
}
