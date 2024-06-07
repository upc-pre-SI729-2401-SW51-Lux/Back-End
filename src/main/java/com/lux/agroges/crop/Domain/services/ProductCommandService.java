package com.lux.agroges.crop.Domain.services;

import com.lux.agroges.crop.Domain.Model.aggregates.Product;
import com.lux.agroges.crop.Domain.Model.commands.*;

import java.util.Optional;

public interface ProductCommandService {

    Optional<Product> handle(CreateProductCommand command);
    Optional<Product>handle(UpdateProductCommand command);
    void handle(DeleteProductCommand command);
}
