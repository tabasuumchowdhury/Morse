package edu.vanier.morse.controller;

import edu.vanier.morse.MainApp;
import edu.vanier.morse.utility.MorseCodeMap;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TransmitController {
    private final static Logger logger = LoggerFactory.getLogger(TransmitController.class);
    private Scene mainScene;

    @FXML
    private Button btnTransmit;

    @FXML
    private Button btnReceive;

    @FXML
    private Button transmitBtn;

    @FXML
    private Label morseLbl;

    @FXML
    private Label letterLbl;

    @FXML
    private Label msgLabel;

    @FXML
    private TextField msgField;
    @FXML
    BorderPane borderPane;
    private Timeline timeline;
    private double timeElapsed = 0.0;

    /**
     * To correctly stop the animation
     * the background must be reset to white
     */
    private void stopAnimation() {
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.stop();
        }
        borderPane.setBackground(Background.fill(Color.WHITE));
    }

    /**
     * Checks if user has given a valid entry
     * @param text what it checks
     * @return false if the input is empty
     */
    public boolean isValidText(String text) {
        return text != null && !text.trim().isEmpty();
    }

    /**
     * Defines what happens when buttons are pressed
     */
    @FXML
    public void initialize() {
        // 1- Loads new scene, back to main
        btnReceive.setOnAction(event -> {
            MainApp.switchScene("menu_layout", new MenuController());
            logger.info("Loaded the receiving scene...");
        });

        // 2- Flashes the screen
        transmitBtn.setOnAction(event -> {
            logger.info("Transmit Button has been pressed");

            // Gets input from user
            String inputText = msgField.getText();

            if (isValidText(inputText)) {
                logger.info("Valid Inputs: ", inputText);
                //System.out.println(inputText); // to test input
                startFlashingForText(inputText);
            } else {
                logger.warn("Invalid Inputs");

                // Display error message
                msgLabel.setText("INVALID FORMAT");
                msgLabel.setTextFill(Color.FIREBRICK);
            }
        });
    }

    /**
     *
     * @param text the character that should be translated
     * @return the morse equivalent
     */
    private String translateToMorse(char text) {
        Map<Character, String> morseCodeMap = MorseCodeMap.getMorseCodeMap();
        if (morseCodeMap.containsKey(text)) {
            String morseCode = morseCodeMap.get(text);
            System.out.println(morseCode);
            return morseCode;
        }
        return " ";
    }

    /**
     * Flashes the screen background as morse code
     * @param inputText the users text
     */
    private void startFlashingForText(String inputText) {
        stopAnimation(); // Reset any running animation

        SequentialTransition sequentialTransition = new SequentialTransition();
        Duration symbolDuration = Duration.millis(150); // Dot duration
        Duration dashDuration = Duration.millis(750);  // Dash duration
        Duration timeBetweenSymbols = Duration.millis(500); // Gap between symbols
        Duration timeBetweenCharacters = Duration.millis(1000); // Gap between characters

        // Goes through every character of the users text,
        for (int i = 0; i < inputText.length(); i++) {
            char inputChar = inputText.toUpperCase().charAt(i);
            String morseCode = translateToMorse(inputChar);

            // Add animation for each character's Morse code sequence
            for (int x = 0; x < morseCode.length(); x++) {
                char symbol = morseCode.charAt(x);

                // Timeline for the current symbol
                Timeline symbolTimeline = new Timeline();

                // Update labels at the start of the sequence
                KeyFrame updateLabels = new KeyFrame(Duration.ZERO, event -> {
                    morseLbl.setText(String.valueOf(symbol));
                    letterLbl.setText(String.valueOf(inputChar));
                });

                // Add the color flashing logic
                KeyFrame flashBlack;
                if (symbol == '.') {
                    flashBlack = new KeyFrame(symbolDuration, this::handleOnFinishedBlack);
                } else if (symbol == '-') {
                    flashBlack = new KeyFrame(dashDuration, this::handleOnFinishedBlack);
                } else {
                    continue; // Skip unsupported symbols
                }

                // Add a white background after the symbol
                KeyFrame flashWhite = new KeyFrame(timeBetweenSymbols, this::handleOnFinishedWhite);

                // Add all keyframes to the timeline
                symbolTimeline.getKeyFrames().addAll(updateLabels, flashBlack, flashWhite);

                // Add the timeline to the sequential transition
                sequentialTransition.getChildren().add(symbolTimeline);
            }

            // Add a gap after the character
            Timeline gapTimeline = new Timeline(new KeyFrame(timeBetweenCharacters));
            sequentialTransition.getChildren().add(gapTimeline);
        }

        // Play the sequential transition
        sequentialTransition.play();
    }

    /**
     * Sets backgrounds to specific colours for flash effect
     * @param actionEvent
     */
    private void handleOnFinishedBlack(ActionEvent actionEvent) {
        borderPane.setBackground(Background.fill(Color.BLACK));
    }
    private void handleOnFinishedWhite(ActionEvent actionEvent) {
        borderPane.setBackground(Background.fill(Color.WHITE));
    }
}
