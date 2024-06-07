package com.lux.agroges.crop.Domain.Model.entities;

import com.lux.agroges.crop.Domain.Model.aggregates.Crop;
import com.lux.agroges.crop.Domain.Model.aggregates.Product;
import com.lux.agroges.shared.domain.Model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;


@Getter
@Setter
@Entity
public class CropItem extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long eid;

    @OneToOne
    @JoinColumn(name = "product_id")
    @NotNull
    private Product product;

    @ManyToOne
    @JoinColumn(name = "next_item_id")
    private CropItem nextItem;

    @ManyToOne
    @JoinColumn(name="crop_id")
    private Crop crop;


    public CropItem(Product product, CropItem nextItem){
        this.product = product;
        this.nextItem = nextItem;

    }
    public CropItem(){

        this.nextItem=null;
    }


    public void updateNextItem(CropItem nextItem) {
        this.nextItem = nextItem;
    }

}
