package com.kadet.util;

import com.kadet.execption.CitiesException;
import com.kadet.store.Cities;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by AlexSoroka on 4/26/2015.
 */
public final class CitiesUtil {

    private static Cities cities;

    private final static List<Character> allowedCharacters = Arrays.asList('а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', ' ', '-');
    private final static List<Character> allowedUpperCharacters = allowedCharacters.stream().map(value -> String.valueOf(value).toUpperCase().charAt(0)).collect(toList());

    public static String upperFirstCharacter (String city) {
        return city.replaceFirst(city.substring(0, 1), city.substring(0, 1).toUpperCase());
    }

    public static boolean allowedCharacters (String word) {
        for (int i = 0; i < word.length(); ++i) {
            if (!allowedCharacters.contains(word.charAt(i)) && !allowedUpperCharacters.contains(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    public static char getLastCharForGame(String city) throws CitiesException {
        char lastChar = getLastChar(city);
        List<Character> unusableInStart = cities.getUnusableInStartChars();
        if (unusableInStart.contains(lastChar)) {
            if (city.length() < 2) {
                throw new CitiesException("The word " + city + " is too small!");
            }
            lastChar = city.charAt(city.length() - 2);
            if (unusableInStart.contains(lastChar)) {
                throw new CitiesException("The word " + city + "is wrong!");
            }
        }
        return lastChar;
    }

    public static char getLastChar (String text) {
        return text.charAt(text.length() - 1);
    }

    public static List<Character> getAllowedCharacters() {
        return allowedCharacters;
    }

    public static void setCities(Cities cities) {
        CitiesUtil.cities = cities;
    }
}
