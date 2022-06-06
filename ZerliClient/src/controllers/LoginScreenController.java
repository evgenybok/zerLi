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

/**
 * @author Evgeny
 * Login screen for the user to enter username and password and login to the system of Zerli.
 */
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
	private ImageView imageLeft;

	@FXML
	private ImageView imageRight;

    /**
     * Closes current screen and disconnects the IP from the server.
     * @param event
     */
	@FXML
	void ExitLogin(MouseEvent event) {
		chat.accept(new Message(MessageType.EXIT, null));
		Stage stage;
		stage = (Stage) LoginStage.getScene().getWindow();
		stage.close();
		System.exit(1);
	}

    /**
     * Checks if the user is registered in the DB and connects him.
     * @param event
     * @throws IOException
     */
	@FXML
	void btnLogin(MouseEvent event) throws IOException {
		if (Username.getText().equals("") || Password.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			chat.accept(new Message(MessageType.LOGIN, new User(Username.getText(), Password.getText())));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return;
			user = (User) AnalyzeMessageFromServer.getData();
		} catch (Exception e) {
			return;
		}
		;
		String roleScreen = caseRoleScreen(user.getRole());
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(roleScreen)));
		parent.getStylesheets().clear();
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		homeStage.setTitle("Home Screen");
		homeStage.setScene(scene);
		homeStage.show();
		homeStage.centerOnScreen();
	}

	/**
	 * Moves to various screen based on the user's role.
	 * @param Role user role
	 * @return fxml file to open
	 */
	public String caseRoleScreen(String Role) {
		switch (Role) {
		case "customer":
			return "/fxml/CustomerScreen.fxml";
		case "worker":
			return "/fxml/WorkerScreen.fxml";
		case "Marketing":
			return "/fxml/MarketingDepartment.fxml";
		case "Delivery":
			return "/fxml/DeliveryLoginScreen.fxml";
		case "branch manager":
			return "/fxml/branchManager.fxml";
		case "customer specialist":
			return "/fxml/CustomerSpecialistScreen.fxml";
		case "customer service":
			return "/fxml/CustomerService.fxml";
		case "ceo":
			return "/fxml/CEOScreenNew.fxml";
		default:
			return "error";
		}
	}

    /**
     * Initialization of data.
     */
	@FXML
	void initialize() {
		Image imgLeft = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/bouquetLogin.png")));
		imageLeft.setImage(imgLeft);
		Image imgRight = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/bouquetLogin2.png")));
		imageRight.setImage(imgRight);
	}

}