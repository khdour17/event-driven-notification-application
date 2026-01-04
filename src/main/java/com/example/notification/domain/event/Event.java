package com.example.notification.domain.event;
import java.time.LocalDateTime;


public interface Event {

    String getType();

    LocalDateTime getTimestamp();
}
