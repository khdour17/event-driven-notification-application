package com.example.notification.service;

import com.example.notification.helpers.EventHistoryTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventHistoryTest {

    @Test
    @DisplayName("Store events correctly")
    void storeEventsCorrectly() {
        EventHistoryTestHelper.storeEventsCorrectly();
    }

    @Test
    @DisplayName("Return empty history when no events exist")
    void returnEmptyHistoryWhenNoEvents() {
        EventHistoryTestHelper.returnEmptyHistoryWhenNoEvents();
    }

    @Test
    @DisplayName("Return only events from the last hour")
    void returnEventsFromLastHourOnly() {
        EventHistoryTestHelper.returnEventsFromLastHourOnly();
    }
}
