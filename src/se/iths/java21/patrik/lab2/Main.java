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

        AdminMenu admin = new AdminMenu(categories, productList);
        TradeMenu trade = new TradeMenu(productList, cart);
        MainMenu menu = new MainMenu(trade, admin);

        menu.run();

        Category meat = new Category("Meat");
        Category dairy = new Category("Dairy");


//        productList.addProductToList(new Product("Bacon", 10, meat, 1 , 10));
//        productList.addProductToList(new Product("Milk", 20, dairy, 2, 5 ));
//        productList.addProductToList(new Product("Chicken clubs", 5, meat, 3, 6 ));





        // Buy item, add to cart, remove from stock

        System.out.println("Available products");
        productList.printList();
        System.out.println("");

        System.out.println("Set Product and Amount 2 DONE");
        cart.setProductAndAmount(1, 2, productList);
        System.out.println("Print Cart");
        cart.printCart(cart);
        System.out.println("");

        System.out.println("Available products");
        productList.printList();
        System.out.println("");

        System.out.println("Add 3 to Product DONE");
        cart.addAmountToProduct(1, 3, productList, cart);
        System.out.println("Print Cart");
        cart.printCart(cart);
        System.out.println("");

        System.out.println("Available products");
        productList.printList();
        System.out.println("");

        System.out.println("Remove 4 of Product DONE");
        cart.removeAmountFromProduct(1, 4, productList, cart);
        System.out.println("Print Cart");
        cart.printCart(cart);
        System.out.println("");

        System.out.println("Available products");
        productList.printList();
        System.out.println("");

        System.out.println("Remove 2 of Product = 0 DONE");
        cart.removeAmountFromProduct(1, 2, productList, cart);
        System.out.println("Print Cart");
        cart.printCart(cart);
        System.out.println(cart.getTotalPrice());
        System.out.println("");

        System.out.println("Available products");
        productList.printList();
        System.out.println("");

        // Search for product by name - OK
//        System.out.println(productList.getProduct("Mil"));
//        System.out.println(productList.getProduct("Chicken"));
//        System.out.println(productList.getProduct("con"));

        // Search for product by EAN - OK
//        System.out.println(productList.getProduct(2));
//        System.out.println(productList.getProduct(1));
//        System.out.println(productList.getProduct(4));








    }

// Egen lagrins klass, kanske repository, storage, accessar som att det vore en lista. Add, get, remove.
// I tisdags, allt i en lista och kategorin är ett av fälten på en produkt
// Streams kan filtrera efter fältet







    private static List<Product> getProductList(Category category) {
        return List.of(
                new Product("Product 1", 10, category, 1, 5 ),
                new Product("Product 2", 2, category, 2, 6 ),
                new Product("Product 3", 5, category, 3, 10 ),
                new Product("Product 4", 20, category, 4, 7 ),
                new Product("Product 5", 7, category, 5, 8 )
        );
    }
}
