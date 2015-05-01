package com.kadet;

import com.kadet.controller.InputController;
import com.kadet.execption.PropertiesFormatException;
import com.kadet.service.CitiesService;
import com.kadet.store.Cities;
import com.kadet.store.History;
import com.kadet.util.CitiesUtil;
import com.kadet.view.CitiesDialog;

import javax.swing.*;

/**
 * Created by AlexSoroka on 4/25/2015.
 */
public class AppBundle {

    private static CitiesDialog citiesDialog;

    public static void init() {
        try {
            AppProperties appProperties = AppProperties.getInstance();
            appProperties.loadProperties();

            Cities cities = new Cities();
            cities.fillStore(appProperties.getCities());
            History history = new History();
            CitiesUtil.setCities(cities);

            CitiesService citiesService = new CitiesService();
            citiesService.setCities(cities);
            citiesService.setHistory(history);

            InputController controller = new InputController();
            controller.setCitiesService(citiesService);

            citiesDialog = new CitiesDialog();
            citiesDialog.setController(controller);

        } catch (PropertiesFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public static void start() {
        if (citiesDialog != null) {
            citiesDialog.showDialog("Input the city:");
        }
    }

}
