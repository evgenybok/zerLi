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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Account;

/**
 * @author Evgeny
 * Only user with the customer role can get to this screen, customer can view the different catalogs, open the cart and view his orders.
 */
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
	private ImageView CartImage;

	@FXML
	private ImageView ordersImg;

	@FXML
	private Button Catalog;

	@FXML
	private Button Orders;

	@FXML
	private Button btnCart;

	@FXML
	private Label lblZerLiCredit;

	@FXML
	private Label creditAmount;

	public static Stage customerScreenStage;
	public static String accountStatus;
	public static String userID;
	public static Account fullAcc;
	public static double accountZerliCredit;

	/**
	 * Opens the premade catalog to add items to purchase.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnCatalog(MouseEvent event) throws IOException {
		Parent parent = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/fxml/Catalog.fxml"))));
		Scene scene = new Scene(parent);
		parent.getStylesheets().add("css/styleNew.css");
		Stage premadeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		premadeStage.setTitle("Premade Catalog");
		premadeStage.setScene(scene);
		premadeStage.show();
		premadeStage.centerOnScreen();

	}

	/**
	 * Opens the cart in which the user can view and organize the items before payment.
	 * @param event
	 */
	@FXML
	void btnCart(MouseEvent event) {
		customerScreenStage = (Stage) btnCart.getScene().getWindow();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/CartScreen.fxml")));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage cartDetailsScreen = new Stage();
			root1.getStylesheets().add("css/styleNew.css");
			root1.getStylesheets().add("css/transTextArea.css");
			cartDetailsScreen.initModality(Modality.APPLICATION_MODAL);
			cartDetailsScreen.initStyle(StageStyle.UNDECORATED);
			cartDetailsScreen.setTitle("Cart Details");
			cartDetailsScreen.setScene((new Scene(root1)));
			cartDetailsScreen.show();
			cartDetailsScreen.centerOnScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens the custom catalog to add items to purchase.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnCustomCatalog(MouseEvent event) throws IOException {
		Parent parent = FXMLLoader
				.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomCatalogScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage customCatalogStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		parent.getStylesheets().add("css/styleNew.css");
		customCatalogStage.setTitle("Custom Catalog");
		customCatalogStage.setScene(scene);
		customCatalogStage.show();
		customCatalogStage.centerOnScreen();
	}

	/**
	 * Logs out the customer and opens the Login screen.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnLogout(MouseEvent event) throws IOException {
		/* Empty the current cart in checkout */
		CatalogController.selectedProducts.clear();
		CatalogController.itemToAmount.clear();
		CustomCatalogController.customItemInCart.clear();
		CustomCatalogController.bouquetCounter = 0;
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
	 * Opens the orders screen in which the customer can view all of his orders.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnOrders(MouseEvent event) throws IOException {
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ViewOrders.fxml")));
		Scene scene = new Scene(parent);
		Stage orderStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		parent.getStylesheets().add("css/styleNew.css");
		orderStage.setTitle("Order Screen");
		orderStage.setScene(scene);
		orderStage.show();
		orderStage.centerOnScreen();
	}

	/**
	 * initialization of various info on screen.
	 */
	@FXML
	void initialize() {

		try {
			chat.accept(new Message(MessageType.GET_ACCOUNT_DETAILS, LoginScreenController.user.getID()));
			@SuppressWarnings("unchecked")
			ArrayList<Account> account = (ArrayList<Account>) AnalyzeMessageFromServer.getData();
			fullAcc = account.get(0);
			accountStatus = account.get(0).getStatus();
			accountZerliCredit = account.get(0).getTotalRefund();
			userID = account.get(0).getUser_ID();
		} catch (NullPointerException e) {
		}
		;
		if (accountStatus.equals("Frozen")) {
			btnCart.setDisable(true);
		}
		this.AccountStatus.setText(accountStatus); // accountStatus - handled from DB
		this.AccountType.setText("Customer"); // accountType
		this.UserName.setText(LoginScreenController.user.getUsername()); // userName
		creditAmount.setText(Double.toString(accountZerliCredit));

		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		PersonImage.setImage(personImage);
		Image catalogImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Catalog.png")));
		CatalogImage.setImage(catalogImage);
		Image custumCatalogImage = new Image(
				Objects.requireNonNull(getClass().getResourceAsStream("/images/CustomCatalog.png")));
		CustomCatalogImage.setImage(custumCatalogImage);
		Image myCartImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Cart.png")));
		CartImage.setImage(myCartImage);
		Image myOrdersImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/orders.png")));
		ordersImg.setImage(myOrdersImage);

	}
}
