package com.lux.agroges.crop.application.internal.CommandServices;


import com.lux.agroges.crop.Domain.Model.aggregates.Product;
import com.lux.agroges.crop.Domain.Model.commands.CreateProductCommand;
import com.lux.agroges.crop.Domain.Model.commands.DeleteProductCommand;
import com.lux.agroges.crop.Domain.Model.commands.UpdateProductCommand;
import com.lux.agroges.crop.Domain.services.ProductCommandService;
import com.lux.agroges.crop.Infrastructure.persistance.jpa.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService{
    private final ProductRepository productRepository;
    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(CreateProductCommand command){
        if(productRepository.existsById(command.productId())){
            throw new IllegalArgumentException("Product already exists");
        }
        var product=new Product(command);
        try {
            productRepository.save(product);
        }catch (Exception e){
            throw new IllegalArgumentException("Error creating product");
        }
        return Optional.of(product);
    }
    @Override
    public Optional<Product> handle(UpdateProductCommand command) {
        if (productRepository.existsById(command.productId()))
            throw new IllegalArgumentException("Product not found");
        var product = productRepository.findById(command.productId());
        if (product.isEmpty())
            throw new IllegalArgumentException("Product not found");
        var productToUpdate = product.get();
        try {
            var productUpdated = productRepository.save(productToUpdate.updateProduct(command.productId(), command.productName(), command.stockProduct()));
            return Optional.of(productUpdated);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating product");
        }
    }

    @Override
    public void handle(DeleteProductCommand command) {
        if (productRepository.existsById(command.productId()))
            throw new IllegalArgumentException("Product not found");


        try {productRepository.deleteById(command.productId());}
        catch (Exception e) {
            throw new IllegalArgumentException("Error deleting this product" + e.getMessage());
        }
    }
}
