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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ocsf.server.ConnectionToClient;

public class LoginScreenController {
	ConnectionToClient client;
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

	@FXML
	void ExitLogin(MouseEvent event) {

		chat.accept(new Message(MessageType.EXIT, null));
		Stage stage;
		stage = (Stage) LoginStage.getScene().getWindow();
		stage.close();
		System.exit(1);

	}

	@SuppressWarnings("null")
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

		Parent parent = FXMLLoader
				.load(Objects.requireNonNull(getClass().getResource("/controllers/CustomerScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage LoginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		LoginStage.setTitle("Customer Screen");
		LoginStage.setScene(scene);
		LoginStage.show();
	}

}
