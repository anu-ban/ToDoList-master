package Model;

import java.io.*;
import java.util.ArrayList;

public class ToDoModel {
    private ArrayList<String> toDoList;
    private String fileName;

    public ToDoModel(String fileName) {
        this.toDoList = new ArrayList<>();
        this.fileName = fileName;
        loadToDoList();
    }

    public void addToDoItem(String newItem) {
        toDoList.add(newItem);
        saveToDoList();
    }

    public String updateToDoItem(int selectedIndex) {
        String updatedItem = toDoList.get(selectedIndex) + " (Completed)";
        toDoList.set(selectedIndex, updatedItem);
        saveToDoList();
        return updatedItem;
    }

    public void deleteToDoItem(int selectedIndex) {
        toDoList.remove(selectedIndex);
        saveToDoList();
    }

    public String[] getAllToDoList() {
        return toDoList.toArray(new String[0]);
    }

    private void loadToDoList() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                toDoList.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error loading to-do list from file: " + e.getMessage());
        }
    }

    private void saveToDoList() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String item : toDoList) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving to-do list to file: " + e.getMessage());
        }
    }
}