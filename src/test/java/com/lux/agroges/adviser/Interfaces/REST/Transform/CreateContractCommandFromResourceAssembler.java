package com.lux.agroges.adviser.Interfaces.REST.Transform;

import com.lux.agroges.adviser.Domain.Model.Commands.CreateContractCommand;
import com.lux.agroges.adviser.Interfaces.REST.Resources.CreateContractResource;

public class CreateContractCommandFromResourceAssembler {
    public static CreateContractCommand toCommandFromResource(CreateContractResource resource) {
        return new CreateContractCommand(resource.companyName(), resource.ruc(), resource.contractorDni(), resource.applicationTime(), resource.averageSalary(), resource.description(), resource.status());
    }
}
