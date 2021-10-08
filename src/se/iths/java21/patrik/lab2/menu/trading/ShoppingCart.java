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

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

        if (cart.containsKey(product))
            addAmountToProduct(product, amount);
        else
            setProductAndAmount(product, amount);

    }

    public void setProductAndAmount(Product product, int amount) {

        if (amount <= 0)
            System.out.println("Antal för lite, försök igen...");
        else {
            cart.putIfAbsent(product, amount);
            product.setQuantity(-amount);
            totalPrice += (amount * product.getPrice());
        }
    }

    public void addAmountToProduct(Product product, int amount) {
        int cartQuantity = cart.get(product);
        int newTotal = cartQuantity + amount;

        if (amount <= 0)
            System.out.println("Antal för lite, försök igen...");
        else {
            cart.replace(product, newTotal);
            product.setQuantity(-amount);
            totalPrice += (amount * product.getPrice());
        }
    }

    public void removeAmountFromProduct(Product product, int amount) {
        int cartQuantity = cart.get(product);
        int newTotal = cartQuantity - amount;

        if (amount < 0)
            System.out.println("Antal för lite, försök igen...");
        else {
            if (newTotal <= 0) {
                cart.remove(product);
                product.setQuantity(cartQuantity);
                totalPrice -= (cartQuantity * product.getPrice());
            } else {
                cart.replace(product, newTotal);
                product.setQuantity(amount);
                totalPrice -= (amount * product.getPrice());
            }
        }

    }

    public void printCart() {
        if (this.cart.isEmpty())
            System.out.println("Varukorgen är tom...");
        else {
            this.cart.entrySet()
                    .forEach(product -> System.out.println(
                            product.getKey().getName() +
                                    ": EAN kod: " + product.getKey().getEan() +
                                    ", Pris: " + product.getKey().getPrice() + " kr" +
                                    ", Kategori: " + product.getKey().getCategory().getName() +
                                    ", Antal i Korgen: " + product.getValue() + " st"));
            System.out.printf("Summa: %.2f kr\n", this.getTotalPrice());
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
