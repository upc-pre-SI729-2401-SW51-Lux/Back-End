package com.lux.agroges.sales.Infrastructure.persistence.jpa.Repositories;

import com.lux.agroges.sales.Domain.Model.aggregates.SalesOrder;
import com.lux.agroges.sales.Domain.Model.entities.SalesOrderItem;
import com.lux.agroges.sales.Domain.Model.valuobjects.InvoiceId;
import com.lux.agroges.sales.Domain.Model.valuobjects.RucFarmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

    List <SalesOrder> findByRuc (RucFarmer ruc);

    boolean existsByInvoiceId(InvoiceId invoiceId);





}