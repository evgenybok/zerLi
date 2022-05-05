package controllers;

import static java.lang.System.exit;

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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.ClientController;
import ocsf.server.ConnectionToClient;

public class IPScreenController {

	Stage primaryStage;

	@FXML
	private AnchorPane ServerStage;

	@FXML
	private Button exitBtn;

	@FXML
	private Button Confirm;

	@FXML
	private Label lblConnect;

	@FXML
	private Label lblIP;

	@FXML
	private TextField txtip;

	public static ClientController chat;

	ConnectionToClient client;

	@FXML
	void btnCnf(MouseEvent event) throws IOException {

		try {
			chat = new ClientController(txtip.getText(), 5555);
		} catch (Exception e) {
			exit(1);
		}

		chat.accept(new Message(MessageType.CONFIRM_IP, txtip.getText()));
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LoginScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage LoginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		LoginStage.setTitle("LoginScreen");
		LoginStage.setScene(scene);
		LoginStage.show();
		LoginStage.centerOnScreen();

	}

	@FXML
	void ExitApp(MouseEvent event) throws IOException {
		Stage stage;
		client.close();
		stage = (Stage) ServerStage.getScene().getWindow();
		stage.close();
		System.exit(1);

	}

	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		Parent root = FXMLLoader
				.load(Objects.requireNonNull(getClass().getResource("/fxml/IPConfirmationScreen.fxml")));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Connect To Server");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();

	}
}