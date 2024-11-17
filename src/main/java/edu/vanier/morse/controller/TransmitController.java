package edu.vanier.morse.controller;

import edu.vanier.morse.MainApp;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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

    private Timeline timeline;

    private static final Map<Character, String> MORSE_CODE_MAP = new HashMap<>();
    static {
        MORSE_CODE_MAP.put('A', ".-");
        MORSE_CODE_MAP.put('B', "-...");
        MORSE_CODE_MAP.put('C', "-.-.");
    }

    public void setScene(Scene scene) {
        this.mainScene = scene;
    }

    public void stopAnimation() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    public boolean isValidText(String inputPointA) {
        return inputPointA != null && !inputPointA.trim().isEmpty();
    }

    public boolean isValidNumber(String inputRadius) {
        if (inputRadius == null || inputRadius.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(inputRadius);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    public void initialize() {
        btnReceive.setOnAction(event -> {
            MainApp.switchScene("receive_layout", new ReceiveController());
            logger.info("Loaded the receiving scene...");
        });

        transmitBtn.setOnAction(event -> {
            logger.info("Transmit Button has been pressed");

            String inputText = msgField.getText();

            if (isValidText(inputText)) {
                logger.info("Valid Inputs: {}", inputText);
                String morseCode = translateToMorse(inputText);
                morseLbl.setText(morseCode);
                letterLbl.setText(inputText);
                startFlashing(morseCode);
            } else {
                logger.warn("Invalid Inputs");

                // Display error message
                msgLabel.setText("INVALID FORMAT");
                msgLabel.setTextFill(Color.FIREBRICK);
            }
        });
    }

    private String translateToMorse(String text) {
        StringBuilder morse = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            if (MORSE_CODE_MAP.containsKey(c)) {
                morse.append(MORSE_CODE_MAP.get(c)).append(" ");
            } else {
                morse.append(" ");
            }
        }
        return morse.toString().trim();
    }

    /**
    private void startFlashing(String morseCode) {
        stopAnimation(); // Stop any existing animations

        timeline = new Timeline();
        Duration timeBetweenSymbols = Duration.millis(500);
        Duration timeBetweenCharacters = Duration.millis(1500);

        double timeCursor = 0;
        for (char symbol : morseCode.toCharArray()) {
            if (symbol == '.') {
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(timeCursor), e -> morseLbl.setStyle("-fx-background-color: black")));
                timeCursor += timeBetweenSymbols.toMillis();
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(timeCursor), e -> morseLbl.setStyle("-fx-background-color: white")));
                timeCursor += timeBetweenSymbols.toMillis();
            } else if (symbol == '-') {
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(timeCursor), e -> morseLbl.setStyle("-fx-background-color: black")));
                timeCursor += 3 * timeBetweenSymbols.toMillis(); // Dash is three times longer than dot
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(timeCursor), e -> morseLbl.setStyle("-fx-background-color: white")));
                timeCursor += timeBetweenSymbols.toMillis();
            } else {
                timeCursor += timeBetweenCharacters.toMillis(); // Space between characters
            }
        }

        timeline.play();
    }
     */
}
