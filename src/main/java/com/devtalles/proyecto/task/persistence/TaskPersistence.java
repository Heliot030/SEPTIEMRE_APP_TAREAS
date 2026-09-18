package com.devtalles.proyecto.task.persistence;

import com.devtalles.proyecto.task.model.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;

public class TaskPersistence {
    private final static String FILE_PATH = "tasks.json";
    //trabaja con json formatedo
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveTasks(List<Task> tasks){
        try (Writer writer = new FileWriter(FILE_PATH)) {
           gson.toJson(tasks, writer);
        } catch (java.io.IOException e) {
            System.err.println("Error al guardar las tareas: " + e.getMessage());
        }
    }

    //carga la aplicacion
    public static List<Task> loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new java.util.ArrayList<>();
        }
        try (java.io.Reader reader = new java.io.FileReader(FILE_PATH)) {
            Type ListType = new TypeToken<List<Task>>() {}.getType();
            return gson.fromJson(reader, ListType);
        } catch (java.io.IOException e) {
            System.err.println("Error al cargar las tareas: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }
}
