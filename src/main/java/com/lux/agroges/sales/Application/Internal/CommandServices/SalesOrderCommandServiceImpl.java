package com.lux.agroges.sales.Application.Internal.CommandServices;

import com.lux.agroges.sales.Domain.Model.Commands.*;
import com.lux.agroges.sales.Domain.Model.aggregates.FarmerProductPrice;
import com.lux.agroges.sales.Domain.Model.aggregates.SalesOrder;
import com.lux.agroges.sales.Domain.Model.entities.SalesOrderItem;
import com.lux.agroges.sales.Domain.services.SalesOrderCommandService;
import com.lux.agroges.sales.Infrastructure.persistence.jpa.Repositories.FarmerProductRepository;
import com.lux.agroges.sales.Infrastructure.persistence.jpa.Repositories.SalesOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SalesOrderCommandServiceImpl implements SalesOrderCommandService {
    private final SalesOrderRepository salesOrderRepository;
    private final FarmerProductRepository farmerProductRepository;


    public SalesOrderCommandServiceImpl(SalesOrderRepository salesOrderRepository, FarmerProductRepository farmerProductRepository) {
        this.salesOrderRepository = salesOrderRepository;

        this.farmerProductRepository = farmerProductRepository;
    }
    @Override
    @Transactional
    public Long handle(CreateSalesOrderCommand command) {
    // Verifica si la orden de venta ya existe
    if (salesOrderRepository.existsById(command.salesOrderId())) {
        throw new IllegalArgumentException("Sales Order already exists");
    }

    // Crea una nueva orden de venta a partir del comando
    var salesOrder = new SalesOrder(command.ruc(), command.orderTimestamp(), command.invoiceId());
    // Intenta guardar la orden de venta
    try {
        salesOrder = salesOrderRepository.save(salesOrder);
    } catch (Exception e) {
        throw new IllegalArgumentException("Error saving sales order: " + e.getMessage());
    }

    // Devuelve el ID de la orden de venta
    return salesOrder.getId();
}

    @Override
    public void handle(UpdateSalesOrderCommand command) {


    }

    @Override
    public void handle(CancelSalesOrderCommand command) {

    }

    @Override
    public void handle(AddFarmerProductToSalesOrderCommand command) {
    SalesOrder salesOrder = salesOrderRepository.findById(command.salesOrderId())
            .orElseThrow(() -> new IllegalArgumentException("Sales Order not found"));
    // Aquí puedes agregar un nuevo ítem a la orden de venta
    // Por ejemplo:
    FarmerProductPrice farmerProductPrice = new FarmerProductPrice(farmerProductRepository.findById(command.FarmerProductPriceId()));
    SalesOrderItem salesOrderItem = new SalesOrderItem(farmerProductPrice, null);
    salesOrder.addItem(farmerProductPrice, salesOrderItem);
    salesOrderRepository.save(salesOrder);
    }

    @Override
    public void handle(RemoveProductFromSalesOrderCommand command) {

    }


}
