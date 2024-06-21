package com.lux.agroges.adviser.application.Internal.queryservices;

import com.lux.agroges.adviser.domain.model.aggregates.Contract;
import com.lux.agroges.adviser.domain.services.ContractQueryService;
import com.lux.agroges.adviser.infrastructure.persistence.jpa.repositories.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractQueryServiceImpl implements ContractQueryService {
    private final ContractRepository contractRepository;

    public ContractQueryServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public List<Contract> getContractsByStatus(String status) {
        return contractRepository.findByStatus(status);
    }
}
