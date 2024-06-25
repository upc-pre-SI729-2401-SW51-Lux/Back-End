package com.lux.agroges.crop.application.internal.CommandServices;

import com.lux.agroges.crop.Domain.Model.aggregates.Crop;
import com.lux.agroges.crop.Domain.Model.commands.*;

import com.lux.agroges.crop.Domain.services.CropCommandService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.lux.agroges.crop.Infrastructure.persistance.jpa.Repositories.*;

import java.util.Optional;

@Service
public class CropCommandServiceImpl implements CropCommandService {

    private final CropRepository cropRepository;

    public CropCommandServiceImpl(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    @Transactional
    public Optional<Crop> handle(CreateCropCommand command) {
        try {
            if (cropRepository.existsById(command.id())) {
                throw new IllegalArgumentException("Crop with this ID already exists");
            }

            var crop = new Crop(command.cropCode(), command.value(), command.currency());
            crop = cropRepository.save(crop);
            return Optional.of(crop);

        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating crop: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Crop> handle(UpdateCropCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(DeleteCropCommand command) {
        if(!cropRepository.existsById(command.id())){
            throw new IllegalArgumentException("Crop not found");
        }
        try {cropRepository.deleteById(command.id());}

        catch (Exception e) {
            throw new IllegalArgumentException("Error deleting crop: " + e.getMessage(), e);
        }
    }

    @Override
    public void handle(AddProductToCropCommand command) {}

}