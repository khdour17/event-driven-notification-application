package com.example.notification.service.event;

import com.example.notification.domain.event.NewTaskEvent;
import com.example.notification.domain.event.TaskPriority;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledEventService {

    private final EventBus eventBus;
    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    public ScheduledEventService(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void startScheduledTasks(int intervalSeconds, int times) {
        System.out.println("Scheduled tasks starting...");

        final int[] counter = {0};

        scheduler.scheduleAtFixedRate(() -> {
            counter[0]++;

            eventBus.publish(
                    new NewTaskEvent(
                            "Scheduled Task #" + counter[0],
                            TaskPriority.MEDIUM
                    )
            );

            if (counter[0] >= times) {
                shutdownScheduler();
            }

        }, 0, intervalSeconds, TimeUnit.SECONDS);
    }

    private void shutdownScheduler() {
        scheduler.shutdown();
        System.out.println("Scheduled tasks completed.");
    }
}
