package com.lux.agroges.adviser.Domain.services;

import com.lux.agroges.adviser.Domain.Model.Commands.CreateContractCommand;
import com.lux.agroges.adviser.Domain.Model.Commands.UpdateContractCommand;
import com.lux.agroges.adviser.Domain.Model.aggregates.Contract;

import java.util.Optional;

public interface ContractCommandService {
    Optional<Contract> handle(CreateContractCommand command);
    Optional<Contract> handle(UpdateContractCommand command);
}
