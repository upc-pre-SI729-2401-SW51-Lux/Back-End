package com.lux.agroges.adviser.Interfaces.REST.Transform;

import com.lux.agroges.adviser.Domain.Model.Commands.UpdateNotificationCommand;
import com.lux.agroges.adviser.Interfaces.REST.Resources.UpdateNotificationResource;

public class UpdateNotificationCommandFromResourceAssembler {
    public static UpdateNotificationCommand toCommand(UpdateNotificationResource resource) {
        return new UpdateNotificationCommand(
                resource.getNotificationId(),
                resource.getMessage(),
                resource.getTransmitter(),
                resource.getReceiver(),
                resource.getCreationDate()
        );
    }
}
