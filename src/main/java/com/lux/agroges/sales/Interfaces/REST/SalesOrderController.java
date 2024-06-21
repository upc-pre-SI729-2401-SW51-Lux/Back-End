package com.lux.agroges.sales.Interfaces.REST;
import com.lux.agroges.sales.Domain.Model.Commands.AddFarmerProductToSalesOrderCommand;
import com.lux.agroges.sales.Domain.Model.Commands.CancelSalesOrderCommand;
import com.lux.agroges.sales.Domain.Model.Queries.GetAllSalesOrderQuery;
import com.lux.agroges.sales.Domain.Model.Queries.GetSalesOrderItemsBySalesOrderId;
import com.lux.agroges.sales.Domain.Model.Queries.SalesOrderByOrderIdQuery;
import com.lux.agroges.sales.Domain.services.SalesOrderCommandService;
import com.lux.agroges.sales.Domain.services.SalesOrderQueryService;
import com.lux.agroges.sales.Interfaces.REST.Resources.*;
import com.lux.agroges.sales.Interfaces.REST.Transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<SalesOrderResource>> getSalesOrder() {
        var getAllSalesOrderQuery = new GetAllSalesOrderQuery();
            var salesOrder = salesOrderQueryService.handle(getAllSalesOrderQuery);
        var salesOrderResource = salesOrder.stream().map(SalesOrderFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(salesOrderResource);

    }
    @GetMapping("/{salesOrderId}")
    public ResponseEntity<SalesOrderResource> getSalesOrderById(@PathVariable Long salesOrderId) {
        var getSalesOrderQuery = new SalesOrderByOrderIdQuery(salesOrderId);
        var salesOrder = salesOrderQueryService.handle(getSalesOrderQuery);
        if(salesOrder.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var salesOrderResource = SalesOrderFromEntityAssembler.toResourceFromEntity(salesOrder.get());
        return ResponseEntity.ok(salesOrderResource);
    }

    @PutMapping("/{salesOrderId}")
    public ResponseEntity<SalesOrderResource> updateSalesOrder(@PathVariable Long salesOrderId,@RequestBody UpdateSalesOrderResource resource) {
        var updateSalesOrder = UpdateSalesOrderCommandFromResourceAssembler.toCommandFromResource(salesOrderId, resource);
        var salesOrder =salesOrderCommandService.handle(updateSalesOrder);
        if(salesOrder.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var salesOrderResource = SalesOrderFromEntityAssembler.toResourceFromEntity(salesOrder.get());
        return ResponseEntity.ok(salesOrderResource);

    }
    @DeleteMapping("/{salesOrderId}")
    public ResponseEntity<?> deleteSalesOrder(@PathVariable Long salesOrderId) {
        var deleteSalesOrderCommand = new CancelSalesOrderCommand(salesOrderId);
        salesOrderCommandService.handle(deleteSalesOrderCommand);
        return ResponseEntity.ok("SalesOrder with given id successfully deleted");
    }

    @PutMapping("/{salesOrderId}/items/{farmerProductPriceId}")
    public ResponseEntity <SalesOrderItemResource> AddFarmerProductToSalesOrder(@PathVariable Long farmerProductPriceId, @PathVariable Long salesOrderId){
        salesOrderCommandService.handle(new AddFarmerProductToSalesOrderCommand(salesOrderId,farmerProductPriceId));
        var salesOrderByOrderIdQuery = new GetSalesOrderItemsBySalesOrderId(salesOrderId);

        var salesOrderItem = salesOrderQueryService.handle(salesOrderByOrderIdQuery);
        if(salesOrderItem.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            var salesOrderItemResource = SalesOrderItemFromResourceFromEntityAssembler.toResourceFromEntity(salesOrderItem.getFirst());
            return ResponseEntity.ok(salesOrderItemResource);
        }


    }






}
