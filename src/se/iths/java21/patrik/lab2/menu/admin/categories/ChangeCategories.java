package se.iths.java21.patrik.lab2.menu.admin.categories;

import se.iths.java21.patrik.lab2.menu.tools.*;

public class ChangeCategories implements MenuTemplate<Integer> {
    private final Command[] commands = new Command[4];
    private static CategorySet categories;

    public ChangeCategories(CategorySet categories) {
        ChangeCategories.categories = categories;

        commands[1] = () -> SearchCategory.run(categories);
        commands[2] = () -> AddCategory.run(categories);
        commands[3] = this::printList;
        commands[0] = this::shutDown;
    }

    private void printList() {
        System.out.println("""

                KATEGORIER:
                ----------------------+""");
        categories.printCategories();
    }

    @Override
    public void run() {
        int choice;

        do {
            printMenuOptions();
            choice = readChoice();
            executeChoice(choice);
        } while (choice != 0);
    }

    @Override
    public void printMenuOptions() {
        System.out.println("""
                           
                           +------------------+ +--------------+ +------------------+ +---------+
                KATEGORIER | 1. Sök / Ändra   | | 2. Lägg till | | 3. Se Kategorier | | 0. Exit |
                           +------------------+ +--------------+ +------------------+ +---------+
                                
                Gör ditt menyval genom att skriva SIFFRAN och sedan trycka ENTER!
                ↓ Skriv här ↓""");
    }

    @Override
    public Integer readChoice() {
        return InputHandler.getIntegerInput();
    }

    @Override
    public void executeChoice(Integer choice) {
        try {
            commands[choice].execute();
        } catch (ArrayIndexOutOfBoundsException ignore) {
            System.out.println("Invalid choice, try again\n");
        }
    }

    @Override
    public void shutDown() {
        System.out.println("\nÅtergår till föregående meny...");
    }
}
