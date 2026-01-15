package com.example.notification.helpers;

import com.example.notification.domain.event.NewTaskEvent;
import com.example.notification.domain.event.TaskPriority;
import com.example.notification.domain.subscription.Subscription;
import com.example.notification.domain.user.User;
import com.example.notification.service.event.EventBus;
import com.example.notification.service.event.EventDispatcher;
import com.example.notification.service.event.EventHistory;
import com.example.notification.service.notification.EmailNotificationService;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

public class EventBusTestHelper {

    private static EventBus createBus() {
        return new EventBus(
                new EventDispatcher(new EmailNotificationService()),
                new EventHistory()
        );
    }

    public static void publishEventWithSubscriber() {
        EventBus bus = createBus();

        bus.subscribe(
                EnumSet.of(TaskPriority.HIGH),
                new Subscription(new User("Ali", "ali@test.com", false))
        );

        bus.publish(new NewTaskEvent("Task", TaskPriority.HIGH));

        assertEquals(1, bus.getHistory().getAll().size());
    }

    public static void publishEventWithoutSubscribers() {
        EventBus bus = createBus();

        bus.publish(new NewTaskEvent("Task", TaskPriority.LOW));

        assertEquals(1, bus.getHistory().getAll().size());
    }

    public static void subscribeUserToMultiplePriorities() {
        EventBus bus = createBus();

        Subscription sub =
                new Subscription(new User("Multi", "m@test.com", false));

        bus.subscribe(EnumSet.of(
                TaskPriority.HIGH,
                TaskPriority.MEDIUM,
                TaskPriority.LOW
        ), sub);

        assertEquals(1, bus.getSubscriptions().get(TaskPriority.HIGH).size());
        assertEquals(1, bus.getSubscriptions().get(TaskPriority.MEDIUM).size());
        assertEquals(1, bus.getSubscriptions().get(TaskPriority.LOW).size());
    }

    public static void workHoursOnlyUserDoesNotBreakPublishing() {
        EventBus bus = createBus();

        bus.subscribe(
                EnumSet.of(TaskPriority.CRITICAL),
                new Subscription(new User("Worker", "w@test.com", true))
        );

        bus.publish(new NewTaskEvent("Critical", TaskPriority.CRITICAL));

        // Event is always stored regardless of notification delivery
        assertEquals(1, bus.getHistory().getAll().size());
    }

    public static void multipleSubscribersSamePriority() {
        EventBus bus = createBus();

        bus.subscribe(
                EnumSet.of(TaskPriority.HIGH),
                new Subscription(new User("A", "a@test.com", false))
        );
        bus.subscribe(
                EnumSet.of(TaskPriority.HIGH),
                new Subscription(new User("B", "b@test.com", false))
        );

        bus.publish(new NewTaskEvent("High Task", TaskPriority.HIGH));

        assertEquals(2,
                bus.getSubscriptions().get(TaskPriority.HIGH).size());
    }
}
