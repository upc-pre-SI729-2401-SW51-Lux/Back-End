package com.lux.agroges.crop.Domain.services;
import com.lux.agroges.crop.Domain.Model.aggregates.Crop;
import com.lux.agroges.crop.Domain.Model.commands.*;

import java.util.Optional;


public interface CropCommandService {
    Optional<Crop>handle(CreateCropCommand command);
    Optional<Crop>handle(UpdateCropCommand command);
    void handle(DeleteCropCommand command);
    void handle(AddProductToCropCommand command);
}
