package com.lux.agroges.adviser.domain.services;

import com.lux.agroges.adviser.domain.model.aggregates.Contract;
import java.util.List;

public interface ContractQueryService {
    List<Contract> getAllContracts();
    List<Contract> getContractsByStatus(String status);
}
