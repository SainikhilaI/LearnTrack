# Design Notes & Architectural Decisions

## 1. Why `ArrayList` was used instead of standard Arrays
* **Dynamic Sizing:** Standard Java arrays have a fixed length determined at instantiation. `ArrayList` automatically resizes dynamically, making it ideal for tracking an unknown and growing number of students, courses, and enrollment records.
* **Built-in Operations:** `ArrayList` provides robust utility methods like `.add()`, `.remove()`, and iteration support that simplify in-memory data management.

## 2. Where and Why Static Members Were Used
* **`IdGenerator` Utility Class:** We utilized static fields (`studentIdCounter`, `courseIdCounter`) and static methods (`getNextStudentId()`) to maintain a single, shared application-wide sequence state for primary keys across all service invocations without needing instance instantiation.

## 3. Inheritance and Object-Oriented Benefits
* **`Person` Base Hierarchy:** `Student` and `Trainer` extend the base class `Person`.
* **Gains Realized:**
  1. **Code Reusability:** Common fields (`id`, `firstName`, `lastName`, `email`) and core getters/setters are defined once in `Person` and inherited, reducing redundancy.
  2. **Polymorphism:** Method overriding on `getDisplayName()` allows customized subclass formatting while presenting a unified interface.