package com.lux.agroges.sales.Application.Internal.QueryServices;

import com.lux.agroges.sales.Domain.Model.Queries.GetAllSalesOrderQuery;
import com.lux.agroges.sales.Domain.Model.Queries.GetSalesOrderItemsBySalesOrderId;
import com.lux.agroges.sales.Domain.Model.Queries.SalesOrderByOrderIdQuery;
import com.lux.agroges.sales.Domain.Model.Queries.SalesOrdersByCustomerIdQuery;
import com.lux.agroges.sales.Domain.Model.aggregates.SalesOrder;
import com.lux.agroges.sales.Domain.Model.entities.SalesOrderItem;
import com.lux.agroges.sales.Domain.Model.valuobjects.RucFarmer;
import com.lux.agroges.sales.Domain.services.SalesOrderQueryService;
import com.lux.agroges.sales.Infrastructure.persistence.jpa.Repositories.SalesOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SalesOrderQueryServiceImpl implements SalesOrderQueryService {
    private final SalesOrderRepository salesOrderRepository;

    public SalesOrderQueryServiceImpl(SalesOrderRepository salesOrderRepository) {
        this.salesOrderRepository = salesOrderRepository;
    }

    @Override
    public Optional<SalesOrder> handle(SalesOrderByOrderIdQuery query) {
        return salesOrderRepository.findById(query.salesOrderId());
    }

    @Override
    public List<SalesOrder> handle(GetAllSalesOrderQuery query) {
        return salesOrderRepository.findAll();
    }

    @Override
    public List<SalesOrder> handle(SalesOrdersByCustomerIdQuery query) {
        return salesOrderRepository.findByRuc(new RucFarmer(query.ruc()));
    }

    @Override
    public List<SalesOrderItem> handle(GetSalesOrderItemsBySalesOrderId query) {

        return salesOrderRepository.findById(query.salesOrderId()).map(SalesOrder::getSalesOrderItems).orElse(null);
    }


}
