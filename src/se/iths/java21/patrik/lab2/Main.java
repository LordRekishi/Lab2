package se.iths.java21.patrik.lab2;

import se.iths.java21.patrik.lab2.menu.admin.AdminMenu;
import se.iths.java21.patrik.lab2.menu.tools.Category;
import se.iths.java21.patrik.lab2.menu.tools.CategoryList;
import se.iths.java21.patrik.lab2.menu.tools.ProductList;
import se.iths.java21.patrik.lab2.menu.tools.ShoppingCart;
import se.iths.java21.patrik.lab2.menu.trading.*;
import se.iths.java21.patrik.lab2.menu.MainMenu;

public class Main {
    public static void main(String[] args) {
        CategoryList categories = new CategoryList();
        ProductList productList = new ProductList();
        ShoppingCart cart = new ShoppingCart();

        categories.addCategory(new Category("Meat"));
        categories.addCategory(new Category("Dairy"));
        categories.addCategory(new Category("Vegetable"));

        productList.addProductToList(new Product("Bacon", 1, categories.getCategory("Meat"), 1, 10));
        productList.addProductToList(new Product("Milk", 20, categories.getCategory("Dairy"), 2, 5));
        productList.addProductToList(new Product("Chicken clubs", 5, categories.getCategory("Meat"), 3, 6));
        productList.addProductToList(new Product("Tomato", 3, categories.getCategory("Vegetable"), 4, 60));
        productList.addProductToList(new Product("Chicken soup", 10, categories.getCategory("Meat"), 5, 80));

        AdminMenu admin = new AdminMenu(categories, productList);
        TradeMenu trade = new TradeMenu(productList, cart);
        MainMenu menu = new MainMenu(trade, admin);

        menu.run();
    }
}

// Egen lagrins klass, kanske repository, storage, accessar som att det vore en lista. Add, get, remove.
// I tisdags, allt i en lista och kategorin är ett av fälten på en produkt
// Streams kan filtrera efter fältet
