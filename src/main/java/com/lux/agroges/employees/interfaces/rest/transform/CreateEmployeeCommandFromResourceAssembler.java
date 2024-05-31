package com.lux.agroges.employees.interfaces.rest.transform;

import com.lux.agroges.employees.domain.model.commands.CreateEmployeeCommand;
import com.lux.agroges.employees.interfaces.rest.resources.CreateEmployeeResource;

public class CreateEmployeeCommandFromResourceAssembler {
    public static CreateEmployeeCommand toCommandFromResource(CreateEmployeeResource resource) {
        return new CreateEmployeeCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.idDocument(),
                resource.workPosition(),
                resource.salary(),
                resource.phone(),
                resource.age(),
                resource.photoUrl()
        );
    }
}
