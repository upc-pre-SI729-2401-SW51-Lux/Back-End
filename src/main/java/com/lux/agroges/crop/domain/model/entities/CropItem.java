package com.lux.agroges.crop.domain.model.entities;

import com.lux.agroges.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import com.lux.agroges.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.validation.constraints.NotNull;

/**
@Getter
@Entity

public class CropItem extends AuditableModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY);
    private long id;

    //Falta terminar esta parte, revisa el codigo del profe

    @NotNull
    private CropItem nextItem;

    public void CropItem(CropItem cropItem){
        this.nextItem=nextItem;

    }
    **/
}
