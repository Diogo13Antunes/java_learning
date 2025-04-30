
import java.lang.classfile.instruction.ThrowInstruction;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

class Cli {
	private static Scanner scanner = new Scanner(System.in);

	public static void closeScanner() {
        scanner.close();
    }

	public static void print(String msg, boolean nl) {
		if (nl)
			System.out.println(msg);
		else
			System.out.print(msg);
	}

	public static void print(String msg) {
		System.out.print(msg);
	}

	public static void debugPrint(String msg) {
		System.out.print("[DEBUG]: " + msg);
	}

	public static void clear() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            ProcessBuilder processBuilder;
            if (os.contains("win"))
                processBuilder = new ProcessBuilder("cmd", "/c", "cls");
            else
                processBuilder = new ProcessBuilder("clear");
            processBuilder.inheritIO().start().waitFor();
        }
		catch (Exception e) {
        	e.printStackTrace();
        }
    }

	public static String input(String msg) {
		if (msg.length() > 0)
			print(msg);
		String res = scanner.nextLine();
		return (res);
	}

	public static int menuOpt(String msg, String[] availableOpts) {
		int opt = -1;
		String res = input(msg);
		boolean found = Arrays.asList(availableOpts).contains(res);
		if (found)
			opt = Integer.parseInt(res);
		return  opt;
	}

	public static void pressEnterToContinue() {
		System.out.print("Press ENTER to continue...");
		scanner.nextLine();
	}
}

class Task {
	private final String name;
	private final String description;

	public Task(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String info() {
		String info = "";
		info += "Name: " + this.name + "\n";
		info += "Description: " + (this.description.isEmpty() ? "" : this.description) + "\n";
		return info;
	}

	@Override
	public String toString() {
		return (this.name);
	}
}

class TaskElementsValidator {

	public static boolean name(String name) {

		boolean isValid = true;
		String invalidReason = null;

		if (name == null || name.trim().isEmpty()) {
			isValid = false;
			invalidReason = "Name cannot be empty.";
        }

		if (isValid && (name.length() < 3 || name.length() > 50)) {
			isValid = false;
			invalidReason = "Name must be between 3 and 50 characters long.";
		}

		if (isValid && !name.matches("^[A-Za-z0-9 ]+$")) {
			isValid = false;
			invalidReason = "Name can only contain letters, numbers, and spaces.";
		}

		if (isValid && !name.matches(".*[A-Za-z0-9].*")) {
			isValid = false;
			invalidReason = "Name must include at least one letter or number.";
		}

		if (!isValid) {
			Cli.print("Invalid task name.", true);
			Cli.print(invalidReason, true);
		}

		return isValid;
	}

	public static boolean isValidDescription(String description) {

		boolean isValid = true;
		String invalidReason = null;

		if (description == null || description.trim().isEmpty()) {
			return true;
        }

		if (isValid && (description.length() < 3 || description.length() > 500)) {
			isValid = false;
			invalidReason = "Description must be between 3 and 500 characters long.";
		}

		if (isValid && !description.matches("^[A-Za-z0-9\\s.,;:!?()'\"&%\\-]*$")) {
			isValid = false;
			invalidReason = "Description can only contain letters, digits, spaces, and common punctuation.";
		}

		if (isValid && !description.matches(".*[A-Za-z0-9].*")) {
			isValid = false;
			invalidReason = "Description must include at least one letter or number.";
		}

		if (!isValid) {
			Cli.print("Invalid task description.", true);
			Cli.print(invalidReason, true);
		}

		return isValid;
	}

}

class Options {

	public static void createTask(Map<String, Task> tasks) {
		Cli.clear();
		String newTaskName;
		String newTaskDescription;
		while (true) { 
			newTaskName = Cli.input("Task Name: ").trim();
			if (TaskElementsValidator.name(newTaskName))
				break;
		}
		while (true) { 
			newTaskDescription = Cli.input("Task Description: ");
			if (TaskElementsValidator.isValidDescription(newTaskDescription))
				break;
			Cli.print("Invalid task description.", true);
		}

		Task newTask = new Task(newTaskName, newTaskDescription);
		byte[] byteArray = ByteBuffer.allocate(Long.BYTES).putLong(System.currentTimeMillis()).array();
		String newTaskId = UUID.nameUUIDFromBytes(byteArray).toString();
		tasks.put(newTaskId, newTask);
	}

	public static void deleteTask(Map<String, Task> tasks) {
		Cli.clear();

		if (tasks.isEmpty()) {
			Cli.print("There is no tasks to remove.", true);
			return ;
		}

		Options.listTasks(tasks);

		String taskId = Cli.input("Task ID: ").trim();

		if (tasks.containsKey(taskId)) {
			tasks.remove(taskId);
			Cli.print("Task with ID " + taskId + " deleted with success.", true);
        }
		else
			Cli.print("Task ID " + taskId + " not found.", true);
	}

	public static void listTasks(Map<String, Task> tasks) {
		Cli.clear();
		if (!tasks.isEmpty()) {
			System.out.printf("| %-36s | %-50s |%n", "ID", "Task Name");
			Cli.print("| ==================================== | ================================================== |", true);
			for (Map.Entry<String, Task> entry : tasks.entrySet()) {
				System.out.printf("| %-36s | %-50s |%n", entry.getKey(), entry.getValue());
			}
			Cli.print("| ==================================== | ================================================== |", true);
		}
		else
			Cli.print("There is no tasks to do. 😁", true);
	}

	public static void viewTaskInfo(Map<String, Task> tasks) {
		Cli.clear();

		if (tasks.isEmpty()) {
			Cli.print("There is no tasks to inspect.", true);
			return ;
		}

		Options.listTasks(tasks);

		String taskId = Cli.input("Task ID: ").trim();

		if (tasks.containsKey(taskId)) {
			Task task = tasks.get(taskId);
			Cli.print(task.info(), true);
        }
		else
			Cli.print("Task ID " + taskId + " not found.", true);
	}
}

class Menu {
	private final Map<Integer, String> options = new HashMap<>();
	private final Map<String, Task> tasks = new HashMap<>();
	private final String[] keysArray;

	public Menu() {
		this.options.put(1, "Add Task");
		this.options.put(2, "Remove Task");
		this.options.put(3, "List Task");
		this.options.put(4, "View Task Info");
		this.options.put(5, "Exit");
		this.keysArray = this.options.keySet().stream().map(String::valueOf).toArray(String[]::new);

		routine();

	}

	private void routine() {
		while (true) {
			Cli.clear();
			printHome();
			int opt = Cli.menuOpt("Choose an option: ", this.keysArray);
			Cli.debugPrint("OPT: " + opt + "\n");
			if (opt == 5)
				break ;
			execute(opt);
			Cli.pressEnterToContinue();
		}
	}

	private void printHome() {
		Cli.print("--------------------------------------------------", true);
		for (Integer key : this.options.keySet()) {
			Cli.print(key + ": " + this.options.get(key), true);
		}
		Cli.print("--------------------------------------------------", true);
	}

	private void execute(int key) {
		switch (key) {
			case 1 -> Options.createTask(tasks);
			case 2 -> Options.deleteTask(tasks);
			case 3 -> Options.listTasks(tasks);
			case 4 -> Options.viewTaskInfo(tasks);
			default -> {}
		}
	}
}

public class main {
	public static void main(String[] args) {
		Menu menu = new Menu();
		Cli.closeScanner();
	}
}
