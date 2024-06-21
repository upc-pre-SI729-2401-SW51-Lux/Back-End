package com.lux.agroges.sales.Domain.Model.entities;

import com.lux.agroges.sales.Domain.Model.aggregates.FarmerProductPrice;
import com.lux.agroges.sales.Domain.Model.aggregates.SalesOrder;
import com.lux.agroges.shared.domain.Model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SalesOrderItem extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "farmer_product_price_id")
    @NotNull
    private FarmerProductPrice farmerProductPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "next_item_id")
    private SalesOrderItem nextItem;

    @ManyToOne
    @JoinColumn(name = "sales_order_id")
    private SalesOrder salesOrder;



    public SalesOrderItem(FarmerProductPrice farmerProductPrice, SalesOrderItem nextItem) {
        this.farmerProductPrice = farmerProductPrice;
        this.nextItem = nextItem;


    }
    public SalesOrderItem(){

        this.nextItem = null;

    }



    public void updateNextItem(SalesOrderItem nextItem) {
        this.nextItem = nextItem;
    }






}
