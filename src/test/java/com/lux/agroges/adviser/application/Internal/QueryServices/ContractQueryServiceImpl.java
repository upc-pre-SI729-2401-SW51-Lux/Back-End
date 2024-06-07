package com.lux.agroges.adviser.application.Internal.QueryServices;

import com.lux.agroges.adviser.Domain.Model.aggregates.Contract;
import com.lux.agroges.adviser.Domain.services.ContractQueryService;
import com.lux.agroges.adviser.Infrastructure.persistence.jpa.Repositories.ContractRepository;
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
