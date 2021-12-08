package com.example.diehlweatherappuidesign;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;
import org.w3c.dom.Document;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * HelloController - controls the UI of the program
 * @author John K. Estell
 * @author Alex Diehl
 */
public class HelloController {

    @FXML
    private Label temperatureLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label windLabel;

    @FXML
    private Label cloudsLabel;

    @FXML
    private ImageView weatherIcon;

    @FXML
    private AnchorPane anchorPane;



    private final String ROOT = "/edu/onu/eccs/timeandweather/";

    // weather data of interest for this version of the program
    // Current weather data:
    private Current     currentWeather;
    private Temperature currentTemp;
    private Wind        currentWind;
    private Clouds      currentClouds;
    private WeatherIcon currentWeatherIcon;
    private String zip = "45810";

    // setup frequency of current weather requests (so we don’t wear out our welcome...)
    private int       updateWeatherTimer = 0;
    private final int WEATHER_UPDATE     = 60 * 15;  // update every 15 minutes

    // current time and date information, and formatting thereof
    private Date date;
    private final SimpleDateFormat sdfDate = new SimpleDateFormat("MMM dd, yyyy");
    private final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

    //private final javax.swing.Timer second;

    private final Color DARK_GRAY = new Color(64, 64, 64);
    int secondsPassed = 0;

    //Image icons to be used
    int frame = 1; //for animating iconsgetClass().getResourceAsStream("com/example/diehlweatherappuidesign/01d_1.png")
    Image frame1;
    Image frame2;
    Image o1d_1 = new Image("/01d_1.png");
    Image o1d_2 = new Image("/01d_2.png");
    Image o1n_1 = new Image("/01n_1.png");
    Image o1n_2 = new Image("/01n_2.png");
    Image o2d_1 = new Image("/02d_1.png");
    Image o2d_2 = new Image("/02d_2.png");
    Image o2n_1 = new Image("/02n_1.png");
    Image o2n_2 = new Image("/02n_2.png");
    Image o3d_1 = new Image("/03d_1.png");
    Image o3d_2 = new Image("/03d_2.png");
    Image o4d_1 = new Image("/04d_1.png");
    Image o4d_2 = new Image("/04d_2.png");
    Image o9d_1 = new Image("/09d_1.png");
    Image o9d_2 = new Image("/09d_2.png");
    Image l0d_1 = new Image("/10d_1.png");
    Image l0d_2 = new Image("/10d_2.png");
    Image l0n_1 = new Image("/10n_1.png");
    Image l0n_2 = new Image("/10n_2.png");
    Image lld_1 = new Image("/11d_1.png");
    Image lld_2 = new Image("/11d_2.png");
    Image l3d_1 = new Image("/13d_1.png");
    Image l3d_2 = new Image("/13d_2.png");


    /**
     * Creates new form TimeAndWeather
     */



    public void initialize()
    {
        updateWeatherDisplay();
        updateTimeDisplay();

        //timer for updating the weather display
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        updateWeatherDisplay();
                        updateTimeDisplay();
                    }
                });
            }
        }, 0, 1000);

        //timer for icon animation
        Timer frameTimer = new Timer();
        frameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        if(frame == 1) { frame = 2; weatherIcon.setImage(frame1);}
                        else { frame = 1; weatherIcon.setImage(frame2);}
                    }
                });
            }
        }, 0, 500);
    }

    // set initial display of information once the frame is shown
    private void formComponentShown(java.awt.event.ComponentEvent evt) {
        updateWeatherDisplay();
        updateTimeDisplay();
    }
    // Update the date and time display every second, and update the weather display
    // as indicated
//    private void secondTimerActionPerformed() {
//        updateWeatherTimer++;
//        if (updateWeatherTimer > WEATHER_UPDATE ) {
//            updateWeatherTimer = 0;
//            updateWeatherDisplay();
//        }
//        updateTimeDisplay();
//    }

    @FXML
    public void zipCodeChange( ActionEvent event )
    {
        Weather.changeZipCode();

    }


    // Fetch the appropriate weather data
    private void updateWeatherDisplay() {

        // Get the XML document containing current weather information
        Document weatherXML = Weather.getXMLDocument(
                Weather.getCurrentWeatherURL( Weather.zipCode ));

        // parse the desired attributes
        currentWeather = new Current();
        currentWeather.setCurrentWeatherAttributes(weatherXML);

        // fetch the temperature and wind information
        currentTemp = currentWeather.getTemperature();
        currentWind = currentWeather.getWind();
        currentClouds = currentWeather.getClouds();
        currentWeatherIcon = currentWeather.getWeatherIcon();
        String windSpeed = currentWind.getSpeed().getValue();

        // wind speed is reported more accurately than people care to hear, so round the
        // data to the nearest mph.
        int ws = (int) (Float.parseFloat(windSpeed) + 0.5);
        String windDirection = currentWind.getDirection().getCode();

        // “N at 0 mph” looks stupid – if winds are calm then say so…
        String windInfo = "winds are calm";
        if ( ws > 0 )
            windInfo = "winds are from the " + windDirection + " at " + ws + " mph";

        //determining which icon to use, assigns image to each frame for animation
        switch(currentWeatherIcon.toString())
        {
            case "01d":
                frame1 = o1d_1;
                frame2 = o1d_2;
                break;
            case "01n":
                frame1 = o1n_1;
                frame2 = o1n_2;
                break;
            case "02d":
                frame1 = o2d_1;
                frame2 = o2d_2;
                break;
            case "02n":
                frame1 = o2n_1;
                frame2 = o2n_2;
                break;
            case "03d":
            case "03n":
                frame1 = o3d_1;
                frame2 = o3d_2;
                break;
            case "04d":
            case "04n":
                frame1 = o4d_1;
                frame2 = o4d_2;
                break;
            case "09d":
            case "09n":
                frame1 = o9d_1;
                frame2 = o9d_2;
                break;
            case "10d":
                frame1 = l0d_1;
                frame2 = l0d_2;
                break;
            case "10n":
                frame1 = l0n_1;
                frame2 = l0n_2;
                break;
            case "11d":
            case "11n":
                frame1 = lld_1;
                frame2 = lld_2;
                break;
            case "13d":
            case "13n":
                frame1 = l3d_1;
                frame2 = l3d_2;
                break;

        }

        temperatureLabel.setText( currentTemp.toString());
        windLabel.setText(windInfo);
        cloudsLabel.setText( currentClouds.toString());
    }


    // Get the current date and time, format, and then display
    private void updateTimeDisplay() {
        date = Calendar.getInstance().getTime();
        dateLabel.setText(sdfDate.format(date));
        timeLabel.setText(sdfTime.format(date));
    }



}