package com.lux.agroges.adviser.Interfaces.REST.Transform;

import com.lux.agroges.adviser.Domain.Model.Commands.UpdateContractCommand;
import com.lux.agroges.adviser.Interfaces.REST.Resources.UpdateContractResource;

public class UpdateContractCommandFromResourceAssembler {
    public static UpdateContractCommand toCommandFromResource(Long id, UpdateContractResource resource) {
        return new UpdateContractCommand(id, resource.companyName(), resource.contractorDni(), resource.applicationTime(), resource.averageSalary(), resource.description(), resource.status());
    }
}
