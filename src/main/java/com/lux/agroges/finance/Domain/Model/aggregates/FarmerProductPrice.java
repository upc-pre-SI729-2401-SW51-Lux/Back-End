package com.lux.agroges.finance.Domain.Model.aggregates;
import com.lux.agroges.finance.Domain.Model.Commands.CreateFarmerProductCommand;
import com.lux.agroges.finance.Domain.Model.valuobjects.Price;
import com.lux.agroges.finance.Domain.Model.valuobjects.ProductId;

import com.lux.agroges.finance.Domain.Model.valuobjects.ValidityTimeFarmerProduct;
import com.lux.agroges.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class FarmerProductPrice extends AuditableAbstractAggregateRoot<FarmerProductPrice> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @AttributeOverrides(
            {@AttributeOverride(name= "productId", column =@Column(name="product_id")),
                         }
    )
    private ProductId productId;
    @Embedded
    @AttributeOverrides(
            {@AttributeOverride(name= "money", column =@Column(name="product_price_money")),
                    @AttributeOverride(name= "amount", column =@Column(name="product_price_amount"))}
    )
    private Price price;
    @Embedded
    @AttributeOverrides(
            {@AttributeOverride(name= "ValidityTimeFarmerProduct", column =@Column(name="validity_time_farmer_product"))}
    )
    ValidityTimeFarmerProduct validityTimeFarmerProduct;

    public FarmerProductPrice(){

        this.productId = new ProductId();
        this.price = new Price();
        this.validityTimeFarmerProduct= new ValidityTimeFarmerProduct();
    }
    public FarmerProductPrice(Long productId, String money, Long amount, LocalDateTime start, LocalDateTime end){
        this();
        this.productId = new ProductId(productId);
        this.price = new Price(money,amount);
        this.validityTimeFarmerProduct = new ValidityTimeFarmerProduct(start,end);
    }
    public FarmerProductPrice (CreateFarmerProductCommand command){
        this();
        this.productId = new ProductId(command.productId());
        this.price = new Price(command.money(),command.amount());
        this.validityTimeFarmerProduct = new ValidityTimeFarmerProduct(command.start(),command.end());
    }

    public FarmerProductPrice updateFarmerProductPriced(String money, Long amount){
        this.price = new Price(money,amount);
        return this;
    }
    public FarmerProductPrice updateValidityTime(LocalDateTime start, LocalDateTime end){
        this.validityTimeFarmerProduct = new ValidityTimeFarmerProduct(start,end);
        return this;
    }

}
