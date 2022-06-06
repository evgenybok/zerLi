package controllers;

import static java.lang.System.exit;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.application.Platform;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.ClientController;
import ocsf.server.ConnectionToClient;

/**
 * @author Evgeny
 * IP configuration screen, checks if IP is already connected to server, if not, connects the IP and moves to Login screen.
 */
public class IPScreenController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView flowerImage;

	@FXML
	private BorderPane ServerStage;

	@FXML
	private Label lblZerli;

	@FXML
	private Label lblIPConnect;

	@FXML
	private Label lblEnterIP;

	@FXML
	private TextField txtIP;

	@FXML
	private Button Connect;

	Stage primaryStage;

	public static ClientController chat;

	ConnectionToClient client;

    /**
     * Connects IP to the server and shows the connection in the server screen.
     * @param event
     * @throws IOException
     */
	@FXML
	void btnConnect(MouseEvent event) throws IOException {

		try {
			chat = new ClientController(txtIP.getText(), 5555);
		} catch (Exception e) {

			exit(1);
		}

		chat.accept(new Message(MessageType.CONFIRM_IP, txtIP.getText()));
		String result = (String) AnalyzeMessageFromServer.getData();
		if (result.equals("Failed")) {
			JOptionPane.showMessageDialog(null, "IP already connected", "Error", JOptionPane.ERROR_MESSAGE);
			Platform.exit();
			System.exit(0);
		}
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/wellcomeScreen.fxml")));
		parent.getStylesheets().clear();
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage LoginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		LoginStage.setTitle("LoginScreen");
		LoginStage.setScene(scene);
		LoginStage.show();
		LoginStage.centerOnScreen();
	}

    /**
     * Initialization of data in the screen.
     */
	@FXML
	void initialize() {
		Image floImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/IPflower.png")));
		flowerImage.setImage(floImage);
	}

	/**
	 * Opens IP configuration screen
	 * @param primaryStage main stage
	 * @throws Exception
	 */
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/IPscreen.fxml")));
		primaryStage.initStyle(StageStyle.UNDECORATED);
		root.getStylesheets().clear();
		root.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(root);
		primaryStage.setTitle("Connect To Server");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();

	}
}
