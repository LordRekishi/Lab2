package se.iths.java21.patrik.lab2.menu.tools;

import se.iths.java21.patrik.lab2.menu.trading.Product;
import se.iths.java21.patrik.lab2.menu.trading.ShoppingCart;

public class Discounts {

    public static void checkDiscounts(ShoppingCart cart) {
        double totalPrice = cart.getTotalPrice();
        Product kantarell = cart.getProduct(10);
        boolean hasThreeKantarellerOrMore = cart.getCart().get(kantarell) >= 3;
        int numberOfKantareller = cart.getCart().get(kantarell);

        if (totalPrice > 500) {
            totalPrice = totalPrice * 0.9;

            System.out.println("\nEftersom du köpt för mer än 500 kr får du 10% rabatt på hela ditt köp!");
            System.out.printf("Ditt nya totalpris: %.2f kr\n", totalPrice);

            if (hasThreeKantarellerOrMore) {
                int freeKantareller = Math.floorDiv(numberOfKantareller, 3);
                totalPrice = totalPrice - freeKantareller * kantarell.getPrice();

                System.out.println("\nVi har specialpris på Kantareller, köp 3 betala för 2");
                System.out.println("Vi har tagit bort kostnaden för " + freeKantareller + " st kantareller");
                System.out.println("Du kommer tjäna " + (freeKantareller * kantarell.getPrice()) + " kr");
                System.out.printf("Ditt nya totalpris: %.2f kr\n", totalPrice);
            }
        }
    }

    public static void executeDiscounts(ShoppingCart cart) {
        double totalPrice = cart.getTotalPrice();
        Product kantarell = cart.getProduct(10);
        boolean hasThreeKantarellerOrMore = cart.getCart().get(kantarell) >= 3;
        int numberOfKantareller = cart.getCart().get(kantarell);

        if (totalPrice > 500) {
            double discount500 = totalPrice * 0.9;

            cart.setTotalPrice(discount500);
            totalPrice = discount500;

            if (hasThreeKantarellerOrMore) {
                int freeKantareller = Math.floorDiv(numberOfKantareller, 3);
                double discount3To2 = totalPrice - freeKantareller * kantarell.getPrice();

                cart.setTotalPrice(discount3To2);
            }
        }
    }
}
