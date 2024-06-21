package com.lux.agroges.crop.application.internal.QueryServices;

import com.lux.agroges.crop.Domain.Model.aggregates.Crop;
import com.lux.agroges.crop.Domain.Model.entities.CropItem;
import com.lux.agroges.crop.Domain.Model.queries.GetAllCropsQuery;
import com.lux.agroges.crop.Domain.Model.queries.GetCropByIdQuery;
import com.lux.agroges.crop.Domain.Model.queries.GetCropItemsByCropId;
import com.lux.agroges.crop.Domain.services.CropQueryService;
import com.lux.agroges.crop.Infrastructure.persistance.jpa.Repositories.CropRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropQueryServiceImpl implements CropQueryService {
    private final CropRepository cropRepository;

    public CropQueryServiceImpl(CropRepository cropRepository){
        this.cropRepository = cropRepository;
    }

    @Override
    public List<Crop> handle(GetAllCropsQuery query){
        return cropRepository.findAll();
    }

    @Override
    public Optional<Crop> handle(GetCropByIdQuery query){
        return cropRepository.findById(query.cropId());
    }

    @Override
    public List<CropItem>handle(GetCropItemsByCropId query){
        return cropRepository.findById(query.cropId()).map(Crop::getCropItems).orElse(null);

    }

}
