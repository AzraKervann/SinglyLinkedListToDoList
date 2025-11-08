# SinglyLinkedListToDoList
Java To-Do List application demonstrating Object-Oriented Programming using a Singly Linked List

This Java program implements a simple To-Do List using a *singly linked list*.
It demonstrates *Object-Oriented Programming* concepts including *classes, encapsulation, and method structuring*.

## Features

•⁠  ⁠Add a task to the to-do list
•⁠  ⁠Remove a task by its position
•⁠  ⁠Display all tasks with their order
•⁠  ⁠Tracks the total number of tasks

## Example Usage

```java
SinglyLinkedListBasedToDoListTemplate todoList = new SinglyLinkedListBasedToDoListTemplate();

todoList.addTask("Finish homework");
todoList.addTask("Pay bills");
todoList.displayTasks();

todoList.removeTask(1);
todoList.displayTasks();

