package se.iths.java21.patrik.lab2.handla.animal;

import se.iths.java21.patrik.lab2.handla.varor.Product;
import se.iths.java21.patrik.lab2.handla.varor.TasteAndSpice;

import java.util.Objects;

public class AnimalBased extends Product {
    private AnimalBasedType type;
    private TasteAndSpice taste;
    private float fatPercent;

    public AnimalBased(String name, float price, float mass, String manufacturer, String origin, AnimalBasedType type, TasteAndSpice taste, float fatPercent) {
        super(name, price, mass, manufacturer, origin);
        this.type = type;
        this.taste = taste;
        this.fatPercent = fatPercent;
    }

    public AnimalBasedType getType() {
        return type;
    }

    public TasteAndSpice getTaste() {
        return taste;
    }

    public float getFatPercent() {
        return fatPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AnimalBased that = (AnimalBased) o;
        return Float.compare(that.fatPercent, fatPercent) == 0 && type == that.type && taste == that.taste;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, taste, fatPercent);
    }
}

