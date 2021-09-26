package se.iths.java21.patrik.lab2.handla.plants;

import se.iths.java21.patrik.lab2.handla.varor.Product;
import se.iths.java21.patrik.lab2.handla.varor.TasteAndSpice;

import java.util.Objects;

public class PlantBased extends Product {
    private PlantType type;
    private QualityClass quality;
    private TasteAndSpice taste;

    public PlantBased(String name, float price, float mass, String manufacturer, String origin, PlantType type, QualityClass quality, TasteAndSpice taste) {
        super(name, price, mass, manufacturer, origin);
        this.type = type;
        this.quality = quality;
        this.taste = taste;
    }


    public PlantType type() {
        return type;
    }

    public QualityClass quality() {
        return quality;
    }

    public void quality(QualityClass quality) {
        this.quality = quality;
    }

    public TasteAndSpice taste() {
        return taste;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PlantBased that = (PlantBased) o;
        return type == that.type && quality == that.quality && taste == that.taste;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, quality, taste);
    }
}

