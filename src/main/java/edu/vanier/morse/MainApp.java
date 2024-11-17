package edu.vanier.morse;

import edu.vanier.morse.controller.MenuController;
import edu.vanier.morse.controller.TransmitController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.logging.Level;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * This is a JavaFX project template to be used for creating GUI applications.
 * JavaFX 20.0.2 is already linked to this project in the build.gradle file.
 * @link: https://openjfx.io/javadoc/22/
 * @see: Build Scripts/build.gradle
 * @author Frostybee.
 */
public class MainApp extends Application {
    private final static Logger logger = LoggerFactory.getLogger(MainApp.class);
    MenuController controller;
    private static Scene scene;

    public static void main(String[] args) {
        System.out.println("Hello there!");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            logger.info("Bootstrapping the application...");
            //-- 1) Load the scene graph from the specified FXML file and
            // associate it with its FXML controller.
            Parent root = loadFXML("menu_layout", new MenuController());

            //-- 2) Create and set the scene to the stage.
            scene = new Scene(root, 960, 540);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tutorial!");
            primaryStage.sizeToScene();
            primaryStage.setAlwaysOnTop(true);
            primaryStage.show();
            primaryStage.setAlwaysOnTop(false);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * Changes the primary stage's current scene.
     *
     * @param fxmlFile The name of the FXML file to be loaded.
     * @param fxmlController An instance of the FXML controller to be associated
     * with the loaded FXML scene graph.
     */
    public static void switchScene(String fxmlFile, Object fxmlController) {
        try {
            scene.setRoot(loadFXML(fxmlFile, fxmlController));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Loads a scene graph from an FXML file.
     *
     * @param fxmlFile The name of the FXML file to be loaded.
     * @param fxmlController An instance of the FXML controller to be associated
     * with the loaded FXML scene graph.
     * @return The root node of the loaded scene graph.
     * @throws IOException
     */
    public static Parent loadFXML(String fxmlFile, Object fxmlController) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/" + fxmlFile + ".fxml"));
        fxmlLoader.setController(fxmlController);
        return fxmlLoader.load();
    }

}
