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
		((Node) event.getSource()).getScene().getWindow().hide();
		chat.accept(new Message(MessageType.LOGOUT, LoginScreenController.user));
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CreateComplaint.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage complaintScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
		complaintScreen.setTitle("Create Complaint Screen");
		complaintScreen.setScene(scene);
		complaintScreen.show();
		complaintScreen.centerOnScreen();
	}

	@FXML
	void btnHandel(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		chat.accept(new Message(MessageType.LOGOUT, LoginScreenController.user));
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/HandelComplaint.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage handleScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
		handleScreen.setTitle("Handle Complaint Screen");
		handleScreen.setScene(scene);
		handleScreen.show();
		handleScreen.centerOnScreen();
	}

	@FXML
	void btnLogout(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		chat.accept(new Message(MessageType.LOGOUT, LoginScreenController.user));
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LoginScreen.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loginStage.setTitle("Login Screen");
		loginStage.setScene(scene);
		loginStage.show();
		loginStage.centerOnScreen();
	}

	@FXML
	void btnSurvey(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		chat.accept(new Message(MessageType.LOGOUT, LoginScreenController.user));
		Parent parent = FXMLLoader
				.load(Objects.requireNonNull(getClass().getResource("/fxml/CreateSurveyScreen.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage surveyScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
		surveyScreen.setTitle("Create Survey Screen");
		surveyScreen.setScene(scene);
		surveyScreen.show();
		surveyScreen.centerOnScreen();
	}

	@FXML
	void initialize() {
		this.accountType.setText("Customer Service"); // accountType
		this.userName.setText(LoginScreenController.user.getUsername()); // userName
	}

}
