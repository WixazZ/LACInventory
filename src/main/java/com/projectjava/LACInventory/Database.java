package com.projectjava.LACInventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;

/**
 * Database class for the LAC Inventory application.
 * Contain all the methods for operation to the database.
 * @see com.projectjava.LACInventory.Controller This class was defined in the Controller class.
 */
public class Database {

    /**
     * Connection object to the database.
     */
    public Connection con = null; // Connection object

    /**
     * Statement object to the database.
     */
    private Statement stmt = null; // Statement object

    /**
     * Boolean to check if the connection was successful
     */
    private boolean success; // Boolean to check if the connection was successful

    /**
     * Constructor for the Database class.
     * There is try to connect to the database. If the connection is successful, the connection is stored in the Connection object,
     * the Statement object is stored in the Statement object and success is set to true.
     */
    public Database() {
        try {
            String url = "jdbc:mysql://localhost:3306/"; // URL for the database
            con = DriverManager.getConnection(url,"root ","alexpara"); // Connection object PASSWORD and USERNAME to be modified
            stmt = con.createStatement(); // Statement object
            setSuccess(true); // Set success to true
        } catch (SQLException e) { // If the connection is not successful
            e.printStackTrace(); // Print the error
            setSuccess(false); // Set success to false
            Controller.showAlert(Alert.AlertType.ERROR, "Error", "Error connecting to the database. Please try again later or contact the administrator."); // Show error message
        }
    }

    /**
     * Method try to add a new product to the database.
     * @param idProduct String value of the idProduct.
     * @param nameProduct String value of the nameProduct.
     * @param costProduct Float value of the costProduct.
     * @return Boolean value of the success of the operation.
     */
    public boolean addData(String idProduct, String nameProduct, float costProduct){
        try{
            String sql = "INSERT INTO Poduct.Product (idProduct, nameProduct, costProduct)VALUES ( '" + idProduct + "', '"+ nameProduct +"', "+ costProduct +")"; // SQL query
            this.stmt.executeUpdate(sql); // Execute the query
            return true; // Return true
        } catch (SQLException e) { // If the query is not successful
            e.printStackTrace(); // Print the error
            return false;  // Return false
        }
    }

    /**
     * Set the success variable to the given value.
     * @param success Boolean value to set the success variable to.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Get the success variable.
     * @return Boolean value of the success variable.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Method try to delete a product from the database.
     * @param idProduct String value of the idProduct.
     * @return Boolean value of the success of the operation.
     */
    public boolean deleteProduct(String idProduct){
        try{ // Try to delete the product
            String sql = "DELETE FROM Poduct.Product WHERE idProduct = '"+ idProduct +"'"; // SQL query
            this.stmt.executeUpdate(sql); // Execute the query
            return true; // Return true
        } catch (SQLException e) { // If the query is not successful
            e.printStackTrace(); // Print the error
            return false; // Return false
        }
    }

    /**
     * Method try to check a product in the database.
     * @param idProduct String value of the idProduct.
     * @return Boolean value of the success of the operation.
     */
    public Boolean checkIdProduct(String idProduct){
        String sql = "SELECT * FROM Poduct.Product WHERE idProduct = '"+ idProduct +"'";
        try{
            ResultSet res = this.stmt.executeQuery(sql); // ResultSet object
            if (res.next()){ // If the result is not empty
                return true; // Return true
            }
        } catch (SQLException e) { // If the result is empty
            e.printStackTrace();
            return false; // Return false
        }
        return false;
    }

    /**
     * Method try to receive data of the database.
     * @return ObservableList of Product value, it's the list of the products of the database.
     */
    public ObservableList<Product> receiveData(){
        String sql = "SELECT * FROM Poduct.Product"; // SQL query
        ObservableList<Product> data = FXCollections.observableArrayList(); // ObservableList object to store the data
        try{
            ResultSet res = this.stmt.executeQuery(sql); // ResultSet object
            while (res.next()){ // If the result is not empty
                data.add(new Product(res.getString("idProduct"), res.getString("nameProduct"), res.getDouble("costProduct"))); // Add the product to the array list
            }
        } catch (SQLException e) { // If the result is empty
            e.printStackTrace();
        }
        return data;
    }
}
