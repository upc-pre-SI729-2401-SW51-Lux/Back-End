package com.lux.agroges.adviser.application.Internal.commandservices;

import com.lux.agroges.adviser.domain.model.commands.CreateNotificationCommand;
import com.lux.agroges.adviser.domain.model.commands.UpdateNotificationCommand;
import com.lux.agroges.adviser.domain.model.commands.DeleteNotificationCommand;
import com.lux.agroges.adviser.domain.model.aggregates.Notification;
import com.lux.agroges.adviser.domain.services.NotificationCommandService;
import com.lux.agroges.adviser.infrastructure.persistence.jpa.repositories.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {
    private final NotificationRepository notificationRepository;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    @Transactional
    public Optional<Notification> handle(CreateNotificationCommand command) {
        var notification = new Notification(command.message(), command.transmitter(), command.receiver());
        try {
            notification = notificationRepository.save(notification);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving notification: " + e.getMessage());
        }
        return Optional.of(notification);
    }

    @Override
    @Transactional
    public Optional<Notification> handle(UpdateNotificationCommand command) {
        var result = notificationRepository.findById(command.notificationId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Notification not found");
        var notificationToUpdate = result.get();
        try {
            var notificationUpdate = notificationRepository.save(notificationToUpdate.updateNotification(command.message(), command.transmitter(), command.receiver()));
            return Optional.of(notificationUpdate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating notification: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void handle(DeleteNotificationCommand command) {
        var result = notificationRepository.findById(command.notificationId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Notification not found");
        try {
            notificationRepository.deleteById(command.notificationId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting notification: " + e.getMessage());
        }
    }
}
