package com.projectjava.LACInventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller class for delete item screen extending the controller class
 * @see com.projectjava.LACInventory.Controller class for more information
 */
public class ControllerDelete extends Controller {
    /**
     * TextField for item ID
     */
    @FXML
    private TextField idDelete;

    /**
     * Method to delete an item from the database
     * @param event gets the information of the scene
     */
    @FXML
    private void deleteProduct(ActionEvent event) { // delete product
        if(idDelete.getText().isEmpty()) { // if idInput is empty
            showAlert(Alert.AlertType.ERROR,"Form Error!", "Please enter your product id"); // show alert if idInput is empty
            return;
        }
        if (!secureIdInput(idDelete.getText())){
            showAlert(Alert.AlertType.ERROR,"Form Error!", "Please enter a valid product id"); // show alert if idInput is not a number
            return;
        }

        if (!Application.db.checkIdProduct(idDelete.getText())){
            showAlert(Alert.AlertType.ERROR,"Error!", "The product not exist in database"); // show alert if idInput is not a number
            return;
        }

        boolean isTry =  Application.db.deleteProduct(idDelete.getText()); // delete product from database and return boolean if it was successful

        if (isTry) { // if it was successful show alert "Successful!" and go to main screen else show alert "Error"
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful!", "Product " + idDelete.getText() + " has been deleted"); // if product has been deleted
            try { // try to go to main screen
                changeScreenMenu(event); // change screen to menu scene
            } catch (IOException e) { // catch exception if it was not successful
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.ERROR,"Form Error!", "There is a problem with database, please try again later"); // if product has not been deleted because the database is not available
        }
    }

}