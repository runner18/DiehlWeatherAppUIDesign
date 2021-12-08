package com.example.diehlweatherappuidesign;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author John K. Estell
 */
public class Current {
    private Temperature temperature;
    private Wind wind;
    private Clouds clouds;
    private WeatherIcon weatherIcon;

    public void setCurrentWeatherAttributes( Document doc ) {
        // get the root element of the document – this can then be used to access all descendent
        // nodes within the XML tree. However, we’ll request only those of interest...
        Element  rootElement = doc.getDocumentElement();

        setAttribute( "temperature", rootElement );
        setAttribute( "wind", rootElement );
        setAttribute( "speed", rootElement );
        setAttribute( "direction", rootElement );
        setAttribute( "clouds", rootElement);
        setAttribute("weather", rootElement);
    }

    // Access all nodes containing the passed tag name, and set our variables accordingly
    private void setAttribute( String currentTagName, Element rootElement ) {
        NodeList currentList    = rootElement.getElementsByTagName(currentTagName);
        Node     currentNode    = currentList.item(0);
        Element  currentElement = (Element) currentNode;

        // instantiate a new object of the appropriate type, using attributes fetched from
        // the XML document.
        switch (currentTagName) {
            case "temperature":
                temperature = new Temperature( currentElement.getAttribute("value"),
                        currentElement.getAttribute("unit") );
                break;
            case "wind":
                wind = new Wind();
                break;
            case "speed":
                Speed speed = new Speed( currentElement.getAttribute("value"),
                        currentElement.getAttribute("name") );
                wind.setSpeed(speed);
                break;
            case "direction":
                Direction direction = new Direction( currentElement.getAttribute("value"),
                        currentElement.getAttribute("code"),
                        currentElement.getAttribute("name") );
                wind.setDirection(direction);
                break;
            case "clouds":
                clouds = new Clouds(currentElement.getAttribute("value"),
                        currentElement.getAttribute("name"));
                break;
            case "weather":
                weatherIcon = new WeatherIcon(currentElement.getAttribute(("icon")));
                break;
        }
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() { return clouds; }

    public WeatherIcon getWeatherIcon()  { return weatherIcon; }
}
