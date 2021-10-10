package se.iths.java21.patrik.lab2.menu.admin.products;

import se.iths.java21.patrik.lab2.menu.admin.categories.CategorySet;
import se.iths.java21.patrik.lab2.menu.tools.*;

public class ChangeProducts implements MenuTemplate<Integer> {
    private final Command[] commands = new Command[4];
    private static ProductList productList;

    public ChangeProducts(CategorySet categories, ProductList productList) {
        ChangeProducts.productList = productList;

        commands[1] = () -> SearchProduct.run(productList);
        commands[2] = () -> AddProduct.run(productList, categories);
        commands[3] = this::printList;
        commands[0] = this::shutDown;
    }

    private void printList() {
        System.out.println("""
                                
                PRODUKTER:
                ----------------------+""");
        productList.printList();
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
                          
                          +------------------+ +--------------+ +-----------------+  +---------+
                PRODUKTER | 1. Sök / Ändra   | | 2. Lägg till | | 3. Se Produkter |  | 0. Exit |
                          +------------------+ +--------------+ +-----------------+  +---------+
                                
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
