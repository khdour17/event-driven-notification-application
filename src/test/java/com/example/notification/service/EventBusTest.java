package com.example.notification.service;

import com.example.notification.helpers.EventBusTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventBusTest {

    @Test
    @DisplayName("Publish event when a subscriber exists")
    void publishEventWithSubscriber() {
        EventBusTestHelper.publishEventWithSubscriber();
    }

    @Test
    @DisplayName("Publish event when no subscribers exist")
    void publishEventWithoutSubscribers() {
        EventBusTestHelper.publishEventWithoutSubscribers();
    }

    @Test
    @DisplayName("Allow subscription to multiple priorities")
    void subscribeUserToMultiplePriorities() {
        EventBusTestHelper.subscribeUserToMultiplePriorities();
    }

    @Test
    @DisplayName("Work-hours-only users do not break event publishing")
    void workHoursOnlyUserDoesNotBreakPublishing() {
        EventBusTestHelper.workHoursOnlyUserDoesNotBreakPublishing();
    }

    @Test
    @DisplayName("Notify multiple subscribers with same priority")
    void multipleSubscribersSamePriority() {
        EventBusTestHelper.multipleSubscribersSamePriority();
    }
}
