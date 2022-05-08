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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.ClientController;
import ocsf.server.ConnectionToClient;

public class IPScreenController {

	Stage primaryStage;

	@FXML
	private ImageView IPimage;

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
	void initialize() {
		assert Confirm != null : "fx:id=\"Confirm\" was not injected: check your FXML file 'IPConfirmationScreen.fxml'.";
		assert IPimage != null : "fx:id=\"IPimage\" was not injected: check your FXML file 'IPConfirmationScreen.fxml'.";
		assert ServerStage != null : "fx:id=\"ServerStage\" was not injected: check your FXML file 'IPConfirmationScreen.fxml'.";
		assert lblConnect != null : "fx:id=\"lblConnect\" was not injected: check your FXML file 'IPConfirmationScreen.fxml'.";
		assert lblIP != null : "fx:id=\"lblIP\" was not injected: check your FXML file 'IPConfirmationScreen.fxml'.";
		assert txtip != null : "fx:id=\"txtip\" was not injected: check your FXML file 'IPConfirmationScreen.fxml'.";
		Image ipimage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Login.jpg")));
		IPimage.setImage(ipimage);
	}

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