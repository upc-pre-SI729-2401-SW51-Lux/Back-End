package com.lux.agroges.finance.Domain.Model.valuobjects;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public record OrderTimestamp(LocalDateTime dateTime) {
    public OrderTimestamp {
        if (dateTime == null || dateTime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("OrderTimestamp cannot be null");
        }
    }
}