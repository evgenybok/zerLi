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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateComplaintController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back;

    @FXML
    private TextField Description;

    @FXML
    private TextField OrderId;

    @FXML
    private Button Submit;

    @FXML
    private TextField UserId;

    @FXML
    private Text accountStatus;

    @FXML
    private Text accountType;

    @FXML
    private Text userName;

    @FXML
    void btnBack(MouseEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide();
 		chat.accept(new Message(MessageType.LOGOUT,LoginScreenController.user));
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerService.fxml")));
		Scene scene = new Scene(parent);
		Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loginStage.setTitle("Create Complaint Screen");
		loginStage.setScene(scene);
		loginStage.show();
		loginStage.centerOnScreen();
    }

    @FXML
    void btnSubmit(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'CreateComplaint.fxml'.";
        assert Description != null : "fx:id=\"Description\" was not injected: check your FXML file 'CreateComplaint.fxml'.";
        assert OrderId != null : "fx:id=\"OrderId\" was not injected: check your FXML file 'CreateComplaint.fxml'.";
        assert Submit != null : "fx:id=\"Submit\" was not injected: check your FXML file 'CreateComplaint.fxml'.";
        assert UserId != null : "fx:id=\"UserId\" was not injected: check your FXML file 'CreateComplaint.fxml'.";
        assert accountStatus != null : "fx:id=\"accountStatus\" was not injected: check your FXML file 'CreateComplaint.fxml'.";
        assert accountType != null : "fx:id=\"accountType\" was not injected: check your FXML file 'CreateComplaint.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'CreateComplaint.fxml'.";
        this.accountStatus.setText("CONFIRMED"); // accountStatus - need to be handled from DB
     		this.accountType.setText("Customer Service"); // accountType - may be handled from DB
         	this.userName.setText(LoginScreenController.user.getUsername()); //userName
    }

}
