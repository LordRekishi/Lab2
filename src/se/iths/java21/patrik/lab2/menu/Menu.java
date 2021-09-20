package se.iths.java21.patrik.lab2.menu;

import java.util.Scanner;

public class Menu implements MenuTemplate<Integer> {
    private final Scanner scanner = new Scanner(System.in);
    private final Command[] commands = new Command[5];

    public Menu() {
        commands[1] = () -> System.out.println("Command 1");
        commands[2] = () -> System.out.println("Command 2");
        commands[3] = () -> System.out.println("Command 3");
        commands[4] = () -> System.out.println("Command 4");
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
                           +----------------+ +-----------+ +----------------+ +----------------+ +---------+
                MATBUTIKEN | 1. Erbjudanden | | 2. Handla | | 3. Hitta Butik | | 4. Kundservice | | 0. Exit |
                           +----------------+ +-----------+ +----------------+ +----------------+ +---------+
                                
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
        System.exit(0);
    }
}
