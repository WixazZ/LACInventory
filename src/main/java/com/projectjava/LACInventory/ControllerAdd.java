package com.projectjava.LACInventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller class for the add item screen extending the controller class
 * @see com.projectjava.LACInventory.Controller class
 */
public class ControllerAdd extends Controller {

    /**
     * receive product id enter by user in text field
     */
    @FXML
    private TextField idProduct;

    /**
     * receive product name enter by user in text field
     */
    @FXML
    private TextField nameProduct;

    /**
     * receive product price enter by user in text field
     */
    @FXML
    private TextField costProduct;


    /**
     * Method for check with regex if nameInput is a string of letters
     * @param nameInput name enter by user
     * @return true if nameInput is a string and respect mobile regex
     */
    private Boolean secureNameInput(String nameInput) {
        return nameInput.matches("^[a-zA-Z\\s]+$"); // regex for check if nameInput is a string of letters
    }

    /**
     * Method for check with regex if costInput is a float number
     * @param costInput cost enter by user
     * @return true if costInput is a float number
     */
    private Boolean secureCostInput(String costInput) {
        return costInput.matches("^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$"); // regex for check if costInput is a float number
    }

    /**
     * Method for check data entered by user with the call of the method secureIdInput, secureNameInput and secureCostInput
     *
     * @param event gets the information of the scene
     */
    @FXML
    public void handle(ActionEvent event) {
        if(idProduct.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,"Form Error!", "Please enter your product id"); // if idProduct is empty show alert
            return;
        }
        if (!secureIdInput(idProduct.getText())){
            showAlert(Alert.AlertType.ERROR,"Form Error!", "Please enter a valid product id"); // if idProduct is not a number show alert
            return;
        }
        if(nameProduct.getText().isEmpty()) { // if nameInput.getText() is empty
            showAlert(Alert.AlertType.ERROR,"Form Error!", "Please enter a product name"); // if nameInput is empty show alert
            return;
        }
        if (!secureNameInput(nameProduct.getText())){
            showAlert(Alert.AlertType.ERROR,"Form Error!", "Please enter a valid product name"); // if nameInput is not a string of letters show alert
        }
        if(costProduct.getText().isEmpty()) { // if costInput.getText() is empty
            showAlert(Alert.AlertType.ERROR,"Form Error!", "Please enter a product cost"); // if costInput is empty show alert
            return;
        }
        if (!secureCostInput(costProduct.getText())){ // if costInput.getText() is not a number
            showAlert(Alert.AlertType.ERROR,"Form Error!", "Please enter a valid product cost"); // if costInput is not a number show alert
            return;
        }

        if(Application.db.checkIdProduct(idProduct.getText())){
            showAlert(Alert.AlertType.ERROR,"Error!", "The product already exist in database"); // if idProduct already exist in database show alert
            return;
        }

        boolean isTry = Application.db.addData(idProduct.getText(), nameProduct.getText(), Float.parseFloat(costProduct.getText())); // call method addData from class Database
        if (isTry) {
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful!", "Product " + nameProduct.getText() + " has been added"); // if isTry is true show alert
            try {
                changeScreenMenu(event);
            } catch (IOException e) { // catch exception
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.ERROR,"Form Error!", "There is a problem with database, please try again later"); // if isTry is false show alert
        }
    }
}





