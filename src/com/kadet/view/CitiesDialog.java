package com.kadet.view;

import com.kadet.controller.InputController;

import javax.swing.*;

/**
 * Created by AlexSoroka on 4/25/2015.
 */
public class CitiesDialog {

    private InputController controller;

    private String word;

    public void showDialog (String message) {
        String clientAnswer = JOptionPane.showInputDialog(null, message);
        if (clientAnswer != null) {
            String serverAnswer = controller.inputCity(clientAnswer);
            serverAnswerAction(serverAnswer, message);
        }
    }

    private void serverAnswerAction (String serverAnswer, String clientAnswer) {
        if (serverAnswer.startsWith("Winner")) {
            word = serverAnswer;
            JOptionPane.showMessageDialog(null, "You are the winner! Congratulations!");
        } else if (serverAnswer.startsWith("Error")) {
            showDialog(serverAnswer + (word != null ? "\n" + word: ""));
        } else {
            word = serverAnswer;
            showDialog(serverAnswer);
        }
    }

    public void setController(InputController controller) {
        this.controller = controller;
    }
}
