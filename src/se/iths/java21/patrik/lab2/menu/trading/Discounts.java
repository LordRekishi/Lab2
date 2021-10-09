package se.iths.java21.patrik.lab2.menu.trading;

public class Discounts {
    private static final int OVER_AMOUNT_DISCOUNT_LIMIT = 500;
    private static final double OVER_AMOUNT_DISCOUNT_PERCENT = 0.9;
    private static final int THREE_FOR_TWO_PRODUCT_EAN = 10;

    public static void checkDiscounts(ShoppingCart cart, ProductList productList) {
        final Product THREE_FOR_TWO_PRODUCT = productList.getProduct(THREE_FOR_TWO_PRODUCT_EAN);
        double totalPrice = cart.getTotalPrice();

        if (totalPrice > OVER_AMOUNT_DISCOUNT_LIMIT) {
            totalPrice = totalPrice * OVER_AMOUNT_DISCOUNT_PERCENT;

            System.out.println("\nEftersom du köpt för mer än " + OVER_AMOUNT_DISCOUNT_LIMIT +
                    " kr får du " + Math.round((1.0 - OVER_AMOUNT_DISCOUNT_PERCENT) * 100) + "% rabatt på hela ditt köp!");
            System.out.printf("Ditt nya totalpris: %.2f kr\n", totalPrice);
        } else {
            System.out.println("\nKom ihåg, om du köper för över " + OVER_AMOUNT_DISCOUNT_LIMIT + " kr får du " +
                    Math.round((1.0 - OVER_AMOUNT_DISCOUNT_PERCENT) * 100) + "% rabatt på hela ditt köp!");
        }

        if (cart.getCart().containsKey(THREE_FOR_TWO_PRODUCT)) {
            final boolean HAS_THREE_OR_MORE = cart.getCart().get(THREE_FOR_TWO_PRODUCT) >= 3;
            final int NUMBER_OF_THREE_FOR_TWO_PRODUCTS = cart.getCart().get(THREE_FOR_TWO_PRODUCT);

            if (HAS_THREE_OR_MORE) {
                final int NUMBER_OF_FREE_PRODUCTS = Math.floorDiv(NUMBER_OF_THREE_FOR_TWO_PRODUCTS, 3);
                totalPrice = totalPrice - NUMBER_OF_FREE_PRODUCTS * THREE_FOR_TWO_PRODUCT.getPrice();

                System.out.println("\nVi har specialpris på " + THREE_FOR_TWO_PRODUCT.getName() + ", köp 3 betala för 2");
                System.out.println("Vi har tagit bort kostnaden för " + NUMBER_OF_FREE_PRODUCTS + " st kantareller");
                System.out.println("Du kommer tjäna " + (NUMBER_OF_FREE_PRODUCTS * THREE_FOR_TWO_PRODUCT.getPrice()) + " kr");
                System.out.printf("Ditt nya totalpris: %.2f kr\n", totalPrice);
            }
        } else {
            System.out.println("\nDu vet väl om att vi har köp 3 betala för 2 på " +
                    THREE_FOR_TWO_PRODUCT.getName() + " just nu!");
        }
    }

    public static void executeDiscounts(ShoppingCart cart, ProductList productList) {
        final Product THREE_FOR_TWO_PRODUCT = productList.getProduct(THREE_FOR_TWO_PRODUCT_EAN);
        double totalPrice = cart.getTotalPrice();

        if (totalPrice > OVER_AMOUNT_DISCOUNT_LIMIT) {
            double discountOverAmount = totalPrice * OVER_AMOUNT_DISCOUNT_PERCENT;

            cart.setTotalPrice(discountOverAmount);
            totalPrice = discountOverAmount;
        }
        if (cart.getCart().containsKey(THREE_FOR_TWO_PRODUCT)) {
            final boolean HAS_THREE_OR_MORE = cart.getCart().get(THREE_FOR_TWO_PRODUCT) >= 3;
            final int NUMBER_OF_THREE_FOR_TWO_PRODUCTS = cart.getCart().get(THREE_FOR_TWO_PRODUCT);

            if (HAS_THREE_OR_MORE) {
                final int NUMBER_OF_FREE_PRODUCTS = Math.floorDiv(NUMBER_OF_THREE_FOR_TWO_PRODUCTS, 3);
                double discount3To2 = totalPrice - NUMBER_OF_FREE_PRODUCTS * THREE_FOR_TWO_PRODUCT.getPrice();

                cart.setTotalPrice(discount3To2);
            }
        }
    }
}
