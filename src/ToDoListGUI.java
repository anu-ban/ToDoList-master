import java.nio.channels.NonWritableChannelException;
import java.time.LocalDate;

import Controller.ToDoController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.stage.Stage;

public class ToDoListGUI extends Application {
    private ToDoController controller;
    private ListView<String> toDoListView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create new instance of ToDoController
        controller = new ToDoController();

        // Create text field for adding new items to to-do list
        TextField addItemField = new TextField();
        addItemField.setPromptText("Add new item");

        // Create button for adding new items to to-do list
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String newItem = addItemField.getText();
            if (!newItem.isEmpty()) {
                controller.addToDoItem(newItem);
                addItemField.clear();
                updateToDoList();
            }
        });

        // Create button for updating selected item in to-do list
        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            int selectedIndex = toDoListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                String updatedItem = controller.updateToDoItem(selectedIndex);
                updateToDoList();
            }
        });

        // Create button for deleting selected item from to-do list
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            int selectedIndex = toDoListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                controller.deleteToDoItem(selectedIndex);
                updateToDoList();
            }
        });
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            Platform.exit();
        });

        // HBox dateBox = new HBox(10);
        // dateBox.setPadding(new Insets(10));
        // dateBox.getChildren().add(dateBox);
        // dateBox.setAlignment(Pos.TOP_RIGHT);

        // Create list view for displaying to-do list
        toDoListView = new ListView<>();
        updateToDoList();
        // create Label current date
        Label dateLabel = new Label();
        dateLabel.setText(LocalDate.now().toString());

        // Create horizontal box for buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(addItemField, addButton, updateButton, deleteButton, closeButton);

        // Create border pane for layout
        BorderPane root = new BorderPane();
        root.setRight(dateLabel);
        root.setCenter(toDoListView);
        root.setBottom(buttonBox);

        // Create scene and set it as the primary stage
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("To-Do List App");
        primaryStage.show();
    }

    private void updateToDoList() {
        // Get current to-do list from controller
        String[] toDoList = controller.getAllToDoList();

        // Clear list view and add items from to-do list
        toDoListView.getItems().clear();
        for (String item : toDoList) {
            toDoListView.getItems().add(item);
        }
    }
}