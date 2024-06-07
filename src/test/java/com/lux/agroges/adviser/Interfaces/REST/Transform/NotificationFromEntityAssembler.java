package com.lux.agroges.adviser.Interfaces.REST.Transform;

import com.lux.agroges.adviser.Domain.Model.aggregates.Notification;
import com.lux.agroges.adviser.Interfaces.REST.Resources.CreateNotificationResource;

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
