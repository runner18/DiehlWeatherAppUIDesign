package com.example.diehlweatherappuidesign;

import java.net.*;
import java.io.*;
import java.util.Optional;
import javax.xml.parsers.*;

import javafx.scene.control.TextInputDialog;
import org.w3c.dom.*;
import javax.swing.*;

/**
 *
 * @author John K. Estell
 * @author Alex Diehl
 */
public class Weather
{
    private static final String APPID = "0fdd87603aca1daefaa205cf9950c80f"; // not actual ID
    public  static final String DEFAULT_ZIP = "45810";
    public static String zipCode = DEFAULT_ZIP;

    // Create URL to retrieve current weather data for the default location for the registered
    // APPID, using units Americans can relate to…
    public static String getCurrentWeatherURL( String zipCode )
    {

        return "http://api.openweathermap.org/data/2.5/weather?zip="
                + zipCode + "&APPID=" + APPID + "&mode=xml&units=imperial";
    }

    public static boolean changeZipCode()
    {
        boolean correctZip = false;
        TextInputDialog td = new TextInputDialog("enter any text");
        td.setHeaderText("enter a zip code");
        String testUrl;

        while(correctZip == false)
        {
            //opens a TextInputDialog for the user to enter a new zipCode
            td.getEditor().clear();
            Optional<String> result = td.showAndWait();

            //if the user didn't cancel or exit out of the window
            if(result.isPresent()) {
                String returnedZip = td.getEditor().getText();
                testUrl = getCurrentWeatherURL(td.getEditor().getText());

                //check API to see make sure null isn't returned
                getCurrentWeatherURL(returnedZip);
                if (getXMLDocument(testUrl) != null) {
                    zipCode = returnedZip;
                    correctZip = true;
                } else {
                    td.setHeaderText("Incorrect code entered. Try again!");
                }
            }
            else return true; //if user cancels or exits the zip code TextInputDialogue, return true
        }
        return true;
    }

    // Routine for obtaining an XML data file from the web.
    public static Document getXMLDocument( String url ) {
        URL            webpageURL        = null;  // explicit assignments to make NetBeans happy...
        URLConnection  webpageConnection = null;
        InputStream    webpageData       = null;
        BufferedReader bin               = null;


        try
        {
            webpageURL = new URL( url );

            // if here then the URL has correct format - now let's try to make the connection...
            webpageConnection = webpageURL.openConnection();
            webpageData = webpageConnection.getInputStream();
        }
        catch ( IOException e )
        {
            String message = "Page not found: " + url;
            javax.swing.JOptionPane.showMessageDialog( null, message , "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return null;
        }
//        catch ( MalformedURLException e ) {
//            String message = "Malformed URL: " + url;
//            javax.swing.JOptionPane.showMessageDialog( null, message , "Error",
//                    javax.swing.JOptionPane.ERROR_MESSAGE);
//            return null;
//        }

        // Have the file – use the canned routines to parse the file into an XML document
        Document xmldoc = null;
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder docReader = dbf.newDocumentBuilder();
            xmldoc = docReader.parse(webpageData);
            xmldoc.getDocumentElement().normalize();
        }
        catch (Exception e) {}

        return xmldoc;
    }
}
