package com.lux.agroges.employees.interfaces.rest.transform;

import com.lux.agroges.employees.domain.model.commands.UpdateEmployeeDetailsCommand;
import com.lux.agroges.employees.interfaces.rest.resources.UpdateEmployeeDetailsResource;

public class UpdateEmployeeDetailsCommandFromResourceAssembler {
    public static UpdateEmployeeDetailsCommand toCommandFromResource(String idDocument, UpdateEmployeeDetailsResource resource) {
        return new UpdateEmployeeDetailsCommand(
                idDocument,
                resource.workPosition(),
                resource.salary(),
                resource.phone(),
                resource.age(),
                resource.photoUrl());
    }
}
