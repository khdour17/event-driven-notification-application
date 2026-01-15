package com.example.notification.helpers;

import com.example.notification.domain.event.NewTaskEvent;
import com.example.notification.domain.event.TaskPriority;
import com.example.notification.service.event.EventHistory;

import static org.junit.jupiter.api.Assertions.*;

public class EventHistoryTestHelper {

    public static void storeEventsCorrectly() {
        EventHistory history = new EventHistory();

        history.add(new NewTaskEvent("A", TaskPriority.LOW));
        history.add(new NewTaskEvent("B", TaskPriority.HIGH));

        assertEquals(2, history.getAll().size());
    }

    public static void returnEmptyHistoryWhenNoEvents() {
        EventHistory history = new EventHistory();

        assertTrue(history.getAll().isEmpty());
    }

    public static void returnEventsFromLastHourOnly() {
        EventHistory history = new EventHistory();

        history.add(new NewTaskEvent("Recent", TaskPriority.MEDIUM));

        assertEquals(1, history.eventsLastHour().size());
    }
}
