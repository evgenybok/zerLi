package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.Objects;

import javax.swing.JOptionPane;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ocsf.server.ConnectionToClient;

public class LoginScreenController {
	ConnectionToClient client;

	@FXML
	private ImageView LoginImage;

	@FXML
	private Button Login;

	@FXML
	private Button exitBtn;

	@FXML
	private PasswordField Password;

	@FXML
	private TextField Username;

	@FXML
	private Pane LoginStage;

	public static String username;
	public static String password;


	@FXML
	void initialize() {
		assert Login != null : "fx:id=\"Login\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		assert LoginImage != null : "fx:id=\"LoginImage\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		assert LoginStage != null : "fx:id=\"LoginStage\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		assert Username != null : "fx:id=\"Username\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'LoginScreen.fxml'.";
		Image loginImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Login.jpg")));
		LoginImage.setImage(loginImage);

	}

	@FXML
	void ExitLogin(MouseEvent event) {

		chat.accept(new Message(MessageType.EXIT, null));
		Stage stage;
		stage = (Stage) LoginStage.getScene().getWindow();
		stage.close();
		System.exit(1);

	}

	@FXML
	void btnLogin(ActionEvent event) throws IOException {

		StringBuilder login = new StringBuilder();
		String truelogin = null;
		if (Username.getText().equals("") || Password.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}


		login.append(Username.getText());
		login.append("@");
		login.append(Password.getText());
		truelogin = login.toString();
		try {
			chat.accept(new Message(MessageType.LOGIN, truelogin));
			if (AnalyzeMessageFromServer.getData().equals("false")) // Incorrect username / password
				return;
		} catch (Exception e) {

		}
		;
		String Role=AnalyzeMessageFromServer.getData();
		String roleScreen=caseRoleScreen(Role);
		username=Username.getText();
		password=Password.getText();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(roleScreen)));
		Scene scene = new Scene(parent);
		Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		homeStage.setTitle("Home");
		homeStage.setScene(scene);
		homeStage.show();
		homeStage.centerOnScreen();
	}
	public String caseRoleScreen(String Role)
	{
		switch(Role)
		{
		case "customer":
			return "/fxml/CustomerScreen.fxml";
		case "worker":
			return "/fxml/WorkerScreen.fxml";
		case "Delivery":
			return "/fxml/CourierScreen.fxml";
		case "branch manager":
			return "/fxml/ManagerScreen.fxml";
		case "customer specialist":	
			return "/fxml/CustomerSpecialistScreen.fxml";
		case "customer service":
			return "/fxml/CustomerServiceScreen.fxml";
		case "ceo":
			return "/fxml/CEOScreen.fxml";
		default:
			return "error";
		}
		
	}

}
