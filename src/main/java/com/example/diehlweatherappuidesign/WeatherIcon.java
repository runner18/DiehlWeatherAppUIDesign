package com.example.diehlweatherappuidesign;

/**
 * WeatherIcon - implements the weather icon attribute.
 * @author Alex Diehl
 */
public class WeatherIcon {
    private String icon;

    public WeatherIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }



    @Override
    public String toString() {
        return icon;
    }
}
