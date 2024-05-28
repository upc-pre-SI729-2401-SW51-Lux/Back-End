package com.lux.agroges.finance.Domain.Model.aggregates;

import com.lux.agroges.finance.Domain.Model.valuobjects.*;
import com.lux.agroges.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class SalesOrder extends AuditableAbstractAggregateRoot<SalesOrder> {
    @Embedded
    @AttributeOverrides(
            {@AttributeOverride(name= "rucFarmer", column =@Column(name="ruc_farmer"))}
    )
    private RucFarmer ruc;








}
