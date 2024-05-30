package com.lux.agroges.finance.Domain.Model.aggregates;


import com.lux.agroges.finance.Domain.Model.valuobjects.InvoiceId;
import com.lux.agroges.finance.Domain.Model.valuobjects.OrderTimestamp;
import com.lux.agroges.finance.Domain.Model.valuobjects.RucFarmer;
import com.lux.agroges.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class SalesOrder extends AuditableAbstractAggregateRoot<SalesOrder> {
    @Getter
    @ManyToOne
    @JoinColumn(name = "farmer_product_id")
    private FarmerProduct farmerProduct;

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
    private InvoiceId invoiceId;











}
