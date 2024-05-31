package com.lux.agroges.sales.Infrastructure.persistence.jpa.Repositories;

import com.lux.agroges.sales.Domain.Model.aggregates.FarmerProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface  FarmerProductRepository extends JpaRepository<FarmerProductPrice, Long> {
    Optional<FarmerProductPrice> findByFarmerProductId(Long farmerProductId);

    boolean existsById(Long farmerProductId);

}
