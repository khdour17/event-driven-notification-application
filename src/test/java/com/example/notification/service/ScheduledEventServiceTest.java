package com.example.notification.service;

import com.example.notification.helpers.ScheduledEventServiceTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ScheduledEventServiceTest {

    @Test
    @DisplayName("Fire scheduled tasks a fixed number of times")
    void shouldFireScheduledTasksFixedNumberOfTimes() {
        ScheduledEventServiceTestHelper.fireScheduledTasksFixedTimes();
    }
}
