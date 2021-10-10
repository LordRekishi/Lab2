package se.iths.java21.patrik.lab2.menu.admin.products;

import se.iths.java21.patrik.lab2.menu.admin.categories.Category;
import se.iths.java21.patrik.lab2.menu.admin.categories.CategorySet;
import se.iths.java21.patrik.lab2.menu.tools.InputHandler;

class AddProduct {
    public static void run(ProductList productList, CategorySet categories) {
        String choice;

        do {
            System.out.println("""
                                            
                    Lägg till Produkt
                    ----------------------+""");

            addNewProductInput(productList, categories);

            System.out.println("""
                                            
                    Varan är tillagd...
                                            
                    Lägga till en till vara? (Y/N)""");
            choice = InputHandler.getStringInput();
        } while (!choice.equalsIgnoreCase("n"));

        System.out.println("\nTillgängliga varor:");
        productList.printList();

        System.out.println("\nÅtergår till föregående meny...");
    }

    private static void addNewProductInput(ProductList productList, CategorySet categories) {

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
    }
}
