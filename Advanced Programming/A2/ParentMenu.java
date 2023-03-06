package Assignment2;

import java.util.ArrayList;

public class ParentMenu {
    private final ArrayList<String> menu;

    public ParentMenu() {
        menu = new ArrayList<>();
    }

    public void add(String s) {
        menu.add(s);
    }

    public void display() {
        displayParentMenu();
    }

    public void displayParentMenu() {
        System.out.println("Welcome to Zotato:");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println("    " + (i+1) + ") " + menu.get(i));
        }
    }
}
