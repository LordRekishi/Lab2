package se.iths.java21.patrik.lab2.menu.trading;

import java.util.*;

public class ShoppingCart {
    private Map<Product, Integer> cart = new HashMap<>();
    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
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

    public void setProductAndAmount(int ean, int amount, ProductList productList) {
        Product addedProduct = new Product(productList.getProduct(ean), amount);

        if (amount <= 0)
            System.out.println("Amount too low...");
        else {
            this.cart.put(addedProduct, amount);
            productList.getProduct(ean).setQuantity(-amount);
            totalPrice += (amount * addedProduct.getPrice());
        }
    }

    public void addAmountToProduct(int ean, int amount, ProductList productList, ShoppingCart cart) {
        try {
            int currentStock = this.cart.get(cart.getProduct(ean));
            Product cartProduct = cart.getProduct(ean);

            this.cart.put(cartProduct, currentStock + amount);
            cartProduct.setQuantity(amount);
            productList.getProduct(ean).setQuantity(-amount);
            totalPrice += (amount * cartProduct.getPrice());
        } catch (NullPointerException e) {
            System.out.println("Wrong EAN code, try again...");
        }
    }

    public void removeAmountFromProduct(int ean, int amount, ProductList productList, ShoppingCart cart) {
        try {
            int currentStock = this.cart.get(cart.getProduct(ean));
            Product cartProduct = cart.getProduct(ean);

            if (currentStock - amount <= 0) {
                this.cart.remove(cartProduct);
                productList.getProduct(ean).setQuantity(currentStock);
                totalPrice -= (currentStock * cartProduct.getPrice());
            } else {
                this.cart.put(cartProduct, currentStock - amount);
                productList.getProduct(ean).setQuantity(amount);
                cartProduct.setQuantity(-amount);
                totalPrice -= (amount * cartProduct.getPrice());
            }
        } catch (NullPointerException e) {
            System.out.println("Wrong EAN code, try again...");
        }

    }

    public void printCart(ShoppingCart cart) {
        if (this.cart.isEmpty())
            System.out.println("Cart is Empty...");
        else {
            this.cart.keySet().stream()
                    .forEach(System.out::println);
            System.out.println("Total price:" + cart.getTotalPrice());
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
