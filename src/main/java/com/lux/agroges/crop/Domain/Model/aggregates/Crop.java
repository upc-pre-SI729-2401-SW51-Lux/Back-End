package com.lux.agroges.crop.Domain.Model.aggregates;
import com.lux.agroges.crop.Domain.Model.commands.CreateProductCommand;
import com.lux.agroges.crop.Domain.Model.entities.CropItem;
import com.lux.agroges.crop.Domain.Model.valueobjects.*;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
public class Crop{
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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
        this.cropCost=new CropCost("USD",0L);
        this.cropCode=new CropCode("A");
        this.cropItems = new ArrayList<>();

    }

    public Crop(String currency, Long amount, String cropCode){
        this();

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

        this.cropCode=new CropCode(code);
        this.cropCost= new CropCost(currency,value);
        return this;
    }

    public boolean emptyCrop(){
        return cropItems.isEmpty();
    }
    public void addProductToCrop(CreateProductCommand createProductCommand){
        System.out.println("Adding product to crop");
        Product product = new Product(createProductCommand);
        addCropItem(product);
        }
}


