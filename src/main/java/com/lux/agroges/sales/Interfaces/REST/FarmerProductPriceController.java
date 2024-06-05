package com.lux.agroges.sales.Interfaces.REST;

import com.lux.agroges.sales.Domain.Model.Queries.GetAllFarmerProductPriceQuery;
import com.lux.agroges.sales.Domain.Model.aggregates.FarmerProductPrice;
import com.lux.agroges.sales.Domain.services.FarmerProductCommandService;
import com.lux.agroges.sales.Domain.services.FarmerProductQueryService;
import com.lux.agroges.sales.Interfaces.REST.Resources.CreateFarmerProductPriceResource;
import com.lux.agroges.sales.Interfaces.REST.Resources.FarmerProductPriceResource;
import com.lux.agroges.sales.Interfaces.REST.Transform.CreateFarmerProductPriceCommandFromResourceAssembler;
import com.lux.agroges.sales.Interfaces.REST.Transform.FarmerProductPriceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/farmerProductPrices", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "FarmerProductPrices", description = "Farmer Product Price Management Endpoints")
public class FarmerProductPriceController {
    private final FarmerProductCommandService farmerProductCommandService;
    private final FarmerProductQueryService farmerProductQueryService;
    public FarmerProductPriceController(FarmerProductCommandService farmerProductCommandService, FarmerProductQueryService farmerProductQueryService) {
        this.farmerProductCommandService = farmerProductCommandService;
        this.farmerProductQueryService = farmerProductQueryService;
    }
    @PostMapping
    public ResponseEntity<FarmerProductPriceResource> createFarmerProductPrice(@RequestBody CreateFarmerProductPriceResource resource) {
        var createFarmerProductCommand = CreateFarmerProductPriceCommandFromResourceAssembler.toCommandFromResource(resource);
        var farmerProductPrice = farmerProductCommandService.handle(createFarmerProductCommand);
        if(farmerProductPrice.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var farmerProductPriceResource = FarmerProductPriceFromEntityAssembler.toResourceFromEntity(farmerProductPrice.get());
        return new ResponseEntity<>(farmerProductPriceResource, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<FarmerProductPriceResource>> getAllFarmerProductPrices() {
        var getAllFarmerProductPriceQuery = new GetAllFarmerProductPriceQuery();
        var farmerProductPrices = farmerProductQueryService.handle(getAllFarmerProductPriceQuery);
        var farmerProductPriceResources = farmerProductPrices.stream()
                .map(FarmerProductPriceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(farmerProductPriceResources);
    }



    // Aquí van tus métodos para manejar las solicitudes HTTP
}