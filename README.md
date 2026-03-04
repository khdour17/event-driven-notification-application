# 📣 Notification Event System

## 📌 Project Overview

This project is a **clean, console-based notification system** built using **Java**, designed to demonstrate a **realistic event-driven architecture** while keeping the code simple, readable, and extensible.

The system supports:

- User subscriptions to task notifications by priority
- Publishing task events
- Filtering notifications based on **working hours**
- Event history tracking
- Scheduled (automatic) task publishing
- Console-based interaction
- Comprehensive unit tests (positive & negative cases)

This project focuses on **clarity, correctness, and good software engineering practices**, not over-engineering.

---

## 🧠 Core Concepts & Techniques Used

### 🔹 Programming & Language

- Java (OOP-focused)
- Java Time API (`LocalDateTime`, `LocalTime`)
- Java Concurrency (`ExecutorService`, `ScheduledExecutorService`)
- Java Collections Framework

---

### 🔹 Architectural Style

- **Event-Driven Architecture (EDA)**

---

### 🔹 Design Patterns Applied

| Pattern | Usage |
|-------|------|
| **Observer** | Users subscribe to events and get notified |
| **Publisher–Subscriber** | `EventBus` manages subscriptions and publishing |
| **Strategy (Policy)** | `WorkHoursPolicy` controls notification rules |
| **Facade (lightweight)** | `ConsoleMenu` hides internal system complexity |
| **Single Responsibility Principle (SRP)** | Each class has one clear purpose |
| **Open/Closed Principle (OCP)** | Easy to add new events, rules, or channels |

---

### 🔹 Software Engineering Practices

- Clear package separation (`domain`, `service`, `ui`, `util`)
- Clean naming conventions
- Simple concurrency (no unnecessary complexity)
- Immutable domain objects where possible
- Real behavior unit tests (no mocks)
- Helper-based test structure for clarity
- Meaningful test names and descriptions

---

## 📦 Project Structure

```
com.example.notification
│
├── domain
│      ├── event
│      │     ├── Event
│      │     ├── NewTaskEvent
│      │     └── TaskPriority
│      ├── subscription
│      │     └── Subscription
│      └── user
│           └── User
│
├── service
│      ├── event
│      │    ├── EventBus
│      │    ├── EventDispatcher
│      │    ├── EventHistory
│      │    └── ScheduledEventService
│      │
│      └── notification
│           ├── NotificationChannel
│           └── EmailNotificationService
│
├── util
│    └── WorkHoursPolicy
│
├── ui
│    └── ConsoleMenu
│
└── test
     ├── helpers
     │    ├── EventBusTestHelper
     │    ├── EventHistoryTestHelper
     │    └── ScheduledEventTestHelper
     └── service
          ├── EventBusTest
          ├── EventHistoryTest
          └── ScheduledEventServiceTest
```

---

## 🧩 Class-by-Class Description

## 🔸 Domain Layer

### `Event`
- Base abstraction for all system events
- Holds event timestamp
- Parent type for future event extensions

---

### `NewTaskEvent`
- Concrete event representing a newly created task
- Contains:
  - Task name
  - Task priority

---

### `TaskPriority`
- Enum defining task importance levels:
  - LOW
  - MEDIUM
  - HIGH
  - CRITICAL
- Used to filter subscriptions

---

### `User`
- Represents a system user
- Contains:
  - Name
  - Email
  - `workHoursOnly` preference
- Determines whether notifications are allowed outside work hours

---

### `Subscription`
- Connects a user to the event system
- Acts as the **Observer** in the Observer pattern

---

## 🔸 Service Layer

### `EventBus`
- Central event publisher
- Responsibilities:
  - Manage subscriptions
  - Publish events
  - Filter subscribers by priority
  - Store event history

**Pattern:** Observer / Publisher–Subscriber

---

### `EventDispatcher`
- Sends notifications to eligible users
- Uses `ExecutorService` for concurrent dispatch
- Applies **work-hours filtering**
- Only notifies users when allowed

---

### `EventHistory`
- Stores all published events
- Supports:
  - Retrieving all events
  - Retrieving events from the last hour
- Used for monitoring and auditing

---

### `ScheduledEventService`
- Publishes events at fixed intervals
- Uses `ScheduledExecutorService`
- Finite execution (no infinite background threads)
- Automatically shuts down after completing tasks

---

## 🔸 Utility Layer

### `WorkHoursPolicy`
- Centralized business rule for work hours
- Defines allowed notification time window (09:00–17:00)
- Keeps time logic out of domain and service classes

---

## 🔸 UI Layer

### `ConsoleMenu`
- Acts as a **Facade**
- Provides user-friendly console interaction:
  - Add users
  - Create tasks
  - Subscribe users
  - View event history
  - Run scheduled tasks

---

## 🧪 Testing Strategy

### 🎯 Testing Goals

- Test **real behavior**, not mocked interactions
- Cover both **positive and negative scenarios**
- Ensure system stability under edge cases
- Keep tests readable and maintainable

---

### 🧩 Test Design Pattern

Inspired by Cypress-style testing:

#### Helper Classes
- Contain all setup and logic
- Perform real actions
- Encapsulate assertions

#### Test Classes
- One helper call per test
- Clear intent
- No duplicated logic

---

### 📌 Covered Test Scenarios

| Scenario | Covered |
|--------|--------|
| Subscribe users to priorities | ✅ |
| Publish events with subscribers | ✅ |
| Publish events with no subscribers | ✅ |
| Work-hours-only user behavior | ✅ |
| Event history storage | ✅ |
| Last-hour filtering | ✅ |
| Scheduled events execution | ✅ |
| Zero scheduled runs | ✅ |
| No users / no events cases | ✅ |

---

## ▶️ How to Run the Project

### Run the Application

From your IDE:
- Run `NotificationApplication` (main class)
- Or run `ConsoleMenu`

---

### Run All Tests

#### Option 1: IDE (Recommended)
- Right-click:
