package se.iths.java21.patrik.lab2.menu.tools;

import se.iths.java21.patrik.lab2.menu.trading.Product;
import se.iths.java21.patrik.lab2.menu.trading.ProductList;
import se.iths.java21.patrik.lab2.menu.trading.ShoppingCart;

public class Console {
    public static void markedProductPrint(Product foundProduct) {
        System.out.println(
                "\nVALD PRODUKT" +
                        "\n----------------------+" +
                        "\n" + foundProduct.getName() +
                        ": EAN kod: " + foundProduct.getEan() +
                        ", Pris: " + foundProduct.getPrice() + " kr" +
                        ", Kategori: " + foundProduct.getCategory().getName() +
                        ", Antal: " + foundProduct.getQuantity() + " st");
    }

    public static void updatedProductPrint(Product foundProduct) {
        System.out.println("""
                                                
                Den uppdaterade produkten
                ----------------------+""");
        System.out.println(
                "\n" + foundProduct.getName() +
                        ": EAN kod: " + foundProduct.getEan() +
                        ", Pris: " + foundProduct.getPrice() + " kr" +
                        ", Kategori: " + foundProduct.getCategory().getName() +
                        ", Antal: " + foundProduct.getQuantity() + " st");
    }

    public static void productInCartPrint(Product foundProduct, ShoppingCart cart) {
        System.out.println(
                "\nVALD PRODUKT" +
                        "\n----------------------+" +
                        "\n" + foundProduct.getName() +
                        ": EAN kod: " + foundProduct.getEan() +
                        ", Pris: " + foundProduct.getPrice() + " kr" +
                        ", Kategori: " + foundProduct.getCategory().getName() +
                        ", Antal: " + cart.getCart().get(foundProduct) + " st");
    }

    public static void getSortedByNamePrint(ProductList productList) {
        productList.sortByName().forEach(product -> System.out.println(
                product.getName() +
                        ": EAN kod: " + product.getEan() +
                        ", Pris: " + product.getPrice() + " kr" +
                        ", Kategori: " + product.getCategory().getName() +
                        ", Antal: " + product.getQuantity() + " st"));
    }

    public static void getSortedByPricePrint(ProductList productList) {
        productList.sortByPrice().forEach(product -> System.out.println(
                product.getName() +
                        ": EAN kod: " + product.getEan() +
                        ", Pris: " + product.getPrice() + " kr" +
                        ", Kategori: " + product.getCategory().getName() +
                        ", Antal: " + product.getQuantity() + " st"));
    }

    public static void getSortedByCategoryPrint(ProductList productList) {
        productList.sortByCategory().forEach(product -> System.out.println(
                product.getName() +
                        ": EAN kod: " + product.getEan() +
                        ", Pris: " + product.getPrice() + " kr" +
                        ", Kategori: " + product.getCategory().getName() +
                        ", Antal: " + product.getQuantity() + " st"));
    }

    public static void getProductsByPricePrint(float min, float max, ProductList productList) {
        productList.getProductsByPrice(min, max).forEach(product -> System.out.println(
                product.getName() +
                        ": EAN kod: " + product.getEan() +
                        ", Pris: " + product.getPrice() + " kr" +
                        ", Kategori: " + product.getCategory().getName() +
                        ", Antal: " + product.getQuantity() + " st"));
    }
}
