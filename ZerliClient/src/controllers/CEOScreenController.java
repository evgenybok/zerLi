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

	@FXML
	void btnComplaint(MouseEvent event) {

	}

	@FXML
	void btnCreate(MouseEvent event) {

	}

	@FXML
	void btnIncome(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/IncomeReport.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}

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
	void btnOrders(MouseEvent event) {

	}

	@FXML
	void initialize() {
		try {
			/*chat.accept(new Message(MessageType.GET_ACCOUNT_DETAILS, LoginScreenController.user.getID()));
			@SuppressWarnings("unchecked")
			ArrayList<String> account = (ArrayList<String>) AnalyzeMessageFromServer.getData();
			AccountStatus.setText(account.get(5)); // *** AccountStatus text field is null!!*/
			AccountStatus.setText("CONFIRMED");
			userName.setText(LoginScreenController.user.getUsername());

		} catch (NullPointerException e) {
		};
	}

}
