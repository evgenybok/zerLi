package controllers;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import static controllers.IPScreenController.chat;

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

public class CEOScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Text AccountStatus;
    
    @FXML
    private Text UserName;
    
    @FXML
    private URL location;

    @FXML
    private Text AccountType;

    @FXML
    private Button ComplainRep;

    @FXML
    private Button CreatRep;

    @FXML
    private Button IncomRep;

    @FXML
    private Button Logout;

    @FXML
    private Button OrdersRep;

    @FXML
    private Text userName;

    @FXML
    void btnComplaint(MouseEvent event) {

    }

    @FXML
    void btnCreate(MouseEvent event) {

    }

    @FXML
    void btnIncom(MouseEvent event) {

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
    void btnOrders(MouseEvent event) {

    }

    @FXML
    void initialize() {
    	//this.AccountStatus.setText("CONFIRMED"); //accountStatus - need to be handled from DB
    	//this.AccountType.setText("CEO"); //accountType - may be handled from DB
    	//this.UserName.setText(LoginScreenController.user.getUsername()); //userName
    	
        assert AccountType != null : "fx:id=\"AccountType\" was not injected: check your FXML file 'CEOScreenNew.fxml'.";
        assert ComplainRep != null : "fx:id=\"ComplainRep\" was not injected: check your FXML file 'CEOScreenNew.fxml'.";
        assert CreatRep != null : "fx:id=\"CreatRep\" was not injected: check your FXML file 'CEOScreenNew.fxml'.";
        assert IncomRep != null : "fx:id=\"IncomRep\" was not injected: check your FXML file 'CEOScreenNew.fxml'.";
        assert Logout != null : "fx:id=\"Logout\" was not injected: check your FXML file 'CEOScreenNew.fxml'.";
        assert OrdersRep != null : "fx:id=\"OrdersRep\" was not injected: check your FXML file 'CEOScreenNew.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'CEOScreenNew.fxml'.";

    }

}
