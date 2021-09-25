package se.iths.java21.patrik.lab2.handla.animal.dairy;

import se.iths.java21.patrik.lab2.handla.animal.AnimalBased;
import se.iths.java21.patrik.lab2.handla.animal.AnimalBasedType;
import se.iths.java21.patrik.lab2.handla.varor.TasteAndSpice;

public class Yoghurts extends AnimalBased {
    public Yoghurts(String name, float price, float mass, String manufacturer, String origin, AnimalBasedType type, TasteAndSpice taste, float fatPercent) {
        super(name, price, mass, manufacturer, origin, type, taste, fatPercent);
    }
}
