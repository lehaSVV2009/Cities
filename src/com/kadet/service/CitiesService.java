package com.kadet.service;

import com.kadet.execption.CitiesException;
import com.kadet.execption.WinException;
import com.kadet.store.Cities;
import com.kadet.store.History;
import com.kadet.util.CitiesUtil;

import java.time.LocalDateTime;

/**
 * Created by AlexSoroka on 4/25/2015.
 */
public class CitiesService {

    private Cities cities;
    private History history;

    private final static int SERVER_MEMORY_MISTAKE_COUNT = 3;

    public String answer (String clientCity) throws CitiesException, WinException {
        validateClientCity(clientCity);
        history.put(clientCity, LocalDateTime.now());

        String serverCity = generateServerCity(CitiesUtil.getLastCharForGame(clientCity));

        history.put(serverCity, LocalDateTime.now());
        return CitiesUtil.upperFirstCharacter(serverCity);
    }

    private String generateServerCity (char lastChar) throws CitiesException, WinException {
        String serverCity = cities.getRandomCity(lastChar);
        int counter = SERVER_MEMORY_MISTAKE_COUNT;
        while (history.contains(serverCity) && counter != 0) {
            serverCity = cities.getRandomCity(lastChar);
            --counter;
        }
        if (counter == 0) {
            throw new WinException();
        }
        return serverCity;
    }

    private void validateClientCity (String clientCity) throws CitiesException {
        if (clientCity.length() == 0) {
            throw new CitiesException("You didn't input any city!");
        }
        if (!CitiesUtil.allowedCharacters(clientCity)) {
            throw new CitiesException("Not allowed characters in the word " + clientCity);
        }
        if (history.last() != null && !history.sameAsLastCharacter(clientCity.substring(0, 1).toLowerCase().charAt(0))) {
            throw new CitiesException("Last character is wrong! Required last character: " + history.requiredLastCharacter());
        }
        if (!cities.contains(clientCity)) {
            throw new CitiesException("The city " + clientCity + " doesn't exist!");
        }
        if (history.contains(clientCity)) {
            LocalDateTime dateTime = history.getDate(clientCity);
            throw new CitiesException(new StringBuilder().append("The city ").append(clientCity).append(" is in History! ").append(dateTime.getHour()).append(":").append(dateTime.getMinute()).append(":").append(dateTime.getSecond()).append(" " ).append(dateTime.getDayOfMonth()).append(".").append(dateTime.getMonthValue()).append(".").append(dateTime.getYear()).toString());
        }
    }

    public void setCities(Cities cities) {
        this.cities = cities;
    }

    public void setHistory(History history) {
        this.history = history;
    }


}
