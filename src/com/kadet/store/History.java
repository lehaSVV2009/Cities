package com.kadet.store;

import com.kadet.execption.CitiesException;
import com.kadet.util.CitiesUtil;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by AlexSoroka on 4/25/2015.
 */
public class History {

    private Map<LocalDateTime, String> map = new HashMap<>();

    public void put (String city, LocalDateTime dateTime) {
        map.put(dateTime, city.toLowerCase());
    }

    public boolean contains (String city) {
        return map.values().stream().anyMatch(value -> value.equals(city.toLowerCase()));
    }

    public LocalDateTime getDate (String city) {
        return map.keySet().stream().filter(value -> map.get(value).equals(city.toLowerCase())).findFirst().get();
    }

    public String last () {
        Optional<LocalDateTime> optional = map.keySet().stream().sorted((a, b) -> -a.compareTo(b)).findFirst();
        if (optional.isPresent()) {
            return map.get(optional.get());
        }
        return null;
    }

    public char requiredLastCharacter () throws CitiesException {
        return CitiesUtil.getLastCharForGame(last());
    }

    public boolean sameAsLastCharacter (char firstChar) throws CitiesException {
        return requiredLastCharacter() == firstChar;
    }

}
