package com.lux.agroges.adviser.Domain.Model.Commands;

public record UpdateNotificationCommand(Long notificationId, String message, String transmitter, String receiver, String creationDate) {
}
