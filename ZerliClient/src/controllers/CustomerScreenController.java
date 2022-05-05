package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import communication.Message;
import communication.MessageType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CustomerScreenController {

    @FXML
    private Button viewCatalog;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button viewOrders;

    @FXML
    private Label lblUserPortal;

    @FXML
    private Label lblStartMsg;

    @FXML
    private Label lblStatus;

    @FXML
    private Button Logout;

    @FXML
    void btnLogout(MouseEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide();
		chat.accept(new Message(MessageType.LOGOUT, null));
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LoginScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loginStage.setTitle("Login Screen");
		loginStage.setScene(scene);
		loginStage.show();
		loginStage.centerOnScreen();  	
    }

    @FXML
    void btnViewOrders(MouseEvent event) throws IOException {

    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/OrderScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage orderStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		orderStage.setTitle("Order Screen");
		orderStage.setScene(scene);
		orderStage.show();
		orderStage.centerOnScreen();  
    }

    @FXML
    void btnViewCatalog(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CatalogTypeScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage catalogStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		catalogStage.setTitle("Catalog Type Screen");
		catalogStage.setScene(scene);
		catalogStage.show();
		catalogStage.centerOnScreen();
    }

    @FXML
    void initialize() {
        assert viewOrders != null : "fx:id=\"viewOrders\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert viewCatalog != null : "fx:id=\"viewCatalog\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert lblUserPortal != null : "fx:id=\"lblUserPortal\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert lblStartMsg != null : "fx:id=\"lblStartMsg\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert Logout != null : "fx:id=\"Logout\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert lblStatus != null : "fx:id=\"lblStatus\" was not injected: check your FXML file 'CustomerScreen.fxml'.";

    }
}
