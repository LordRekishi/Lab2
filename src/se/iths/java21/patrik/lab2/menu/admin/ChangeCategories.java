package se.iths.java21.patrik.lab2.menu.admin;

import se.iths.java21.patrik.lab2.menu.tools.*;
import se.iths.java21.patrik.lab2.menu.trading.Category;
import se.iths.java21.patrik.lab2.menu.trading.CategoryList;

public class ChangeCategories implements MenuTemplate<Integer> {
    private final Command[] commands = new Command[4];
    private static CategoryList categories;

    public ChangeCategories(CategoryList categories) {
        ChangeCategories.categories = categories;

        commands[1] = SearchCategory::run;
        commands[2] = AddCategory::run;
        commands[3] = this::printList;
        commands[0] = this::shutDown;
    }

    private void printList() {
        System.out.println("\nKATEGORIER:");
        categories.printCategories();
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
                           
                           +------------------+ +--------------+ +------------------+ +---------+
                KATEGORIER | 1. Sök / Ändra   | | 2. Lägg till | | 3. Se Kategorier | | 0. Exit |
                           +------------------+ +--------------+ +------------------+ +---------+
                                
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

    static class SearchCategory {
        public static void run() {

            System.out.println("\nSök på Kategorins NAMN");
            Category foundCategory = searchCategory();

            System.out.println("\nHittade Kategori: " + foundCategory.getName());

            System.out.println("\nTa bort vald kategori? (Y/N)");
            if (InputHandler.getStringInput().equalsIgnoreCase("y")) {
                categories.removeCategory(foundCategory);
            } else {
                System.out.println("\nÄndra på kategorin? (Y/N)");
                if (InputHandler.getStringInput().equalsIgnoreCase("y")) {
                    System.out.println("\n↓ Skriv nytt namn här ↓");

                    String newName = InputHandler.getStringInput();

                    foundCategory.setName(newName);

                    System.out.println("""
                                                            
                            Den uppdaterade kategorin:""");
                    System.out.println(foundCategory);
                    System.out.println("\nÅtergår till föregående meny...");

                }
            }
        }

        private static Category searchCategory() {
            String searchInput = InputHandler.getStringInput();

            return categories.getCategory(searchInput);
        }
    }

    static class AddCategory {
        public static void run() {
            String choice;

            do {
                System.out.println("""
                                                
                        Lägg till Kategori
                        ↓ Skriv här ↓""");

                System.out.println("\nKategorins NAMN:");
                String name = InputHandler.getStringInput();

                // Skapa kategorin och lägg till i set
                categories.addCategory(new Category(name));

                System.out.println("""
                                                
                        Kategorin är tillagd...
                                                
                        Lägga till en till kategori? (Y/N)""");

                choice = InputHandler.getStringInput();

            } while (!choice.equalsIgnoreCase("n"));

            System.out.println("\nTillgängliga kategorier:");
            categories.printCategories();

            System.out.println("\nÅtergår till föregående meny...");
        }
    }
}
