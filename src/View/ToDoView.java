package View;

import java.util.Scanner;

public class ToDoView {
    Scanner sc;

    public ToDoView() {
        this.sc = new Scanner(System.in);
    }

    public String addToDo() {
        System.out.println("What would you like to List: ");
        return sc.nextLine();
    }

    public String updateToDo() {
        System.out.println("Enter index of item to update:");
        int index = sc.nextInt();
        sc.nextLine(); // consume newline character

        return String.valueOf(index);
    }
}
