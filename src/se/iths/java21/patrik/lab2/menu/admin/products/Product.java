package se.iths.java21.patrik.lab2.menu.admin.products;

import se.iths.java21.patrik.lab2.menu.admin.categories.Category;

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
        this.quantity = Math.max(this.quantity + amount, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", ean=" + ean +
                ", quantity=" + quantity +
                '}';
    }
}
