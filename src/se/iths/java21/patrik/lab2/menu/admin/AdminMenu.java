package se.iths.java21.patrik.lab2.menu.admin;

import se.iths.java21.patrik.lab2.menu.admin.categories.CategorySet;
import se.iths.java21.patrik.lab2.menu.admin.categories.ChangeCategories;
import se.iths.java21.patrik.lab2.menu.admin.products.ChangeProducts;
import se.iths.java21.patrik.lab2.menu.admin.products.ProductList;
import se.iths.java21.patrik.lab2.menu.tools.*;

public class AdminMenu implements MenuTemplate<Integer> {
    private final Command[] commands = new Command[4];
    private static ProductList productList;
    private static CategorySet categories;

    public AdminMenu(CategorySet categories, ProductList productList) {
        ChangeProducts changeProducts = new ChangeProducts(categories, productList);
        ChangeCategories addCategories = new ChangeCategories(categories);
        AdminMenu.productList = productList;
        AdminMenu.categories = categories;

        commands[1] = changeProducts::run;
        commands[2] = addCategories::run;
        commands[3] = this::save;
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
                      
                      +--------------------+ +---------------------+ +----------+ +---------+
                ADMIN | 1. Ändra Produkter | | 2. Ändra Kategorier | | 3. Spara | | 0. Exit |
                      +--------------------+ +---------------------+ +----------+ +---------+
                                
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

    private void save() {
        System.out.println("Spara ändringar? (Y/N)");
        if (InputHandler.getStringInput().equalsIgnoreCase("y")) {
            CsvWriter.saveCategorySet(categories);
            CsvWriter.saveProductList(productList);
        }
        System.out.println("\nÄndringarna sparade...");
    }

    @Override
    public void shutDown() {
        System.out.println("\nÅtergår till föregående meny...");
    }
}
