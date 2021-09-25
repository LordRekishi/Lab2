package se.iths.java21.patrik.lab2.handla.plants.grains;

import se.iths.java21.patrik.lab2.handla.plants.PlantBased;
import se.iths.java21.patrik.lab2.handla.plants.PlantType;
import se.iths.java21.patrik.lab2.handla.plants.QualityClass;
import se.iths.java21.patrik.lab2.handla.varor.TasteAndSpice;

public class Flours extends PlantBased {
    public Flours(String name, float price, float mass, String manufacturer, String origin, PlantType type, QualityClass quality, TasteAndSpice taste) {
        super(name, price, mass, manufacturer, origin, type, quality, taste);
    }
}
