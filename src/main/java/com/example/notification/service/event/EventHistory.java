package com.example.notification.service.event;

import com.example.notification.domain.event.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class EventHistory {

    private final List<Event> events = new ArrayList<>();

    public synchronized void add(Event event) {
        events.add(event);
    }

    public List<Event> eventsLastHour() {
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);

        return events.stream()
                .filter(e -> e.getTimestamp().isAfter(oneHourAgo))
                .collect(Collectors.toList());
    }

    public List<Event> getAll() {
        return events;
    }
}
