package edu.vanier.morse.controller;

import edu.vanier.morse.MainApp;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransmitController {
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
    public void setScene(Scene scene) {
        mainScene = scene;
    }

    public void stopAnimation() {

    }

    @FXML
    public void initialize() {
        btnReceive.setOnAction(event -> {
            MainApp.switchScene("receive_layout", new ReceiveController());
            logger.info("Loaded the receiving scene...");
        });

        transmitBtn.setOnAction(event -> {

//            System.out.println("Submit Button has been pressed");
//            String inputPointA = txtFieldA.getText();
//            String inputRadius = txtFieldRadius.getText();
//
//            if (isValidPostalCode(inputPointA) && isValidNumber(inputRadius)) {
//                System.out.println("Valid");
//                pointA = postalCodeController.getPostalCodes().get(inputPointA);
//                radius = Double.parseDouble(inputRadius);
//                close();
//            } else {
//                System.out.println("Invalid Inputs");
//
//                //In case of Invalid format, will warn the user
//                final Text actionTarget = new Text();
//                grid.add(actionTarget, 1, 6);
//                actionTarget.setFill(Color.FIREBRICK);
//                actionTarget.setText("INVALID FORMAT");
//            }
//

        });
    }
}
