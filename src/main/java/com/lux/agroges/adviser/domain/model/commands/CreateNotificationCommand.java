package com.lux.agroges.adviser.domain.model.commands;

public record CreateNotificationCommand(String message, String transmitter, String receiver) {
}
