package com.lux.agroges.adviser.Domain.services;

import com.lux.agroges.adviser.Domain.Model.Commands.CreateNotificationCommand;
import com.lux.agroges.adviser.Domain.Model.Commands.UpdateNotificationCommand;
import com.lux.agroges.adviser.Domain.Model.Commands.DeleteNotificationCommand;
import com.lux.agroges.adviser.Domain.Model.aggregates.Notification;

import java.util.Optional;

public interface NotificationCommandService {
    Optional<Notification> handle(CreateNotificationCommand command);
    Optional<Notification> handle(UpdateNotificationCommand command);
    void handle(DeleteNotificationCommand command);
}
