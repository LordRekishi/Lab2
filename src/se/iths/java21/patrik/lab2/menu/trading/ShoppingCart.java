package se.iths.java21.patrik.lab2.menu.trading;

import se.iths.java21.patrik.lab2.menu.tools.InputHandler;

import java.util.*;

public class ShoppingCart {
    private Map<Product, Integer> cart;
    private double totalPrice;

    public ShoppingCart() {
        cart = new HashMap<>();
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Product searchProduct() {
        String searchInput = InputHandler.getStringInput();

        if (InputHandler.isNumeric(searchInput)) {
            return this.getProduct(Integer.parseInt(searchInput));
        } else {
            return this.getProduct(searchInput);
        }
    }

    public Product getProduct(String name) {
        return cart.keySet().stream()
                .filter(product -> product.getName().contains(name))
                .findFirst()
                .orElse(new Product("NULL", 0, new Category("NONE"), 0, 0));
    }

    public Product getProduct(int ean) {
        return cart.keySet().stream()
                .filter(product -> product.getEan() == ean)
                .findFirst()
                .orElse(new Product("NULL", 0, new Category("NONE"), 0, 0));
    }

    public void addProduct(Product product, int amount) {
        int exists = cart.keySet().stream()
                .mapToInt(Product::getEan)
                .filter(value -> value == product.getEan())
                .findFirst()
                .orElse(0);

        if (exists <= 0)
            setProductAndAmount(product, amount);
        else
            addAmountToProduct(product, amount);
    }

    public void setProductAndAmount(Product product, int amount) {
        Product addedProduct = new Product(product);

        if (amount <= 0)
            System.out.println("Amount too low...");
        else {
            this.cart.put(addedProduct, amount);
            product.setQuantity(-amount);
            totalPrice += (amount * addedProduct.getPrice());
        }
    }

    public void addAmountToProduct(Product product, int amount) {
        int ean = product.getEan();
        Product cartProduct = this.getProduct(ean);
        int currentStock = cart.get(cartProduct);

        // Put cart product and new stock
        cart.put(cartProduct, currentStock + amount);
        // Update cart product object
        cartProduct.setQuantity(amount);
        // Update stock
        product.setQuantity(-amount);
        // Update total price
        totalPrice += (amount * product.getPrice());
    }

    public void removeAmountFromProduct(Product product, int amount, ProductList productList) {
        try {
            int currentStock = cart.get(product);
            int ean = product.getEan();

            if (currentStock - amount <= 0) {
                cart.remove(product);
                productList.getProduct(ean).setQuantity(currentStock);
                totalPrice -= (currentStock * product.getPrice());
            } else {
                cart.put(product, currentStock - amount);
                productList.getProduct(ean).setQuantity(amount);
                product.setQuantity(-amount);
                totalPrice -= (amount * product.getPrice());
            }
        } catch (NullPointerException e) {
            System.out.println("Wrong EAN code, try again...");
        }

    }

    public void printCart() {
        if (this.cart.isEmpty())
            System.out.println("Cart is Empty...");
        else {
            this.cart.entrySet()
                    .forEach(product -> System.out.println(
                            product.getKey().getName() + ": " +
                                    "\nEAN kod: " + product.getKey().getEan() +
                                    ", Pris: " + product.getKey().getPrice() +
                                    ", Kategori: " + product.getKey().getCategory().getName() +
                                    ", Antal i Korgen: " + product.getKey()));
            System.out.println("Summa: " + this.getTotalPrice() + " kr");
        }
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cart=" + cart +
                "Total price: " + totalPrice +
                '}';
    }
}
