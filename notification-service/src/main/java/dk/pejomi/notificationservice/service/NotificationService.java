package dk.pejomi.notificationservice.service;

import dk.pejomi.notificationservice.dto.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {
    public String sendNotification(NotificationRequest notificationRequest) {
        log.info("Sending notification to: [{}] with order number: [{}] and message: [{}]",
                notificationRequest.getEmail(),
                notificationRequest.getOrderNumber(),
                notificationRequest.getMessage());

        // TODO: send out a real email notification

        return "Notification sent successfully";
    }
}
