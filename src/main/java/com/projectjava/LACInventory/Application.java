package com.projectjava.LACInventory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * principal class to launch the application.
 * @author AlexisParadiso LioraChemla CeliaMilano
 * @version 1.0
 */
public class Application extends javafx.application.Application {
    /**
     * created database object
     */
    public static Database db; // create database;

    /**
     * Override start method to load the main window
     * @param stage the stage of the application
     * @throws IOException if the fxml file is not found
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("home-view.fxml")); // Load the fxml file
        Scene scene = new Scene(fxmlLoader.load(), 600, 400); // Create a new scene with the loaded fxml file
        stage.setTitle("L.A.C Inventory"); // Set the stage title
        stage.setScene(scene); // Set the scene to the stage
        stage.setResizable(false); // Set the stage to not be resizable
        db=new Database();
        stage.show(); // Show the stage
    }

    /**
     * Main method to launch the application
     * @param args the arguments on the main method
     */
    public static void main(String[] args) {
        launch();
    } // Main method
}