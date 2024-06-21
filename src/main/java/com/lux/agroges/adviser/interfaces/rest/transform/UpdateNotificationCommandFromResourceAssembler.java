package com.lux.agroges.adviser.interfaces.rest.transform;

import com.lux.agroges.adviser.domain.model.commands.UpdateNotificationCommand;
import com.lux.agroges.adviser.interfaces.rest.resources.UpdateNotificationResource;

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
