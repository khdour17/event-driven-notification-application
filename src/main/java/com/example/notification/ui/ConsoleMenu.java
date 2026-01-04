package com.example.notification.ui;

import com.example.notification.domain.event.NewTaskEvent;
import com.example.notification.domain.event.TaskPriority;
import com.example.notification.domain.subscription.Subscription;
import com.example.notification.domain.user.User;
import com.example.notification.service.event.EventBus;
import com.example.notification.service.event.ScheduledEventService;

import java.util.EnumSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Console interface.
 */
public class ConsoleMenu {

    private final EventBus eventBus;
    private final ScheduledEventService scheduler;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(EventBus eventBus, ScheduledEventService scheduler) {
        this.eventBus = eventBus;
        this.scheduler = scheduler;
    }

    public void start() {
        while (true) {
            System.out.println("""
                    1. Register user
                    2. Create task
                    3. Show last hour events
                    4. Start scheduled events (10s)
                    5. Exit
                    """);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> registerUser();
                case 2 -> createTask();
                case 3 -> showHistory();
                case 4 -> scheduler.startHeartbeat(10);
                case 5 -> System.exit(0);
            }
        }
    }

    private void registerUser() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.println("Choose priorities (e.g. 1 2 3) or 0 for ALL:");
        System.out.println("1=CRITICAL 2=HIGH 3=MEDIUM 4=LOW");

        String input = scanner.nextLine();
        Set<TaskPriority> priorities = EnumSet.noneOf(TaskPriority.class);

        if (input.equals("0")) {
            priorities = EnumSet.allOf(TaskPriority.class);
        } else {
            for (String i : input.split(" ")) {
                priorities.add(TaskPriority.values()[Integer.parseInt(i) - 1]);
            }
        }

        eventBus.subscribe(priorities, new Subscription(new User(name, email)));
        System.out.println("✔ User registered successfully");
    }

    private void createTask() {
        System.out.print("Task name: ");
        String name = scanner.nextLine();

        System.out.print("Priority (1-4): ");
        TaskPriority p = TaskPriority.values()[scanner.nextInt() - 1];
        scanner.nextLine();

        eventBus.publish(new NewTaskEvent(name, p));
    }

    private void showHistory() {
        eventBus.getHistory()
                .eventsLastHour()
                .forEach(e ->
                        System.out.println(
                                e.getType() + " | " + e.getTimestamp()
                        )
                );
    }
}
