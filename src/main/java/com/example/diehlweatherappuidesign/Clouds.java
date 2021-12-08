package com.example.diehlweatherappuidesign;

/**
 * Clouds - implements speed and direction attributes.
 * @author Alex Diehl
 */
public class Clouds {
    private String value;
    private String name;

    public Clouds(String value, String unit) {
        this.value = value;
        this.name = unit;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }



    @Override
    public String toString() {
        return name;
    }
}
