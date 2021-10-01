package se.iths.java21.patrik.lab2.menu.trading;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private static List<Product> products = new ArrayList<>();

    public List<Product> getList() {
        return products;
    }

    public void addProductToList(Product product) {
        products.add(product);
    }

    public Product getProduct(String name) {
        return products.stream()
                .filter(product -> product.getName().contains(name))
                .findFirst()
                .orElse(new Product("NULL",0, new Category("NONE"),0, 0));
    }

    public Product getProduct(int ean) {
        return products.stream()
                .filter(product -> product.getEan() == ean)
                .findFirst()
                .orElse(new Product("NULL",0, new Category("NONE"),0, 0));
    }

    public void printList() {
        products.forEach(System.out::println);
    }


}
