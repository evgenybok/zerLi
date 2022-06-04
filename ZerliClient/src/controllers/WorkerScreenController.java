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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Account;

public class WorkerScreenController {

    @FXML
    private Button Logout;

    @FXML
    private Text accountStatus;

    @FXML
    private Text accountType;

    @FXML
    private ImageView avatarImage;

    @FXML
    private ImageView catalogImage;


    @FXML
    private Button updateCatalog;

    @FXML
    private Text userName;

    public static String accountStatuss;
    

	@FXML
	void btnLogout(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		chat.accept(new Message(MessageType.LOGOUT, LoginScreenController.user));
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LoginScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loginStage.setTitle("Login Screen");
		loginStage.setScene(scene);
		loginStage.show();
		loginStage.centerOnScreen();
	}

	@FXML
	void btnUpdateCatalog(MouseEvent event) throws IOException {
		  Parent parent =
		  FXMLLoader.load((Objects.requireNonNull(getClass().getResource(
		  "/fxml/CatalogUpdate.fxml")))); Scene scene = new Scene(parent);
		  parent.getStylesheets().add("css/style.css"); Stage catalogUpdateStage =
		  (Stage) ((Node) event.getSource()).getScene().getWindow();
		  catalogUpdateStage.setTitle("Catalog Update");
		  catalogUpdateStage.setScene(scene); catalogUpdateStage.show();
		  catalogUpdateStage.centerOnScreen();
		 
	}

	@FXML
	void initialize() {
		try {
			chat.accept(new Message(MessageType.GET_ACCOUNT_DETAILS, LoginScreenController.user.getID()));
			@SuppressWarnings("unchecked")
			ArrayList<Account> account = (ArrayList<Account>) AnalyzeMessageFromServer.getData();
			accountStatuss= account.get(0).getStatus();
		} catch (NullPointerException e) {
		};
		//Image catalogImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Catalog.png")));
		//catalogImage.setImage(catalogImg);
    	this.accountStatus.setText(accountStatuss); //accountStatus - handled from DB
    	this.accountType.setText("Store Worker"); //accountType - may be handled from DB
    	this.userName.setText(LoginScreenController.user.getUsername()); //userName

		/*
		 * 
		 * Image personImage = new
		 * Image(Objects.requireNonNull(getClass().getResourceAsStream(
		 * "/images/Avatar.png"))); PersonImage.setImage(personImage);
		 */
	}
}
