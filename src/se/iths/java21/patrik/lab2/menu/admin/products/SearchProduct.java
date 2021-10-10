package se.iths.java21.patrik.lab2.menu.admin.products;

import se.iths.java21.patrik.lab2.menu.tools.Console;
import se.iths.java21.patrik.lab2.menu.tools.InputHandler;

class SearchProduct {
    public static void run(ProductList productList) {

        System.out.println("\nSök på NAMN eller EAN kod");
        Product foundProduct = productList.searchProduct();

        Console.markedProductPrint(foundProduct);
        removeOrChangeProduct(productList, foundProduct);
    }

    private static void removeOrChangeProduct(ProductList productList, Product foundProduct) {
        if (foundProduct.getEan() != 0) {
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
        } else {
            System.out.println("\nÅtergår till föregående meny...");
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
