package com.lux.agroges.adviser.interfaces.rest.transform;

import com.lux.agroges.adviser.domain.model.aggregates.Contract;
import com.lux.agroges.adviser.interfaces.rest.resources.CreateContractResource;

import java.util.List;
import java.util.stream.Collectors;

public class ContractFromEntityAssembler {
    public static CreateContractResource toResourceFromEntity(Contract contract) {
        return new CreateContractResource(contract.getCompanyName(), contract.getRuc().getRuc(), contract.getContractorDni(), contract.getApplicationTime(), contract.getAverageSalary(), contract.getDescription(), contract.getStatus());
    }

    public static List<CreateContractResource> toResourcesFromEntities(List<Contract> contracts) {
        return contracts.stream().map(ContractFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
    }
}
