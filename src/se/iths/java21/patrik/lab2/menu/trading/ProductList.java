package se.iths.java21.patrik.lab2.menu.trading;

import se.iths.java21.patrik.lab2.menu.tools.InputHandler;

import java.util.*;

public class ProductList implements Iterable<Product> {
    private static List<Product> products;

    public ProductList() {
        products = new ArrayList<>();
    }

    public List<Product> getList() {
        return Collections.unmodifiableList(products);
    }

    public void setProducts(List<Product> products) {
        ProductList.products = products;
    }

    public void addProductToList(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
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
        return products.stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .findFirst()
                .orElse(new Product("NO PRODUCT FOUND", 0, new Category("NONE"), 0, 0));
    }

    public Product getProduct(int ean) {
        return products.stream()
                .filter(product -> product.getEan() == ean)
                .findFirst()
                .orElse(new Product("NO PRODUCT FOUND", 0, new Category("NONE"), 0, 0));
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
                .sorted(Comparator.comparing(product -> product.getCategory().getName()))
                .toList();
    }

    public void printList() {
        products.forEach(product -> System.out.println(
                product.getName() +
                        ": EAN kod: " + product.getEan() +
                        ", Pris: " + product.getPrice() + " kr" +
                        ", Kategori: " + product.getCategory().getName() +
                        ", Antal: " + product.getQuantity() + " st"));
    }

    @Override
    public Iterator<Product> iterator() {
        return new ProductListIterator();
    }

    private static class ProductListIterator implements Iterator<Product> {
        private int position = 0;

        @Override
        public boolean hasNext() {
            return position < products.size();
        }

        @Override
        public Product next() {
            if (position >= products.size())
                throw new NoSuchElementException();
            return products.get(position++);
        }
    }
}
