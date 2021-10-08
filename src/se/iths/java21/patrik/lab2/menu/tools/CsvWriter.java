package se.iths.java21.patrik.lab2.menu.tools;

import se.iths.java21.patrik.lab2.menu.trading.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static se.iths.java21.patrik.lab2.menu.tools.CheckedSupplier.wrap;

public class CsvWriter {
    public static void writeReceipt(ShoppingCart cart) {
        Path receiptPath = Path.of(wrap(() -> ClassLoader.getSystemResource("kvitto.csv").toURI()));
        List<String> strings = new ArrayList<>();
        cart.getCart().entrySet().forEach(productIntegerEntry -> convertToStringsAndIntegers(productIntegerEntry, strings));
        strings.add("Summa: " + cart.getTotalPrice() + " kr");

        try {
            Files.write(receiptPath, strings, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertToStringsAndIntegers(Map.Entry<Product, Integer> productIntegerEntry, List<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();

        strings.add(stringBuilder
                .append(productIntegerEntry.getKey().getName())
                .append(": ")
                .append(productIntegerEntry.getKey().getPrice())
                .append(" kr / styck, Antal: ")
                .append(productIntegerEntry.getValue())
                .append(" stycken")
                .toString());
    }

    public static void saveProductList(ProductList productList) {
        Path productPath = Path.of(wrap(() -> ClassLoader.getSystemResource("products.csv").toURI()));
        List<String> strings = new ArrayList<>();
        productList.forEach(product -> convertToStrings(product, strings));

        try {
            Files.write(productPath, strings, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertToStrings(Product product, List<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();

        strings.add(stringBuilder
                .append(product.getName())
                .append(",")
                .append(product.getPrice())
                .append(",")
                .append(product.getCategory().getName())
                .append(",")
                .append(product.getEan())
                .append(",")
                .append(product.getQuantity())
                .toString());
    }

    public static void saveCategorySet(CategoryList categories) {
        Path categoryPath = Path.of(wrap(() -> ClassLoader.getSystemResource("categories.csv").toURI()));
        List<String> strings = getStrings(categories);

        try {
            Files.write(categoryPath, strings, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getStrings(CategoryList categories) {
        return categories.getCategorySet().stream()
                .map(Category::getName)
                .toList();
    }
}
