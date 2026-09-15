package com.devtalles.proyecto.task.controller;

import com.devtalles.proyecto.task.exepciones.TaskExeption;
import com.devtalles.proyecto.task.exepciones.TaskValidationException;
import com.devtalles.proyecto.task.model.Task;
import com.devtalles.proyecto.task.model.TaskRepository;

import java.util.List;

public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String id, String title, String completed, String description) throws TaskValidationException, TaskExeption {
        validaTaskData(id, title, completed, description);
        Task newTask = new Task(id, title, completed, description);
        this.taskRepository.save(newTask);
        System.out.println("La tarea se ha agregado correctamente: " + newTask);
    }

    public void removeTask(String id) throws TaskValidationException, TaskExeption {
        if(id == null || id.isEmpty()) {
            throw new TaskValidationException("El ID de la tarea no puede ser nulo o vacío");
        }
            this.taskRepository.remove(id);
    }

    public  void showsTasks() throws TaskValidationException, TaskExeption {
        List<Task> tasks = this.taskRepository.findAll();
        if(tasks.isEmpty()) {
            throw new TaskValidationException("La lista de tareas está vacía");
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void updateTask(String id, String title, String completed, String description) throws TaskValidationException, TaskExeption {
        validaTaskData(id, title, completed, description);
        Task taskToUpdate = new Task(id, title, completed, description);
        this.taskRepository.updateTask(taskToUpdate);
    }

    private void validaTaskData(String id, String title, String completed, String description) throws TaskValidationException {
        if(id == null || id.isEmpty()) {
            throw new TaskValidationException("El ID de la tarea no puede ser nulo o vacío");
        }
        if(title == null || title.isEmpty()) {
            throw new TaskValidationException("El título de la tarea no puede ser nulo o vacío");
        }
        if(completed == null || completed.isEmpty()) {
            throw new TaskValidationException("El estado de la tarea no puede ser nulo o vacío");
        }
        if(description == null || description.isEmpty()) {
            throw new TaskValidationException("La descripción de la tarea no puede ser nula o vacía");
        }
    }
}
