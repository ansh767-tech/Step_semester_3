# Session 10: Linked List & System Design Implementation

This directory contains solutions for **Session 10 Class Problems** and **Category B Assignment Problems**.

---

## 📂 Directory Structure

```text
Linked_List/
├── class_problems/
│   ├── BankAccountWithdrawalSystem.java
│   ├── CampusVehiclePassSystem.java
│   ├── StudentClubRegistrationManagement.java
│   ├── ClassMarksGridAnalysis.java
│   └── PayrollRegisterSystem.java
└── assignment_problems/
    ├── PriorityTokenInsertion.java
    ├── CancelledOrdersCleanup.java
    ├── ReverseTheQueue.java
    ├── TrainCoachesBothWays.java
    └── RoundRobinGameTurns.java
```

---

## 🛠️ Problems Implemented

### 1. Class Problems

* **`BankAccountWithdrawalSystem.java`**
  * **Concepts**: Abstract classes, inheritance, encapsulation, business rule validation.
  * **Description**: Models `Savings` (minimum balance of 1000) and `Current` accounts (overdraft limit up to 5000) with withdrawal validation and rejection messages.
  * **Time Complexity**: $O(1)$ per withdrawal operation.

* **`CampusVehiclePassSystem.java`**
  * **Concepts**: Inheritance, interfaces (`Chargeable`), runtime type checks.
  * **Description**: Manages campus vehicle registration fees (`Bike`: 300, `Car`: 1000, `EBike`: 300, `ECar`: 1000) and allows only electric vehicles implementing `Chargeable` to book charging bays.
  * **Time Complexity**: $O(1)$ per registration / charging query.

* **`StudentClubRegistrationManagement.java`**
  * **Concepts**: Object equality, hashing contract (`equals()` & `hashCode()`), `Set` uniqueness.
  * **Description**: Enforces member uniqueness based strictly on student `rollNumber` to reject duplicates regardless of instance creation.
  * **Time Complexity**: $O(1)$ average per addition and membership verification.

* **`ClassMarksGridAnalysis.java`**
  * **Concepts**: 2D array (grid) processing, formatting, column and row traversals.
  * **Description**: Computes each student's total marks, subject averages formatted to 2 decimal places, and identifies the topper.
  * **Time Complexity**: $O(R \times C)$ where $R$ is students count and $C$ is subjects count.

* **`PayrollRegisterSystem.java`**
  * **Concepts**: Polymorphism, method overriding, array aggregation.
  * **Description**: Processes `FullTime` (salary), `PartTime` (hours $\times$ rate), and `Intern` (stipend) staff, generates readable payslips, total payroll, and top earner.
  * **Time Complexity**: $O(N)$ where $N$ is staff count.

---

### 2. Assignment Problems (Category B — Custom Linked Lists)

*Note: All linked list problems implement custom node structures without using `java.util.LinkedList`.*

* **`PriorityTokenInsertion.java`**
  * **Structure**: Singly Linked List (`Node` with `data` and `next`).
  * **Operations**:
    * `addFirst(int token)`: Inserts at front in $O(1)$ time.
    * `addLast(int token)`: Inserts at end in $O(1)$ time (with tail reference).
    * `insertAt(int index, int token)`: Inserts at 0-based index in $O(N)$ time.
  * **Space Complexity**: $O(1)$ auxiliary space.

* **`CancelledOrdersCleanup.java`**
  * **Structure**: Singly Linked List.
  * **Task**: Removes all occurrences of a given order code from the list in-place.
  * **Time Complexity**: $O(N)$ single pass.
  * **Space Complexity**: $O(1)$ auxiliary space.

* **`ReverseTheQueue.java`**
  * **Structure**: Singly Linked List.
  * **Task**: Reverses the list in place without creating new nodes using three references (`prev`, `curr`, `next`).
  * **Time Complexity**: $O(N)$ single pass.
  * **Space Complexity**: $O(1)$ auxiliary space.

* **`TrainCoachesBothWays.java`**
  * **Structure**: Doubly Linked List with `head` and `tail` references (`prev` and `next` pointers).
  * **Operations**:
    * `addLast(String name)`: $O(1)$ time.
    * `remove(String name)`: $O(N)$ search time, $O(1)$ unlinking time.
    * `printForward()` / `printBackward()`: $O(N)$ traversal time.
  * **Space Complexity**: $O(1)$ auxiliary space.

* **`RoundRobinGameTurns.java`**
  * **Structure**: Circular Singly Linked List with `tail` reference (`tail.next` is `head`).
  * **Operations**:
    * `addLast(String name)`: $O(1)$ time.
    * `printTurns(int turns)`: $O(T)$ where $T = \text{turns}$.
  * **Space Complexity**: $O(1)$ auxiliary space.

---

## 🚀 How to Compile & Run

From the root project directory (`c:\Users\Ansh Singh\Desktop\STEP PROGRAM\Step_semester_3`):

### Compile All Files
```bash
javac src/main/java/Linked_List/class_problems/*.java src/main/java/Linked_List/assignment_problems/*.java
```

### Run Class Problems
```bash
java -cp src/main/java Linked_List.class_problems.BankAccountWithdrawalSystem
java -cp src/main/java Linked_List.class_problems.CampusVehiclePassSystem
java -cp src/main/java Linked_List.class_problems.StudentClubRegistrationManagement
java -cp src/main/java Linked_List.class_problems.ClassMarksGridAnalysis
java -cp src/main/java Linked_List.class_problems.PayrollRegisterSystem
```

### Run Assignment Problems
```bash
java -cp src/main/java Linked_List.assignment_problems.PriorityTokenInsertion
java -cp src/main/java Linked_List.assignment_problems.CancelledOrdersCleanup
java -cp src/main/java Linked_List.assignment_problems.ReverseTheQueue
java -cp src/main/java Linked_List.assignment_problems.TrainCoachesBothWays
java -cp src/main/java Linked_List.assignment_problems.RoundRobinGameTurns
```
