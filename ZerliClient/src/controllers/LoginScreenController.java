package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import static controllers.IPScreenController.chat;
import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.User;
import ocsf.server.ConnectionToClient;

public class LoginScreenController {
	ConnectionToClient client;
	public static User user;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Login;

    @FXML
    private ImageView LoginImage;

    @FXML
    private AnchorPane LoginStage;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField Username;

    @FXML
    private Button exitBtn;

    @FXML
    private Text lblZerli;

    @FXML
    void ExitLogin(MouseEvent event) {
		chat.accept(new Message(MessageType.EXIT, null));
		Stage stage;
		stage = (Stage) LoginStage.getScene().getWindow();
		stage.close();
		System.exit(1);
    }

    @FXML
    void btnLogin(MouseEvent event) throws IOException {
		if (Username.getText().equals("") || Password.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			chat.accept(new Message(MessageType.LOGIN, new User(Username.getText(),Password.getText())));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return;
			
		} catch (Exception e) {
			return;
		}
		;
		user=(User)AnalyzeMessageFromServer.getData();
		String roleScreen=caseRoleScreen(user.getRole());
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

    @FXML
    void initialize() {
		Image loginImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/LoginScreen.jpg")));
		LoginImage.setImage(loginImage);
		
        assert Login != null : "fx:id=\"Login\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert LoginImage != null : "fx:id=\"LoginImage\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert LoginStage != null : "fx:id=\"LoginStage\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert Username != null : "fx:id=\"Username\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert lblZerli != null : "fx:id=\"lblZerli\" was not injected: check your FXML file 'LoginScreen.fxml'.";

    }

}