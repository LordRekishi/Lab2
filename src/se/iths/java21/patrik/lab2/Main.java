package se.iths.java21.patrik.lab2;

import se.iths.java21.patrik.lab2.menu.tools.CsvReader;
import se.iths.java21.patrik.lab2.menu.trading.CategoryList;
import se.iths.java21.patrik.lab2.menu.trading.ProductList;
import se.iths.java21.patrik.lab2.menu.trading.ShoppingCart;
import se.iths.java21.patrik.lab2.menu.MainMenu;

public class Main {
    public static void main(String[] args) {
        CategoryList categories = CsvReader.readCategories();
        ProductList productList = CsvReader.readProducts();
        ShoppingCart cart = new ShoppingCart();

        MainMenu menu = new MainMenu(productList, categories, cart);
        menu.run();
    }
}
