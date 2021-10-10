package se.iths.java21.patrik.lab2.menu.tools;

import se.iths.java21.patrik.lab2.menu.admin.products.Product;
import se.iths.java21.patrik.lab2.menu.admin.products.ProductList;
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
                foundProduct.getName() +
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

    public static void overLimitPrint(double totalPrice, int OVER_AMOUNT_DISCOUNT_LIMIT, double OVER_AMOUNT_DISCOUNT_PERCENT) {
        System.out.println("\nEftersom du köpt för mer än " + OVER_AMOUNT_DISCOUNT_LIMIT +
                " kr får du " + Math.round((1.0 - OVER_AMOUNT_DISCOUNT_PERCENT) * 100) + "% rabatt på hela ditt köp!");
        System.out.printf("Ditt nya totalpris: %.2f kr\n", totalPrice);
    }

    public static void notOverLimitPrint(int OVER_AMOUNT_DISCOUNT_LIMIT, double OVER_AMOUNT_DISCOUNT_PERCENT) {
        System.out.println("\nKom ihåg, om du köper för över " + OVER_AMOUNT_DISCOUNT_LIMIT + " kr får du " +
                Math.round((1.0 - OVER_AMOUNT_DISCOUNT_PERCENT) * 100) + "% rabatt på hela ditt köp!");
    }

    public static void threeForTwoPrint(Product THREE_FOR_TWO_PRODUCT, double totalPrice, int NUMBER_OF_FREE_PRODUCTS) {
        System.out.println("\nVi har specialpris på " + THREE_FOR_TWO_PRODUCT.getName() + ", köp 3 betala för 2");
        System.out.println("Vi har tagit bort kostnaden för " + NUMBER_OF_FREE_PRODUCTS + " st kantareller");
        System.out.println("Du kommer tjäna " + (NUMBER_OF_FREE_PRODUCTS * THREE_FOR_TWO_PRODUCT.getPrice()) + " kr");
        System.out.printf("Ditt nya totalpris: %.2f kr\n", totalPrice);
    }

    public static void notThreeForTwoPrint(Product THREE_FOR_TWO_PRODUCT) {
        System.out.println("\nDu vet väl om att vi har köp 3 betala för 2 på " +
                THREE_FOR_TWO_PRODUCT.getName() + " just nu!");
    }

    public static void addCategoryPrint() {
        System.out.println("""
                                        
                Lägg till Kategori
                ↓ Skriv här ↓""");
    }

    public static void categoryAddedPrint() {
        System.out.println("""
                                        
                Kategorin är tillagd...
                                        
                Lägga till en till kategori? (Y/N)""");
    }

}
