package com.example.notification.service.event;

import com.example.notification.domain.event.NewTaskEvent;
import com.example.notification.domain.event.TaskPriority;


public class ScheduledEventService {

    private final EventBus eventBus;

    public ScheduledEventService(EventBus eventBus) {
        this.eventBus = eventBus;
    }


    public void startScheduledTasks(int intervalSeconds, int times) {
        System.out.println("Scheduled tasks starting...");

        for (int i = 1; i <= times; i++) {

            // Create and publish the event
            eventBus.publish(
                    new NewTaskEvent("Scheduled Task #" + i, TaskPriority.MEDIUM)
            );

            // Wait for the interval before the next event
            if (i < times) { // no need to sleep after last one
                try {
                    Thread.sleep(intervalSeconds);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Scheduled tasks interrupted!");
                    break;
                }
            }
        }

        System.out.println("Scheduled tasks completed.");
    }
}
