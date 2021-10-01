package se.iths.java21.patrik.lab2.menu.admin;

import se.iths.java21.patrik.lab2.menu.tools.Command;
import se.iths.java21.patrik.lab2.menu.tools.MenuTemplate;
import se.iths.java21.patrik.lab2.menu.trading.ProductList;

import java.util.Scanner;

public class ChangeProducts implements MenuTemplate<Integer> {
    private final Scanner scanner = new Scanner(System.in);
    private final Command[] commands = new Command[3];
    private ProductList productList;

    public ChangeProducts(ProductList productList) {
        commands[1] = () -> System.out.println("Command 1");
        commands[2] = () -> System.out.println("Command 2");
        commands[0] = this::shutDown;

        this.productList = productList;
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
                          +----------------+ +--------------+ +---------+
                PRODUKTER | 1. Sök / Ändra | | 2. Lägg till | | 0. Exit |
                          +----------------+ +--------------+ +---------+
                                
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


//        public static void run() {
//            System.out.println("""
//                    +-----------------+
//                    | Ändra Produkter |
//                    +-----------------+
//
//                    Sök produkt produkt här:
//                    ↓ Skriv här ↓""");
//
//            System.out.println("Add new product");
//
//            System.out.println("Name");
//            String name = scanner.nextLine();
//            System.out.println("Price");
//            float price = Float.parseFloat(scanner.nextLine());
//            System.out.println("Category");                         // Behöver lösa!
//            Category category = new Category(scanner.nextLine());
//            System.out.println("Ean Code");
//            int ean = Integer.parseInt(scanner.nextLine());
//            System.out.println("Stock available");
//            int stock = Integer.parseInt(scanner.nextLine());
//
//            productList.addProductToList(new Product(name, price, category, ean, stock));
//        }
}
