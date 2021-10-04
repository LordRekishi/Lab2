package se.iths.java21.patrik.lab2.menu.admin;

import se.iths.java21.patrik.lab2.menu.tools.Command;
import se.iths.java21.patrik.lab2.menu.tools.MenuTemplate;
import se.iths.java21.patrik.lab2.menu.tools.CategoryList;
import se.iths.java21.patrik.lab2.menu.tools.ProductList;

import java.util.Scanner;

public class AdminMenu implements MenuTemplate<Integer> {
    private static final Scanner scanner = new Scanner(System.in);
    private final Command[] commands = new Command[3];
    private CategoryList categories;
    private ProductList productList;

    public AdminMenu(CategoryList categories, ProductList productList) {
        this.categories = categories;
        this.productList = productList;

        ChangeProducts changeProducts = new ChangeProducts(categories, productList);
        ChangeCategories addCategories = new ChangeCategories(categories);

        commands[1] = changeProducts::run;
        commands[2] = addCategories::run;
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
                      +--------------------+ +---------------------+ +---------+
                ADMIN | 1. Ändra Produkter | | 2. Ändra Kategorier | | 0. Exit |
                      +--------------------+ +---------------------+ +---------+
                                
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
