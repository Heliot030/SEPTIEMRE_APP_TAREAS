package com.devtalles.proyecto.task.view;

import com.devtalles.proyecto.task.controller.TaskController;
import com.devtalles.proyecto.task.exepciones.TaskExeption;
import com.devtalles.proyecto.task.exepciones.TaskValidationException;

import java.util.Scanner;

public class TaskView {
    private final TaskController taskController;
    private final Scanner scanner;

    public TaskView(TaskController taskController, Scanner scanner) {
        this.taskController = taskController;
        this.scanner = new Scanner(System.in);
    }

     public void showMenu() {
         while (true) {
             System.out.println("Bienvenido al gestor de tareas");
             System.out.println("1. Agregar tarea");
             System.out.println("2. Eliminar tarea");
             System.out.println("3. Mostrar tareas");
             System.out.println("4. Actualizar tarea");
             System.out.println("5. Salir");
             System.out.print("Seleccione una opción: ");

             String option = scanner.nextLine();
             switch (option){
                    case "1":
                        try {
                            System.out.print("Ingrese el ID de la tarea: ");
                            String id = scanner.nextLine();
                            System.out.print("Ingrese el título de la tarea: ");
                            String title = scanner.nextLine();
                            System.out.print("Ingrese el estado de la tarea (true/false): ");
                            String completed = scanner.nextLine();
                            System.out.print("Ingrese la descripción de la tarea: ");
                            String description = scanner.nextLine();
                            taskController.addTask(id, title, description , completed);
                        } catch (TaskExeption | TaskValidationException e) {
                            System.out.println("Error al agregar la tarea: " + e.getMessage());
                        }
                        break;
                    case "2":
                        try {
                            System.out.print("Ingrese el ID de la tarea a eliminar: ");
                            String idToRemove = scanner.nextLine();
                            taskController.removeTask(idToRemove);
                            System.out.println("Tarea eliminada correctamente");
                        } catch (TaskExeption | TaskValidationException e) {
                            System.out.println("Error al eliminar la tarea: " + e.getMessage());
                        }
                        break;
                    case "3":
                        try {
                            taskController.showsTasks();
                        } catch (TaskExeption | TaskValidationException e) {
                            System.out.println("Error al mostrar las tareas: " + e.getMessage());
                        }
                        break;
                    case "4":
                        try {
                            System.out.print("Ingrese el ID de la tarea a actualizar: ");
                            String idToUpdate = scanner.nextLine();
                            System.out.print("Ingrese el nuevo título de la tarea: ");
                            String newTitle = scanner.nextLine();
                            System.out.print("Ingrese el nuevo estado de la tarea (true/false): ");
                            String newCompleted = scanner.nextLine();
                            System.out.print("Ingrese la nueva descripción de la tarea: ");
                            String newDescription = scanner.nextLine();
                            taskController.updateTask(idToUpdate, newTitle, newCompleted, newDescription);
                            System.out.println("Tarea actualizada correctamente");
                        } catch (TaskExeption | TaskValidationException e) {
                            System.out.println("Error al actualizar la tarea: " + e.getMessage());
                        }
                        break;
                    case "5":
                        System.out.println("Saliendo del gestor de tareas...");
                        return;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
             }
         }
     }
}
