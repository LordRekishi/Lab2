package se.iths.java21.patrik.lab2.menu.trading;

import java.util.HashSet;
import java.util.Set;

public class CategoryList {
    private static Set<Category> categorySet;

    public CategoryList() {
        categorySet = new HashSet<>();
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        CategoryList.categorySet = categorySet;
    }

    public void addCategory(Category category) {
        categorySet.add(category);
    }

    public Category getCategory(String name) {
        return categorySet.stream()
                .filter(category -> category.getName().toLowerCase().contains(name.toLowerCase()))
                .findFirst()
                .orElse(new Category("NONE"));
    }

    public void removeCategory(Category category) {
        categorySet.remove(category);
    }

    public void printCategories() {
        categorySet.forEach(System.out::println);
    }
}

// Iterator
