package se.iths.java21.patrik.lab2.menu;

import java.util.Scanner;

public interface MenuTemplate<T> {
    void run();
    void printMenuOptions();
    T readChoice(Scanner scanner);
    void executeChoice(T choice);
    void shutDown();
}
