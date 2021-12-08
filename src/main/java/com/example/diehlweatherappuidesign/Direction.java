package com.example.diehlweatherappuidesign;

/**
 *
 * @author John K. Estell
 */
public class Direction {
    private String value;
    private String code;
    private String name;

    public Direction(String value, String code, String name) {
        this.value = value;
        this.code = code;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
