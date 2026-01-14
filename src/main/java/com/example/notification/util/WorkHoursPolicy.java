package com.example.notification.util;

import java.time.LocalTime;

public class WorkHoursPolicy {

    private static final LocalTime START = LocalTime.of(9, 0);
    private static final LocalTime END = LocalTime.of(17, 0);

    private WorkHoursPolicy() {}

    public static boolean isWithinWorkHours() {
        LocalTime now = LocalTime.now();
        return !now.isBefore(START) && !now.isAfter(END);
    }
}
