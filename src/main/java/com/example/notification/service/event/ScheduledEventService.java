package com.example.notification.service.event;

import com.example.notification.domain.event.NewTaskEvent;
import com.example.notification.domain.event.TaskPriority;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Fires scheduled task events using a ScheduledExecutorService.
 */
public class ScheduledEventService {

    private final EventBus eventBus;

    public ScheduledEventService(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void startScheduledTasks(int intervalSeconds, int times) {

        System.out.println("Scheduled tasks starting...");

        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();

        Runnable task = new Runnable() {
            private int counter = 0;

            @Override
            public void run() {
                counter++;

                eventBus.publish(
                        new NewTaskEvent(
                                "Scheduled Task #" + counter,
                                TaskPriority.MEDIUM
                        )
                );

                if (counter >= times) {
                    scheduler.shutdown();
                    System.out.println("Scheduled tasks completed.");
                }
            }
        };

        scheduler.scheduleAtFixedRate(
                task,
                0,
                intervalSeconds,
                TimeUnit.SECONDS
        );
    }
}
