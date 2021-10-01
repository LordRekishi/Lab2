package se.iths.java21.patrik.lab2;

import se.iths.java21.patrik.lab2.menu.admin.AdminMenu;
import se.iths.java21.patrik.lab2.menu.trading.*;
import se.iths.java21.patrik.lab2.menu.MainMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Category> categories = new ArrayList<>();
        ProductList productList = new ProductList();
        ShoppingCart cart = new ShoppingCart();

        Category meat = new Category("Meat");
        Category dairy = new Category("Dairy");

        categories.add(meat);
        categories.add(dairy);

        productList.addProductToList(new Product("Bacon", 10, meat, 1 , 10));
        productList.addProductToList(new Product("Milk", 20, dairy, 2, 5 ));
        productList.addProductToList(new Product("Chicken clubs", 5, meat, 3, 6 ));

        AdminMenu admin = new AdminMenu(categories, productList);
        TradeMenu trade = new TradeMenu(productList, cart);
        MainMenu menu = new MainMenu(trade, admin);

        menu.run();









        // Search for product by name - OK
//        System.out.println(productList.getProduct("Mil"));
//        System.out.println(productList.getProduct("Chicken"));
//        System.out.println(productList.getProduct("con"));

        // Search for product by EAN - OK
//        System.out.println(productList.getProduct(2));
//        System.out.println(productList.getProduct(1));
//        System.out.println(productList.getProduct(4));

    }
}

// Egen lagrins klass, kanske repository, storage, accessar som att det vore en lista. Add, get, remove.
// I tisdags, allt i en lista och kategorin är ett av fälten på en produkt
// Streams kan filtrera efter fältet
