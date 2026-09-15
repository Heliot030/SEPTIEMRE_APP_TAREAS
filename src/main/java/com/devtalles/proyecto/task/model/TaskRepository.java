package com.devtalles.proyecto.task.model;

import com.devtalles.proyecto.task.exepciones.TaskExeption;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    List<Task> tasks = new ArrayList<>();

    public void save(Task task) {
       if(task == null) {
           throw new TaskExeption("Tarea no puede ser nula");
       }
       tasks.add(task);
    }

    public Task findTaskById(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public void remove(String id) {
       // tasks.removeIf(task -> task.getId().equals(id));
        Task taskToRemove = findTaskById(id);
        if(taskToRemove == null) {
            throw new TaskExeption("Tarea no puede ser nula");
        }
        tasks.remove(taskToRemove);
    }

    public void remove(Task task) {
        // tasks.removeIf(task -> task.getId().equals(id));
        if(task == null) {
            throw new TaskExeption("Tarea no puede ser nula");
        }
        if(!tasks.contains(task)) {
            throw new TaskExeption("Tarea no encontrada");
        }
        tasks.remove(task);
    }

    public List<Task> findAll() {
        if(tasks.isEmpty()) {
            throw new TaskExeption("La lista de tareas está vacía");
        }
        return tasks;
    }

    public int findIndexById(String id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void updateTask(Task updatedTask) {
        if(updatedTask == null) {
            throw new TaskExeption("Tarea actualizada no puede ser nula");
        }
        int index = findIndexById(updatedTask.getId());
        if (index != -1) {
            tasks.set(index, updatedTask);
        } else {
            throw new TaskExeption("El indice " + index + " no encontrada");
        }
    }
 }