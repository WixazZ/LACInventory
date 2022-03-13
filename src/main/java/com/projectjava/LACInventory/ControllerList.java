package com.projectjava.LACInventory;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class it's main controller for display list of product.
 * @see com.projectjava.LACInventory.Controller principal controller class
 */
public class ControllerList extends Controller implements Initializable {

    /**
     * This variable is used to receive list of product and for display it.
     */
    @FXML
    private TableView<Product> tableView;

    /**
     * This variable is used to receive id of product and link to tableView.
     */
    @FXML
    private TableColumn<Product, String> tIdProduct;

    /**
     * This variable is used to receive name of product and link to tableView.
     */
    @FXML
    private TableColumn<Product, String> tNameProduct;

    /**
     * This variable is used to receive cost of product and link to tableView.
     */
    @FXML
    private TableColumn<Product, String> tCostProduct;


    /**
     * This method is used in initialisation of a page.
     * @param url is used to receive url of page.
     * @param resourceBundle is used to receive resourceBundle of page.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Product> products = Application.db.receiveData(); // receive list of product from database

        tIdProduct.setCellValueFactory(new PropertyValueFactory<>("idProduct")); // set id of product in tableView
        tNameProduct.setCellValueFactory(new PropertyValueFactory<>("NameProduct")); // set name of product in tableView
        tCostProduct.setCellValueFactory(new PropertyValueFactory<>("CostProduct")); // set cost of product in tableView

        tableView.setItems(products); // set list of product in tableView
    }
}
