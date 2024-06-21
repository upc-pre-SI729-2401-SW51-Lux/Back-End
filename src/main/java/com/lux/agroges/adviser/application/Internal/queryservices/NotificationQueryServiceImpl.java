package com.lux.agroges.adviser.application.Internal.queryservices;

import com.lux.agroges.adviser.domain.model.aggregates.Notification;
import com.lux.agroges.adviser.domain.services.NotificationQueryService;
import com.lux.agroges.adviser.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {
    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getAllNotificationsByTransmitter(String transmitter) {
        return notificationRepository.findByTransmitter(transmitter);
    }

    @Override
    public Optional<Notification> getNotificationByTransmitter(String transmitter) {
        return notificationRepository.findFirstByTransmitter(transmitter);
    }
}
