package com.lux.agroges.sales.Domain.services;


import com.lux.agroges.sales.Domain.Model.Commands.CreateFarmerProductCommand;
import com.lux.agroges.sales.Domain.Model.Commands.DeleteFarmerProductCommand;
import com.lux.agroges.sales.Domain.Model.Commands.UpdateFarmerProductCommand;
import com.lux.agroges.sales.Domain.Model.aggregates.FarmerProductPrice;

import java.util.Optional;

public interface FarmerProductCommandService {
    Optional<FarmerProductPrice> handle(CreateFarmerProductCommand command);
    Optional<FarmerProductPrice> handle(UpdateFarmerProductCommand command);
    void handle(DeleteFarmerProductCommand command);



}
