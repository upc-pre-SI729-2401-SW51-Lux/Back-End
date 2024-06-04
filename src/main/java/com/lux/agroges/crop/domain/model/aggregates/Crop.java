package com.lux.agroges.crop.domain.model.aggregates;
import com.lux.agroges.crop.domain.model.commands.CreateCropCommand;
import com.lux.agroges.crop.domain.model.commands.CreateProductCommand;
import com.lux.agroges.crop.domain.model.entities.CropItem;
import com.lux.agroges.crop.domain.model.valueobjects.*;
import com.lux.agroges.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
public class Crop extends AuditableAbstractAggregateRoot<Crop> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //No usar un ; al lado de un generated value, puede causar problemas a largo plazo
    private Long Id;

    @Embedded
    @AttributeOverrides(
        {
            @AttributeOverride(name = "cropId", column = @Column(name = "crop_id"))

        }

    )
    private CropId cropId;


    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name="cropCode",column=@Column(name="crop_code")),
            }
    )
    private CropCode cropCode;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="productId",column=@Column(name="product_id")),
    })
    private ProductId productId;

    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name="productName",column=@Column(name="product_name")),
            }
    )

    //en estos override podemos meter la snake case
    private ProductName productName;


    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name="stockProduct",column=@Column(name="stock_product")),
            }
    )
    private StockProduct stockProduct;


    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name="cropCost",column=@Column(name="crop_cost")),
            }
    )

    private CropCost cropCost;

    //Constructor de crop
//    public Crop(){
//        this.cropId = new CropId();
//        this.cropCode = new CropCode("");
//        //voy a revisar porque sale error en este codigo cuando pongo () y soluciona cuando ("")
//
//        this.productName=new ProductName();
//        this.cropCost=new CropCost();
//
//
//    }
    @OneToMany(mappedBy= "Product",cascade = CascadeType.ALL)
    private List<CropItem> cropItems;
    public Crop(){
        this.cropId=new CropId(0L);
        this.cropCost=new CropCost("",0L);
        this.cropCode=new CropCode("");
        this.cropItems = new ArrayList<>();

    }

    public Crop(Long id, String currency, Long amount, String cropCode){
        this();
        this.cropId=new CropId(id);
        this.cropCost= new CropCost(currency,amount);
        this.cropCode= new CropCode(cropCode);
        this.cropItems = new ArrayList<>();

    }

    public Crop(CreateCropCommand command, CreateProductCommand productCommand){
        this();
        this.cropId = new CropId(command.cropId());
        this.cropCode = new CropCode(command.cropCode());
        this.cropCost=new CropCost();
        this.cropItems= new ArrayList<>();


    }
    public void addCropItem(Product product, CropItem nextItem){
        System.out.println("Adding this crop item to the list");
        CropItem cropItem = new CropItem(product,nextItem);

    }

}
