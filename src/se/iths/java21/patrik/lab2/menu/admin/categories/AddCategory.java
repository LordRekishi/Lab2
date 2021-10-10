package se.iths.java21.patrik.lab2.menu.admin.categories;

import se.iths.java21.patrik.lab2.menu.tools.Console;
import se.iths.java21.patrik.lab2.menu.tools.InputHandler;

class AddCategory {
    public static void run(CategorySet categories) {
        String choice;

        do {
            Console.addCategoryPrint();

            System.out.println("\nKategorins NAMN:");
            String name = InputHandler.getStringInput();

            categories.addCategory(new Category(name));
            Console.categoryAddedPrint();

            choice = InputHandler.getStringInput();
        } while (!choice.equalsIgnoreCase("n"));

        System.out.println("\nTillgängliga kategorier:");
        categories.printCategories();

        System.out.println("\nÅtergår till föregående meny...");
    }

}
