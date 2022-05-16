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

public class WorkerScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView WrokerScreenImage;

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
    private Label accountType;

    @FXML
    private Button updateCatalog;

    @FXML
    private Button itemsOnSale;

    @FXML
    void btnItemsOnSale(MouseEvent event) {

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
    void btnUpdateCatalog(MouseEvent event) {

    }

    @FXML
    void initialize() {
    	this.accountStatus.setText("CONFIRMED"); //accountStatus - need to be handled from DB
    	this.accountType.setText("Store Worker"); //accountType - may be handled from DB
    	this.userName.setText(LoginScreenController.user.getUsername()); //userName
    	
        Image homeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/HomeScreen.jpeg")));
        WrokerScreenImage.setImage(homeImage);
    }
}
