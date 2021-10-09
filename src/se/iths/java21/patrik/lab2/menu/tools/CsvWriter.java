package se.iths.java21.patrik.lab2.menu.tools;

import se.iths.java21.patrik.lab2.menu.trading.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static se.iths.java21.patrik.lab2.menu.tools.CheckedSupplier.wrap;

public class CsvWriter {
    public static void writeReceipt(ShoppingCart cart) {
        Path receiptPath = Path.of(wrap(() -> ClassLoader.getSystemResource("kvitto.csv").toURI()));
        List<String> strings = new ArrayList<>();
        cart.getCart().entrySet().forEach(productIntegerEntry -> convertToStringsAndIntegers(productIntegerEntry, strings));
        strings.add(String.format("Summa: %.2f kr", cart.getTotalPrice()));

        try {
            Files.write(receiptPath, strings);
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
                .append(" kr/st, Antal: ")
                .append(productIntegerEntry.getValue())
                .append(" stycken")
                .toString());
    }

    public static void saveProductList(ProductList productList) {
        Path productPath = Path.of(wrap(() -> ClassLoader.getSystemResource("products.csv").toURI()));
        List<String> strings = new ArrayList<>();
        productList.forEach(product -> convertToStrings(product, strings));

        try {
            Files.write(productPath, strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertToStrings(Product product, List<String> strings) {
        strings.add(String.join(",",
                product.getName(),
                String.valueOf(product.getPrice()),
                product.getCategory().getName(),
                String.valueOf(product.getEan()),
                String.valueOf(product.getQuantity())));
    }

    public static void saveCategorySet(CategorySet categories) {
        Path categoryPath = Path.of(wrap(() -> ClassLoader.getSystemResource("categories.csv").toURI()));
        List<String> strings = getStrings(categories);

        try {
            Files.write(categoryPath, strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getStrings(CategorySet categories) {
        return categories.getCategorySet().stream()
                .map(Category::getName)
                .toList();
    }
}
