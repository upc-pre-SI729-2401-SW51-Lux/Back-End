package com.lux.agroges.sales.Application.Internal.CommandServices;

import com.lux.agroges.sales.Domain.Model.Commands.*;
import com.lux.agroges.sales.Domain.Model.aggregates.FarmerProductPrice;
import com.lux.agroges.sales.Domain.Model.aggregates.SalesOrder;
import com.lux.agroges.sales.Domain.Model.entities.SalesOrderItem;
import com.lux.agroges.sales.Domain.Model.valuobjects.InvoiceId;
import com.lux.agroges.sales.Domain.services.SalesOrderCommandService;
import com.lux.agroges.sales.Infrastructure.persistence.jpa.Repositories.FarmerProductRepository;
import com.lux.agroges.sales.Infrastructure.persistence.jpa.Repositories.SalesOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Optional<SalesOrder> handle(CreateSalesOrderCommand command) {
        if(salesOrderRepository.existsByInvoiceId(new InvoiceId(command.invoiceId()))){
            throw new IllegalArgumentException("Invoice already exists");
        }

    var salesOrder = new SalesOrder(command.ruc(),  command.orderTimestamp(), command.invoiceId());
    try {
        salesOrder = salesOrderRepository.save(salesOrder);
    } catch (Exception e) {
        throw new IllegalArgumentException("Error saving sales order: " + e.getMessage());
    }
    return Optional.of(salesOrder);
}

    @Override
    @Transactional
    public void handle(UpdateSalesOrderCommand command) {

    SalesOrder salesOrder = salesOrderRepository.findById(command.SalesOrderId())
        .orElseThrow(() -> new IllegalArgumentException("Sales Order not found"));


    FarmerProductPrice updatedProductPrice = farmerProductRepository.findById(command.FarmerProductId())
        .orElseThrow(() -> new IllegalArgumentException("Farmer Product Price not found"));


    SalesOrderItem itemToUpdate = salesOrder.getSalesOrderItems().stream()
        .filter(item -> item.getFarmerProductPrice().getId().equals(command.FarmerProductId()))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Sales Order Item not found"));
    itemToUpdate.setFarmerProductPrice(updatedProductPrice);
    salesOrderRepository.save(salesOrder);

    }

    @Override
    public void handle(CancelSalesOrderCommand command) {
            SalesOrder salesOrder = salesOrderRepository.findById(command.SalesOrderId())
        .orElseThrow(() -> new IllegalArgumentException("Sales Order not found"));
            salesOrderRepository.delete(salesOrder);
    }

    @Override
    public void handle(AddFarmerProductToSalesOrderCommand command) {
    SalesOrder salesOrder = salesOrderRepository.findById(command.salesOrderId())
            .orElseThrow(() -> new IllegalArgumentException("Sales Order not found"));

    var Product = farmerProductRepository.findById(command.farmerProductPriceId());
    if (Product.isPresent()){
            FarmerProductPrice farmerProductPrice = Product.get();
            SalesOrderItem salesOrderItem = new SalesOrderItem(farmerProductPrice, null);
            salesOrder.addItem(farmerProductPrice, salesOrderItem);
            salesOrderRepository.save(salesOrder);
    }
    else {
        throw new IllegalArgumentException("FarmerProductPrice not found");
    }
    }
    @Override
    public void handle(RemoveProductFromSalesOrderCommand command) {
    SalesOrder salesOrder = salesOrderRepository.findById(command.SalesOrderId())
        .orElseThrow(() -> new IllegalArgumentException("Sales Order not found"));
    FarmerProductPrice removeProductPrice = farmerProductRepository.findById(command.farmerProductPriceId())
        .orElseThrow(() -> new IllegalArgumentException("Farmer Product Price not found"));
    SalesOrderItem itemToRemove = salesOrder.getSalesOrderItems().stream()
        .filter(item -> item.getFarmerProductPrice().equals(removeProductPrice))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Sales Order Item not found"));
    salesOrder.removeItem(itemToRemove);
    salesOrderRepository.save(salesOrder);
    }
}
