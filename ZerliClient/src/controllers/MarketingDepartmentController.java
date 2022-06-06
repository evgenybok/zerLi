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

/**
 * @author Evgeny Only user with the Marketing role can get to this screen, can
 *         view various reports and create a new report.
 */
public class MarketingDepartmentController {

	@FXML
	private Button Logout;

	@FXML
	private Text accountStatus;

	@FXML
	private Text accountType;

	@FXML
	private ImageView avatarImage;

	@FXML
	private ImageView catalogImage;

	@FXML
	private Button updateCatalog;

	@FXML
	private Text userName;

	@FXML
	private Label accTypeScreen;

	/**
	 * Logs the user out of the system and opens the login screen
	 * 
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
	 * Allows the marketing worker to update the catalog and put items on sale/
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
	 * Data initialization on screen
	 */
	@FXML
	void initialize() {
		this.userName.setText(LoginScreenController.user.getUsername()); // userName
		if (LoginScreenController.user.getRole().equals("worker")) {
			this.accountType.setText("Store Worker"); // accountType
			accTypeScreen.setText("Store Worker Screen");
		} else {
			accTypeScreen.setText("Marketing Department Screen");
			this.accountType.setText("Marketing Department"); // accountType
		}
		Image catalogImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/salee.png")));
		catalogImage.setImage(catalogImg);
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImage.setImage(personImage);

	}
}
