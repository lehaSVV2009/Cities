package com.kadet.controller;

import com.kadet.execption.CitiesException;
import com.kadet.execption.WinException;
import com.kadet.service.CitiesService;

/**
 * Created by AlexSoroka on 4/25/2015.
 */
public class InputController {

    private CitiesService citiesService;

    public String inputCity (String city) {
        try {
            return citiesService.answer(city);
        } catch (CitiesException e) {
            return "Error! " + e.getMessage();
        } catch (WinException e) {
            return "Winner";
        }
    }

    public void setCitiesService(CitiesService citiesService) {
        this.citiesService = citiesService;
    }
}
