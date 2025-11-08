import java.util.Scanner;
import java.io.*;

// A to-do list implementation that uses a singly linked list for storing tasks.
public class SinglyLinkedListBasedToDoList {
   // A nested Node class for the linked list
   private class Node {
      String task; // the task stored in this node
      Node next; // a reference to the subsequent node in the linked list

      // A constructor for creating a node with the given task
      Node(String task) {
         this.task = task;
         next = null;
      }
   }

   // the instance variables of the SinglyLinkedListBasedToDoList class
   private Node head; // the head node of the linked list
   private int count; // the number of the tasks stored in the linked list

   // A no-argument constructor for creating an empty to-do list
   public SinglyLinkedListBasedToDoList() {
      head = null;
      count = 0;
   }

   // A method for adding a task to the end of the to-do list
   public void addTask(String task) {
      // create a new node to store the given task
      Node newNode = new Node(task);
      // if the list is empty
      if (head == null)
         // the new node becomes the first node
         head = newNode;
      // otherwise
      else {
         // traverse the list until reaching its end
         Node current = head;
         while (current.next != null)
            current = current.next;
         // add the new node to the end of the list
         current.next = newNode;
      }
      count++; // increase the task count by 1
      // inform the user that the given task has been added to the to-do list
      System.out.println("Task added: " + task);
   }

   // A method for removing a task by its position
   public void removeTask(int position) {
      // check the validity of the given position
      if (position < 1 || position > count) {
         System.out.println("Invalid task number: " + position);
         return; // end the void removeTask method
      }
      String removedTask;
      // if the first node is the one to remove
      if (position == 1) {
         removedTask = head.task;
         // the second node in the linked list becomes the new head
         head = head.next;
      }
      // otherwise
      else {
         // traverse the list to reach the previous node of the node to remove
         Node previous = head;
         for (int i = 1; i < position - 1; i++)
            previous = previous.next;
         // current is the node to be removed
         Node current = previous.next;
         removedTask = current.task;
         // update the next node of previous as the next node of the removed node
         previous.next = current.next;
      }
      count--; // decrease the task count by 1
      // inform the user that the task has been removed from the to-do list
      System.out.println("Task removed: " + removedTask);
   }

   // A method for displaying all the tasks on the console
   public void displayTasks() {
      // inform the user if the to-do list is empty
      if (count == 0) {
         System.out.println("To-do list is empty!");
         return; // end the void displayTasks method
      }
      // otherwise, display all the tasks in the to-do list
      System.out.println("To-do list:");
      // by printing all the elements in the linked list
      Node current = head; // starting from the head
      int taskNumber = 1;
      while (current != null) { // until reaching the end of the list
         System.out.println(taskNumber + ". " + current.task);
         current = current.next; // traverse each element in the list
         taskNumber++;
      }
      // followed by the number of the tasks
      System.out.println("Total tasks: " + count);
   }

   // A method for saving all the tasks to a file
   public void saveTasks(String filename) {
      // inform the user if the to-do list is empty
      if (count == 0) {
         System.out.println("To-do list is empty! No tasks to save.");
         return; // end the void saveTasks method
      }
      // using a try-catch block to handle possible exceptions
      try {
         File file = new File(filename);
         PrintWriter output = new PrintWriter(file);
         // write all the tasks to the given file
         Node current = head; // starting from the head
         while (current != null) { // until reaching the end of the list
            output.println(current.task);
            current = current.next; // traverse each element in the list
         }
         output.close(); // close the file
         // inform the user that the tasks have been saved to the given file
         System.out.println("Tasks saved to " + filename);
      } catch (Exception e) {
         // inform the user if an exception is thrown
         System.out.println("Error saving tasks to file: " + e.getMessage());
      }
   }

   // A method for loading the tasks from a file
   public void loadTasks(String filename) {
      // using a try-catch block to handle possible exceptions
      try {
         File file = new File(filename);
         Scanner input = new Scanner(file);
         // read each task from the file and add it to the linked list
         while (input.hasNextLine()) {
            String task = input.nextLine();
            addTask(task);
         }
         input.close(); // close the file
         // if some tasks have been added
         if (count != 0)
            // inform the user that the tasks have been loaded from the given file
            System.out.println("Tasks loaded from " + filename);
         // otherwise
         else
            // inform the user that the file is empty
            System.out.println("No tasks in file: " + filename);
      } catch (Exception e) {
         // inform the user if an exception is thrown
         System.out.println("Error loading tasks from file: " + e.getMessage());
      }
   }

   // The main method
   public static void main(String[] args) {
      // create a singly linked list based to-do list
      SinglyLinkedListBasedToDoList toDoList = new SinglyLinkedListBasedToDoList();
      // load the tasks from the file (if there is any)
      toDoList.loadTasks("tasks.txt");

      // a user menu implemented by using a switch statement in a do-while loop
      Scanner scanner = new Scanner(System.in);
      int choice;
      // loop until the user selects to exit the program
      do {
         // list the options to the user
         System.out.println("\nTo-Do List Menu:");
         System.out.println("1. Add task");
         System.out.println("2. Display tasks");
         System.out.println("3. Remove task");
         System.out.println("4. Exit");
         System.out.print("Choose an option: ");

         // read the user-selected option
         choice = scanner.nextInt();
         scanner.nextLine(); // consume the newline

         // perform based on the user-selected option
         System.out.println(); // add a newline for readability
         switch (choice) {
            case 1: // adding a new task to the end of the list
               System.out.print("Enter the task description: ");
               String task = scanner.nextLine();
               toDoList.addTask(task);
               break;
            case 2: // displaying all the tasks
               toDoList.displayTasks();
               break;
            case 3: // removing a task by its position
               System.out.print("Enter the task number to remove: ");
               int taskNumber = scanner.nextInt();
               toDoList.removeTask(taskNumber);
               break;
            case 4: // saving the tasks to the file and exiting the program
               toDoList.saveTasks("tasks.txt");
               System.out.println("Exiting...");
               break;
            default: // an invalid number is given by the user
               System.out.println("Invalid option. Please try again.");
         }

      } while (choice != 4); // loop continuation condition

      // close the scanner input
      scanner.close();
   }
}
