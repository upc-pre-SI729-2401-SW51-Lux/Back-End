package com.lux.agroges.adviser.Domain.Model.Commands;

public record CreateNotificationCommand(String message, String transmitter, String receiver) {
}
