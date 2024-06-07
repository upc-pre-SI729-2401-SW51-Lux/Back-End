package com.lux.agroges.crop.Infrastructure.persistance.jpa.Repositories;

import com.lux.agroges.crop.Domain.Model.aggregates.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lux.agroges.crop.Domain.Model.valueobjects.*;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

    boolean existsByCropId(CropId cropId);
}
