package com.lux.agroges.adviser.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String transmitter;

    @Column(nullable = false)
    private String receiver;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    // Default constructor
    public Notification() {}

    // Full constructor
    public Notification(String message, String transmitter, String receiver) {
        if (message == null || transmitter == null || receiver == null || message.isEmpty() || transmitter.isEmpty() || receiver.isEmpty()) {
            throw new IllegalArgumentException("None of the fields can be null or empty");
        }
        this.message = message;
        this.transmitter = transmitter;
        this.receiver = receiver;
        this.creationDate = LocalDateTime.now();
    }

    // Update method
    public Notification updateNotification(String message, String transmitter, String receiver) {
        if (message == null || transmitter == null || receiver == null || message.isEmpty() || transmitter.isEmpty() || receiver.isEmpty()) {
            throw new IllegalArgumentException("None of the fields can be null or empty");
        }
        this.message = message;
        this.transmitter = transmitter;
        this.receiver = receiver;
        return this;
    }
}
