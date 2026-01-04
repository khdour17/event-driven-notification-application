package com.example.notification.domain.event;
import java.time.LocalDateTime;


public class NewTaskEvent implements Event {

    private final String taskName;
    private final TaskPriority priority;
    private final LocalDateTime timestamp;

    public NewTaskEvent(String taskName, TaskPriority priority) {
        this.taskName = taskName;
        this.priority = priority;
        this.timestamp = LocalDateTime.now();
    }

    public String getTaskName() {
        return taskName;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    @Override
    public String getType() {
        return "NEW_TASK";
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
