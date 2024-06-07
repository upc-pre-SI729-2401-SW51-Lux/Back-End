package com.lux.agroges.adviser.Infrastructure.persistence.jpa.Repositories;

import com.lux.agroges.adviser.Domain.Model.aggregates.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByTransmitter(String transmitter);
    Optional<Notification> findFirstByTransmitter(String transmitter);
}
