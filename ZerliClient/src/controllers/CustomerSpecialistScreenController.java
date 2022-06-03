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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerSpecialistScreenController {

	@FXML
	private Text userName;

	@FXML
	private Text accountType;

	@FXML
	private Button Logout;

	@FXML
	private Button analyseSurvey;

	@FXML
	void btnLogout(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		chat.accept(new Message(MessageType.LOGOUT, LoginScreenController.user));
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LoginScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loginStage.setTitle("Login Screen");
		loginStage.setScene(scene);
		loginStage.show();
		loginStage.centerOnScreen();
	}

    @FXML
    void btnAnalyseSurvey(MouseEvent event) {
    	try {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SPAnalyseSurveyScreen.fxml")));
			Scene scene = new Scene(parent);
			Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			loginStage.setTitle("Analyse Survey");
			loginStage.setScene(scene);
			loginStage.show();
			loginStage.centerOnScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


	@FXML
	void initialize() {
		this.accountType.setText("Customer Service Specialist"); // accountType - may be handled from DB
		this.userName.setText(LoginScreenController.user.getUsername()); // userName
	}
}
