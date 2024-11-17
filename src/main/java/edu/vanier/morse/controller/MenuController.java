package edu.vanier.morse.controller;

import edu.vanier.morse.MainApp;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuController {
    private final static Logger logger = LoggerFactory.getLogger(MenuController.class);
    private Scene mainScene;
    @FXML
    Button btnTransmit;
    @FXML
    Button btnReceive;
    @FXML
    ImageView imgViewPresentation;
    public void setScene(Scene scene) {
        mainScene = scene;
    }
    @FXML
    public void initialize() {
        // Load an image from the resources folder using getClass().getResource()
        Image material = new Image(getClass().getResource("/images/material.png").toExternalForm());

        // Create an ImageView to display the image
        imgViewPresentation.setImage(material);

        // Get Size of the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = screenBounds.getWidth();
        double height = screenBounds.getHeight();


        // Set the size of the image
        imgViewPresentation.setFitWidth(width);
        imgViewPresentation.setFitHeight(height);

        btnTransmit.setOnAction(event -> {
            MainApp.switchScene("transmit_layout", new TransmitController());
            logger.info("Loaded the transmitting scene...");
        });
        btnReceive.setOnAction(event -> {
            MainApp.switchScene("receive_layout", new ReceiveController());
            logger.info("Loaded the receiving scene...");
        });

    }
}
