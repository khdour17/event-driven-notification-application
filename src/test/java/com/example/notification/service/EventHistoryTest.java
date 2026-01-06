package com.example.notification.service;

import com.example.notification.helpers.EventHistoryTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class EventHistoryTest {

    @Test
    @DisplayName("Store and retrieve published events")
    void shouldStoreAndRetrieveEvents() {
        EventHistoryTestHelper.storeAndRetrieveEvents();
    }

    @Test
    @DisplayName("Return only events from the last hour")
    void shouldReturnOnlyEventsFromLastHour() {
        EventHistoryTestHelper.returnOnlyEventsFromLastHour();
    }
}
