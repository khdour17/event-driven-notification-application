package com.example.notification.helpers;

import com.example.notification.domain.event.TaskPriority;
import com.example.notification.domain.subscription.Subscription;
import com.example.notification.domain.user.User;
import com.example.notification.service.event.*;

import static org.junit.jupiter.api.Assertions.*;


public class ScheduledEventServiceTestHelper {

    public static void fireScheduledTasksFixedTimes() {
        EventHistory history = new EventHistory();
        EventBus bus = new EventBus(
                new EventDispatcher(
                        new com.example.notification.service.notification.EmailNotificationService()
                ),
                history
        );

        bus.subscribe(
                TaskPriority.MEDIUM,
                new Subscription(new User("Timer", "t@test.com"))
        );

        ScheduledEventService scheduler =
                new ScheduledEventService(bus);

        scheduler.startScheduledTasks(2, 3);
        try {
            long totalSleepMillis = (long) 2 * 3 * 1000;
            Thread.sleep(totalSleepMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertEquals(3, history.getAll().size());
    }
}
