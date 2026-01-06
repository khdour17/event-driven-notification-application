package com.example.notification.service.event;

import com.example.notification.domain.event.NewTaskEvent;
import com.example.notification.domain.event.TaskPriority;
import com.example.notification.domain.subscription.Subscription;

import java.util.*;


public class EventBus {

    private final Map<TaskPriority, List<Subscription>> subscriptions =
            new EnumMap<>(TaskPriority.class);

    private final EventDispatcher dispatcher;
    private final EventHistory history;

    public EventBus(EventDispatcher dispatcher, EventHistory history) {
        this.dispatcher = dispatcher;
        this.history = history;

        for (TaskPriority p : TaskPriority.values()) {
            subscriptions.put(p, new ArrayList<>());
        }
    }

    public void subscribe(Set<TaskPriority> priorities, Subscription subscription) {
        priorities.forEach(p -> subscriptions.get(p).add(subscription));
    }

    //overloading for the helper class in the testing
    public void subscribe(TaskPriority priority, Subscription subscription) {
        subscribe(Collections.singleton(priority), subscription);
    }

    public void publish(NewTaskEvent event) {
        history.add(event);

        List<Subscription> targets = subscriptions.get(event.getPriority());
        dispatcher.dispatch(event, targets);

        System.out.println(
                "Task '" + event.getTaskName()
                        + "' [" + event.getPriority()
                        + "] notified " + targets.size() + " users"
        );
    }

    public EventHistory getHistory() {
        return history;
    }

    public Map<TaskPriority, List<Subscription>> getSubscriptions() {
        return subscriptions;
    }
}
