package com.example.notification.service.event;

import com.example.notification.domain.event.Event;
import com.example.notification.domain.subscription.Subscription;
import com.example.notification.service.notification.NotificationChannel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class EventDispatcher {

    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private final NotificationChannel channel;

    public EventDispatcher(NotificationChannel channel) {
        this.channel = channel;
    }

    public void dispatch(Event event, List<Subscription> subscriptions) {

        for (Subscription subscription : subscriptions) {
            executor.submit(() ->
                    channel.send(subscription.getUser(), event)
            );
        }

        waitForCompletion();
    }

    private void waitForCompletion() {
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}