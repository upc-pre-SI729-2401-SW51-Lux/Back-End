package com.lux.agroges.crop.domain.model.entities;

import com.lux.agroges.crop.domain.model.aggregates.Crop;
import com.lux.agroges.crop.domain.model.aggregates.Product;
import com.lux.agroges.crop.domain.model.valueobjects.ProductPrice;
import com.lux.agroges.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import com.lux.agroges.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.validation.constraints.NotNull;


@Getter
@Entity

public class CropItem extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull
    private Product product;


    @ManyToOne
    @JoinColumn(name = "crop_id")
    @NotNull
    private CropItem nextItem;





    public void CropItem(Product product, CropItem nextItem){
        this.product = product;
        this.nextItem=nextItem;

    }
    public CropItem(){

        this.nextItem=null;
    }
    public void CropItem(CropItem nextItem) {
        this.nextItem = nextItem;
    }

    public void updateNextItem(CropItem nextItem) {
        this.nextItem = nextItem;
    }

}
