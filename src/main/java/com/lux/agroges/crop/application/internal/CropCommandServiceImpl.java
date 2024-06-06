package com.lux.agroges.crop.application.internal;

import com.lux.agroges.crop.Domain.Model.aggregates.Crop;
import com.lux.agroges.crop.Domain.Model.aggregates.Product;
import com.lux.agroges.crop.Domain.Model.commands.CreateCropCommand;

import com.lux.agroges.crop.Domain.Model.commands.DeleteCropCommand;
import com.lux.agroges.crop.Domain.Model.commands.UpdateCropCommand;
import com.lux.agroges.crop.Domain.Model.entities.CropItem;
import com.lux.agroges.crop.Domain.Model.valueobjects.CropId;
import com.lux.agroges.crop.Domain.services.CropCommandService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.lux.agroges.crop.Infrastructure.persistance.jpa.Repositories.*;

import java.util.Optional;

@Service
public class CropCommandServiceImpl implements CropCommandService {
    private CropRepository cropRepository;
    private ProductRepository productRepository;

    public CropCommandServiceImpl(CropRepository cropRepository, ProductRepository productRepository) {
        this.cropRepository = cropRepository;
        this.productRepository = productRepository;
    }
    @Override
    @Transactional

    public Optional<Crop> handle(CreateCropCommand command) {
        if (cropRepository.existsByCropId(new CropId(command.cropId()))) {
            throw new IllegalArgumentException("Crop already exists");

        }
        var crop = new Crop(command.cropId(), command.cropCode(), command.value(), command.cropCurrency());
        try {
            crop = cropRepository.save(crop);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating crop");
        }
        return Optional.of(crop);


    }

    @Override
    @Transactional
    public Optional<Crop> handle(UpdateCropCommand command){
        var updt = cropRepository.findById(command.cropId());
        if(updt.isEmpty())
            throw new IllegalArgumentException("Crop not found");
        var cropUpdate=updt.get();
        try{
            var cropUpdated = cropRepository
                    .save(cropUpdate.updateCrop(command.cropId(),command.cropCode(),command.cropCurrency(),command.value()));
            return Optional.of(cropUpdated);
        }catch (Exception e){
            throw new IllegalArgumentException("Error updating crop");
        }
    }

    @Override
    public void handle(DeleteCropCommand command){
        Crop crop= cropRepository.findById(command.cropId()).
                orElseThrow(()->new IllegalArgumentException("Crop not Found"));
        cropRepository.delete(crop);
    }


}
