package se.iths.java21.patrik.lab2.menu.tools;

import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntegerInput() {
        return Integer.parseInt(scanner.nextLine());
    }

    public static float getFloatInput() {
        return Float.parseFloat(scanner.nextLine());
    }

    public static String getStringInput() {
        return scanner.nextLine();
    }

    public static boolean isNumeric(String searchInput) {
        if (searchInput == null)
            return false;

        try {
            Integer.parseInt(searchInput);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
