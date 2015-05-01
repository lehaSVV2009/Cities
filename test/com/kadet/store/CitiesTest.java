package com.kadet.store;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by AlexSoroka on 4/25/2015.
 */
public class CitiesTest {

    private Cities citiesStore;

    @Before
    public void init () {
        citiesStore = new Cities();
    }

    @Test
    public void shouldNotContainCity () {
        // given
        String anyCity = "city";

        // when
        boolean contains = citiesStore.contains(anyCity);

        // then
        assertEquals(false, contains);
    }


    @Test
    public void shouldContainCity () {
        // given
        String anyCity = "city";

        // when
        boolean contains = citiesStore.contains(anyCity);

        // then
        assertEquals(false, contains);
    }

    @Test
    public void shouldFillStore () {
        // given
        List<String> cities = new ArrayList<>();
        cities.add("Браавос");
        cities.add("Королевская Гавань");
        cities.add("Меерин");
        cities.add("Кварт");
        cities.add("Волантис");
        cities.add("Астапор");
        cities.add("Пентос");
        cities.add("Ваес Дотрак");

        // when
        citiesStore.fillStore(cities);

        // then
        assertEquals(8, citiesStore.count());
    }

    @Test
    public void shouldDeleteDuplicates () {
        // given
        List<String> cities  = new ArrayList<>();
        cities.add("Monreal");
        cities.add("Monreal");
        citiesStore.fillStore(cities);

        // when
        cities = citiesStore.distinct();

        // then
        assertEquals(1, cities.size());
    }


    @Test
    public void shouldFilterByFirstCharacter () {
        // given
        List<String> cities = new ArrayList<>();
        cities.add("Винтерфелл");
        cities.add("Драконий Камень");
        cities.add("Харренхол");
        cities.add("Утёс Кастерли");
        cities.add("Дредфорт");
        cities.add("Чёрный замок");
        cities.add("Риверран");
        cities.add("Орлиное гнездо");
        cities.add("Близнецы");
        cities.add("Пайк");
        cities.add("Хайгарден");
        cities.add("Цитадель");
        cities.add("Штормовой предел");
        citiesStore.fillStore(cities);

        // when
        cities = citiesStore.filterByFirstChar('Д');

        // then
        assertEquals(2, cities.size());
    }

    @Test
    public void shouldLimitList () {
        // given
        List<String> cities  = new ArrayList<>();
        cities.add("New York");
        cities.add("Minsk");
        cities.add("Borisov");
        cities.add("Monreal");
        citiesStore.fillStore(cities);

        // when
        cities = citiesStore.limit(2);

        // then
        assertEquals(2, cities.size());
    }

    @Test
    public void shouldSkipList () {
        // given
        List<String> cities  = new ArrayList<>();
        cities.add("Astana");
        cities.add("Stolin");
        cities.add("Warsaw");
        cities.add("Kiev");
        citiesStore.fillStore(cities);

        // when
        cities = citiesStore.skip(5);

        // then
        assertEquals(0, cities.size());
    }

    @Test
    public void shouldSortByAlphabet() {
        // given
        List<String> cities  = new ArrayList<>();
        cities.add("Mozyr");
        cities.add("Rogachev");
        cities.add("Polotsk");
        cities.add("Mogilev");
        citiesStore.fillStore(cities);

        // when
        cities = citiesStore.sortByAlphabet();

        // then
        assertEquals("Mogilev", cities.get(0));
        assertEquals("Rogachev", cities.get(3));
    }

    @Test
    public void shouldJoinToText() {
        // given
        List<String> cities  = new ArrayList<>();
        cities.add("I ");
        cities.add("Love ");
        cities.add("You ");
        cities.add("Minsk!");
        citiesStore.fillStore(cities);

        // when
        String text = citiesStore.reduce();

        // then
        assertEquals("All the elements: I Love You Minsk!", text);
    }


    @Test
    public void shouldReturnCities() {
        // given
        String firstOtherCity = "Braavos";

        // when
        List<String> otherCities = citiesStore.otherCities();

        // then
        assertEquals(firstOtherCity, otherCities.get(0));
    }

    @Test
    public void shouldReturnLast() {
        // given
        List<String> cities  = new ArrayList<>();
        cities.add("Arhangelsk");
        cities.add("Rome");
        cities.add("Budapest");
        citiesStore.fillStore(cities);

        // when
        String city = citiesStore.last();

        // then
        assertEquals("Budapest", city);
    }


    @Test
    public void shouldReturnLengthsSum() {
        // given
        List<String> cities  = new ArrayList<>();
        cities.add("Buharest");
        cities.add("Praha");
        cities.add("Paris");
        citiesStore.fillStore(cities);

        // when
        int sum = citiesStore.lengthsSum();

        // then
        assertEquals(18, sum);
    }


}
