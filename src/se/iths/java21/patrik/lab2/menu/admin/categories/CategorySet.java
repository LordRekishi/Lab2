package se.iths.java21.patrik.lab2.menu.admin.categories;

import java.util.HashSet;
import java.util.Set;

public class CategorySet {
    private static Set<Category> categorySet;

    public CategorySet() {
        categorySet = new HashSet<>();
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        CategorySet.categorySet = categorySet;
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
        categorySet.forEach(category -> System.out.println(category.getName()));
    }
}
