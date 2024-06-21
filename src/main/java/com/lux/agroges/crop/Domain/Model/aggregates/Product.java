package com.lux.agroges.crop.Domain.Model.aggregates;

import com.lux.agroges.crop.Domain.Model.commands.CreateProductCommand;
import com.lux.agroges.crop.Domain.Model.valueobjects.ProductId;
import com.lux.agroges.crop.Domain.Model.valueobjects.ProductName;
import com.lux.agroges.crop.Domain.Model.valueobjects.StockProduct;
import com.lux.agroges.shared.domain.Model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Product extends AuditableAbstractAggregateRoot<Product> {


    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "productId", column = @Column(name = "product_id")),
            }
    )
    private ProductId productId;


    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "productName", column = @Column(name = "product_name")),
            }
    )
    private ProductName productName;

    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "stockProduct", column = @Column(name = "stock_product"))
            }
    )
    //This serves as a reference of the quantity of the product that was harvested.
    private StockProduct stockProduct;

    public Product() {
        this.productId = new ProductId();
        this.productName = new ProductName("A");
        this.stockProduct = new StockProduct();
    }

    public Product(Long productId,String productName,Long stockProduct){
    this();
    this.productId = new ProductId(productId);
    this.productName=new ProductName(productName);
    this.stockProduct=new StockProduct(stockProduct);
    }
    public Product(CreateProductCommand command){
        this();
        this.productId = new ProductId(command.productId());
        this.productName = new ProductName(command.productName());
        this.stockProduct = new StockProduct(command.stockProduct());
    }

    public Product updateProduct(Long id, String Name, Long stock ){
        this.stockProduct=new StockProduct(stock);
        return this;
    }


}

