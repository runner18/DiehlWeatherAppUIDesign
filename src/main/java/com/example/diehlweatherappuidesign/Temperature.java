package com.example.diehlweatherappuidesign;

/**
 * Temperature - implements value and unit attributes; adds additional field
 * for rendering unit string in the degrees-letter format.
 * @author John K. Estell
 * @version 1.0 - 15 April 2018
 */
public class Temperature {
    private String value;
    private String unit;
    private String degreesUnit;

    public Temperature(String value, String unit) {
        this.value = value;
        this.unit = unit;
        this.degreesUnit = "\u00B0" + unit.toUpperCase().charAt(0);
    }

    public String getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getDegreesUnit() {
        return degreesUnit;
    }

    @Override
    public String toString() {
        int roundedValue = (int) ( Float.parseFloat( value.trim() ) + 0.5 );
        return "" + roundedValue + degreesUnit;
    }
}
