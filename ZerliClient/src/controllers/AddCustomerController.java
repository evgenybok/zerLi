package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Add;

    @FXML
    private Button Back;

    @FXML
    private Text accountType;

    @FXML
    private Text userName;

    @FXML
    void btnAdd(MouseEvent event) {

    }

    @FXML
    void btnBack(MouseEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/branchManager.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
    }

    @FXML
    void initialize() {
        assert Add != null : "fx:id=\"Add\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";
        assert accountType != null : "fx:id=\"accountType\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'AddNewCustomer.fxml'.";

    }

}
