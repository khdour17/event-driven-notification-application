
package com.example.notification.helpers;

import com.example.notification.domain.event.NewTaskEvent;
import com.example.notification.domain.event.TaskPriority;
import com.example.notification.domain.subscription.Subscription;
import com.example.notification.domain.user.User;
import com.example.notification.service.event.EventBus;
import com.example.notification.service.event.EventDispatcher;
import com.example.notification.service.event.EventHistory;
import com.example.notification.service.notification.EmailNotificationService;

import static org.junit.jupiter.api.Assertions.*;


public class EventBusTestHelper {

    private static EventBus createEventBus() {
        return new EventBus(
                new EventDispatcher(new EmailNotificationService()),
                new EventHistory()
        );
    }

    public static void publishEventWithSingleSubscriber() {
        EventBus bus = createEventBus();

        bus.subscribe(
                TaskPriority.HIGH,
                new Subscription(new User("Ali", "ali@test.com"))
        );

        bus.publish(new NewTaskEvent("Test Task", TaskPriority.HIGH));

        assertEquals(1, bus.getHistory().getAll().size());
    }

    public static void publishEventWithNoSubscribers() {
        EventBus bus = createEventBus();

        bus.publish(new NewTaskEvent("Lonely Task", TaskPriority.LOW));

        assertEquals(1, bus.getHistory().getAll().size());
    }

    public static void notifyMultipleSubscribersSamePriority() {
        EventBus bus = createEventBus();

        bus.subscribe(
                TaskPriority.CRITICAL,
                new Subscription(new User("A", "a@test.com"))
        );
        bus.subscribe(
                TaskPriority.CRITICAL,
                new Subscription(new User("B", "b@test.com"))
        );

        bus.publish(new NewTaskEvent("Critical Task", TaskPriority.CRITICAL));

        assertEquals(1, bus.getHistory().getAll().size());
        assertEquals(
                2,
                bus.getSubscriptions().get(TaskPriority.CRITICAL).size()
        );
    }

    public static void subscribeToMultiplePriorities() {
        EventBus bus = createEventBus();

        Subscription sub =
                new Subscription(new User("Multi", "m@test.com"));

        bus.subscribe(
                TaskPriority.HIGH,
                sub
        );
        bus.subscribe(
                TaskPriority.MEDIUM,
                sub
        );

        assertEquals(
                1,
                bus.getSubscriptions().get(TaskPriority.HIGH).size()
        );
        assertEquals(
                1,
                bus.getSubscriptions().get(TaskPriority.MEDIUM).size()
        );
    }
}
