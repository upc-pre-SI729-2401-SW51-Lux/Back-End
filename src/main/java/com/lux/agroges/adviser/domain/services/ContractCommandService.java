package com.lux.agroges.adviser.domain.services;

import com.lux.agroges.adviser.domain.model.commands.CreateContractCommand;
import com.lux.agroges.adviser.domain.model.commands.UpdateContractCommand;
import com.lux.agroges.adviser.domain.model.aggregates.Contract;

import java.util.Optional;

public interface ContractCommandService {
    Optional<Contract> handle(CreateContractCommand command);
    Optional<Contract> handle(UpdateContractCommand command);
}
