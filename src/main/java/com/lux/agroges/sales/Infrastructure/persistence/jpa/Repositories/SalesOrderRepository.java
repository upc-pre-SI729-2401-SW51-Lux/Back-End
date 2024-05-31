package com.lux.agroges.sales.Infrastructure.persistence.jpa.Repositories;

import com.lux.agroges.sales.Domain.Model.aggregates.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {
    List<SalesOrder> findBySalesOrderId(Long salesOrderId);
    List<SalesOrder> findByStatus(String status);
    boolean existsById(Long salesOrderId);

}