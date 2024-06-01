package com.lux.agroges.crop.domain.model.aggregates;
import com.lux.agroges.crop.domain.model.commands.CreateCropCommand;
import com.lux.agroges.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.*;
import lombok.Getter;

import com.lux.agroges.crop.domain.model.valueobjects.CropCode;
import com.lux.agroges.crop.domain.model.valueobjects.CropId;


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

    //Constructor de crop
    public Crop(){
        this.cropId = new CropId();
        this.cropCode = new CropCode("");

    }

    public Crop(CreateCropCommand command){
        this();
        this.cropId = new CropId(command.cropId());
        this.cropCode = new CropCode(command.cropCode());
    }

}
