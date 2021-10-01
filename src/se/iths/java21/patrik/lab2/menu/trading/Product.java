package se.iths.java21.patrik.lab2.menu.trading;

public class Product {
    private final String name;
    private float price;
    private final Category category;
    private final int ean;
    private int stock;

    public Product(String name, float price, Category category, int ean, int stock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.ean = ean;
        this.stock = stock;
    }

    public Product(Product product, int amount) {
        this.name = product.name;
        this.price = product.price;
        this.category = product.category;
        this.ean = product.ean;
        this.stock = amount;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int amount) {
        if (this.stock + amount <= 0)
            this.stock = 0;
        else
            this.stock = this.stock + amount;
    }

    @Override
    public String toString() {
        return name + ": Price: " + price + "kr, Category: " + category + ", EAN Code:" + ean + ", Amount: " + stock;
    }
}
