package com.lux.agroges.adviser.Interfaces.REST.Transform;

import com.lux.agroges.adviser.Domain.Model.Commands.CreateNotificationCommand;
import com.lux.agroges.adviser.Interfaces.REST.Resources.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {
    public static CreateNotificationCommand toCommand(CreateNotificationResource resource) {
        return new CreateNotificationCommand(
                resource.getMessage(),
                resource.getTransmitter(),
                resource.getReceiver()
        );
    }
}
