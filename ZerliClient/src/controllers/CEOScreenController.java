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
 * CEO main screen, only accessed by the user with the CEO role
 * can view various reports and compare them
 */
public class CEOScreenController {

	@FXML
	private Text AccountType;

	@FXML
	private Button ComplaintRep;

	@FXML
	private Button IncomeRep;

	@FXML
	private Button Logout;

	@FXML
	private Button OrdersRep;

	@FXML
	private ImageView avatarImg;

	@FXML
	private ImageView complaintImg;

	@FXML
	private ImageView incomeImg;

	@FXML
	private ImageView ordersImg;

	@FXML
	private Text userName;

	/**
	 * Opens the complaint report screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnComplaint(MouseEvent event) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader
				.load(Objects.requireNonNull(getClass().getResource("/fxml/CEOComplaintScreen.fxml")));
		parent.getStylesheets().add("/css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();

	}

	@FXML
	void btnCreate(MouseEvent event) {

	}

	/**
	 * Opens the income report screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnIncome(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CEOIncomeScreen.fxml")));
		parent.getStylesheets().add("/css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}

	/**
	 * Logs the user out of the system and opens the login screen
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
	 * Opens the order report screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnOrders(MouseEvent event) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CEOOrderScreen.fxml")));
		parent.getStylesheets().add("/css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();

	}

	/**
	 * Initializes data shown on screen
	 */
	@FXML
	void initialize() {
		try {
			userName.setText(LoginScreenController.user.getUsername());
			Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
			avatarImg.setImage(personImage);
			Image ordersImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/report-icon-13315.png")));
			ordersImg.setImage(ordersImage);
			Image incomeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/225-2259206_mansfield-sales-order-sku-transparent-background-report-icon.png")));
			incomeImg.setImage(incomeImage);
			Image complaintImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/reports1.jpeg")));
			complaintImg.setImage(complaintImage);
		} catch (NullPointerException e) {
		}
		;
	}

	//
}
