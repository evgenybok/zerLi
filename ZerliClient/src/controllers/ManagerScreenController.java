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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ManagerScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button EditInfo;

    @FXML
    private Button Logout;

    @FXML
    private Text accountType;

    @FXML
    private Button addCustomer;

    @FXML
    private Text userName;

    @FXML
    private Button viewOrders;

    @FXML
    private Button viewReports;

    @FXML
    void btnAdd(MouseEvent event) {

    }

    @FXML
    void btnEdit(MouseEvent event) {

    }

    @FXML
    void btnLogout(MouseEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide();
 		chat.accept(new Message(MessageType.LOGOUT,LoginScreenController.user));
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
   
    }

    @FXML
    void btnViewReports(MouseEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide();
 		chat.accept(new Message(MessageType.LOGOUT,LoginScreenController.user));
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/BranchManagerReports.fxml")));
		Scene scene = new Scene(parent);
		Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loginStage.setTitle("Reports Screen");
		loginStage.setScene(scene);
		loginStage.show();
		loginStage.centerOnScreen();
    }

    @FXML
    void initialize() {
        assert EditInfo != null : "fx:id=\"EditInfo\" was not injected: check your FXML file 'branchManager.fxml'.";
        assert Logout != null : "fx:id=\"Logout\" was not injected: check your FXML file 'branchManager.fxml'.";
        assert accountType != null : "fx:id=\"accountType\" was not injected: check your FXML file 'branchManager.fxml'.";
        assert addCustomer != null : "fx:id=\"addCustomer\" was not injected: check your FXML file 'branchManager.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'branchManager.fxml'.";
        assert viewOrders != null : "fx:id=\"viewOrders\" was not injected: check your FXML file 'branchManager.fxml'.";
        assert viewReports != null : "fx:id=\"viewReports\" was not injected: check your FXML file 'branchManager.fxml'.";

    }

}
