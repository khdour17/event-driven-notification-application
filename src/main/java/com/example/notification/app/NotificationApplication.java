package com.example.notification.app;

import com.example.notification.service.event.*;
import com.example.notification.service.notification.EmailNotificationService;
import com.example.notification.ui.ConsoleMenu;

public class NotificationApplication {

    public static void main(String[] args) {

        EventHistory history = new EventHistory();
        EventDispatcher dispatcher =
                new EventDispatcher(new EmailNotificationService());

        EventBus eventBus = new EventBus(dispatcher, history);
        ScheduledEventService scheduler = new ScheduledEventService(eventBus);

        new ConsoleMenu(eventBus, scheduler).start();
    }
}
