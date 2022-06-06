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

/**
 * @author Evgeny
 * CEO main screen, only accessed by the user with the CEO role
 * can view various reports and compare them
 */
public class CEOScreenController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Text userName;

	@FXML
	private Text AccountType;

	@FXML
	private Text AccountStatus;

	@FXML
	private Button Logout;

	@FXML
	private Button IncomeRep;

	@FXML
	private Button OrdersRep;

	@FXML
	private Button ComplaintRep;

	@FXML
	private Button CreateRep;

	/**
	 * Opens the complaint report screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnComplaint(MouseEvent event) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CEOComplaintScreen.fxml")));
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

			AccountStatus.setText("CONFIRMED");
			userName.setText(LoginScreenController.user.getUsername());

		} catch (NullPointerException e) {
		};
	}

}
