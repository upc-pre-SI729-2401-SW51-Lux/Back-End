package com.lux.agroges.crop.Domain.services;

import com.lux.agroges.crop.Domain.Model.aggregates.Crop;
import com.lux.agroges.crop.Domain.Model.entities.CropItem;
import com.lux.agroges.crop.Domain.Model.queries.GetAllCropsQuery;
import com.lux.agroges.crop.Domain.Model.queries.GetCropByIdQuery;
import com.lux.agroges.crop.Domain.Model.queries.GetCropItemsByCropId;

import java.util.List;
import java.util.Optional;

public interface CropQueryService {
    Optional<Crop> handle(GetCropByIdQuery query);
    List<Crop> handle(GetAllCropsQuery query);
    List<CropItem> handle(GetCropItemsByCropId query);
}
