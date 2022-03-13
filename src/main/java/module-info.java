/**
 * Information about the module and which library it uses.
 */
module com.projectjava.LACInventory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.projectjava.LACInventory to javafx.fxml;
    exports com.projectjava.LACInventory;
}