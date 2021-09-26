package se.iths.java21.patrik.lab2.handla.varor;

import java.util.Objects;

public class Product {
    private String name;
    private float price;
    private float mass;
    private String manufacturer;
    private String origin;

    public Product(String name, float price, float mass, String manufacturer, String origin) {
        this.name = name;
        this.price = price;
        this.mass = mass;
        this.manufacturer = manufacturer;
        this.origin = origin;
    }


    public String name() {
        return name;
    }

    public float price() {
        return price;
    }

    public void price(float price) {
        this.price = price;
    }

    public float mass() {
        return mass;
    }

    public String manufacturer() {
        return manufacturer;
    }

    public String origin() {
        return origin;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Float.compare(product.price, price) == 0 && Float.compare(product.mass, mass) == 0 && Objects.equals(name, product.name) && Objects.equals(manufacturer, product.manufacturer) && Objects.equals(origin, product.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, mass, manufacturer, origin);
    }
}
