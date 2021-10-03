package se.iths.java21.patrik.lab2.menu.admin;

import se.iths.java21.patrik.lab2.menu.tools.Command;
import se.iths.java21.patrik.lab2.menu.tools.MenuTemplate;
import se.iths.java21.patrik.lab2.menu.trading.Category;
import se.iths.java21.patrik.lab2.menu.trading.CategoryList;
import se.iths.java21.patrik.lab2.menu.trading.Product;
import se.iths.java21.patrik.lab2.menu.trading.ProductList;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ChangeProducts implements MenuTemplate<Integer> {
    private static final Scanner scanner = new Scanner(System.in);
    private final Command[] commands = new Command[3];
    private static CategoryList categories;
    private static ProductList productList;

    public ChangeProducts(CategoryList categories, ProductList productList) {
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
            Product product;

            scanner.nextLine();

            System.out.println("\nSkriv på NAMN eller EAN kod, tryck sedan ENTER");
            Product foundProduct = searchProduct();

            System.out.println("\nFound Product: " + foundProduct);

            System.out.println("\nTa bort vald produkt? (Y/N)");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                productList.removeProduct(foundProduct);
            }
        }

        private static Product searchProduct() {
            String searchInput = scanner.nextLine();
            Product product;

            if (isNumeric(searchInput)) {
                return product = productList.getProduct(Integer.parseInt(searchInput));
            } else {
                return product = productList.getProduct(searchInput);
            }
        }

        private static boolean isNumeric(String searchInput) {
            if (searchInput == null)
                return false;
            try {
                Integer.parseInt(searchInput);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
    }

    static class AddProduct {
        public static void run() {
            String input;

            do {
                System.out.println("""
                                                
                        Lägg till Produkt
                        ↓ Skriv här ↓""");

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
                                                
                        Lägga till en till? (Y/N)
                        """);

                input = scanner.nextLine();
            } while (!input.equalsIgnoreCase("n"));

            System.out.println("\nTillgängliga varor:");
            productList.printList();

            System.out.println("\nÅtergår till föregående meny\n");
        }
    }
}
