package com.lux.agroges.farming.interfaces.rest.transform;

import com.lux.agroges.farming.domain.model.commands.CreateRucCommand;
import com.lux.agroges.farming.interfaces.rest.resources.CreateRucResource;

public class CreateRucCommandFromResourceAssembler {

    public static CreateRucCommand toCommandFromResource(CreateRucResource resource) {
        return new CreateRucCommand(resource.ruc(), resource.email(), resource.phone(), resource.socialReason());
    }
}
