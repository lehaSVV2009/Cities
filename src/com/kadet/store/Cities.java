package com.kadet.store;

import com.kadet.execption.CitiesException;
import com.kadet.execption.WinException;
import com.kadet.util.CitiesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by AlexSoroka on 4/25/2015.
 */
public class Cities {

    private List<String> cities = new ArrayList<>();

    public String getRandomCity (char firstChar) throws CitiesException, WinException {
        if (cities.size() == 0) {
            throw new WinException();
        }
        firstChar = String.valueOf(firstChar).toLowerCase().charAt(0);
        List<String> filteredCities = filterByFirstChar(firstChar);
        if (filteredCities.size() == 0) {
            throw new WinException();
        }
        return filteredCities.get(new Random().nextInt(filteredCities.size()));
    }

    public boolean contains (final String city) {
        return cities.stream().anyMatch(f -> f.equals(city.toLowerCase()));
    }

    public String first () {
        return cities.stream().findFirst().get();
    }

    public String last () {
        return cities.stream().reduce((first, second) -> second).get();
    }

    public String reduce () {
        return cities.stream().reduce("All the elements: ", (a, b) -> a + b);
    }

    public List<Integer> lengths () {
        return cities.stream().map(String::length).collect(toList());
    }

    public List<String> sortByAlphabet () {
        return cities.stream().sorted().collect(toList());
    }

    public List<String> filterByFirstChar (char firstChar) {
        return cities.stream().filter(city -> city.charAt(0) == firstChar).collect(toList());
    }

    public List<String> limit (int limit) {
        return cities.stream().limit(limit).collect(toList());
    }

    public List<String> distinct () {
        return cities.stream().distinct().collect(toList());
    }

    public List<String> skip (int skip) {
        return cities.stream().skip(skip).collect(toList());
    }

    public boolean noMatch (String city) {
        return cities.stream().noneMatch(value -> value.equals(city));
    }

    public void printIfAnyExist () {
        cities.stream().findAny().ifPresent(value -> {
            System.out.println("It exisits: " + value);
            System.out.println("Yahoo!!");
        });
    }

    public void fillStore (List<String> newCities) {
        cities.addAll(newCities.stream()
                               .filter(city -> city != null && city.length() != 0)
                               .map(String::toLowerCase)
                               .collect(toList()));
    }

    public int lengthsSum () {
        return cities.stream().mapToInt(String::length).sum();
    }

    public List<String> otherCities () {
        return Stream.of("Braavos", "Winterfell", "King's Landing").collect(toList());
    }

    public void printAll () {
        cities.stream().forEach(value -> System.out.println(value.getClass() + ": " + value));
    }

    public long count() {
        return cities.stream().count();
    }

    public List<Character> getUnusableInStartChars () {
        List<Character> allowedCharacters = CitiesUtil.getAllowedCharacters();
        return allowedCharacters.stream()
                         .filter(value -> cities.stream().noneMatch(city -> city.startsWith(String.valueOf(value))))
                         .collect(toList());
    }

}
