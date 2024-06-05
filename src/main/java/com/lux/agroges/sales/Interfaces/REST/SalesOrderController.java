package com.lux.agroges.sales.Interfaces.REST;

import com.lux.agroges.sales.Domain.services.SalesOrderCommandService;
import com.lux.agroges.sales.Domain.services.SalesOrderQueryService;
import com.lux.agroges.sales.Interfaces.REST.Resources.CreateSalesOrderResource;
import com.lux.agroges.sales.Interfaces.REST.Resources.SalesOrderResource;
import com.lux.agroges.sales.Interfaces.REST.Transform.CreateSalesOrderCommandFromResourceAssembler;
import com.lux.agroges.sales.Interfaces.REST.Transform.SalesOrderFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/SalesOrder", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "SalesOrder", description = "Sales Order Management Endpoints")
public class SalesOrderController {
    private final SalesOrderCommandService salesOrderCommandService;
    private final SalesOrderQueryService salesOrderQueryService;
    public SalesOrderController(SalesOrderCommandService salesOrderCommandService, SalesOrderQueryService salesOrderQueryService) {
        this.salesOrderCommandService = salesOrderCommandService;
        this.salesOrderQueryService = salesOrderQueryService;
    }
    @PostMapping
    public ResponseEntity<SalesOrderResource> createSalesOrder(@RequestBody CreateSalesOrderResource resource) {
        if (resource.orderTimestamp() == null) {
        return ResponseEntity.badRequest().build();
    }
        var createSalesOrderCommand = CreateSalesOrderCommandFromResourceAssembler.toCommandFromResource(resource);
        var salesOrder = salesOrderCommandService.handle(createSalesOrderCommand);
        if(salesOrder.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var salesOrderResource = SalesOrderFromEntityAssembler.toResourceFromEntity(salesOrder.get());

        return new ResponseEntity<>(salesOrderResource, HttpStatus.CREATED);

    }




}
