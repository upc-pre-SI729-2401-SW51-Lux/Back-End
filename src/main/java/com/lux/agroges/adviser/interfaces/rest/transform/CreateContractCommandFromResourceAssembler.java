package com.lux.agroges.adviser.interfaces.rest.transform;

import com.lux.agroges.adviser.domain.model.commands.CreateContractCommand;
import com.lux.agroges.adviser.interfaces.rest.resources.CreateContractResource;

public class CreateContractCommandFromResourceAssembler {
    public static CreateContractCommand toCommandFromResource(CreateContractResource resource) {
        return new CreateContractCommand(resource.companyName(), resource.ruc(), resource.contractorDni(), resource.applicationTime(), resource.averageSalary(), resource.description(), resource.status());
    }
}
