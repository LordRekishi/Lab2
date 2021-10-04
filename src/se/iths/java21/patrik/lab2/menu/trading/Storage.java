package se.iths.java21.patrik.lab2.menu.trading;

import se.iths.java21.patrik.lab2.menu.tools.Category;
import se.iths.java21.patrik.lab2.menu.tools.ProductList;
import se.iths.java21.patrik.lab2.menu.tools.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<ProductList> productLists = new ArrayList<>();
    private List<ShoppingCart> carts = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    public List<ProductList> getProductLists() {
        return productLists;
    }

    public void setProductLists(List<ProductList> productLists) {
        this.productLists = productLists;
    }

    public List<ShoppingCart> getCarts() {
        return carts;
    }

    public void setCarts(List<ShoppingCart> carts) {
        this.carts = carts;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
