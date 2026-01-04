package com.example.notification.service.event;

import com.example.notification.domain.event.Event;
import com.example.notification.domain.subscription.Subscription;
import com.example.notification.service.notification.NotificationChannel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class EventDispatcher {

    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private final NotificationChannel channel;

    public EventDispatcher(NotificationChannel channel) {
        this.channel = channel;
    }

    public void dispatch(Event event, List<Subscription> subscriptions) {
        for (Subscription s : subscriptions) {
            executor.submit(() -> channel.send(s.getUser(), event));
        }
    }
}
