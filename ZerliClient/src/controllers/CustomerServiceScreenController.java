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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Evgeny
 * Only user with the customer service role can get to this screen, user can create a new complaint,handle existing complaint and create a survey.
 */
public class CustomerServiceScreenController {

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
	private ImageView avatarImg;

	@FXML
	private ImageView handImg;

	@FXML
	private ImageView satiImg;

	@FXML
	private Text userName;


	@FXML
	private ImageView writeImg;

    /**
     * moves to a new screen where the user can create a new complaint after recieving it from the customer.
     * @param event
     * @throws IOException
     */
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

    /**
     * Opens a new screen where the user can handle existing complaint.
     * @param event
     * @throws IOException
     */
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

    /**
     * Logs the user out of the system and opens the Login screen.
     * @param event
     * @throws IOException
     */
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

    /**
     * New screen where the user can create a new survey.
     * @param event
     * @throws IOException
     */
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

	/**
	 * Initializes data shown on screen
	 */
	@FXML
	void initialize() {
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		Image writeImage = new Image(
				Objects.requireNonNull(getClass().getResourceAsStream("/images/writeComplaint.png")));
		writeImg.setImage(writeImage);
		Image satiCompImage = new Image(
				Objects.requireNonNull(getClass().getResourceAsStream("/images/satiComplaint.png")));
		satiImg.setImage(satiCompImage);
		Image handleImage = new Image(
				Objects.requireNonNull(getClass().getResourceAsStream("/images/handleComplaint.png")));
		handImg.setImage(handleImage);
		this.accountType.setText("Customer Service"); // accountType
		this.userName.setText(LoginScreenController.user.getUsername()); // userName
	}

}
