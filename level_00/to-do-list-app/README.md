# To Do List App

## Description

This Java program is a **Command-Line Task Manager** that allows you to create, list, view, and delete tasks.
Each task has a **name**, an **optional description**, and a **creation date**.

The program runs entirely in the terminal and includes input validation to ensure data integrity.

---

## Features

* **Add new tasks** with name and optional description
* **List all tasks** in a formatted table
* **View task details** for a specific task
* **Delete tasks** by their ID
* **Input validation** for task names and descriptions
* **Automatic unique ID generation** for each task
* **Clean CLI interface** with menu navigation

---

## How the Program Works

### 1. **Main Menu**

When the program starts, you see the menu options:

1. Add Task
2. Remove Task
3. List Task
4. View Task Info
5. Exit

### 2. **Adding a Task**

* The program prompts for a task name and validates:

  * Length between 3 and 50 characters
  * Only letters, numbers, and spaces allowed
* Description is optional but validated if provided.
* A unique ID is generated automatically, and the task is saved.

### 3. **Removing a Task**

* Displays all current tasks.
* User enters the task ID to delete.
* If the ID exists, the task is removed successfully.

### 4. **Listing Tasks**

* Displays all tasks in a formatted table with:

  * **ID**
  * **Task Name**
  * **Creation Date**
* If no tasks exist, a friendly message is displayed.

### 5. **Viewing Task Info**

* Displays all tasks for reference.
* Prompts the user to enter the task ID.
* Shows the task’s name, description, and creation date.

---

## Code Overview

### 1. **`Cli` Class**

Utility class for user interaction in the terminal:

* Message printing (`print`, `debugPrint`)
* User input (`input`, `menuOpt`)
* Screen clearing (`clear`)
* Pause with ENTER (`pressEnterToContinue`)

### 2. **`Task` Class**

Represents a single task with:

* Name
* Description
* Creation date (formatted `yyyy-MM-dd`)
* Method to display task details

### 3. **`TaskElementsValidator` Class**

Handles validation for:

* **Name**: required, length limits, allowed characters
* **Description**: optional, length limits, allowed characters

### 4. **`Options` Class**

Contains main logic for:

* Creating tasks (`createTask`)
* Deleting tasks (`deleteTask`)
* Listing tasks (`listTasks`)
* Viewing task info (`viewTaskInfo`)

### 5. **`Menu` Class**

* Holds the available menu options
* Displays the main menu
* Executes the user’s chosen action

### 6. **`main` Class**

* Entry point of the program
* Initializes the menu and closes the scanner

---

## Example Usage

When running the program:

```
--------------------------------------------------
1: Add Task
2: Remove Task
3: List Task
4: View Task Info
5: Exit
--------------------------------------------------
Choose an option: 1
Task Name: Homework
Task Description: Math exercises

Press ENTER to continue...
```

Listing tasks:

```
| ID                                  | Task Name                                         | Creation Date |
| ==================================== | ================================================== | ============= |
| 9b8c1d6c-4f2f-3e41-b1a2-3a22cb452f3b | Homework                                          | 2025-08-14    |
| ==================================== | ================================================== | ============= |
```

Viewing task details:

```
Task ID: 9b8c1d6c-4f2f-3e41-b1a2-3a22cb452f3b
Name: Homework
Description: Math exercises
Creation Date: 2025-08-14
```

---

## How to Run

### Requirements

* **Java 8** or newer
* Terminal or IDE (IntelliJ IDEA, Eclipse, etc.)

### Running the Program

1. Save the code into a file named `main.java`
2. Compile it:

   ```bash
   javac main.java
   ```
3. Run it:

   ```bash
   java main
   ```
