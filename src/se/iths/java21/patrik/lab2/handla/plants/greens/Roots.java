package se.iths.java21.patrik.lab2.handla.plants.greens;

import se.iths.java21.patrik.lab2.handla.plants.PlantBased;
import se.iths.java21.patrik.lab2.handla.plants.PlantType;
import se.iths.java21.patrik.lab2.handla.plants.QualityClass;
import se.iths.java21.patrik.lab2.handla.varor.TasteAndSpice;

public class Roots extends PlantBased {
    public Roots(String name, float price, float mass, String manufacturer, String origin, PlantType type, QualityClass quality, TasteAndSpice taste) {
        super(name, price, mass, manufacturer, origin, type, quality, taste);
    }
}
