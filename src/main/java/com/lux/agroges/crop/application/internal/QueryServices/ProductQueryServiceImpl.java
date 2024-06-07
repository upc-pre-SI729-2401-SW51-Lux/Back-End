package com.lux.agroges.crop.application.internal.QueryServices;

import com.lux.agroges.crop.Domain.Model.aggregates.Product;
import com.lux.agroges.crop.Domain.Model.queries.GetAllProductsQuery;
import com.lux.agroges.crop.Domain.Model.queries.GetProductByIdQuery;
import com.lux.agroges.crop.Domain.services.ProductQueryService;
import com.lux.agroges.crop.Infrastructure.persistance.jpa.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;
    public  ProductQueryServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> handle(GetAllProductsQuery query){
        return productRepository.findAll();
    }
    @Override
    public Optional<Product> handle(GetProductByIdQuery query){
        return productRepository.findById(query.productId());
    }
}
