# Simple Calculator

## Description

This Java program is a simple **Calculator** that performs basic arithmetic operations: addition, subtraction, multiplication, and division. It takes user input for two values and an operator, performs the calculation, and displays the result. It also handles errors, such as division by zero and invalid input.

The calculator program uses the **Scanner** class to receive user input and validates it to ensure the values are integers and the operator is valid. The program will continue to prompt the user until valid input is provided.

---

## Features

- **Addition (`+`)**
- **Subtraction (`-`)**
- **Multiplication (`*`)**
- **Division (`/`)**
- Input validation to ensure integers are entered.
- Special handling for division by zero.
- A prompt for choosing the operation from a valid list of operators.

---

## How the Program Works

### 1. **Getting User Input for Values**
   The program prompts the user to input two integers, `value0` and `value1`. It ensures that these values are valid integers and handles any input errors. If a user attempts to divide by zero, the program will prompt again for a valid input.

### 2. **Choosing an Operator**
   The program asks the user to select an operator from a list of valid operations: `+`, `-`, `*`, or `/`. If an invalid operator is provided, the program will display a message and ask for a valid operator.

### 3. **Performing the Calculation**
   After receiving both values and the operator, the program performs the appropriate calculation and prints the result.

---

## Code Overview

### 1. **`Calculator` Class**
   This class contains all the logic for interacting with the user, performing the calculations, and printing the results.

   - **`getValue(String valueNumber, char operator)`**: Prompts the user to input a value (either `value0` or `value1`) and validates the input.
   - **`getOperator()`**: Prompts the user to input an operator (`+`, `-`, `*`, or `/`) and validates it.
   - **`printResult(String result)`**: Prints the result of the operation.
   - **`calculate()`**: Performs the calculation based on the operator and values, and calls `printResult()` to display the output.
   - **Constructor**: Initializes the values and operator and calls the `calculate()` method.

### 2. **`main` Class**
   The entry point of the program. It creates an instance of the `Calculator` class and runs the calculations.

---

## Example Usage

When you run the program, it will prompt you for inputs:

```
Please insert the value 1: 5
Available Operations -> + | - | * | /
Please insert the operator: *
Please insert the value 2: 4
The result of 5 * 4 is 20.
```

If you try dividing by zero:
```
Please insert the value 1: 5
Available Operations -> + | - | * | /
Please insert the operator: /
Please insert the value 2: 0
Impossible to devide by zero.
Please insert the value 2: 4
The result of 5 / 4 is 1.25.
```

---

## How to Run

### Requirements

- Java 8 or later
- A terminal or IDE to compile and run Java code.

### Running the Program

```bash
java main.java
```
