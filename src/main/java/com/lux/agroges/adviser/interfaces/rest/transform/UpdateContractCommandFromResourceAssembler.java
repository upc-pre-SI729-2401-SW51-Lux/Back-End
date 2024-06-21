package com.lux.agroges.adviser.interfaces.rest.transform;

import com.lux.agroges.adviser.domain.model.commands.UpdateContractCommand;
import com.lux.agroges.adviser.interfaces.rest.resources.UpdateContractResource;

public class UpdateContractCommandFromResourceAssembler {
    public static UpdateContractCommand toCommandFromResource(Long id, UpdateContractResource resource) {
        return new UpdateContractCommand(id, resource.companyName(), resource.contractorDni(), resource.applicationTime(), resource.averageSalary(), resource.description(), resource.status());
    }
}
