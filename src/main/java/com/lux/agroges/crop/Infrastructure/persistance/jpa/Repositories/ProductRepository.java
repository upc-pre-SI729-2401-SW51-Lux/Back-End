package com.lux.agroges.crop.Infrastructure.persistance.jpa.Repositories;

import com.lux.agroges.crop.Domain.Model.aggregates.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
