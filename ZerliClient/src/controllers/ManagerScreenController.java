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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Account;

public class ManagerScreenController {
	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Text userName;

	    @FXML
	    private Text accountType;

	    @FXML
	    private Button Logout;

	    @FXML
	    private Button viewReports;

	    @FXML
	    private Button addCustomer;

	    @FXML
	    private Button EditInfo;

	    @FXML
	    private Button viewOrders;
    @FXML
    private ImageView accIMG;
    @FXML
    private ImageView viewIMG;

    @FXML
    void btnAdd(MouseEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/AddNewCustomer.fxml")));
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
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
    }

    @FXML
    void btnLogout(MouseEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide();
 		chat.accept(new Message(MessageType.LOGOUT,LoginScreenController.user));
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LoginScreen.fxml")));
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
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Manager Orders");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
    }

    @FXML
    void btnViewReports(MouseEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ReportsNew.fxml")));
		Scene scene = new Scene(parent);
		Stage reportStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		reportStage.setTitle("Reports Screen");
		reportStage.setScene(scene);
		reportStage.show();
		reportStage.centerOnScreen();
    }

    @FXML
    void initialize() {
    	try {
			chat.accept(new Message(MessageType.GET_ACCOUNT_DETAILS, LoginScreenController.user.getID()));
			@SuppressWarnings("unchecked")
			ArrayList<Account> account = (ArrayList<Account>) AnalyzeMessageFromServer.getData();
			userName.setText(account.get(0).getUser_ID());
		} catch (NullPointerException e) {
		}
		;
    }

}
