package com.lux.agroges.crop.domain.model.entities;

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
    private long id;

    @ManyToOne
    @JoinColumn(name = "crop_id")

    @NotNull
    private CropItem nextItem;


    public void CropItem(CropItem id,){
        this.nextItem=nextItem;

    }
}
