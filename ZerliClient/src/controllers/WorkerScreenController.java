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
 * @author Evgeny Only user with the Worker role can get to this screen, can
 *         update the catalog.
 */
public class WorkerScreenController {

	   @FXML
	    private Button Logout;

	    @FXML
	    private Text accountType;

	    @FXML
	    private ImageView avatarImg;

	    @FXML
	    private ImageView catalogImage;

	    @FXML
	    private Button surveyAnswers;

	    @FXML
	    private ImageView surveyImg;

	    @FXML
	    private Button updateCatalog;

	    @FXML
	    private Text userName;
	    @FXML
	    private ImageView survImage;

	/**
	 * User can log out of the system and return to the Login screen.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnLogout(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		chat.accept(new Message(MessageType.LOGOUT, LoginScreenController.user));
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LoginScreen.fxml")));
		parent.getStylesheets().add("/css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loginStage.setTitle("Login Screen");
		loginStage.setScene(scene);
		loginStage.show();
		loginStage.centerOnScreen();
	}

	/**
	 * User can update the catalog, items data,catalog type.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnUpdateCatalog(MouseEvent event) throws IOException {
		Parent parent = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/fxml/CatalogUpdate.fxml"))));
		Scene scene = new Scene(parent);
		parent.getStylesheets().add("css/styleNew.css");
		Stage catalogUpdateStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		catalogUpdateStage.setTitle("Catalog Update");
		catalogUpdateStage.setScene(scene);
		catalogUpdateStage.show();
		catalogUpdateStage.centerOnScreen();

	}

	/**
	 *User can fill the customer's answers to the survey and add them to the DB here.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnSurveyAnswers(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerSurvey.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage answerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		answerStage.setTitle("Survey Answers");
		answerStage.setScene(scene);
		answerStage.show();
		answerStage.centerOnScreen();
	}

	/**
	 * Initialization of data on screen.
	 */
	@FXML
	void initialize() {
		this.accountType.setText("Store Worker"); // accountType - may be handled from DB
		this.userName.setText(LoginScreenController.user.getUsername()); // userName
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		Image catalogImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Catalog.png")));
		catalogImage.setImage(catalogImg);
		Image surveyImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/satiComplaint.png")));
		surveyImg.setImage(surveyImage);
		
		
		
	}
}
