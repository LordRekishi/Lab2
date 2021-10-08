package se.iths.java21.patrik.lab2.menu;

import se.iths.java21.patrik.lab2.menu.admin.AdminMenu;
import se.iths.java21.patrik.lab2.menu.tools.*;
import se.iths.java21.patrik.lab2.menu.trading.CategoryList;
import se.iths.java21.patrik.lab2.menu.trading.ProductList;
import se.iths.java21.patrik.lab2.menu.trading.ShoppingCart;
import se.iths.java21.patrik.lab2.menu.trading.TradeMenu;

public class MainMenu implements MenuTemplate<Integer> {
    private final Command[] commands = new Command[4];
    ProductList productList;
    CategoryList categories;

    public MainMenu(ProductList productList, CategoryList categories, ShoppingCart cart) {
        AdminMenu admin = new AdminMenu(categories, productList);
        TradeMenu trade = new TradeMenu(productList, categories, cart);
        this.productList = productList;
        this.categories = categories;

        commands[1] = () -> System.out.println("Erbjudanden");
        commands[2] = trade::run;
        commands[3] = admin::run;
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
                           
                           +----------------+ +-----------+ +----------+ +---------+
                MATBUTIKEN | 1. Erbjudanden | | 2. Handla | | 3. Admin | | 0. Exit |
                           +----------------+ +-----------+ +----------+ +---------+
                                
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
        System.out.println("Spara ändringar? (Y/N)");
        if (InputHandler.getStringInput().equalsIgnoreCase("y")) {
            CsvWriter.saveCategorySet(categories);
            CsvWriter.saveProductList(productList);
        }
        System.out.println("\nAvslutar programmet...");
        System.exit(0);
    }
}
