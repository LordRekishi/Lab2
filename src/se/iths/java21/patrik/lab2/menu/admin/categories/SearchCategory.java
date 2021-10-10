package se.iths.java21.patrik.lab2.menu.admin.categories;

import se.iths.java21.patrik.lab2.menu.tools.InputHandler;

class SearchCategory {
    public static void run(CategorySet categories) {

        System.out.println("\nSök på Kategorins NAMN");
        Category foundCategory = searchCategory(categories);

        System.out.println("\nHittade Kategori: " + foundCategory.getName());
        removeOrChangeCategory(categories, foundCategory);
    }

    private static Category searchCategory(CategorySet categories) {
        String searchInput = InputHandler.getStringInput();
        return categories.getCategory(searchInput);
    }

    private static void removeOrChangeCategory(CategorySet categories, Category foundCategory) {
        if (!foundCategory.getName().equalsIgnoreCase("NONE")) {
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
                    System.out.println(foundCategory.getName());
                    System.out.println("\nÅtergår till föregående meny...");
                }
            }
        } else {
            System.out.println("\nÅtergår till föregående meny...");
        }
    }
}
