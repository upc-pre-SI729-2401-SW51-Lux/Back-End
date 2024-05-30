package com.lux.agroges.finance.Domain.Model.aggregates;
import com.lux.agroges.finance.Domain.Model.valuobjects.ProductDetails;
import com.lux.agroges.finance.Domain.Model.valuobjects.ProductPrice;
import com.lux.agroges.finance.Domain.Model.valuobjects.ProductQuantity;
import com.lux.agroges.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class FarmerProduct extends AuditableAbstractAggregateRoot<FarmerProduct> {

    @Embedded
    @AttributeOverrides(
            {@AttributeOverride(name= "productName", column =@Column(name="product_name")),
                    @AttributeOverride(name= "productDescription", column =@Column(name="product_description"))
            }
    )
    private ProductDetails productDetails;
    @Embedded
    @AttributeOverrides(
            {@AttributeOverride(name= "value", column =@Column(name="product_price"))}
    )
    private ProductPrice productPrice;







}
