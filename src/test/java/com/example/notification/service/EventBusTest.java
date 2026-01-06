package com.example.notification.service;

import com.example.notification.helpers.EventBusTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for EventBus behavior.
 */
class EventBusTest {

    @Test
    @DisplayName("Publish event when single subscriber exists")
    void shouldPublishEventWhenSingleSubscriberExists() {
        EventBusTestHelper.publishEventWithSingleSubscriber();
    }

    @Test
    @DisplayName("Publish event when no subscribers exist")
    void shouldPublishEventWhenNoSubscribersExist() {
        EventBusTestHelper.publishEventWithNoSubscribers();
    }

    @Test
    @DisplayName("Notify all subscribers of the same priority")
    void shouldNotifyAllSubscribersOfSamePriority() {
        EventBusTestHelper.notifyMultipleSubscribersSamePriority();
    }

    @Test
    @DisplayName("Allow subscription to multiple priorities")
    void shouldAllowSubscriptionToMultiplePriorities() {
        EventBusTestHelper.subscribeToMultiplePriorities();
    }
}
