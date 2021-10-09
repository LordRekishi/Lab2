package se.iths.java21.patrik.lab2.menu.admin;

import se.iths.java21.patrik.lab2.menu.tools.*;
import se.iths.java21.patrik.lab2.menu.trading.Category;
import se.iths.java21.patrik.lab2.menu.trading.CategorySet;
import se.iths.java21.patrik.lab2.menu.trading.Product;
import se.iths.java21.patrik.lab2.menu.trading.ProductList;

public class ChangeProducts implements MenuTemplate<Integer> {
    private final Command[] commands = new Command[4];
    private static CategorySet categories;
    private static ProductList productList;

    public ChangeProducts(CategorySet categories, ProductList productList) {
        ChangeProducts.categories = categories;
        ChangeProducts.productList = productList;

        commands[1] = SearchProduct::run;
        commands[2] = AddProduct::run;
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
    }

    static class SearchProduct {
        public static void run() {

            System.out.println("\nSök på NAMN eller EAN kod");
            Product foundProduct = productList.searchProduct();

            Console.markedProductPrint(foundProduct);

            System.out.println("\nTa bort vald produkt? (Y/N)");
            if (InputHandler.getStringInput().equalsIgnoreCase("y")) {
                productList.removeProduct(foundProduct);
            } else {
                System.out.println("\nÄndra på produkten? (Y/N)");
                if (InputHandler.getStringInput().equalsIgnoreCase("y")) {
                    System.out.println("""
                                                        
                            VAD VILL DU ÄNDRA?
                            ----------------------+
                            1. PRIS
                            2. SALDO
                            0. Exit
                                                        
                            Gör ditt menyval genom att skriva SIFFRAN och sedan trycka ENTER!
                            ↓ Skriv här ↓""");

                    int choice = InputHandler.getIntegerInput();
                    changeMenuChoiceExecution(foundProduct, choice);
                }
            }
        }

        private static void changeMenuChoiceExecution(Product foundProduct, int choice) {
            switch (choice) {
                case 1 -> {
                    System.out.println(
                            "\nDet nuvarande priset är: " + foundProduct.getPrice() + "kr." +
                            "\nSkriv det nya priset: " +
                            "\n↓ Skriv här ↓");
                    foundProduct.setPrice(InputHandler.getFloatInput());

                    Console.updatedProductPrint(foundProduct);
                    System.out.println("\nÅtergår till föregående meny...");
                }
                case 2 -> {
                    System.out.println(
                            "\nDet nuvarande lagersaldot är: " + foundProduct.getQuantity() + " st." +
                            "\nHur många skall läggas till: " +
                            "\n↓ Skriv här ↓");
                    foundProduct.setQuantity(InputHandler.getIntegerInput());

                    Console.updatedProductPrint(foundProduct);
                    System.out.println("\nÅtergår till föregående meny...");
                }
            }
        }

    }

    static class AddProduct {
        public static void run() {
            String choice;

            do {
                System.out.println("""
                                                
                        Lägg till Produkt
                        ----------------------+""");

                // Set name
                System.out.println("\nVarans NAMN:");
                String name = InputHandler.getStringInput();

                // Set price
                System.out.println("\nPRIS");
                float price = InputHandler.getFloatInput();

                // Set Category
                System.out.println("\nTillgängliga Kategorier: ");
                categories.printCategories();
                System.out.println("\nKATEGORI:");
                Category category = categories.getCategory(InputHandler.getStringInput());

                // Set EAN code automatically
                int maxEan = productList.getList().stream()
                        .mapToInt(Product::getEan)
                        .max()
                        .orElse(0);
                int ean = maxEan + 1;
                System.out.println("\nVarans EAN kod: " + ean);

                // Set Quantity in stock
                System.out.println("\nAntal i lager:");
                int stock = InputHandler.getIntegerInput();


                // Skapa produkten och lägg till i listan
                productList.addProductToList(new Product(name, price, category, ean, stock));

                System.out.println("""
                                                
                        Varan är tillagd...
                                                
                        Lägga till en till vara? (Y/N)""");
                choice = InputHandler.getStringInput();

            } while (!choice.equalsIgnoreCase("n"));

            System.out.println("\nTillgängliga varor:");
            productList.printList();

            System.out.println("\nÅtergår till föregående meny...");
        }
    }

}
