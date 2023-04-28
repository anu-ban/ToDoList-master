package Controller;

import Model.ToDoModel;
import View.ToDoView;

public class ToDoController {
    private ToDoModel model;

    public ToDoController() {
        this.model = new ToDoModel("todo.txt");
    }

    public void addToDoItem(String newItem) {
        model.addToDoItem(newItem);
    }

    public String updateToDoItem(int selectedIndex) {
        return model.updateToDoItem(selectedIndex);
    }

    public void deleteToDoItem(int selectedIndex) {
        model.deleteToDoItem(selectedIndex);
    }

    public String[] getAllToDoList() {
        return model.getAllToDoList();
    }
}
