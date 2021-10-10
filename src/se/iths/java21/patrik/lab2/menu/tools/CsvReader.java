package se.iths.java21.patrik.lab2.menu.tools;

import se.iths.java21.patrik.lab2.menu.admin.categories.Category;
import se.iths.java21.patrik.lab2.menu.admin.categories.CategorySet;
import se.iths.java21.patrik.lab2.menu.admin.products.Product;
import se.iths.java21.patrik.lab2.menu.admin.products.ProductList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static se.iths.java21.patrik.lab2.menu.tools.CheckedSupplier.wrap;

public class CsvReader {
    private static final CategorySet categorySet = new CategorySet();
    private static final ProductList productList = new ProductList();
    private static final Pattern pattern = Pattern.compile(",");

    public static ProductList readProducts() {
        Path productPath = Path.of(wrap(() -> ClassLoader.getSystemResource("products.csv").toURI()));

        List<Product> products = List.of();

        try (Stream<String> lines = Files.lines(productPath)) {
            products = lines
                    .map(CsvReader::createProduct)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        productList.setProducts(products);
        return productList;
    }

    private static Product createProduct(String line) {
        String[] arr = pattern.split(line);
        return new Product(arr[0], Float.parseFloat(arr[1]), categorySet.getCategory(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]));
    }

    public static CategorySet readCategories() {
        Path categoryPath = Path.of(wrap(() -> ClassLoader.getSystemResource("categories.csv").toURI()));

        Set<Category> categories = Set.of();

        try (Stream<String> lines = Files.lines(categoryPath)) {
            categories = lines
                    .map(CsvReader::createCategory)
                    .collect(Collectors.toSet());

        } catch (IOException e) {
            e.printStackTrace();
        }
        categorySet.setCategorySet(categories);
        return categorySet;
    }

    private static Category createCategory(String line) {
        return new Category(line);
    }
}
