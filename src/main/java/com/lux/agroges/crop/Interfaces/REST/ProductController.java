package com.lux.agroges.crop.Interfaces.REST;

import com.lux.agroges.crop.Domain.Model.commands.DeleteProductCommand;
import com.lux.agroges.crop.Domain.Model.queries.GetAllProductsQuery;
import com.lux.agroges.crop.Domain.Model.queries.GetProductByIdQuery;
import com.lux.agroges.crop.Domain.services.*;
import com.lux.agroges.crop.Interfaces.REST.Resources.CreateProductResource;
import com.lux.agroges.crop.Interfaces.REST.Resources.ProductResource;
import com.lux.agroges.crop.Interfaces.REST.Transform.CreateProductCommandFromResourceAssembler;
import com.lux.agroges.crop.Interfaces.REST.Transform.ProductFromEntityToAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Tag(name="Prducts",description = "Product management endpoints")
public class ProductController {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;
    public ProductController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }
    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody CreateProductResource resource){
        var createProductCommand= CreateProductCommandFromResourceAssembler.toCommandFromResource(resource);
        var product=productCommandService.handle(createProductCommand);
        if(product.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var productResource= ProductFromEntityToAssembler.toResourceFromEntity(product.get());
        return new ResponseEntity<>(productResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResource>>getAllProducts(){
        var getAllProductsQuery= new GetAllProductsQuery();
        var products= productQueryService.handle(getAllProductsQuery);
        var productResources=products.stream().map(ProductFromEntityToAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(productResources);

    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResource>getProductById(@PathVariable Long productId){
        var getProductQuery= new GetProductByIdQuery(productId);
        var product=productQueryService.handle(getProductQuery);
        if(product.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var productResource= ProductFromEntityToAssembler.toResourceFromEntity(product.get());
        return ResponseEntity.ok(productResource);

    }
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResource>updateProduct(@PathVariable Long productId, @RequestBody CreateProductResource resource){
        var updateProductCommand= CreateProductCommandFromResourceAssembler.toCommandFromResource(resource);
        var product=productCommandService.handle(updateProductCommand);
        if(product.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var productResource= ProductFromEntityToAssembler.toResourceFromEntity(product.get());
        return ResponseEntity.ok(productResource);
    }

    @DeleteMapping
    public ResponseEntity<?>deleteProduct(@PathVariable Long productId){
        var deleteProductCommand= new DeleteProductCommand(productId);
        productCommandService.handle(deleteProductCommand);
        return ResponseEntity.noContent().build();
    }
}
