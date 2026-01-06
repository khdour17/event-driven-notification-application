package com.example.notification.helpers;

import com.example.notification.domain.event.NewTaskEvent;
import com.example.notification.domain.event.TaskPriority;
import com.example.notification.service.event.EventHistory;

import static org.junit.jupiter.api.Assertions.*;


public class EventHistoryTestHelper {

    public static void storeAndRetrieveEvents() {
        EventHistory history = new EventHistory();

        history.add(new NewTaskEvent("Task 1", TaskPriority.LOW));
        history.add(new NewTaskEvent("Task 2", TaskPriority.HIGH));

        assertEquals(2, history.getAll().size());
    }

    public static void returnOnlyEventsFromLastHour() {
        EventHistory history = new EventHistory();

        history.add(new NewTaskEvent("Recent Task", TaskPriority.MEDIUM));

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        history.add(new NewTaskEvent("Another Recent Task", TaskPriority.HIGH));

        assertEquals(2, history.eventsLastHour().size());
    }
}
