package se.iths.java21.patrik.lab2.menu.trading;

import se.iths.java21.patrik.lab2.menu.tools.InputHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductList {
    private static List<Product> products;

    public ProductList() {
        products = new ArrayList<>();
    }

    public Product searchProduct() {
        String searchInput = InputHandler.getStringInput();

        if (InputHandler.isNumeric(searchInput)) {
            return this.getProduct(Integer.parseInt(searchInput));
        } else {
            return this.getProduct(searchInput);
        }
    }

    public List<Product> getList() {
        return Collections.unmodifiableList(products);
    }

    public void addProductToList(Product product) {
        products.add(product);
    }

    public Product getProduct(String name) {
        return products.stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .findFirst()
                .orElse(new Product("NO PRODUCT FOUND",0, new Category("NONE"),0, 0));
    }

    public Product getProduct(int ean) {
        return products.stream()
                .filter(product -> product.getEan() == ean)
                .findFirst()
                .orElse(new Product("NO PRODUCT FOUND",0, new Category("NONE"),0, 0));
    }

    public List<Product> getProductsByPrice(float min, float max) {
        return products.stream()
                .filter(product -> product.getPrice() >= min)
                .filter(product -> product.getPrice() <= max)
                .sorted(Comparator.comparing(Product::getPrice))
                .toList();
    }

    public List<Product> sortByName() {
        return products.stream()
                .sorted(Comparator.comparing(Product::getName))
                .toList();
    }

    public List<Product> sortByPrice() {
        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .toList();
    }

    public List<Product> sortByCategory() {
        return products.stream()
                .sorted(Comparator.comparing(Product::getName))
                .toList();
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void printList() {
        products.forEach(System.out::println);
    }


}
