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
 * Only user with the delivery role can get to this screen, user can verify delivery has been accepted, and view delivered orders.
 */
public class DeliveryLoginScreenController {


    @FXML
    private Text AccountType;

    @FXML
    private Button Logout;

    @FXML
    private ImageView accImg;

    @FXML
    private ImageView avatarImg;

    @FXML
    private Text userName;

    @FXML
    private ImageView viewIMG;

	public static Stage acceptStage;

	/**
	 * User verifies that the customer has accepted the order, changes status.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void AcceptOrder(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader
				.load(Objects.requireNonNull(getClass().getResource("/fxml/AcceptDeliveryScreen.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		acceptStage = customerStage;
		customerStage.setTitle("Accept Orders");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}

	/**
	 * User can view delivered orders.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void ViewMyOrder(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader
				.load(Objects.requireNonNull(getClass().getResource("/fxml/DeliverySelfOrders.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Self Delivery Orders");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
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
	 * Initializes data
	 */
	@FXML
	void initialize() {
		
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		Image viewImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Delivery2.png")));
		viewIMG.setImage(viewImage);
		Image accImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Delivery1.png")));
		accImg.setImage(accImage);
	
		userName.setText(LoginScreenController.user.getUsername());
	}
}