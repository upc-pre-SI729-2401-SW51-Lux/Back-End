package com.lux.agroges.adviser.Interfaces.REST;

import com.lux.agroges.adviser.Domain.Model.Commands.DeleteNotificationCommand;
import com.lux.agroges.adviser.Domain.Model.aggregates.Notification;
import com.lux.agroges.adviser.Domain.services.NotificationCommandService;
import com.lux.agroges.adviser.Domain.services.NotificationQueryService;
import com.lux.agroges.adviser.Interfaces.REST.Resources.CreateNotificationResource;
import com.lux.agroges.adviser.Interfaces.REST.Resources.UpdateNotificationResource;
import com.lux.agroges.adviser.Interfaces.REST.Transform.CreateNotificationCommandFromResourceAssembler;
import com.lux.agroges.adviser.Interfaces.REST.Transform.UpdateNotificationCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public NotificationController(NotificationCommandService notificationCommandService, NotificationQueryService notificationQueryService) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody CreateNotificationResource resource) {
        var command = CreateNotificationCommandFromResourceAssembler.toCommand(resource);
        var notification = notificationCommandService.handle(command);
        return notification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody UpdateNotificationResource resource) {
        resource.setNotificationId(id);
        var command = UpdateNotificationCommandFromResourceAssembler.toCommand(resource);
        var notification = notificationCommandService.handle(command);
        return notification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        var command = new DeleteNotificationCommand(id);
        notificationCommandService.handle(command);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotificationsByTransmitter(@RequestParam String transmitter) {
        var notifications = notificationQueryService.getAllNotificationsByTransmitter(transmitter);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{transmitter}")
    public ResponseEntity<Notification> getNotificationByTransmitter(@PathVariable String transmitter) {
        var notification = notificationQueryService.getNotificationByTransmitter(transmitter);
        return notification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
