package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Account;

/**
 * @author Evgeny Only user with the Manager role can get to this screen,Can add
 *         a new user, edit user info,view various reports and orders.
 */
public class ManagerScreenController {

	@FXML
	private Button EditInfo;

	@FXML
	private Button Logout;

	@FXML
	private Text accountType;

	@FXML
	private Button addCustomer;


	@FXML
	private ImageView avatarImg;

	@FXML
	private ImageView editImg;

	@FXML
	private ImageView ordersManageImg;

	@FXML
	private Text userName;

	@FXML
	private Button viewOrders;

	@FXML
	private Button viewReports;

	@FXML
	private ImageView viewReportsImg;

	@FXML
	private Button editStatus;

	@FXML
	private ImageView roleImg;

	@FXML
	private Button editRole;

    @FXML
    private ImageView addImg;
    
	/**
	 * Registers a new user to the system.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnAdd(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/AddNewCustomer.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}

	/**
	 * Edits the role of workers
	 * @param event
	 */
	@FXML
	void btnEditRole(MouseEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent;
		try {
			parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/EditWorkerRoleScreen.fxml")));
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			customerStage.setTitle("Customer");
			customerStage.setScene(scene);
			customerStage.show();
			customerStage.centerOnScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Updates existing user's info in the DB.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnEdit(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/EditUsers.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}

	/**
	 * Logs the user out of the system.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnLogout(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		chat.accept(new Message(MessageType.LOGOUT, LoginScreenController.user));
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LoginScreen.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loginStage.setTitle("Login Screen");
		loginStage.setScene(scene);
		loginStage.show();
		loginStage.centerOnScreen();
	}

	/**
	 * Allows the user to view his store's orders.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnViewOrders(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ManageOrders.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Manager Orders");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}

	/**
	 * Allows the user to view his store's reports.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnViewReports(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ReportsNew.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage reportStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		reportStage.setTitle("Reports Screen");
		reportStage.setScene(scene);
		reportStage.show();
		reportStage.centerOnScreen();
	}

	/**
	 * Initialization of data on the screen.
	 */
	@FXML
	void initialize() {
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		Image orderImage = new Image(
				Objects.requireNonNull(getClass().getResourceAsStream("/images/ordersManage.png")));
		ordersManageImg.setImage(orderImage);
		Image viewRepImage = new Image(
				Objects.requireNonNull(getClass().getResourceAsStream("/images/viewReports.png")));
		viewReportsImg.setImage(viewRepImage);
		Image editImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/account.png")));
		editImg.setImage(editImage);
		Image addImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/account.png")));
		addImg.setImage(addImage);
		Image roleImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/account.png")));
		roleImg.setImage(roleImage);
		
		userName.setText(LoginScreenController.user.getUsername());
		try {
			chat.accept(new Message(MessageType.GET_ACCOUNT_DETAILS, LoginScreenController.user.getID()));
			@SuppressWarnings("unchecked")
			ArrayList<Account> account = (ArrayList<Account>) AnalyzeMessageFromServer.getData();
		} catch (NullPointerException e) {
		}
		;
	}

}
