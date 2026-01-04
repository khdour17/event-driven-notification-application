package com.example.notification.service.notification;

import com.example.notification.domain.event.Event;
import com.example.notification.domain.user.User;


public interface NotificationChannel {

    void send(User user, Event event);
}
