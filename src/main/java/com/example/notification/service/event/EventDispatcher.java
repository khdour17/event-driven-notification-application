package com.example.notification.service.event;

import com.example.notification.domain.event.Event;
import com.example.notification.domain.subscription.Subscription;
import com.example.notification.domain.user.User;
import com.example.notification.service.notification.NotificationChannel;
import com.example.notification.util.WorkHoursPolicy;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class EventDispatcher {

    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private final NotificationChannel channel;

    public EventDispatcher(NotificationChannel channel) {
        this.channel = channel;
    }

    public int dispatch(Event event, List<Subscription> subscriptions) {
        int notifiedCount = 0;

        for (Subscription subscription : subscriptions) {
            User user = subscription.getUser();

            if (shouldNotify(user)) {
                executor.submit(() ->
                        channel.send(user, event)
                );
                notifiedCount++;
            }
        }
        waitBriefly();

        return notifiedCount;

    }

    private boolean shouldNotify(User user) {
        if (!user.isWorkHoursOnly()) {
            return true;
        }
        return WorkHoursPolicy.isWithinWorkHours();
    }

    private void waitBriefly() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}