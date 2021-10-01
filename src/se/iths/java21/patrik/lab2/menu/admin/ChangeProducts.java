package se.iths.java21.patrik.lab2.menu.admin;

import se.iths.java21.patrik.lab2.menu.tools.Command;
import se.iths.java21.patrik.lab2.menu.tools.MenuTemplate;
import se.iths.java21.patrik.lab2.menu.trading.Category;
import se.iths.java21.patrik.lab2.menu.trading.Product;
import se.iths.java21.patrik.lab2.menu.trading.ProductList;

import java.util.List;
import java.util.Scanner;

public class ChangeProducts implements MenuTemplate<Integer> {
    private static final Scanner scanner = new Scanner(System.in);
    private final Command[] commands = new Command[3];
    private static List<Category> categories;
    private static ProductList productList;

    public ChangeProducts(List<Category> categories, ProductList productList) {
        ChangeProducts.categories = categories;
        ChangeProducts.productList = productList;

        commands[1] = SearchProduct::run;
        commands[2] = AddProduct::run;
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
                          +------------------+ +--------------+ +---------+
                PRODUKTER | 1. Sök / Ta Bort | | 2. Lägg till | | 0. Exit |
                          +------------------+ +--------------+ +---------+
                                
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

    static class SearchProduct {
        public static void run() {
            int input;

            while (true) {
                System.out.println("""
                        +------------------+ +---------+
                        | 1. Sök / Ta Bort | | 0. Exit |
                        +------------------+ +---------+

                        Gör ditt menyval genom att skriva SIFFRAN och sedan trycka ENTER!
                        ↓ Skriv här ↓""");

                input = scanner.nextInt();

                if (input == 0)
                    break;

                System.out.println("\nSkriv på NAMN eller EAN kod, tryck sedan ENTER");
                var searchInput = scanner.nextLine();

            }


        }
    }

    static class AddProduct {
        public static void run() {
            int input;

            while (true) {
                System.out.println("""
                    +----------------------+ +---------+
                    | 1. Lägg till Produkt | | 0. Exit |
                    +----------------------+ +---------+

                    Gör ditt menyval genom att skriva SIFFRAN och sedan trycka ENTER!
                    ↓ Skriv här ↓""");

                input = scanner.nextInt();

                if (input == 0)
                    break;

                System.out.println("\nTillgängliga varor:");
                productList.printList();

                System.out.println("\nVarans NAMN:");
                scanner.nextLine();
                String name = scanner.nextLine();
                System.out.println("PRIS");
                float price = Float.parseFloat(scanner.nextLine());
                System.out.println("KATEGORI:");                         // Behöver lösa!
                Category category = new Category(scanner.nextLine());
                System.out.println("EAN kod:");
                int ean = Integer.parseInt(scanner.nextLine());
                System.out.println("Antal i lager:");
                int stock = Integer.parseInt(scanner.nextLine());

                productList.addProductToList(new Product(name, price, category, ean, stock));
                System.out.println("""
                        
                        Varan är tillagd...
                        """);
            }
        }
    }
}
