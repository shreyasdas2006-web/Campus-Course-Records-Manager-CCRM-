# Campus Course & Records Manager (CCRM)
## Project Report

---

| Field               | Details                                      |
|---------------------|----------------------------------------------|
| **Project Title**   | Campus Course & Records Manager (CCRM)       |
| **Language**        | Java (SE 17+)                                |
| **Type**            | Console-Based Application                    |
| **Version**         | 1.0                                          |
| **Repository**      | https://github.com/shreyasdas2006-web/Campus-Course-Records-Manager-CCRM- |

---

## Table of Contents

1. [Project Overview](#1-project-overview)
2. [Objectives](#2-objectives)
3. [System Requirements](#3-system-requirements)
4. [Project Structure](#4-project-structure)
5. [Architecture & Design](#5-architecture--design)
6. [Core Modules](#6-core-modules)
7. [OOP Concepts Applied](#7-oop-concepts-applied)
8. [Advanced Java Features Used](#8-advanced-java-features-used)
9. [Design Patterns](#9-design-patterns)
10. [Data Flow & Workflow](#10-data-flow--workflow)
11. [Exception Handling](#11-exception-handling)
12. [File I/O & Data Persistence](#12-file-io--data-persistence)
13. [Grading System](#13-grading-system)
14. [Sample Output](#14-sample-output)
15. [Syllabus Mapping](#15-syllabus-mapping)
16. [Limitations & Future Enhancements](#16-limitations--future-enhancements)
17. [Conclusion](#17-conclusion)

---

## 1. Project Overview

The **Campus Course & Records Manager (CCRM)** is a console-based Java application developed to simulate a simplified academic management system for an educational institution. It enables administrators to manage students, courses, enrollments, grades, and generate transcript records — all through an interactive menu-driven command-line interface.

The project is built entirely using **Java Standard Edition (SE)** and demonstrates a wide range of core and advanced Java programming concepts including Object-Oriented Programming, Collections, Exception Handling, File I/O with NIO.2, Generics, Lambdas, Streams, Enums, and Design Patterns.

---

## 2. Objectives

- Design and implement a **multi-module Java application** following OOP best practices
- Apply **Inheritance, Encapsulation, Abstraction, and Polymorphism** in real-world context
- Use the **Builder and Singleton design patterns** for cleaner, maintainable code
- Implement **custom exception handling** to enforce business rules
- Perform **file operations** using Java NIO.2 for CSV import/export and data backup
- Demonstrate usage of **Java Generics, Collections, Streams, Lambdas, Enums**, and the **Date/Time API**
- Provide a complete **console menu system** for all operations

---

## 3. System Requirements

| Requirement         | Specification                                 |
|---------------------|-----------------------------------------------|
| **JDK Version**     | Java 17 or higher (LTS recommended)           |
| **IDE**             | IntelliJ IDEA / Eclipse                       |
| **OS**              | Windows / macOS / Linux                       |
| **Build Tool**      | Manual `javac` compilation (no Maven/Gradle)  |
| **External Deps**   | None (100% standard Java SE library)          |

### Build & Run

```bash
# Clone the repository
git clone https://github.com/shreyasdas2006-web/Campus-Course-Records-Manager-CCRM-.git
cd Campus-Course-Records-Manager-CCRM-

# Compile
javac src/edu/ccrm/CrmApp.java

# Run
java -cp src edu.ccrm.CrmApp
```

---

## 4. Project Structure

```
Campus-Course-Records-Manager-CCRM/
│
├── src/edu/ccrm/                    ← Main source root
│   ├── CrmApp.java                  ← Application entry point & menu system
│   │
│   ├── config/
│   │   └── AppConfig.java           ← Singleton: central data store
│   │
│   ├── model/                       ← Domain model classes
│   │   ├── Person.java              ← Abstract base class
│   │   ├── Student.java             ← Inherits Person
│   │   ├── Instructor.java          ← Inherits Person
│   │   ├── Course.java              ← Uses Builder pattern
│   │   ├── Enrollment.java          ← Links Student ↔ Course
│   │   ├── Grade.java               ← Enum with grade logic
│   │   └── Semester.java            ← Enum (FALL, SPRING, SUMMER)
│   │
│   ├── service/                     ← Business logic layer
│   │   ├── StudentService.java      ← Student CRUD operations
│   │   ├── CourseService.java       ← Course CRUD + search
│   │   └── EnrollmentService.java   ← Enroll, grade, GPA, transcript
│   │
│   └── util/                        ← Utilities
│       ├── exception/
│       │   ├── DuplicateEnrollmentException.java
│       │   └── MaxCreditLimitExceededException.java
│       └── io/
│           └── FileHandler.java     ← CSV export, backup, folder size
│
├── ccrm_data/                       ← Runtime-generated export files
│   ├── students_export.csv
│   └── courses_export.csv
│
├── proj_data/                       ← Sample seed data
│   ├── students_sample.csv
│   └── courses_sample.csv
│
├── screenshots/                     ← Application screenshots
├── README.md
├── Usage.md
└── Project_Report.md                ← This file
```

---

## 5. Architecture & Design

The application follows a **3-layer architecture**:

```
┌─────────────────────────────────┐
│         Presentation Layer      │  CrmApp.java  (Menu & I/O)
├─────────────────────────────────┤
│          Service Layer          │  StudentService, CourseService,
│                                 │  EnrollmentService
├─────────────────────────────────┤
│           Model Layer           │  Person, Student, Instructor,
│                                 │  Course, Enrollment, Grade
└─────────────────────────────────┘
         ↓ Shared State ↓
┌─────────────────────────────────┐
│    AppConfig (Singleton)        │  In-memory data store
└─────────────────────────────────┘
```

- **Presentation Layer** handles user input and output via the console menu.
- **Service Layer** contains all business logic and validation.
- **Model Layer** defines the data entities using OOP principles.
- **AppConfig** acts as a centralized, singleton data store holding all runtime lists (students, courses, enrollments, instructors).

---

## 6. Core Modules

### 6.1 `CrmApp.java` — Entry Point
The main class that bootstraps the application. It:
- Seeds initial sample data (students, instructors, courses)
- Runs the main `while` loop with `Scanner` input
- Delegates user actions to the appropriate service handler methods

**Menu Options:**
| Option | Action |
|--------|--------|
| 1 | Add New Student |
| 2 | List All Students |
| 3 | List All Courses |
| 4 | Enroll a Student in a Course |
| 5 | Record Marks for a Student |
| 6 | View Student Transcript & GPA |
| 7 | Export Data to CSV |
| 8 | Create a Backup |
| 9 | Exit |

---

### 6.2 `model/` — Domain Entities

| Class | Type | Key Responsibility |
|---|---|---|
| `Person` | Abstract class | Base entity with `id`, `name`, `email`; declares abstract `getDetails()` |
| `Student` | Extends `Person` | Adds `regNo`, list of enrolled courses |
| `Instructor` | Extends `Person` | Adds `department` field |
| `Course` | Regular class | Uses **Builder pattern**; holds `code`, `title`, `credits`, `instructor`, `semester` |
| `Enrollment` | Regular class | Links a `Student` to a `Course`; holds `enrollmentDate` and `Grade` |
| `Grade` | Enum | `S, A, B, C, D, F`; maps marks to grade points (10.0 → 0.0) |
| `Semester` | Enum | `FALL, SPRING, SUMMER` |

---

### 6.3 `service/` — Business Logic

**`StudentService.java`**
- `addStudent(Student)` — validates uniqueness and registers the student
- `findStudentById(int)` — searches in-memory list using Streams
- `printAllStudents()` — formatted console output

**`CourseService.java`**
- `addCourse(Course)` — adds a course to the global store
- `findCourseByCode(String)` — case-insensitive lookup
- `printAllCourses()` — formatted console output

**`EnrollmentService.java`**
- `enrollStudent(Student, Course)` — validates uniqueness and credit cap; throws custom exceptions
- `recordMarks(Student, Course, int)` — assigns a `Grade` enum by converting marks
- `getEnrollmentsForStudent(Student)` — returns all enrollments for a given student
- `computeGpa(Student)` — calculates weighted GPA based on credits and grade points
- `printAllEnrollments()` — utility to list all enrollment records

---

### 6.4 `util/` — Utilities

**`FileHandler.java`**
- `exportToCsv(Path, headers, rows)` — writes data to a CSV file using Java NIO.2
- `createBackupFolder(Path)` — creates a timestamped backup directory using recursion
- `getFolderSize(Path)` — recursively computes total file size within a folder

---

## 7. OOP Concepts Applied

### 7.1 Encapsulation
All model fields are `private`, accessed only via public getters and setters. For example, `Student.regNo`, `Course.credits`, and `Enrollment.grade` are never directly exposed.

### 7.2 Inheritance
```
Person (abstract)
├── Student
└── Instructor
```
Both `Student` and `Instructor` inherit common attributes (`id`, `name`, `email`) and behavior from the abstract `Person` class.

### 7.3 Abstraction
- `Person` is an **abstract class** that defines the contract `getDetails()`, forcing all subclasses to provide their own implementation.
- Service interfaces provide a layer of abstraction between the menu and the data model.

### 7.4 Polymorphism
- `getDetails()` is overridden in both `Student` and `Instructor` with their own specific implementations.
- Service methods take generic `Person` references, enabling polymorphic dispatch.

---

## 8. Advanced Java Features Used

| Feature | Where Used |
|---|---|
| **Generics** | `List<Student>`, `List<Course>`, `List<Enrollment>` in all services |
| **Collections Framework** | `ArrayList` throughout for dynamic in-memory storage |
| **Enums** | `Grade` (with custom methods), `Semester` |
| **Streams & Lambdas** | Student/Course lookup (`findFirst()`, `filter()`) |
| **Date/Time API** | `LocalDate` used in `Enrollment` for `enrollmentDate` |
| **Java NIO.2** | `Path`, `Paths`, `Files` in `FileHandler.java` |
| **Custom Exceptions** | `DuplicateEnrollmentException`, `MaxCreditLimitExceededException` |
| **Assertions** | Constructor invariant checks (`assert id > 0`) |
| **Scanner** | Console input handling in `CrmApp.java` |

---

## 9. Design Patterns

### 9.1 Singleton Pattern — `AppConfig`
`AppConfig` holds the in-memory data store (lists of students, courses, enrollments, instructors) and ensures only **one instance** exists throughout the application's lifecycle.

```java
// Single global instance
public static AppConfig getInstance() {
    if (instance == null) {
        instance = new AppConfig();
    }
    return instance;
}
```

This prevents multiple conflicting data stores and ensures all services share the same state.

### 9.2 Builder Pattern — `Course`
`Course` objects are constructed using a **fluent Builder** rather than a telescoping constructor, making creation readable and flexible:

```java
Course c1 = new Course.Builder("CS101")
        .title("Intro to Programming")
        .credits(4)
        .semester(Semester.FALL)
        .instructor(profGupta)
        .build();
```

This avoids ambiguous constructor overloads and supports optional fields naturally.

---

## 10. Data Flow & Workflow

```
User Input (Console)
       │
       ▼
  CrmApp.java  ─── delegates to ───►  Service Layer
                                            │
                              ┌─────────────┼──────────────┐
                              ▼             ▼              ▼
                       StudentService  CourseService  EnrollmentService
                              │             │              │
                              └─────────────┴──────────────┘
                                            │
                                            ▼
                                    AppConfig (Singleton)
                                    [students, courses,
                                     enrollments, instructors]
                                            │
                                            ▼ (on export/backup)
                                    FileHandler (NIO.2)
                                    ─► ccrm_data/*.csv
                                    ─► ccrm_data/backup_<timestamp>/
```

---

## 11. Exception Handling

The project defines two **custom checked exceptions** extending `Exception`:

| Exception | Trigger Condition |
|---|---|
| `DuplicateEnrollmentException` | Student tries to enroll in a course they are already enrolled in |
| `MaxCreditLimitExceededException` | Total enrolled credits would exceed the cap of **21 credits** |

These exceptions are thrown by `EnrollmentService.enrollStudent()` and caught in `CrmApp.handleEnrollment()`, giving the user clear, domain-appropriate error messages instead of generic runtime crashes.

Additionally, `NumberFormatException` is handled wherever integer input is expected (Student ID, Marks), and `IOException` is handled in all file operations.

---

## 12. File I/O & Data Persistence

The application uses **Java NIO.2** (`java.nio.file` package) for all file operations:

- **Export:** Writes student and course data to CSV files under `ccrm_data/` using `Files.write()` with `BufferedWriter`.
- **Backup:** Uses `Files.createDirectories()` with a **timestamped folder name** to non-destructively version exported data.
- **Folder Size:** Recursively walks the directory tree using `Files.walk()` to sum file sizes.

### CSV Format

**`students_export.csv`**
```
ID,RegistrationNumber,FullName,Email
1,REG101,Ravi Kumar,ravi.k@email.com
2,REG102,Priya Singh,priya.s@email.com
```

**`courses_export.csv`**
```
Code,Title,Credits,InstructorName
CS101,Intro to Programming,4,Dr. Gupta
PHY202,Quantum Physics,3,Dr. Sharma
```

---

## 13. Grading System

Grades are modeled as a Java **Enum** (`Grade.java`) with associated grade points, following a standard 10-point GPA scale:

| Grade | Marks Range | Grade Points |
|-------|-------------|--------------|
| S     | 90 – 100    | 10.0         |
| A     | 80 – 89     | 9.0          |
| B     | 70 – 79     | 8.0          |
| C     | 60 – 69     | 7.0          |
| D     | 50 – 59     | 6.0          |
| F     | Below 50    | 0.0          |

**GPA Calculation:**
```
GPA = Σ (GradePoints × Credits) / Σ Credits
```
Only graded enrollments are counted. Ungraded courses are excluded from the GPA calculation.

---

## 14. Sample Output

### Adding a Student & Enrolling in a Course

```text
--- Main Menu ---
1. Add New Student
...
Enter your choice: 1

--- Add New Student ---
Enter Student ID (a unique number): 3
Enter Full Name: Amit Patel
Enter Email: amit.p@email.com
Enter Registration Number (e.g., REG103): REG103

Student added: Amit Patel

Enter your choice: 4
Enter Student ID to enroll: 3
Enter Course Code (e.g., CS101): CS101

Success! Amit Patel is now enrolled in Intro to Programming
```

### Recording Marks & Viewing Transcript

```text
Enter your choice: 5

--- Record Student Marks ---
Enter Student ID: 3
Enter Course Code: CS101
Enter Marks (0-100): 92

Marks recorded. Amit Patel gets grade S for Intro to Programming

Enter your choice: 6

--- View Student Transcript ---
Enter Student ID: 3

----------------------------------------
Transcript for: Amit Patel (REG103)
----------------------------------------
Course: Intro to Programming   | Grade: S
----------------------------------------
Cumulative GPA: 10.00
----------------------------------------
```

---

## 15. Syllabus Mapping

| Syllabus Topic | Implementation in CCRM |
|---|---|
| **OOP – Encapsulation** | Private fields + public getters/setters in all model classes |
| **OOP – Inheritance** | `Student` and `Instructor` both extend `Person` |
| **OOP – Abstraction** | `Person` is abstract with abstract method `getDetails()` |
| **OOP – Polymorphism** | `getDetails()` overridden in `Student` and `Instructor` |
| **Packages** | `edu.ccrm.model`, `edu.ccrm.service`, `edu.ccrm.util` |
| **Exception Handling** | Custom checked exceptions; try-catch in all risky operations |
| **Collections Framework** | `ArrayList<Student>`, `ArrayList<Course>`, etc. |
| **Generics** | Service methods use `List<Student>`, `List<Course>` |
| **Enums** | `Grade` (with `fromMarks()`), `Semester` |
| **Streams & Lambdas** | Filtering and searching student/course lists |
| **Date/Time API** | `LocalDate` for `Enrollment.enrollmentDate` |
| **File I/O (NIO.2)** | `FileHandler.java` — export CSV, backup, folder size |
| **Recursion** | `getFolderSize()` walks directory tree recursively |
| **Assertions** | Constructor-level invariants (`assert id > 0`) |
| **Design Pattern – Singleton** | `AppConfig.getInstance()` |
| **Design Pattern – Builder** | `Course.Builder` for flexible course construction |

---

## 16. Limitations & Future Enhancements

### Current Limitations
- **No persistent database** — all data is in-memory and lost on exit (CSV export is manual)
- **No authentication** — no login or role-based access control
- **No GUI** — purely console-based; no graphical user interface
- **Single-user** — not designed for concurrent multi-user access
- **No import from CSV** — export is implemented but import from existing CSV files is missing

### Possible Future Enhancements
- **Database Integration** — Use JDBC with SQLite or MySQL for persistent storage
- **REST API** — Expose services via Spring Boot for web/mobile front-ends
- **GUI** — Add a JavaFX or Swing interface for better usability
- **CSV Import** — Allow seeding the system from existing student/course CSV files
- **Authentication** — Add role-based login (Admin, Instructor, Student views)
- **Unit Tests** — Add JUnit test coverage for service and model classes
- **Logging** — Integrate SLF4J/Log4j for structured application logging

---

## 17. Conclusion

The **Campus Course & Records Manager (CCRM)** successfully demonstrates the practical application of core and advanced Java SE programming concepts within a structured, real-world domain. The project applies:

- A clean **3-layer architecture** (Presentation → Service → Model)
- Core **OOP principles** (Encapsulation, Inheritance, Abstraction, Polymorphism)
- **Design patterns** (Singleton, Builder) for maintainable and scalable code
- **Advanced Java features** including Generics, Streams, Lambdas, Enums, NIO.2, and the Date/Time API
- Robust **exception handling** with custom business-rule exceptions

The result is a well-structured, extensible Java application that serves as a strong academic foundation for understanding enterprise-level software design.

---

*Report generated for the Campus Course & Records Manager (CCRM) — Version 1.0*
