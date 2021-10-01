package se.iths.java21.patrik.lab2.menu.trading;

import se.iths.java21.patrik.lab2.menu.tools.Command;
import se.iths.java21.patrik.lab2.menu.tools.MenuTemplate;

import java.util.Scanner;

public class TradeMenu implements MenuTemplate<Integer> {
    private final Scanner scanner = new Scanner(System.in);
    private final Command[] commands = new Command[3];
    private ProductList productList;
    private ShoppingCart cart;

    public TradeMenu(ProductList productList, ShoppingCart cart) {
        this.productList = productList;
        this.cart = cart;

        commands[1] = () -> System.out.println("Command 1");
        commands[2] = () -> System.out.println("Command 1");
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
                       +-------------------+ +-----------+ +---------+
                HANDLA | 1. Lägg till Vara | | 2. Kassan | | 0. Exit |
                       +-------------------+ +-----------+ +---------+
                                
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
