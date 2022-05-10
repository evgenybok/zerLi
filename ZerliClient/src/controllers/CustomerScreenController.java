package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
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

    @FXML
    void btnCatalog(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SelfAssemblyItems.fxml")));
		Scene scene = new Scene(parent);
		Stage selfAssemblyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		selfAssemblyStage.setTitle("Self Assembly Items");
		selfAssemblyStage.setScene(scene);
		selfAssemblyStage.show();
		selfAssemblyStage.centerOnScreen();
    }

    @FXML
    void btnComplaint(MouseEvent event) {

    }
    
    @FXML
    void btnCustomCatalog(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/PreMadeItems.fxml")));
		Scene scene = new Scene(parent);
		Stage premadeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		premadeStage.setTitle("Premade Items");
		premadeStage.setScene(scene);
		premadeStage.show();
		premadeStage.centerOnScreen();
    }
    
    @FXML
    void btnLogout(MouseEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide();
        StringBuilder login = new StringBuilder();
        String truelogin = null;
        login.append(LoginScreenController.username);
        login.append("@");
        login.append(LoginScreenController.password);
        truelogin = login.toString();

		chat.accept(new Message(MessageType.LOGOUT, truelogin));
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
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/OrderScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage orderStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		orderStage.setTitle("Order Screen");
		orderStage.setScene(scene);
		orderStage.show();
		orderStage.centerOnScreen();  
    }

    @FXML
    void initialize() {
    	this.AccountStatus.setText("CONFIRMED"); //accountStatus - need to be handled from DB
    	this.AccountType.setText("Customer"); //accountType - may be handled from DB
    	this.UserName.setText(LoginScreenController.username); //userName
    	
      //  Image homeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/HomeScreen.jpeg")));
       //CustomerScreenImage.setImage(homeImage);
    	Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
    	PersonImage.setImage(personImage);
    	Image catalogImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Catalog.png")));
    	CatalogImage.setImage(catalogImage);
    	Image custumCatalogImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/CustomCatalog.png")));
    	CustomCatalogImage.setImage(custumCatalogImage);
    	Image ordersImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/CustomCatalog.png")));
    	OrdersImage.setImage(ordersImage);
    	Image complaintImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/complaint.png")));
    	ComplaintImage.setImage(complaintImage);
    	
    	
        assert lblZerLi != null : "fx:id=\"lblZerLi\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert lblStartMsg != null : "fx:id=\"lblStartMsg\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert UserName != null : "fx:id=\"UserName\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert PersonImage != null : "fx:id=\"PersonImage\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert AccountType != null : "fx:id=\"AccountType\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert AccountStatus != null : "fx:id=\"AccountStatus\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert Logout != null : "fx:id=\"Logout\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert CustomCatalog != null : "fx:id=\"CustomCatalog\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert CatalogImage != null : "fx:id=\"CatalogImage\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert CustomCatalogImage != null : "fx:id=\"CustomCatalogImage\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert ComplaintImage != null : "fx:id=\"ComplaintImage\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert OrdersImage != null : "fx:id=\"OrdersImage\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert Catalog != null : "fx:id=\"Catalog\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert Orders != null : "fx:id=\"Orders\" was not injected: check your FXML file 'CustomerScreen.fxml'.";
        assert Complaint != null : "fx:id=\"Complaint\" was not injected: check your FXML file 'CustomerScreen.fxml'.";

    }
}

