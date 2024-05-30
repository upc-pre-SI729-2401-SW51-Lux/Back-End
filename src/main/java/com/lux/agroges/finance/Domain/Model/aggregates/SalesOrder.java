package com.lux.agroges.finance.Domain.Model.aggregates;


import com.lux.agroges.finance.Domain.Model.entities.SalesOrderItem;
import com.lux.agroges.finance.Domain.Model.valuobjects.OrderTimestamp;
import com.lux.agroges.finance.Domain.Model.valuobjects.RucFarmer;
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












}
