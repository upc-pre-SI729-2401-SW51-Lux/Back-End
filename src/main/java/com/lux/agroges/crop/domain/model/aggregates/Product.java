package com.lux.agroges.crop.domain.model.aggregates;

import com.lux.agroges.crop.domain.model.commands.CreateProductCommand;
import com.lux.agroges.crop.domain.model.commands.UpdateProductCommand;
import com.lux.agroges.crop.domain.model.valueobjects.ProductId;
import com.lux.agroges.crop.domain.model.valueobjects.ProductName;
import com.lux.agroges.crop.domain.model.valueobjects.ProductPrice;
import com.lux.agroges.crop.domain.model.valueobjects.StockProduct;
import com.lux.agroges.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Product extends AuditableAbstractAggregateRoot<Product> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
                    @AttributeOverride(name = "currency", column = @Column(name = "product_price_currency")),
                    @AttributeOverride(name = "amount", column = @Column(name = "product_price_amount"))
            }
    )
    private ProductPrice productPrice;

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
        this.productPrice = new ProductPrice();
        this.productName = new ProductName();
        this.stockProduct = new StockProduct();
    }

    public Product(Long productId,String currency,Long value,String productName,Long stockProduct){
    this();
    this.productId = new ProductId(productId);
    this.productPrice=new ProductPrice(currency,value);
    this.productName=new ProductName(productName);
    this.stockProduct=new StockProduct(stockProduct);
    }
    public Product(CreateProductCommand command){
        this();
        this.productId = new ProductId(command.productId());
        this.productPrice = new ProductPrice(command.currency(),command.amount());
        this.productName = new ProductName(command.productName());
        this.stockProduct = new StockProduct(command.stockProduct());
    }

    public Product updateProduct(String currency, Long amount,Long stock){
        this.productPrice=new ProductPrice(currency,amount);
        this.stockProduct=new StockProduct(stock);
        return this;
    }


}
