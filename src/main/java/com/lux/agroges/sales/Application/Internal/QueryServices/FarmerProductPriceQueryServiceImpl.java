package com.lux.agroges.sales.Application.Internal.QueryServices;

import com.lux.agroges.sales.Domain.Model.Queries.GetAllFarmerProductPriceQuery;
import com.lux.agroges.sales.Domain.Model.Queries.GetFarmerProductByIdQuery;
import com.lux.agroges.sales.Domain.Model.aggregates.FarmerProductPrice;
import com.lux.agroges.sales.Domain.services.FarmerProductQueryService;
import com.lux.agroges.sales.Infrastructure.persistence.jpa.Repositories.FarmerProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FarmerProductPriceQueryServiceImpl  implements FarmerProductQueryService {
    private final FarmerProductRepository farmerProductRepository;


    public FarmerProductPriceQueryServiceImpl(FarmerProductRepository farmerProductRepository) {
        this.farmerProductRepository = farmerProductRepository;
    }
    @Override
    public List<FarmerProductPrice> handle  (GetAllFarmerProductPriceQuery query){
        return farmerProductRepository.findAll();
    }



    @Override
    public Optional<FarmerProductPrice> handle (GetFarmerProductByIdQuery query){
        return farmerProductRepository.findById(query.farmerProductId());
    }


}
