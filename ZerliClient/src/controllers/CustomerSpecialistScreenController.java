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
 *Only user with the customer specialist role can get to this screen, user can analyse existing surveys.
 */
public class CustomerSpecialistScreenController {

    @FXML
    private Button Logout;

    @FXML
    private Text accountType;

    @FXML
    private ImageView anaImg;

    @FXML
    private Button analyseSurvey;

    @FXML
    private ImageView avatarImg;

    @FXML
    private Text userName;


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
     * New screen where user can analyse survey.
     * @param event
     */
    @FXML
    void btnAnalyseSurvey(MouseEvent event) {
    	try {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SPAnalyseSurveyScreen.fxml")));
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage surveyScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
			surveyScreen.setTitle("Analyse Survey");
			surveyScreen.setScene(scene);
			surveyScreen.show();
			surveyScreen.centerOnScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


	/**
	 * Initializes data
	 */
	@FXML
	void initialize() {
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		Image anaImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/orders.png")));
		anaImg.setImage(anaImage);
		this.accountType.setText("Customer Service Specialist"); // accountType - may be handled from DB
		this.userName.setText(LoginScreenController.user.getUsername()); // userName
	}
}
