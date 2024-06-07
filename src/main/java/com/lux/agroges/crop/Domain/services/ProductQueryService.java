package com.lux.agroges.crop.Domain.services;

import com.lux.agroges.crop.Domain.Model.aggregates.Product;
import com.lux.agroges.crop.Domain.Model.queries.GetAllProductsQuery;
import com.lux.agroges.crop.Domain.Model.queries.GetProductByIdQuery;
import java.util.*;
public interface ProductQueryService {

    Optional<Product>handle(GetProductByIdQuery query);
    List <Product> handle(GetAllProductsQuery query);
}
