package se.iths.java21.patrik.lab2.menu;

import se.iths.java21.patrik.lab2.menu.admin.AdminMenu;
import se.iths.java21.patrik.lab2.menu.tools.Command;
import se.iths.java21.patrik.lab2.menu.tools.InputHandler;
import se.iths.java21.patrik.lab2.menu.tools.MenuTemplate;
import se.iths.java21.patrik.lab2.menu.trading.TradeMenu;

public class MainMenu implements MenuTemplate<Integer> {
    private final Command[] commands = new Command[6];

    public MainMenu(TradeMenu trade, AdminMenu admin) {
        commands[1] = () -> System.out.println("Erbjudanden");
        commands[2] = trade::run;
        commands[3] = () -> System.out.println("Hitta Butik");
        commands[4] = () -> System.out.println("Kundservice");
        commands[5] = admin::run;
        commands[0] = this::shutDown;
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
                           
                           +----------------+ +-----------+ +----------------+ +----------------+ +----------+ +---------+
                MATBUTIKEN | 1. Erbjudanden | | 2. Handla | | 3. Hitta Butik | | 4. Kundservice | | 5. Admin | | 0. Exit |
                           +----------------+ +-----------+ +----------------+ +----------------+ +----------+ +---------+
                                
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
        System.out.println("\nExiting program...");
        System.exit(0);
    }
}
