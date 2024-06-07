package com.lux.agroges.crop.Domain.Model.aggregates;
import com.lux.agroges.crop.Domain.Model.entities.CropItem;
import com.lux.agroges.crop.Domain.Model.valueobjects.*;
import com.lux.agroges.shared.domain.Model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.AttributeOverride;
import lombok.Getter;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
public class Crop extends AuditableAbstractAggregateRoot<Crop> {

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
    @AttributeOverrides(
            {
                    @AttributeOverride(name="cropCost",column=@Column(name="crop_cost")),
            }
    )

    private CropCost cropCost;





    @OneToMany(mappedBy= "crop",cascade = CascadeType.ALL)
    private List<CropItem> cropItems;
    public Crop(){
        this.cropId=new CropId(0L);
        this.cropCost=new CropCost("USD",0L);
        this.cropCode=new CropCode("A");
        this.cropItems = new ArrayList<>();

    }

    public Crop(Long cropId, String currency, Long amount, String cropCode){
        this();
        this.cropId=new CropId(cropId);
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

    public void updateItem(Long itemId, Product product, CropItem cropItem){

    }


    public CropItem getPreviousItem(CropItem cropItem){
        return cropItems.stream().filter(item->item.getNextItem()== cropItem)
                .findFirst().orElse(null);
    }

    public CropItem getLastCropItem(){
        return cropItems.stream().filter(item->item.getNextItem()==null)
                .findFirst().orElse(null);
    }


    public Crop updateCrop(Long id,String code,String currency,Long value){
        this.cropId=new CropId(id);
        this.cropCode=new CropCode(code);
        this.cropCost= new CropCost(currency,value);
        return this;
    }

    public boolean emptyCrop(){
        return cropItems.isEmpty();
    }
}
