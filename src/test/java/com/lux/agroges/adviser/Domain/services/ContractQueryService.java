package com.lux.agroges.adviser.Domain.services;

import com.lux.agroges.adviser.Domain.Model.aggregates.Contract;
import java.util.List;

public interface ContractQueryService {
    List<Contract> getAllContracts();
    List<Contract> getContractsByStatus(String status);
}
