package com.example.diehlweatherappuidesign;

/**
 * Speed - uses value and name attributes
 * @author John K. Estell
 */
public class Speed {
    private String value;
    private String name;

    public Speed(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
