package com.lux.agroges.adviser.infrastructure.persistence.jpa.repositories;

import com.lux.agroges.adviser.domain.model.aggregates.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByStatus(String status);
}
