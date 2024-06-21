package com.lux.agroges.adviser.domain.model.commands;

public record UpdateNotificationCommand(Long notificationId, String message, String transmitter, String receiver, String creationDate) {
}
