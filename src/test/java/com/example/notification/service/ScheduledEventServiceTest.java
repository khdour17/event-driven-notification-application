package com.example.notification.service;

import com.example.notification.helpers.ScheduledEventServiceTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for ScheduledEventService behavior.
 */
class ScheduledEventServiceTest {

    @Test
    @DisplayName("Schedule and publish a single event successfully")
    void shouldScheduleSingleEventSuccessfully() throws InterruptedException {
        ScheduledEventServiceTestHelper.scheduleSingleEventSuccessfully();
    }

    @Test
    @DisplayName("Schedule and publish multiple events successfully")
    void shouldScheduleMultipleEventsSuccessfully() throws InterruptedException {
        ScheduledEventServiceTestHelper.scheduleMultipleEventsSuccessfully();
    }

    @Test
    @DisplayName("Do nothing when scheduling with zero times")
    void shouldNotScheduleAnyEventWhenTimesIsZero() throws InterruptedException {
        ScheduledEventServiceTestHelper.scheduleWithZeroTimesDoesNothing();
    }
}
