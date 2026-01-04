package com.example.notification.service.notification;

import com.example.notification.domain.event.Event;
import com.example.notification.domain.user.User;


public class EmailNotificationService implements NotificationChannel {

    @Override
    public void send(User user, Event event) {
        System.out.println(
                "[EMAIL] To: " + user.getEmail()
                        + " | Event: " + event.getType()
                        + " | Time: " + event.getTimestamp()
        );
    }
}
