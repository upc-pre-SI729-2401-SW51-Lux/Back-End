package com.lux.agroges.finance.Domain.Model.entities;

import com.lux.agroges.finance.Domain.Model.aggregates.FarmerProduct;
import com.lux.agroges.finance.Domain.Model.valuobjects.ProductDetails;
import com.lux.agroges.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class SalesOrderItem extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sales_order_id")
    @NotNull
    private FarmerProduct product;
    @NotNull
    private Long salesOrderId;

    @ManyToOne
    @JoinColumn(name = "sales_order_item_id")

    @NotNull
    private SalesOrderItem nextItem;



    public SalesOrderItem(FarmerProduct product,Long salesOrderId, SalesOrderItem nextItem) {
        this.product = product;
        this.nextItem = nextItem;
        this.salesOrderId = salesOrderId;
    }
    public SalesOrderItem(){
        this.salesOrderId = 0L;
        this.nextItem = null;

    }
    public void SalesOrderItem(SalesOrderItem nextItem){;
        this.nextItem = nextItem;
    }


}
