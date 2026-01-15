package com.example.notification.helpers;

import com.example.notification.service.event.EventBus;
import com.example.notification.service.event.EventDispatcher;
import com.example.notification.service.event.EventHistory;
import com.example.notification.service.event.ScheduledEventService;
import com.example.notification.service.notification.EmailNotificationService;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduledEventServiceTestHelper {

    private static EventBus createEventBus() {
        return new EventBus(
                new EventDispatcher(new EmailNotificationService()),
                new EventHistory()
        );
    }

    public static void scheduleSingleEventSuccessfully() throws InterruptedException {
        EventBus bus = createEventBus();
        ScheduledEventService scheduler = new ScheduledEventService(bus);

        scheduler.startScheduledTasks(1, 1);

        Thread.sleep(1500); // allow task to run

        assertEquals(1, bus.getHistory().getAll().size());
    }

    public static void scheduleMultipleEventsSuccessfully() throws InterruptedException {
        EventBus bus = createEventBus();
        ScheduledEventService scheduler = new ScheduledEventService(bus);

        scheduler.startScheduledTasks(1, 3);

        Thread.sleep(3500);

        assertEquals(3, bus.getHistory().getAll().size());
    }

    public static void scheduleWithZeroTimesDoesNothing() throws InterruptedException {
        EventBus bus = createEventBus();
        ScheduledEventService scheduler = new ScheduledEventService(bus);

        scheduler.startScheduledTasks(1, 0);

        Thread.sleep(1000);

        assertEquals(0, bus.getHistory().getAll().size());
    }
}
