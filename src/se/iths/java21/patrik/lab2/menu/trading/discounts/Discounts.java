package se.iths.java21.patrik.lab2.menu.trading.discounts;

import se.iths.java21.patrik.lab2.menu.admin.products.Product;
import se.iths.java21.patrik.lab2.menu.admin.products.ProductList;
import se.iths.java21.patrik.lab2.menu.tools.Console;
import se.iths.java21.patrik.lab2.menu.trading.ShoppingCart;

import java.math.BigDecimal;

public class Discounts {
    private static final int OVER_AMOUNT_DISCOUNT_LIMIT = 500;
    private static final double OVER_AMOUNT_DISCOUNT_PERCENT = 0.9;
    private static final int THREE_FOR_TWO_PRODUCT_EAN = 10;

    public static void checkDiscounts(ShoppingCart cart, ProductList productList) {
        final Product THREE_FOR_TWO_PRODUCT = productList.getProduct(THREE_FOR_TWO_PRODUCT_EAN);
        double totalPrice = cart.getTotalPrice();

        totalPrice = checkOverLimitDiscount(totalPrice);
        checkTwoForThreeDiscount(cart, THREE_FOR_TWO_PRODUCT, totalPrice);
    }

    private static double checkOverLimitDiscount(double totalPrice) {
        if (totalPrice > OVER_AMOUNT_DISCOUNT_LIMIT) {
            totalPrice = totalPrice * OVER_AMOUNT_DISCOUNT_PERCENT;
            Console.overLimitPrint(totalPrice, OVER_AMOUNT_DISCOUNT_LIMIT, OVER_AMOUNT_DISCOUNT_PERCENT);
        } else {
            Console.notOverLimitPrint(OVER_AMOUNT_DISCOUNT_LIMIT, OVER_AMOUNT_DISCOUNT_PERCENT);
        }
        return totalPrice;
    }

    private static void checkTwoForThreeDiscount(ShoppingCart cart, Product THREE_FOR_TWO_PRODUCT, double totalPrice) {
        if (cart.getCart().containsKey(THREE_FOR_TWO_PRODUCT)) {
            final boolean HAS_THREE_OR_MORE = cart.getCart().get(THREE_FOR_TWO_PRODUCT) >= 3;
            final int NUMBER_OF_THREE_FOR_TWO_PRODUCTS = cart.getCart().get(THREE_FOR_TWO_PRODUCT);

            if (HAS_THREE_OR_MORE) {
                final int NUMBER_OF_FREE_PRODUCTS = Math.floorDiv(NUMBER_OF_THREE_FOR_TWO_PRODUCTS, 3);
                totalPrice = totalPrice - NUMBER_OF_FREE_PRODUCTS * THREE_FOR_TWO_PRODUCT.getPrice();
                Console.threeForTwoPrint(THREE_FOR_TWO_PRODUCT, totalPrice, NUMBER_OF_FREE_PRODUCTS);
            }
        } else {
            Console.notThreeForTwoPrint(THREE_FOR_TWO_PRODUCT);
        }
    }

    public static void executeDiscounts(ShoppingCart cart, ProductList productList) {
        final Product THREE_FOR_TWO_PRODUCT = productList.getProduct(THREE_FOR_TWO_PRODUCT_EAN);
        double totalPrice = cart.getTotalPrice();
        BigDecimal oldTotalPrice = BigDecimal.valueOf(totalPrice);

        totalPrice = executeOverLimitDiscount(cart, totalPrice);
        totalPrice = executeThreeForTwoDiscount(cart, THREE_FOR_TWO_PRODUCT, totalPrice);
        setTotalDiscountedSum(cart, totalPrice, oldTotalPrice);
    }

    private static double executeOverLimitDiscount(ShoppingCart cart, double totalPrice) {
        if (totalPrice > OVER_AMOUNT_DISCOUNT_LIMIT) {
            double discountOverAmount = totalPrice * OVER_AMOUNT_DISCOUNT_PERCENT;
            cart.setTotalPrice(discountOverAmount);
            totalPrice = discountOverAmount;
        }
        return totalPrice;
    }

    private static double executeThreeForTwoDiscount(ShoppingCart cart, Product THREE_FOR_TWO_PRODUCT, double totalPrice) {
        if (cart.getCart().containsKey(THREE_FOR_TWO_PRODUCT)) {
            final boolean HAS_THREE_OR_MORE = cart.getCart().get(THREE_FOR_TWO_PRODUCT) >= 3;
            final int NUMBER_OF_THREE_FOR_TWO_PRODUCTS = cart.getCart().get(THREE_FOR_TWO_PRODUCT);

            if (HAS_THREE_OR_MORE) {
                final int NUMBER_OF_FREE_PRODUCTS = Math.floorDiv(NUMBER_OF_THREE_FOR_TWO_PRODUCTS, 3);
                double discount3To2 = totalPrice - NUMBER_OF_FREE_PRODUCTS * THREE_FOR_TWO_PRODUCT.getPrice();
                cart.setTotalPrice(discount3To2);
            }
        }
        return totalPrice;
    }

    private static void setTotalDiscountedSum(ShoppingCart cart, double totalPrice, BigDecimal oldTotalPrice) {
        cart.setTotalDiscount(oldTotalPrice.subtract(BigDecimal.valueOf(totalPrice)));
    }
}
