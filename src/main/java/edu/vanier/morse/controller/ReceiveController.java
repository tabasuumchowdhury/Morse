package edu.vanier.morse.controller;

import edu.vanier.morse.MainApp;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReceiveController {
    private final static Logger logger = LoggerFactory.getLogger(TransmitController.class);
    private Scene mainScene;
    @FXML
    Button btnTransmit;
    @FXML
    Button btnReceive;
    @FXML
    Button transmitBtn;
    @FXML
    Label morseLbl;
    @FXML
    Label letterLbl;
    @FXML
    Label msgLabel;
    @FXML
    TextField msgField;
    @FXML
    ImageView imageViewLogo;
    @FXML
    Slider sensSlider;
    @FXML
    Label txtLabel;
    @FXML
    TextField txtField;

    @FXML
    Label mrsLabel;
    @FXML
    TextField mrsField;

    @FXML
    Label fpsLabel;
    @FXML
    TextField fpsField;

    public void setScene(Scene scene) {
        mainScene = scene;
    }

    public void stopAnimation() {

    }

    @FXML
    public void initialize() {

        btnTransmit.setOnAction(event -> {
            MainApp.switchScene("transmit_layout", new TransmitController());
            logger.info("Loaded the transmitting scene...");
        });
    }

}
