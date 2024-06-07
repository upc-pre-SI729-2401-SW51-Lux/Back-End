package com.lux.agroges.adviser.application.Internal.QueryServices;

import com.lux.agroges.adviser.Domain.Model.aggregates.Notification;
import com.lux.agroges.adviser.Domain.services.NotificationQueryService;
import com.lux.agroges.adviser.Infrastructure.persistence.jpa.Repositories.NotificationRepository;
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
