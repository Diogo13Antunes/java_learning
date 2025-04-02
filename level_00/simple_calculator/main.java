import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class Calculator {
	Scanner scanner = new Scanner(System.in);
	List<Character> validOperations = Arrays.asList('+', '-', '*', '/');
	int value0 = 0;
	int value1 = 0;
	char operator = '+';

	private void print(String msg) {
		System.out.print(msg);
	}

	private int getValue(String valueNumber, char operator) {
		while (true) {
			try {
				print("Please insert the value " + valueNumber + ": ");
				int val = scanner.nextInt();
				if (operator == '/' && val == 0) {
					print("Impossible to devide by zero.\n");
					scanner.nextLine();
				}
				else
					return (val);
			}
			catch (Exception e) {
				print("The value is not valid. It must be an integer between -2147483648 e 2147483647.\n");
				scanner.nextLine();
			}
		}
	}

	private char getOperator() {
		while (true) {
			try {
				print("Available Operations -> ");
				for (int i = 0; i < this.validOperations.size(); i++) {
					print(this.validOperations.get(i).toString());
					if (i < this.validOperations.size() - 1)
						print(" | ");
				}
				print("\n");
				print("Please insert the operator: ");
				String input = scanner.next();
				if (input.length() == 1 && this.validOperations.contains(input.charAt(0)))
					return (input.charAt(0));
				else {
					print("Invalid Operator. Please choose one from the available list.\n");
					scanner.nextLine();
				}
			}
			catch (Exception e) {
				print("The operator is not valid.\n");
				scanner.nextLine();
			}
		}
	}

	private void printResult(String result) {
		print("The result of " + this.value0 + " " + this.operator + " " + this.value1 + " is " + result + ".\n");
	}

	private void calculate() {
		switch (this.operator) {
			case '+':
				long resultSum = (long) this.value0 + this.value1;
				printResult(String.valueOf(resultSum));
				break;
			case '-':
				long resultSub = (long) this.value0 - this.value1;
				printResult(String.valueOf(resultSub));
				break;
			case '*':
				long resultMult = (long) this.value0 * this.value1;
				printResult(String.valueOf(resultMult));
				break;
			case '/':
				double resultDiv = this.value0 * this.value1;
				printResult(String.valueOf(resultDiv));
				break;
		}
	}

	public Calculator() {
		this.value0 = getValue("1", this.operator);
		this.operator = getOperator();
		this.value1 = getValue("2", this.operator);
		calculate();
	}
}

public class main {
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
	}
}
