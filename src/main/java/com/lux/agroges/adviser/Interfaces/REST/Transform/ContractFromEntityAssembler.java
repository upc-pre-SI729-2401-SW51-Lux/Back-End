package com.lux.agroges.adviser.Interfaces.REST.Transform;

import com.lux.agroges.adviser.Domain.Model.aggregates.Contract;
import com.lux.agroges.adviser.Interfaces.REST.Resources.CreateContractResource;
import com.lux.agroges.adviser.Interfaces.REST.Resources.UpdateContractResource;

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
