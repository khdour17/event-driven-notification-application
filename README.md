# 📣 Notification Event System

## 📌 Project Overview

This project is a **simple, clean, console-based notification system** built using **Java**. It demonstrates how to design an **event-driven system** using **object-oriented principles**, **design patterns**, and **good software engineering practices**, while keeping the implementation easy to understand and extend.

The system allows:

* Users to subscribe to task notifications based on priority
* Tasks (events) to be published to subscribers
* Event history tracking
* Scheduled (automatic) task generation
* Console-based interaction
* Fully covered unit tests with a clean helper-based structure

---

## 🧠 Core Concepts & Techniques Used

### 🔹 Programming & Language

* Java (OOP-focused)
* Java Time API (`LocalDateTime`)
* Collections Framework

### 🔹 Architectural Style

* **Event-Driven Architecture (EDA)**

### 🔹 Design Patterns Applied

| Pattern                                   | Usage                                      |
| ----------------------------------------- | ------------------------------------------ |
| **Observer**                              | Subscribers listen for published events    |
| **Publisher–Subscriber**                  | EventBus manages event distribution        |
| **Facade (lightweight)**                  | ConsoleMenu hides system complexity        |
| **Single Responsibility Principle (SRP)** | Each class has one clear purpose           |
| **Open/Closed Principle (OCP)**           | Easy to add new event types or subscribers |

### 🔹 Software Engineering Practices

* Clear package separation (domain / service / ui)
* Clean naming conventions
* No unnecessary over-engineering
* Readable, maintainable code
* Unit testing without mocks (real behavior testing)
* Test helpers for reuse and clarity

---

## 📦 Project Structure

```
com.example.notification
│
├── domain
│   ├── event
│   │   ├── Event
│   │   ├── NewTaskEvent
│   │   └── TaskPriority
│   ├── subscription
│   │   └── Subscription
│   └── user
│       └── User
│
├── service
│   ├── event
│   │   ├── EventBus
│   │   ├── EventDispatcher
│   │   └── EventHistory
│   │
│   └── notification
│       ├── NotificationChannel
│       └── EmailNotificationService
│
└── ui
    └── ConsoleMenu

── test
    ├── helpers
    │   ├── EventBusTestHelper
    │   ├── EventHistoryTestHelper
    │   └── ScheduledEventServiceTestHelper
    └── tests
        ├── EventBusTest
        ├── EventHistoryTest
        └── ScheduledEventServiceTest
```

---

## 🧩 Class-by-Class Description

### 🔸 Domain Layer

#### `Event`

* Base abstract representation of a system event
* Contains timestamp and event type

#### `NewTaskEvent`

* Concrete event representing a newly created task
* Holds task name and priority

#### `TaskPriority`

* Enum defining task importance levels
* Used for filtering subscriptions

#### `User`

* Represents a system user
* Holds user identity data

#### `Subscription`

* Connects a user to the event system
* Acts as an observer in the Observer pattern

---

### 🔸 Service Layer

#### `EventBus`

* Central event dispatcher (Publisher)
* Responsibilities:

  * Manage subscriptions
  * Publish events
  * Notify subscribers
  * Store event history

**Pattern:** Observer 

---

#### `EventHistory`

* Stores published events
* Supports time-based queries (e.g. last hour)

---

#### `ScheduledEventService`

* Publishes events at fixed intervals
* Uses simple blocking logic (`Thread.sleep`)
* Finite execution (no infinite threads)

**Design Goal:** Simplicity over concurrency complexity

---

### 🔸 UI Layer

#### `ConsoleMenu`

* Acts as the system interface (Facade)
* Allows:

  * User registration
  * Task creation
  * Viewing event history
  * Viewing subscribers
  * Running scheduled tasks

---

## 🧪 Testing Strategy

### 🎯 Testing Goals

* Test **real behavior**, not mocked logic
* Clear, readable tests
* One meaningful assertion per test
* Easy to extend

### 🧩 Test Design Pattern

Inspired by Cypress **spec/helper** structure:

* **Helper Classes**

  * Contain all setup and logic
  * Perform real actions

* **Test Classes**

  * Call one helper method per test
  * Zero clutter
  * Clear intention

---

### 🧪 Example Test Naming

```java
@Test
@DisplayName("Event should be stored when published")
void shouldStoreEventWhenPublished() {
    helper.publishEventAndVerifyHistory();
}
```

---

### 📌 What Is Covered by Tests

| Component                          | Covered |
| ---------------------------------- | ------- |
| Event publishing                   | ✅       |
| Event history storage              | ✅       |
| Time-based filtering               | ✅       |
| Scheduled event execution          | ✅       |
| Multiple events handling           | ✅       |
| Edge cases (empty history, limits) | ✅       |

---

## ▶️ How to Run the Project

### Run the Application

From IDE:

* Run the `main` method (if present)
* Or run `ConsoleMenu`

---

### Run All Tests

#### Option 1: From IDE (Recommended)

* Right-click:

```
src/test/java
```

* Click **Run All Tests**

---

#### Option 2: Using Maven

```bash
mvn test
```

This will:

* Compile the project
* Execute all unit tests
* Fail on any broken test

---

## 🚀 Future Extensions

This system is intentionally simple but easily extensible:

* Add new event types
* Add email / SMS notification services
* Replace console UI with REST API
* Add persistence (database)
* Introduce async scheduling later

---

## ✅ Summary

This project demonstrates:

* Clean Java OOP design
* Event-driven architecture
* Proper use of design patterns
* Simple but effective scheduling
* Professional unit testing strategy

**Designed for learning, clarity, and scalability.**
