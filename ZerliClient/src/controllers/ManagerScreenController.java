package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import clientanalyze.AnalyzeMessageFromServer;

import communication.Message;
import communication.MessageType;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
	private FontAwesomeIconView addImg;

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
