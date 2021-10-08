package se.iths.java21.patrik.lab2.menu.trading;

import se.iths.java21.patrik.lab2.menu.tools.*;

public class TradeMenu implements MenuTemplate<Integer> {
    private final Command[] commands = new Command[4];
    private static ProductList productList;
    private static CategoryList categories;
    private static ShoppingCart cart;

    public TradeMenu(ProductList productList, CategoryList categories, ShoppingCart cart) {
        TradeMenu.productList = productList;
        TradeMenu.categories = categories;
        TradeMenu.cart = cart;

        commands[1] = AddProductToCart::run;
        commands[2] = Cart::run;
        commands[3] = CheckOut::run;
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
                       
                       +-------------------+ +-------------+ +-----------+ +---------+
                HANDLA | 1. Lägg till Vara | | 2. Varukorg | | 3. Kassan | | 0. Exit |
                       +-------------------+ +-------------+ +-----------+ +---------+
                                
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

    private static class AddProductToCart {

        public static void run() {
            System.out.println("""
                                        
                    +-------------------+
                    | LÄGG TILL EN VARA |
                    +-------------------+
                                        
                    1. Sök Produkt
                    2. Se prisintervall
                    3. Se alla produkter
                    0. Exit
                                    
                    Gör ditt menyval genom att skriva SIFFRAN och sedan trycka ENTER!
                    ↓ Skriv här ↓""");

            int choice = InputHandler.getIntegerInput();

            switch (choice) {
                case 1 -> {
                    System.out.println("\nSök på NAMN eller EAN kod");
                    Product foundProduct = findProductStock();

                    System.out.println("\nLägga till i varukorgen? (Y/N)");

                    if (InputHandler.getStringInput().equalsIgnoreCase("y")) {
                        chooseQuantityThenAddToCart(foundProduct);
                    }
                }
                case 2 -> {
                    System.out.println("""
                                                        
                            VÄLJ PRISINTERVALL
                            ----------------------+
                                                        
                            Lägsta pris?""");
                    float min = InputHandler.getFloatInput();

                    System.out.println("\nHögsta pris?");
                    float max = InputHandler.getFloatInput();

                    System.out.println("\nVAROR INOM PRISINTERVALL:");
                    productList.getProductsByPrice(min, max).forEach(product -> System.out.println(
                            product.getName() +
                                    ": EAN kod: " + product.getEan() +
                                    ", Pris: " + product.getPrice() + " kr" +
                                    ", Kategori: " + product.getCategory().getName() +
                                    ", Antal: " + product.getQuantity() + " st"));

                    System.out.println("\nVill du lägga till en vara i varukorgen? (Y/N)");
                    if (InputHandler.getStringInput().equalsIgnoreCase("y")) {
                        System.out.println("""
                                                            
                                Skriv NAMN eller EAN kod på den vara du vill LÄGGA TILL i varukorgen, tryck sedan ENTER""");
                        Product foundProduct = findProductStock();
                        chooseQuantityThenAddToCart(foundProduct);
                    }
                }
                case 3 -> {
                    System.out.println("""
                                            
                            HUR VILL DU SORTERA?
                            ----------------------+
                            1. Efter NAMN
                            2. Efter PRIS
                            3. Efter KATEGORI
                                            
                            Gör ditt menyval genom att skriva SIFFRAN och sedan trycka ENTER!
                            ↓ Skriv här ↓""");

                    choice = InputHandler.getIntegerInput();

                    switch (choice) {
                        case 1 -> {
                            System.out.println("\nPRODUKTER efter NAMN:");
                            productList.sortByName().forEach(product -> System.out.println(
                                    product.getName() +
                                            ": EAN kod: " + product.getEan() +
                                            ", Pris: " + product.getPrice() + " kr" +
                                            ", Kategori: " + product.getCategory().getName() +
                                            ", Antal: " + product.getQuantity() + " st"));
                        }
                        case 2 -> {
                            System.out.println("\nPRODUKTER efter PRIS:");
                            productList.sortByPrice().forEach(product -> System.out.println(
                                    product.getName() +
                                            ": EAN kod: " + product.getEan() +
                                            ", Pris: " + product.getPrice() + " kr" +
                                            ", Kategori: " + product.getCategory().getName() +
                                            ", Antal: " + product.getQuantity() + " st"));
                        }
                        case 3 -> {
                            System.out.println("\nPRODUKTER efter KATEGORI:");
                            productList.sortByCategory().forEach(product -> System.out.println(
                                    product.getName() +
                                            ": EAN kod: " + product.getEan() +
                                            ", Pris: " + product.getPrice() + " kr" +
                                            ", Kategori: " + product.getCategory().getName() +
                                            ", Antal: " + product.getQuantity() + " st"));
                        }
                    }

                    System.out.println("\nVill du lägga till en vara i varukorgen? (Y/N)");
                    if (InputHandler.getStringInput().equalsIgnoreCase("y")) {
                        System.out.println("""
                                                            
                                Skriv NAMN eller EAN kod på den vara du vill LÄGGA TILL i varukorgen, tryck sedan ENTER""");
                        Product foundProduct = findProductStock();
                        chooseQuantityThenAddToCart(foundProduct);
                    }
                }
            }
        }
    }

    private static class Cart {
        public static void run() {
            printCart();
            removeFromCart();
        }

        private static void removeFromCart() {
            System.out.println("\nTa bort varor? (Y/N)");
            if (InputHandler.getStringInput().equalsIgnoreCase("y")) {
                System.out.println("""
                                                    
                        Skriv NAMN eller EAN kod på den vara du vill TA BORT från varukorgen, tryck sedan ENTER""");
                Product foundProduct = findProductCart();
                chooseQuantityThenRemoveFromCart(foundProduct);
            }
        }

        private static void printCart() {
            System.out.println("""

                    VARUKORGEN
                    ----------------------+""");
            cart.printCart();
        }
    }

    private static class CheckOut {

        public static void run() {
            System.out.println("""
                                        
                    +-----------------------+
                    | Välkommen till KASSAN |
                    +-----------------------+
                                        
                    VARUKORGEN
                    ----------------------+""");
            cart.printCart();

            System.out.println("\nKontrollerar rabatter...");
            Discounts.checkDiscounts(cart);

            System.out.println("\nGå vidare till betalning? (Y/N)");
            if (InputHandler.getStringInput().equalsIgnoreCase("y")) {
                Discounts.executeDiscounts(cart);
                CsvWriter.writeReceipt(cart);
                System.out.println("""
                                                
                        TACK FÖR DITT KÖP

                        Du hittar ditt kvitto i följande mapp
                        Lab2\\out\\production\\Lab2
                        """);
                System.out.println("Spara ändringar? (Y/N)");
                if (InputHandler.getStringInput().equalsIgnoreCase("y")) {
                    CsvWriter.saveCategorySet(categories);
                    CsvWriter.saveProductList(productList);
                }
                System.out.println("\nAvslutar programmet...");
                System.exit(0);
            }
        }
    }

    private static Product findProductStock() {
        Product foundProduct = productList.searchProduct();

        System.out.println("\nVALD PRODUKT" +
                "\n----------------------+" +
                "\n" + foundProduct.getName() +
                ": EAN kod: " + foundProduct.getEan() +
                ", Pris: " + foundProduct.getPrice() + " kr" +
                ", Kategori: " + foundProduct.getCategory().getName() +
                ", Antal: " + foundProduct.getQuantity() + " st");
        return foundProduct;
    }

    private static Product findProductCart() {
        Product foundProduct = cart.searchProduct();

        System.out.println("\nVALD PRODUKT" +
                "\n----------------------+" +
                "\n" + foundProduct.getName() +
                ": EAN kod: " + foundProduct.getEan() +
                ", Pris: " + foundProduct.getPrice() + " kr" +
                ", Kategori: " + foundProduct.getCategory().getName() +
                ", Antal: " + cart.getCart().get(foundProduct) + " st");
        return foundProduct;
    }

    private static void chooseQuantityThenAddToCart(Product foundProduct) {
        System.out.println("\nAntal att lägga till:");
        int quantity = InputHandler.getIntegerInput();

        cart.addProduct(foundProduct, quantity);

        System.out.println("\nVaran lagt i varukorgen!");
        System.out.println("""

                DIN VARUKORG
                ----------------------+""");
        cart.printCart();

        System.out.println("\nÅtergår till föregående meny...");
    }

    private static void chooseQuantityThenRemoveFromCart(Product foundProduct) {
        System.out.println("\nAntal att ta bort:");
        int quantity = InputHandler.getIntegerInput();

        cart.removeAmountFromProduct(foundProduct, quantity);

        System.out.println("\nAntal borttagna från varukorg");
        System.out.println("""

                DIN VARUKORG
                ----------------------+""");
        cart.printCart();

        System.out.println("\nÅtergår till föregående meny...");
    }
}
