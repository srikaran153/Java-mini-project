import java.util.*;

class Task implements Comparable<Task> {
    String name;
    String priority;
    String deadline;
    String status;
public Task(String name, String priority, String deadline) {
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
        this.status = "Pending";
    }

    public int compareTo(Task t) {
        return getPriorityValue(t.priority) - getPriorityValue(this.priority);
    }

    private int getPriorityValue(String p) {
        if (p.equalsIgnoreCase("High")) return 3;
        if (p.equalsIgnoreCase("Medium")) return 2;
        return 1;
    }

    public String toString() {
        return "Task: " + name + " | Priority: " + priority +
               " | Deadline: " + deadline + " | Status: " + status;
    }
}

public class SmartToDoManager {
    static PriorityQueue<Task> taskList = new PriorityQueue<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Smart To-Do Task Manager ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Mark Task Completed");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    markCompleted();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    static void addTask() {
        System.out.print("Enter task name: ");
        String name = sc.nextLine();
        System.out.print("Enter priority (High/Medium/Low): ");
        String priority = sc.nextLine();
        System.out.print("Enter deadline: ");
        String deadline = sc.nextLine();

        taskList.add(new Task(name, priority, deadline));
        System.out.println("Task added successfully!");
    }

    static void viewTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("\nTasks (by priority):");
        PriorityQueue<Task> temp = new PriorityQueue<>(taskList);

        while (!temp.isEmpty()) {
            System.out.println(temp.poll());
        }
    }

    static void deleteTask() {
        System.out.print("Enter task name to delete: ");
        String name = sc.nextLine();

        Iterator<Task> it = taskList.iterator();
        boolean found = false;

        while (it.hasNext()) {
            Task t = it.next();
            if (t.name.equalsIgnoreCase(name)) {
                it.remove();
                found = true;
                System.out.println("Task deleted!");
                break;
            }
        }

        if (!found) {
            System.out.println("Task not found!");
        }
    }

    static void markCompleted() {
        System.out.print("Enter task name to mark completed: ");
        String name = sc.nextLine();

        for (Task t : taskList) {
            if (t.name.equalsIgnoreCase(name)) {
                t.status = "Completed";
                System.out.println("Task marked as completed!");
                return;
            }
        }
        System.out.println("Task not found!");
    }
}
