package com.lux.agroges.sales.Domain.Model.aggregates;


import com.lux.agroges.sales.Domain.Model.entities.SalesOrderItem;
import com.lux.agroges.sales.Domain.Model.valuobjects.InvoiceId;
import com.lux.agroges.sales.Domain.Model.valuobjects.OrderTimestamp;
import com.lux.agroges.sales.Domain.Model.valuobjects.RucFarmer;
import com.lux.agroges.shared.domain.Model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class SalesOrder extends AuditableAbstractAggregateRoot<SalesOrder> {

    @Embedded
    @AttributeOverrides(
            {@AttributeOverride(name= "rucFarmer", column =@Column(name="ruc_farmer"))}
    )
    private RucFarmer ruc;
    @Embedded
    @AttributeOverrides(
            {@AttributeOverride(name= "dateTime", column =@Column(name="order_timestamp"))}
    )
    private OrderTimestamp orderTimestamp;
    @Embedded
    @AttributeOverrides(
            {@AttributeOverride(name= "invoice", column =@Column(name="invoice_id"))}
    )
    private InvoiceId invoiceId;
    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL)
    private List<SalesOrderItem> salesOrderItems;
    public SalesOrder(){
        this.orderTimestamp = new OrderTimestamp();
        this.ruc= new RucFarmer(0L);
        this.invoiceId = new InvoiceId("");
        this.salesOrderItems= new ArrayList<>();
    }
    public SalesOrder(Long ruc, LocalDateTime orderTimestamp, String invoiceId){
        this();
        this.ruc = new RucFarmer(ruc);
        this.orderTimestamp = new OrderTimestamp(orderTimestamp);
        this.invoiceId = new InvoiceId(invoiceId);
        this.salesOrderItems= new ArrayList<>();
    }
    public void addItem( FarmerProductPrice farmerProductPrice,SalesOrderItem nextItem ){
      System.out.println("Adding item to sales order");
        SalesOrderItem salesOrderItem = new SalesOrderItem(farmerProductPrice, nextItem);
        this.salesOrderItems.add(salesOrderItem);

    }
    public void addItem( FarmerProductPrice farmerProductPrice){
        System.out.println("Adding item to sales order");
        SalesOrderItem salesOrderItem = new SalesOrderItem(farmerProductPrice,null);
        SalesOrderItem originalLastItem = null;
        if(!salesOrderItems.isEmpty()) originalLastItem = getLastItemSalesOrder();
        else {
            System.out.println("Sales order is empty");
        }
        salesOrderItems.add(salesOrderItem);
        System.out.println("Item added to sales order");
        if(originalLastItem != null) originalLastItem.updateNextItem(salesOrderItem);

    }
    public void addItem(FarmerProductPrice farmerProductPrice, Long salesOrderItemId){
         SalesOrderItem nextItem= getSalesOrderItemById(salesOrderItemId);
         addItem(farmerProductPrice,nextItem);

     }

    public void removeItem(SalesOrderItem salesOrderItem){
        System.out.println("Removing item from sales order");
        SalesOrderItem previousItem = getPreviousItem(salesOrderItem);
        SalesOrderItem nextItem = salesOrderItem.getNextItem();
        if(previousItem != null) previousItem.updateNextItem(nextItem);
        else salesOrderItems.remove(salesOrderItem);
        System.out.println("Item removed from sales order");
    }
    public void updateItem(Long itemId,SalesOrderItem salesOrderItem, FarmerProductPrice farmerProductPrice){
        System.out.println("Updating item from sales order");
        SalesOrderItem previousItem = getPreviousItem(salesOrderItem);
        SalesOrderItem nextItem = salesOrderItem.getNextItem();
    if (previousItem != null) {
        previousItem.updateNextItem(nextItem);
    } else {
        var firstItem = salesOrderItems.stream()
                .filter(item -> item.getNextItem() == salesOrderItem)
                .findFirst();
        firstItem.ifPresent(orderItem -> orderItem.updateNextItem(nextItem));
    }

        System.out.println("Item updated from sales order");



    }
    private SalesOrderItem getSalesOrderItemById(Long itemId){
        return salesOrderItems.stream().filter(item -> item.getId().equals(itemId))
                .findFirst().orElse(null);
    }


    private SalesOrderItem getPreviousItem(SalesOrderItem salesOrderItem) {
        return salesOrderItems.stream().filter(item -> item.getNextItem() == salesOrderItem)
                .findFirst().orElse(null);
    }


    public  SalesOrderItem getLastItemSalesOrder(){
        return salesOrderItems.stream().filter(item -> item.getNextItem() == null)
                .findFirst().orElse(null);
    }
    public SalesOrder updateSalesOrder(Long ruc, LocalDateTime orderTimestamp, String invoiceId){
        this.ruc = new RucFarmer(ruc);
        this.orderTimestamp = new OrderTimestamp(orderTimestamp);
        this.invoiceId = new InvoiceId(invoiceId);
        return this;
    }
    public boolean isEmpty() {
        return salesOrderItems.isEmpty();
    }












}
