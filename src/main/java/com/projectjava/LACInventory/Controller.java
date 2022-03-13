package com.projectjava.LACInventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class which allows to make the link between the user interface and the backend
 * @see com.projectjava.LACInventory.ControllerAdd Controller class which allows to cadd a new item
 * @see com.projectjava.LACInventory.ControllerDelete Controller class which allows to delete an item
 */
public class Controller{ // Controller class for the main menu

    /**
     * This method is called when the user clicks the "back" button to return to the home.
     * @param event gets the information of the scene
     * @throws IOException if the fxml file is not found
     */
    @FXML
    protected void changeScreenHome(ActionEvent event) throws IOException { // change to home scene
        FXMLLoader fmxHome = new FXMLLoader(Application.class.getResource("home-view.fxml")); // load home scene
        Scene sceneHome = new Scene(fmxHome.load()); // create scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get stage
        stage.setScene(sceneHome); // set scene
        stage.show(); // show scene
    }

    /**
     * This method is called when the user clicks the "start" button on the home scene
     * or "back" button to return to the menu.
     * @param event gets the information of the scene
     * @throws IOException if the fxml file is not found
     */
    @FXML
    protected void changeScreenMenu(ActionEvent event) throws IOException { // change to menu scene
        FXMLLoader fmxMenu = new FXMLLoader(Application.class.getResource("menu-view.fxml")); // load menu scene
        Scene sceneMenu = new Scene(fmxMenu.load()); // create scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get stage
        stage.setScene(sceneMenu); // set scene
        stage.show(); // show scene
    }

    /**
     * This method is called when the user clicks the "Add" button on the menu scene
     * if database is connected else show alert
     * @param event gets the information of the scene
     * @throws IOException if the fxml file is not found
     */
    @FXML
    protected void changeScreenAdd(ActionEvent event) throws IOException { // change to add scene
        if (Application.db.isSuccess()) { // if database is connected
            FXMLLoader fmxAdd = new FXMLLoader(Application.class.getResource("add-view.fxml")); // load add scene
            Scene sceneAdd = new Scene(fmxAdd.load()); // create scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get stage
            stage.setScene(sceneAdd); // set scene
            stage.show(); // show scene
        } else { // if database is not connected
            showAlert(Alert.AlertType.ERROR, "Error", "Error connecting to the database. Please try again later or contact the administrator."); // show alert
        }
    }

    /**
     * This method is called when the user clicks the "Delete" button on the menu scene
     * if database is connected else show alert
     * @param event gets the information of the scene
     * @throws IOException if the fxml file is not found
     */
    @FXML
    protected void changeScreenDelete(ActionEvent event) throws IOException {
        if (Application.db.isSuccess()) { // if database is connected
            FXMLLoader fmxAdd = new FXMLLoader(Application.class.getResource("delete-view.fxml")); // load delete scene
            Scene sceneAdd = new Scene(fmxAdd.load()); // create scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get stage
            stage.setScene(sceneAdd); // set scene
            stage.show(); // show scene
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Error connecting to the database. Please try again later or contact the administrator."); // show alert
        }
    }

    /**
     * This method is called when the user clicks the "List" button on the menu scene
     * if database is connected else show alert
     * @param event gets the information of the scene
     * @throws IOException if the fxml file is not found
     */
    @FXML
    protected void changeScreenList(ActionEvent event) throws IOException {
        if (Application.db.isSuccess()) { // if database is connected
            FXMLLoader fmxAdd = new FXMLLoader(Application.class.getResource("list-view.fxml")); // load delete scene
            Scene sceneList = new Scene(fmxAdd.load()); // create scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // get stage
            stage.setScene(sceneList); // set scene
            stage.show(); // show scene
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Error connecting to the database. Please try again later or contact the administrator."); // show alert
        }
    }

    /**
     * This method is called when the user add data / remove data from the database,
     * if there is an error in the database or if the user not respect the format of data.
     * @param alertType the type of alert
     * @param title the title of the alert
     * @param message  the message of the alert
     */
    public static void showAlert(Alert.AlertType alertType, String title, String message) { // show alert
        Alert alert = new Alert(alertType); // create alert
        alert.setTitle(title); // set title
        alert.setHeaderText(null); // set header
        alert.setContentText(message); // set content
        alert.showAndWait().ifPresent(response -> {}); // show alert and wait for response
    }

    /**
     * Method for check with regex if idInput is a number
     * @param idInput product id enter by user
     * @return true if idInput is a number
     */
    public Boolean secureIdInput(String idInput) {
        return idInput.matches("^[0-9]+$"); // regex for check if idInput is a number
    }

}