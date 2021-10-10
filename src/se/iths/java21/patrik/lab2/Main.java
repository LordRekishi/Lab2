package se.iths.java21.patrik.lab2;

import se.iths.java21.patrik.lab2.menu.tools.CsvReader;
import se.iths.java21.patrik.lab2.menu.admin.categories.CategorySet;
import se.iths.java21.patrik.lab2.menu.admin.products.ProductList;
import se.iths.java21.patrik.lab2.menu.trading.ShoppingCart;
import se.iths.java21.patrik.lab2.menu.MainMenu;

public class Main {
    public static void main(String[] args) {
        CategorySet categories = CsvReader.readCategories();
        ProductList productList = CsvReader.readProducts();
        ShoppingCart cart = new ShoppingCart();

        MainMenu menu = new MainMenu(productList, categories, cart);
        menu.run();
    }
}
