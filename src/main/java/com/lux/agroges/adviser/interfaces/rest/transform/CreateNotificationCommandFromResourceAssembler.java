package com.lux.agroges.adviser.interfaces.rest.transform;

import com.lux.agroges.adviser.domain.model.commands.CreateNotificationCommand;
import com.lux.agroges.adviser.interfaces.rest.resources.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {
    public static CreateNotificationCommand toCommand(CreateNotificationResource resource) {
        return new CreateNotificationCommand(
                resource.getMessage(),
                resource.getTransmitter(),
                resource.getReceiver()
        );
    }
}
