# LearnTrack — Student & Course Management System

> A modular, console-based Student and Course Management System built with Core Java, demonstrating clean code principles, Object-Oriented Programming (OOP) fundamentals, layered architecture, and robust exception handling.

---

## 📌 Project Overview

LearnTrack is designed to manage educational entities including Students, Courses, and Enrollments. Built strictly with Core Java fundamentals, this project focuses on solid software architecture, modular package design, and in-memory repository patterns.

---

## ✨ Features

- **Student Management:**
  - Register new students with constructor overloading.
  - Search students by unique auto-generated IDs.
  - Soft-deactivate students to preserve records.
- **Course Management:**
  - Add new courses with duration tracking.
  - View course catalogs.
  - Toggle course statuses (ACTIVE, INACTIVE, ARCHIVED).
- **Enrollment Management:**
  - Enroll students in courses with strict state validations (e.g., rejecting inactive students/courses).
  - Track enrollment statuses (ACTIVE, COMPLETED, CANCELLED).
  - Search enrollments per student.
- **Defensive Input Validation & Exception Handling:**
  - Handles invalid menu options, empty inputs, and missing entities gracefully without crashing.

---

## 📊 Class Diagram & Architecture

```text
               +-------------------+
               |      Person       |
               | (Abstract/Base)   |
               +-------------------+
               | - id: int         |
               | - firstName: String|
               | - lastName: String|
               | - email: String   |
               +-------------------+
                         ^
                         | (Inheritance)
         +---------------+---------------+
         |                               |
+-------------------+          +-------------------+
|      Student      |          |      Trainer      |
+-------------------+          +-------------------+
| - batch: String   |          | - specialization  |
| - active: boolean |          | - experience      |
+-------------------+          +-------------------+

+-----------------------+      +-----------------------+
|        Course         |      |      Enrollment       |
+-----------------------+      +-----------------------+
| - id: int             |      | - id: int             |
| - courseName: String  |      | - studentId: int      |
| - durationInWeeks: int|      | - courseId: int       |
| - status: CourseStatus|      | - status: Status      |
+-----------------------+      +-----------------------+
            ^                              ^
            |                              |
 (References Course ID)          (References Student ID)

 Package & Directory Structure

 LearnTrack/
├── docs/
│   ├── Setup_Instructions.md
│   ├── JVM_Basics.md
│   └── Design_Notes.md
├── src/
│   └── com/
│       └── airtribe/
│           └── learntrack/
│               ├── Main.java
│               ├── entity/
│               ├── repository/
│               ├── service/
│               ├── exception/
│               ├── util/
│               ├── constants/
│               └── enums/
└── README.md

🚀 How to Compile and Run
Open terminal in project root folder.

Compile source code:
1.mkdir -p bin
2.javac -d bin $(find src -name "*.java")

3.Run application:
java -cp bin com.airtribe.learntrack.Main

**To compile and run the project manually from the project root directory:

1.Create a build directory:

Bash
mkdir -p bin
Compile all source code files:

2.Bash
javac -d bin $(find src -name "*.java")
Run the application entry point:

3.Bash
java -cp bin com.airtribe.learntrack.Main
Running these commands initializes the interactive console menu for managing students, courses, and enrollments.




