package com.lux.agroges.adviser.domain.services;

import com.lux.agroges.adviser.domain.model.commands.CreateNotificationCommand;
import com.lux.agroges.adviser.domain.model.commands.UpdateNotificationCommand;
import com.lux.agroges.adviser.domain.model.commands.DeleteNotificationCommand;
import com.lux.agroges.adviser.domain.model.aggregates.Notification;

import java.util.Optional;

public interface NotificationCommandService {
    Optional<Notification> handle(CreateNotificationCommand command);
    Optional<Notification> handle(UpdateNotificationCommand command);
    void handle(DeleteNotificationCommand command);
}
