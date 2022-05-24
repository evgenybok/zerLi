package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import clientanalyze.AnalyzeMessageFromServer;

import static controllers.IPScreenController.chat;

import communication.Message;
import communication.MessageType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerScreenController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblZerLi;

    @FXML
    private Label lblStartMsg;

    @FXML
    private Text UserName;

    @FXML
    private ImageView PersonImage;

    @FXML
    private Text AccountType;

    @FXML
    private Text AccountStatus;

    @FXML
    private Button Logout;

    @FXML
    private Button CustomCatalog;

    @FXML
    private ImageView CatalogImage;

    @FXML
    private ImageView CustomCatalogImage;

    @FXML
    private ImageView ComplaintImage;

    @FXML
    private ImageView OrdersImage;

    @FXML
    private Button Catalog;

    @FXML
    private Button Orders;

    @FXML
    private Button Complaint;
    
    public static String accountStatus;

    @FXML
    void btnCatalog(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/fxml/Catalog.fxml"))));
		Scene scene = new Scene(parent);
        parent.getStylesheets().add("css/style.css");
        Stage premadeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		premadeStage.setTitle("Premade Catalog");
		premadeStage.setScene(scene);
		premadeStage.show();
		premadeStage.centerOnScreen();

    }

    @FXML
    void btnComplaint(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/fxml/CustomerComplaintScreen.fxml"))));
		Scene scene = new Scene(parent);
        parent.getStylesheets().add("css/style.css");
        Stage premadeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		premadeStage.setTitle("Customer Complaint");
		premadeStage.setScene(scene);
		premadeStage.show();
		premadeStage.centerOnScreen();
    }
    
    @FXML
    void btnCustomCatalog(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomCatalogScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage customCatalogStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customCatalogStage.setTitle("Custom Catalog");
		customCatalogStage.setScene(scene);
		customCatalogStage.show();
		customCatalogStage.centerOnScreen();
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
    void btnOrders(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ViewOrders.fxml")));
		Scene scene = new Scene(parent);
		Stage orderStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		orderStage.setTitle("Order Screen");
		orderStage.setScene(scene);
		orderStage.show();
		orderStage.centerOnScreen();  
    }

    @FXML
    void initialize() {
		try {
			chat.accept(new Message(MessageType.GET_ACCOUNT_DETAILS, LoginScreenController.user.getID()));
			@SuppressWarnings("unchecked")
			ArrayList<String> account = (ArrayList<String>) AnalyzeMessageFromServer.getData();
			accountStatus= account.get(5); // *** AccountStatus text field is null!!
		} catch (NullPointerException e) {
		};
    	this.AccountStatus.setText(accountStatus); //accountStatus - handled from DB
    	this.AccountType.setText("Customer"); //accountType - may be handled from DB
    	this.UserName.setText(LoginScreenController.user.getUsername()); //userName



    	Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
    	PersonImage.setImage(personImage);
    	Image catalogImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Catalog.png")));
    	CatalogImage.setImage(catalogImage);
    	Image custumCatalogImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/CustomCatalog.png")));
    	CustomCatalogImage.setImage(custumCatalogImage);
    	Image ordersImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/orders.jpg")));
    	OrdersImage.setImage(ordersImage);
    	Image complaintImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/complaint.png")));
    	ComplaintImage.setImage(complaintImage);
    	
    }
}

