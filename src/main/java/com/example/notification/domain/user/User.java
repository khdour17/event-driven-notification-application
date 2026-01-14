package com.example.notification.domain.user;

public class User {

    private final String name;
    private final String email;
    private final boolean workHoursOnly;

    public User(String name, String email, boolean workHoursOnly) {
        this.name = name;
        this.email = email;
        this.workHoursOnly = workHoursOnly;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isWorkHoursOnly() {
        return workHoursOnly;
    }
}
