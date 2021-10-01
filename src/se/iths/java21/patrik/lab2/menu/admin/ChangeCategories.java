package se.iths.java21.patrik.lab2.menu.admin;

import se.iths.java21.patrik.lab2.menu.tools.Command;
import se.iths.java21.patrik.lab2.menu.tools.MenuTemplate;
import se.iths.java21.patrik.lab2.menu.trading.Category;

import java.util.List;
import java.util.Scanner;

public class ChangeCategories implements MenuTemplate<Integer> {
    private static final Scanner scanner = new Scanner(System.in);
    private final Command[] commands = new Command[3];
    private List<Category> categories;

    public ChangeCategories(List<Category> categories) {
        this.categories = categories;

        commands[1] = () -> System.out.println("Command 1");
        commands[2] = () -> System.out.println("Command 2");
        commands[0] = this::shutDown;
    }

    @Override
    public void run() {
        int choice = 0;

        do {
            printMenuOptions();
            choice = readChoice(scanner);
            executeChoice(choice);
        } while (choice != 0);
    }

    @Override
    public void printMenuOptions() {
        System.out.println("""
                           +----------------+ +--------------+ +---------+
                KATEGORIER | 1. Sök / Ändra | | 2. Lägg till | | 0. Exit |
                           +----------------+ +--------------+ +---------+
                                
                Gör ditt menyval genom att skriva SIFFRAN och sedan trycka ENTER!
                ↓ Skriv här ↓""");
    }

    @Override
    public Integer readChoice(Scanner scanner) {
        return scanner.nextInt();
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
    }
}
