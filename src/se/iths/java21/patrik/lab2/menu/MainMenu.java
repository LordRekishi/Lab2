package se.iths.java21.patrik.lab2.menu;

import se.iths.java21.patrik.lab2.menu.admin.AdminMenu;
import se.iths.java21.patrik.lab2.menu.tools.*;
import se.iths.java21.patrik.lab2.menu.admin.categories.CategorySet;
import se.iths.java21.patrik.lab2.menu.admin.products.ProductList;
import se.iths.java21.patrik.lab2.menu.trading.ShoppingCart;
import se.iths.java21.patrik.lab2.menu.trading.TradeMenu;

public class MainMenu implements MenuTemplate<Integer> {
    private final Command[] commands = new Command[4];
    private final AdminMenu admin;
    private final TradeMenu trade;
    private static ProductList productList;
    private static CategorySet categories;

    public MainMenu(ProductList productList, CategorySet categories, ShoppingCart cart) {
        this.admin = new AdminMenu(categories, productList);
        this.trade = new TradeMenu(productList, categories, cart);
        MainMenu.productList = productList;
        MainMenu.categories = categories;

        commands[1] = this::discounts;
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

    private void discounts() {
        System.out.println("""
                                
                +------------------------+
                | MATBUTIKEN Erbjudanden |
                +------------------------+
                                
                --------------------------------+
                Köp för över 500 kr och få 10% rabatt på hela ditt köp!
                --------------------------------+
                Älskar du kantareller? Vi också, det är därför vi har köp 3, betala för 2 resten av året!
                --------------------------------+
                                
                VÄLKOMMEN IN TILL VÅR MATBUTIK!
                                
                Direkt till Handla? (Y/N)""");
        if (InputHandler.getStringInput().equalsIgnoreCase("y")) {
            Runnable runnable = trade::run;
            runnable.run();
        }
    }
}
