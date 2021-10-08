package se.iths.java21.patrik.lab2;

import se.iths.java21.patrik.lab2.menu.admin.AdminMenu;
import se.iths.java21.patrik.lab2.menu.tools.CsvReader;
import se.iths.java21.patrik.lab2.menu.trading.Category;
import se.iths.java21.patrik.lab2.menu.trading.CategoryList;
import se.iths.java21.patrik.lab2.menu.trading.ProductList;
import se.iths.java21.patrik.lab2.menu.trading.ShoppingCart;
import se.iths.java21.patrik.lab2.menu.trading.*;
import se.iths.java21.patrik.lab2.menu.MainMenu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        CategoryList categories = CsvReader.readCategories();
        ProductList productList = CsvReader.readProducts();
        ShoppingCart cart = new ShoppingCart();

        MainMenu menu = new MainMenu(productList, categories, cart);

        menu.run();
    }


}

// Egen lagrins klass, kanske repository, storage, accessar som att det vore en lista. Add, get, remove.
// I tisdags, allt i en lista och kategorin är ett av fälten på en produkt
// Streams kan filtrera efter fältet
