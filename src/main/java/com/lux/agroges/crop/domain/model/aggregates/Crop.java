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
    private ProductId product;

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

    //METODOS
    public void addCropItem(Product product, CropItem nextItem){
        System.out.println("Adding this crop as an item");
        CropItem cropItem = new CropItem(product,nextItem);
        this.cropItems.add(cropItem);

    }
    public void addCropItem(Product product){
        System.out.println("Adding to the item");
        CropItem cropItem=new CropItem(product, null);
        CropItem originalLastItem=null;
        if(!cropItems.isEmpty()) {  originalLastItem=getLastCropItem();   }
        else{
            System.out.println("Item is empty");
        }
        cropItems.add(cropItem);
        System.out.println("item added successfully to the list");
        if(originalLastItem!=null){     originalLastItem.updateNextItem(cropItem);  }

    }

    public void removeItem(CropItem cropItem){
        System.out.println("Starting the removal of the item, please standby");
        CropItem previousItem=getPreviousItem(cropItem);
        CropItem nextItem=cropItem.getNextItem();
        if(previousItem!=null) {    previousItem.updateNextItem(nextItem);  }
        else {  cropItems.remove(cropItem);   }
        System.out.println("Item removed successfully");


    }

    public void updateItem(CropItem cropItem){
        //Implementar todavía falta
    }


    public CropItem getPreviousItem(CropItem cropItem){
        return cropItems.stream().filter(item->item.getNextItem()== cropItem).findFirst().orElse(null);-
    }

    public CropItem getLastCropItem(){
        return cropItems.stream().filter(item->item.getNextItem()==null).findFirst().orElse(null);
    }

    public boolean emptyCrop(){
        return cropItems.isEmpty();
    }
}
