package com.lux.agroges.adviser.interfaces.rest.transform;

import com.lux.agroges.adviser.domain.model.aggregates.Notification;
import com.lux.agroges.adviser.interfaces.rest.resources.CreateNotificationResource;

public class NotificationFromEntityAssembler {
    public static CreateNotificationResource toResource(Notification notification) {
        var resource = new CreateNotificationResource();
        resource.setMessage(notification.getMessage());
        resource.setTransmitter(notification.getTransmitter());
        resource.setReceiver(notification.getReceiver());
    //    resource.setCreationDate(notification.getCreationDate());
        return resource;
    }
}
