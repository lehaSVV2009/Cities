package com.kadet;

import com.kadet.execption.PropertiesFormatException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static java.util.stream.Collectors.toList;

/**
 * Created by AlexSoroka on 4/26/2015.
 */
public class AppProperties {

    private static AppProperties instance = new AppProperties();

    public static AppProperties getInstance () {
        return instance;
    }

    private AppProperties () {}

    private final static String CITIES_PROPERTY_PATH = "cities.properties";
    private final static String CITIES_PROPERTY = "CITIES";

    private List<String> cities;

    public void loadProperties () throws PropertiesFormatException {
        try {
            Properties properties = new Properties();
            InputStream stream = AppProperties.class.getClassLoader().getResourceAsStream(CITIES_PROPERTY_PATH);
            InputStreamReader reader = new InputStreamReader(stream, "CP1251");
            properties.load(reader);
            String citiesText = properties.getProperty(CITIES_PROPERTY);
            cities = parseTextToList(citiesText);
        } catch (Exception e) {
            throw new PropertiesFormatException(e.getMessage());
        }
    }

    private List<String> parseTextToList (String text) throws PropertiesFormatException {
        if (text == null) {
            throw new PropertiesFormatException("There is no property '" + CITIES_PROPERTY + "' " + CITIES_PROPERTY_PATH + "!");
        }
        return Arrays.asList(
                text.split(","))
                .stream()
                .filter(city -> city != null && city.length() != 0)
                .map(String::trim)
                .collect(toList());
    }

    public List<String> getCities () {
        return cities == null ? Arrays.asList() : cities;
    }

}
