package com.example.notification.domain.subscription;

import com.example.notification.domain.user.User;


public class Subscription {

    private final User user;

    public Subscription(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
