package se.iths.java21.patrik.lab2.menu.trading;

import se.iths.java21.patrik.lab2.menu.tools.Category;

import java.util.Objects;

public class Product {
    private final String name;
    private float price;
    private final Category category;
    private final int ean;
    private int quantity;

    public Product(String name, float price, Category category, int ean, int stock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.ean = ean;
        this.quantity = stock;
    }

    public Product(Product product, int amount) {
        this.name = product.name;
        this.price = product.price;
        this.category = product.category;
        this.ean = product.ean;
        this.quantity = amount;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public int getEan() {
        return ean;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int amount) {
        if (this.quantity + amount <= 0)
            this.quantity = 0;
        else
            this.quantity = this.quantity + amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Float.compare(product.price, price) == 0 && ean == product.ean && quantity == product.quantity && Objects.equals(name, product.name) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, category, ean, quantity);
    }

    @Override
    public String toString() {
        if (this.getName().equals("NO PRODUCT FOUND"))
            return "No such product found!";
        else
            return name + ": Price: " + price + "kr, Category: " + category + ", EAN Code:" + ean + ", Quantity: " + quantity;
    }
}
