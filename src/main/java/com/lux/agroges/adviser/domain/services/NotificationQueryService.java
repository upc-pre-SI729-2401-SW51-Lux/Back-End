package com.lux.agroges.adviser.domain.services;

import com.lux.agroges.adviser.domain.model.aggregates.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationQueryService {
    List<Notification> getAllNotificationsByTransmitter(String transmitter);
    Optional<Notification> getNotificationByTransmitter(String transmitter);
}
