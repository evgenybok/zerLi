package controllers;

import static controllers.IPScreenController.chat;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
 * @author Evgeny Login screen for the user to enter username and password and
 *         login to the system of Zerli.
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

	public static IMySqlConnection mySqlConnection;
	public static IJavaFx javaFx;

	public static String username, password,role;

	public LoginScreenController(IMySqlConnection MySqlConnection, IJavaFx JavaFx) {
		mySqlConnection = MySqlConnection;
		javaFx = JavaFx;
	}

	public LoginScreenController() {
		this.mySqlConnection = new IManager();
		this.javaFx = new IManager();
//		test = false;
	}

	public void setDataField(String Username, String Password,String Role) {
		username = Username;
		password = Password;
		role=Role;
	}

	/**
	 * Closes current screen and disconnects the IP from the server.
	 * 
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
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public boolean btnLogin(MouseEvent event) throws IOException {

		javaFx.setDetails();
		//if (!(username == null) && !(password == null)) {
			if (!username.isEmpty() && !password.isEmpty()) {
				FXMLLoader loader = new FXMLLoader();
				String role = mySqlConnection.Login(username, password);
				String fxml = caseRoleScreen(role);
				switch (fxml) {
				case "/fxml/CustomerScreen.fxml": {
					javaFx.javaFxmlCustomer(event, loader);
					return true;
				}
				case "/fxml/WorkerScreen.fxml": {
					javaFx.javaFxmlWorker(event, loader);
					return true;
				}
				case "/fxml/MarketingDepartment.fxml": {
					javaFx.javaFxmlMarketing(event, loader);
					return true;
				}
				case "/fxml/DeliveryLoginScreen.fxml": {
					javaFx.javaFxmlDelivery(event, loader);
					return true;
				}
				case "/fxml/branchManager.fxml": {
					javaFx.javaFxmlManager(event, loader);
					return true;
				}
				case "/fxml/CustomerSpecialistScreen.fxml": {
					javaFx.javaFxmlSpecialist(event, loader);
					return true;
				}
				case "/fxml/CustomerService.fxml": {
					javaFx.javaFxmlService(event, loader);
					return true;
				}
				case "/fxml/CEOScreenNew.fxml": {
					javaFx.javaFxmlCEO(event, loader);
					return true;
				}
				case "loggedIn":
				{
					javaFx.javaFxmlAlreadyLogged();
					return false;
				}
				case "not found":
				{
					javaFx.javaFxmlNotFound();
					return false;
				}
				default:
					return false;
				}
			}
			else {
				javaFx.javaFxmlEmptyFields();
				return false;
			}
			}
			/*
		//}
		if (Username.getText().equals("") || Password.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			chat.accept(new Message(MessageType.LOGIN, new User(Username.getText(), Password.getText())));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return false;
			user = (User) AnalyzeMessageFromServer.getData();
		} catch (Exception e) {
			return false;
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
		return true;
	}
	*/

	/**
	 * Moves to various screen based on the user's role.
	 * 
	 * @param Role user role
	 * @return fxml file to open
	 */
	public String caseRoleScreen(String Role) {
		switch (Role) {
		case "customer":
			return "/fxml/CustomerScreen.fxml";
		case "Worker":
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

	class IManager implements IMySqlConnection, IJavaFx {

		@Override
		public String Login(String Username, String Password) {
			chat.accept(new Message(MessageType.LOGIN, new User(Username, Password)));
			user=new User(username,password,role);
			return user.getRole();
			//return user.getRole();
		}

		@Override
		public void javaFxmlNotFound() {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Validation Error");
					alert.setHeaderText(null);
					alert.setContentText("Wrong Username of Password.");
					alert.showAndWait();

				}
			});
		}

		@Override
		public void javaFxmlAlreadyLogged() {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Connection Error");
					alert.setHeaderText(null);
					alert.setContentText("User Allready Connected.");
					alert.showAndWait();
				}
			});
		}

		@Override
		public void javaFxmlEmptyFields() {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Empty Fields");
					alert.setHeaderText(null);
					alert.setContentText("Please insert User name and Password.");
					alert.showAndWait();
				}
			});
		}

		@Override
		public void setDetails() {
			setDataField(Username.getText(), Password.getText(),role);
		}

		@Override
		public void javaFxmlCustomer(MouseEvent event, FXMLLoader loader) throws IOException {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerScreen.fxml")));
			parent.getStylesheets().clear();
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			homeStage.setTitle("Home Screen");
			homeStage.setScene(scene);
			homeStage.show();
			homeStage.centerOnScreen();
			
		}

		@Override
		public void javaFxmlWorker(MouseEvent event, FXMLLoader loader) throws IOException {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/WorkerScreen.fxml")));
			parent.getStylesheets().clear();
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			homeStage.setTitle("Home Screen");
			homeStage.setScene(scene);
			homeStage.show();
			homeStage.centerOnScreen();			
		}

		@Override
		public void javaFxmlDelivery(MouseEvent event, FXMLLoader loader) throws IOException {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/DeliveryLoginScreen.fxml")));
			parent.getStylesheets().clear();
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			homeStage.setTitle("Home Screen");
			homeStage.setScene(scene);
			homeStage.show();
			homeStage.centerOnScreen();			
		}

		@Override
		public void javaFxmlManager(MouseEvent event, FXMLLoader loader) throws IOException {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/branchManager.fxml")));
			parent.getStylesheets().clear();
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			homeStage.setTitle("Home Screen");
			homeStage.setScene(scene);
			homeStage.show();
			homeStage.centerOnScreen();			
		}

		@Override
		public void javaFxmlCEO(MouseEvent event, FXMLLoader loader) throws IOException {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CEOScreenNew.fxml")));
			parent.getStylesheets().clear();
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			homeStage.setTitle("Home Screen");
			homeStage.setScene(scene);
			homeStage.show();
			homeStage.centerOnScreen();			
		}

		@Override
		public void javaFxmlSpecialist(MouseEvent event, FXMLLoader loader) throws IOException {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerSpecialistScreen.fxml")));
			parent.getStylesheets().clear();
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			homeStage.setTitle("Home Screen");
			homeStage.setScene(scene);
			homeStage.show();
			homeStage.centerOnScreen();			
		}

		@Override
		public void javaFxmlService(MouseEvent event, FXMLLoader loader) throws IOException {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerService.fxml")));
			parent.getStylesheets().clear();
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			homeStage.setTitle("Home Screen");
			homeStage.setScene(scene);
			homeStage.show();
			homeStage.centerOnScreen();			
		}

		@Override
		public void javaFxmlMarketing(MouseEvent event, FXMLLoader loader) throws IOException {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MarketingDepartment.fxml")));
			parent.getStylesheets().clear();
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			homeStage.setTitle("Home Screen");
			homeStage.setScene(scene);
			homeStage.show();
			homeStage.centerOnScreen();			
		}

	}

}