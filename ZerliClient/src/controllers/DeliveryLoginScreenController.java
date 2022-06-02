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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DeliveryLoginScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text AccountStatus;

    @FXML
    private Text AccountType;

    @FXML
    private Button ComplaintRep;

    @FXML
    private Button IncomeRep;

    @FXML
    private Button Logout;

    @FXML
    private Text userName;

    @FXML
    void AcceptOrder(MouseEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/AcceptDeliveryScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Accept Orders");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
    }

    @FXML
    void ViewMyOrder(MouseEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/DeliverySelfOrder.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Self Delivery Orders");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
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
    void initialize() {
        assert AccountStatus != null : "fx:id=\"AccountStatus\" was not injected: check your FXML file 'DeliveryLoginScreen.fxml'.";
        assert AccountType != null : "fx:id=\"AccountType\" was not injected: check your FXML file 'DeliveryLoginScreen.fxml'.";
        assert ComplaintRep != null : "fx:id=\"ComplaintRep\" was not injected: check your FXML file 'DeliveryLoginScreen.fxml'.";
        assert IncomeRep != null : "fx:id=\"IncomeRep\" was not injected: check your FXML file 'DeliveryLoginScreen.fxml'.";
        assert Logout != null : "fx:id=\"Logout\" was not injected: check your FXML file 'DeliveryLoginScreen.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'DeliveryLoginScreen.fxml'.";
        userName.setText(LoginScreenController.user.getFirstName());
        AccountStatus.setText("Active");
    }
}