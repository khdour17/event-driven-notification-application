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
                    
                    === Notification System Menu ===
                    1. Register user
                    2. Create task
                    3. Show last hour events
                    4. Start scheduled tasks
                    5. Show subscriptions
                    6. Exit
                    """);

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> registerUser();
                case 2 -> createTask();
                case 3 -> showHistory();
                case 4 -> startScheduledTasks();
                case 5 -> showSubscriptions();
                case 6 -> {
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
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
                try {
                    priorities.add(TaskPriority.values()[Integer.parseInt(i) - 1]);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid input ignored: " + i);
                }
            }
        }

        eventBus.subscribe(priorities, new Subscription(new User(name, email)));
        System.out.println("User registered successfully for priorities: " + priorities);
    }


    private void createTask() {
        System.out.print("Task name: ");
        String name = scanner.nextLine();

        System.out.println("Choose priority (1=CRITICAL, 2=HIGH, 3=MEDIUM, 4=LOW): ");
        TaskPriority priority = TaskPriority.values()[scanner.nextInt() - 1];
        scanner.nextLine(); // consume newline

        eventBus.publish(new NewTaskEvent(name, priority));
        System.out.println("Task '" + name + "' created with priority " + priority);
    }


    /**
     * Show events published in the last hour with detailed info.
     */
    private void showHistory() {
        System.out.println("\n=== Events in the Last Hour ===");
        var events = eventBus.getHistory().eventsLastHour();

        if (events.isEmpty()) {
            System.out.println("No events in the last hour.");
            return;
        }

        for (var event : events) {
            if (event instanceof NewTaskEvent taskEvent) {
                System.out.println(
                        "Task: " + taskEvent.getTaskName() +
                                " | Priority: " + taskEvent.getPriority() +
                                " | Type: " + taskEvent.getType() +
                                " | Timestamp: " + taskEvent.getTimestamp()
                );
            } else {
                // fallback for future event types
                System.out.println(
                        "Event: " + event.getType() +
                                " | Timestamp: " + event.getTimestamp()
                );
            }
        }
        System.out.println("==============================\n");
    }



    private void startScheduledTasks() {
        System.out.print("Interval between tasks (seconds): ");
        int interval = scanner.nextInt();

        System.out.print("Number of tasks to schedule: ");
        int repetitions = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.println("Starting scheduled tasks...");
        scheduler.startScheduledTasks(interval, repetitions);
        System.out.println("Scheduled tasks completed.");
    }


    private void showSubscriptions() {
        System.out.println("=== Current User Subscriptions ===");
        eventBus.getSubscriptions().forEach((priority, list) -> {
            System.out.println(priority + " → " + list.size() + " subscriber(s)");
            for (Subscription sub : list) {
                User user = sub.getUser();
                System.out.println("   - " + user.getName() + " (" + user.getEmail() + ")");
            }
        });
    }
}
