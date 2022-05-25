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

public class CustomerServiceScreenController {

	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button AnsSurvey;

	    @FXML
	    private Button HandelComp;

	    @FXML
	    private Button Logout;

	    @FXML
	    private Button MakeComplaint;

	    @FXML
	    private Text accountType;

	    @FXML
	    private Text accountStatus;
	    
	    @FXML
	    private Text userName;

	    @FXML
	    void btnComp(MouseEvent event) throws IOException {
	    	((Node)event.getSource()).getScene().getWindow().hide();
	 		chat.accept(new Message(MessageType.LOGOUT,LoginScreenController.user));
	    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CreateComplaint.fxml")));
			Scene scene = new Scene(parent);
			Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			loginStage.setTitle("Login Screen");
			loginStage.setScene(scene);
			loginStage.show();
			loginStage.centerOnScreen();
	    }

	    @FXML
	    void btnHandel(MouseEvent event) throws IOException {
	    	((Node)event.getSource()).getScene().getWindow().hide();
	 		chat.accept(new Message(MessageType.LOGOUT,LoginScreenController.user));
	    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/HandelComplaint.fxml")));
			Scene scene = new Scene(parent);
			Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			loginStage.setTitle("Handel Complaint Screen");
			loginStage.setScene(scene);
			loginStage.show();
			loginStage.centerOnScreen();
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
	    void btnSurvey(MouseEvent event) throws IOException {
	    	((Node)event.getSource()).getScene().getWindow().hide();
	 		chat.accept(new Message(MessageType.LOGOUT,LoginScreenController.user));
	    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CreateSurveyScreen.fxml")));
			Scene scene = new Scene(parent);
			Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			loginStage.setTitle("Create Survey Screen");
			loginStage.setScene(scene);
			loginStage.show();
			loginStage.centerOnScreen();
	    }

	    @FXML
	    void initialize() {
	        assert AnsSurvey != null : "fx:id=\"AnsSurvey\" was not injected: check your FXML file 'CustomerService.fxml'.";
	        assert HandelComp != null : "fx:id=\"HandelComp\" was not injected: check your FXML file 'CustomerService.fxml'.";
	        assert Logout != null : "fx:id=\"Logout\" was not injected: check your FXML file 'CustomerService.fxml'.";
	        assert MakeComplaint != null : "fx:id=\"MakeComplaint\" was not injected: check your FXML file 'CustomerService.fxml'.";
	        assert accountType != null : "fx:id=\"accountType\" was not injected: check your FXML file 'CustomerService.fxml'.";
	        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'CustomerService.fxml'.";
	        this.accountStatus.setText("CONFIRMED"); // accountStatus - need to be handled from DB
			this.accountType.setText("Customer Service"); // accountType - may be handled from DB
	    	this.userName.setText(LoginScreenController.user.getUsername()); //userName
	    }

}
