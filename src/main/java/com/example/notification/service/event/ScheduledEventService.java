package com.example.notification.service.event;

import com.example.notification.domain.event.NewTaskEvent;
import com.example.notification.domain.event.TaskPriority;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduledEventService {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(2);

    private final EventBus eventBus;

    public ScheduledEventService(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void startHeartbeat(int seconds) {
        scheduler.scheduleAtFixedRate(
                () -> eventBus.publish(
                        new NewTaskEvent("Scheduled Task", TaskPriority.MEDIUM)
                ),
                seconds,
                seconds,
                TimeUnit.SECONDS
        );
    }
}
