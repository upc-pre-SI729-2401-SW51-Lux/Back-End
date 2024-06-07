package com.lux.agroges.adviser.Application.Internal.CommandServices;

import com.lux.agroges.adviser.domain.model.commands.CreateContractCommand;
import com.lux.agroges.adviser.domain.model.commands.UpdateContractCommand;
import com.lux.agroges.adviser.domain.model.aggregates.Contract;
import com.lux.agroges.adviser.domain.services.ContractCommandService;
import com.lux.agroges.adviser.infrastructure.persistence.jpa.repositories.ContractRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractCommandServiceImpl implements ContractCommandService {
    private final ContractRepository contractRepository;

    public ContractCommandServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    @Transactional
    public Optional<Contract> handle(CreateContractCommand command) {
        var contract = new Contract(command.companyName(), command.ruc(), command.contractorDni(), command.applicationTime(), command.averageSalary(), command.description(), command.status());
        try {
            contract = contractRepository.save(contract);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving contract: " + e.getMessage());
        }
        return Optional.of(contract);
    }

    @Override
    @Transactional
    public Optional<Contract> handle(UpdateContractCommand command) {
        var result = contractRepository.findById(command.contractId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Contract not found");
        var contractToUpdate = result.get();
        try {
            var contractUpdate = contractRepository.save(contractToUpdate.updateContract(command.companyName(), command.contractorDni(), command.applicationTime(), command.averageSalary(), command.description(), command.status()));
            return Optional.of(contractUpdate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating contract: " + e.getMessage());
        }
    }
}
