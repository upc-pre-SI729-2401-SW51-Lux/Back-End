package com.lux.agroges.sales.Domain.Model.aggregates;


import com.lux.agroges.sales.Domain.Model.entities.SalesOrderItem;
import com.lux.agroges.sales.Domain.Model.valuobjects.OrderTimestamp;
import com.lux.agroges.sales.Domain.Model.valuobjects.RucFarmer;
import com.lux.agroges.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class SalesOrder extends AuditableAbstractAggregateRoot<SalesOrder> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
            {@AttributeOverride(name= "value", column =@Column(name="invoice_id"))}
    )
    private String invoiceId;

    @OneToMany(mappedBy  = "FarmerProductPrice", cascade = CascadeType.ALL)
    private List<SalesOrderItem> salesOrderItems;
    public SalesOrder(){
        this.orderTimestamp = new OrderTimestamp();
        this.ruc= new RucFarmer(0L);
        this.invoiceId = "";
        this.salesOrderItems= new ArrayList<>();
    }
    public SalesOrder( RucFarmer ruc, OrderTimestamp orderTimestamp, String invoiceId){
        this();
        this.ruc = ruc;
        this.orderTimestamp = orderTimestamp;
        this.invoiceId = invoiceId;
        this.salesOrderItems= new ArrayList<>();
    }
    public void addItem( FarmerProductPrice farmerProductPrice,SalesOrderItem nextItem ){
        System.out.println("Adding item to sales order");
        SalesOrderItem salesOrderItem = new SalesOrderItem(farmerProductPrice,nextItem);
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

        System.out.println("Item updated from sales order");


    }

    private SalesOrderItem getPreviousItem(SalesOrderItem salesOrderItem) {
        return salesOrderItems.stream().filter(item -> item.getNextItem() == salesOrderItem)
                .findFirst().orElse(null);
    }


    public  SalesOrderItem getLastItemSalesOrder(){
        return salesOrderItems.stream().filter(item -> item.getNextItem() == null)
                .findFirst().orElse(null);
    }
    public boolean isEmpty() {
        return salesOrderItems.isEmpty();
    }












}
