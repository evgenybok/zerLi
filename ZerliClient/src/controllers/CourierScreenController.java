package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.Objects;

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

public class CourierScreenController {
    @FXML
    private ImageView CourierScreenImage;
    
    @FXML
    private Button updateOrderStatus;

    @FXML
    private Label lblUserPortal;

    @FXML
    private Label lblStartMsg;

    @FXML
    private Text userName;

    @FXML
    private Text accountStatus;

    @FXML
    private Button Logout;

    @FXML
    private Label lblStatus;

    @FXML
    private Text accountType;

    @FXML
    void btnLogout(MouseEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide();
        StringBuilder login = new StringBuilder();
        String truelogin = null;
        login.append(LoginScreenController.username);
        login.append("@");
        login.append(LoginScreenController.password);
        truelogin = login.toString();

		chat.accept(new Message(MessageType.LOGOUT, truelogin));
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LoginScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loginStage.setTitle("Login Screen");
		loginStage.setScene(scene);
		loginStage.show();
		loginStage.centerOnScreen();

    }

    @FXML
    void btnUpdateOrderStatus(MouseEvent event) {

    }
    
    @FXML
    void initialize() {
      	this.accountStatus.setText("CONFIRMED"); //accountStatus - need to be handled from DB
    	this.accountType.setText("Courier"); //accountType - may be handled from DB
    	this.userName.setText(LoginScreenController.username); //userName
    	
        Image homeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/HomeScreen.jpeg")));
        CourierScreenImage.setImage(homeImage);
    	
        assert CourierScreenImage != null : "fx:id=\"CourierScreenImage\" was not injected: check your FXML file 'CourierScreen.fxml'.";
        assert updateOrderStatus != null : "fx:id=\"updateOrderStatus\" was not injected: check your FXML file 'CourierScreen.fxml'.";
        assert lblUserPortal != null : "fx:id=\"lblUserPortal\" was not injected: check your FXML file 'CourierScreen.fxml'.";
        assert lblStartMsg != null : "fx:id=\"lblStartMsg\" was not injected: check your FXML file 'CourierScreen.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'CourierScreen.fxml'.";
        assert accountStatus != null : "fx:id=\"accountStatus\" was not injected: check your FXML file 'CourierScreen.fxml'.";
        assert Logout != null : "fx:id=\"Logout\" was not injected: check your FXML file 'CourierScreen.fxml'.";
        assert lblStatus != null : "fx:id=\"lblStatus\" was not injected: check your FXML file 'CourierScreen.fxml'.";
        assert accountType != null : "fx:id=\"accountType\" was not injected: check your FXML file 'CourierScreen.fxml'.";


    }

}
