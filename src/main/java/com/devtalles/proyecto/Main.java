package com.devtalles.proyecto;

import com.devtalles.proyecto.task.controller.TaskController;
import com.devtalles.proyecto.task.model.TaskRepository;
import com.devtalles.proyecto.task.view.TaskView;

public class Main {
    public static void main(String[] args) {

        TaskRepository taskRepository = new TaskRepository();
        TaskController taskController = new TaskController(taskRepository);
        TaskView taskView = new TaskView(taskController, new java.util.Scanner(System.in));
        taskView.showMenu();
    }
}