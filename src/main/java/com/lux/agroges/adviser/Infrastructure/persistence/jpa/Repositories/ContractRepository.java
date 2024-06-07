package com.lux.agroges.adviser.Infrastructure.persistence.jpa.Repositories;

import com.lux.agroges.adviser.Domain.Model.aggregates.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByStatus(String status);
}
