package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Item;

public class CartController {
	@FXML
	private Button AddGreeting;

	@FXML
	private Button checkOut;

	@FXML
	private Button close;

	@FXML
	private Label creditAmount;

	@FXML
	private GridPane grid;

	@FXML
	private Label lblTotalPrice;

	@FXML
	private Label lblZerLiCredit;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private Label titleAmount;

	@FXML
	private Label titleImage;

	@FXML
	private Label titleName;

	@FXML
	private Label titlePrice;

	@FXML
	private Label titleTotalPrice;

	@FXML
	private Text totalItemPrice;

	static Text totalItemsPrice;
	static GridPane staticGrid;

	public static ArrayList<String[]> customItemInCart = CustomCatalogController.customItemInCart;
	public static ArrayList<Item> selectedProductsPremade = CatalogController.selectedProducts;
	public static Map<Integer, ArrayList<String>> itemToAmountPremade = CatalogController.itemToAmount;
	public static double amountToPay = 0;
	public boolean flag = true;

	@FXML
	void btnCheckout(MouseEvent event) throws IOException {
		if (grid.getChildren().isEmpty() || Double.parseDouble(totalItemPrice.getText().substring(1)) == 0) {
			JOptionPane.showMessageDialog(null, "Your cart is empty!", "Info", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		Stage stage;
		try {
			stage = CustomCatalogController.customCatalogStage;
			stage.hide();
		} catch (Exception e) {
		}

		try {
			stage = CatalogController.premadeCatalogStage;
			stage.hide();
		} catch (Exception e) {
		}
        try {
            stage = CustomerScreenController.customerScreenStage;
            stage.hide();
        } catch (Exception e) {
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/PaymentScreenNew2.fxml")));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage paymentScreen = new Stage();
        paymentScreen.initModality(Modality.APPLICATION_MODAL);
        paymentScreen.initStyle(StageStyle.UNDECORATED);
        paymentScreen.setTitle("Checkout");
        paymentScreen.setScene((new Scene(root1)));
        paymentScreen.show();
        paymentScreen.centerOnScreen();
	}

	@FXML
	void btnGreeting(MouseEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/AddGreeting.fxml")));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage cartDetailsScreen = new Stage();
        cartDetailsScreen.initModality(Modality.APPLICATION_MODAL);
        cartDetailsScreen.initStyle(StageStyle.UNDECORATED);
        cartDetailsScreen.setTitle("Greeting");
        cartDetailsScreen.setScene((new Scene(root1)));
        cartDetailsScreen.show();
        cartDetailsScreen.centerOnScreen();
	}

	@FXML
	void btnClose(MouseEvent event) {
		Stage stage = (Stage) close.getScene().getWindow();
		stage.close();

		Stage stage1;
		try {
			stage1 = CustomCatalogController.customCatalogStage;
			stage1.show();
		} catch (Exception e) {
		}
		try {
			stage1 = CatalogController.premadeCatalogStage;
			stage1.show();
		} catch (Exception e) {
		}
        try {
            stage = CustomerScreenController.customerScreenStage;
            stage.show();
        } catch (Exception e) {
        }

	}

	@FXML
	void initialize() {
		amountToPay = 0;
		staticGrid = grid;
		totalItemsPrice = totalItemPrice;
		creditAmount.setText((Double.toString(CustomerScreenController.accountZerliCredit)));
		int column = 0;
		int row = 1;
		Map<Integer, ArrayList<String>> itemAmount = new HashMap<Integer, ArrayList<String>>();
		ArrayList<Item> items = new ArrayList<>();
		itemAmount = itemToAmountPremade;
		items = selectedProductsPremade;

		try {
			grid.getChildren().clear();

			for (int i = 0; i < items.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/ItemInCart.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				ItemInCartController itemInCartController = fxmlLoader.getController();
				itemInCartController.setData(items.get(i), itemAmount.get(items.get(i).getID()).get(3));
				if (items.get(i).isOnSale()) {
					amountToPay += Integer.parseInt(itemAmount.get(items.get(i).getID()).get(3))
							* items.get(i).getSalePrice();
				} else
					amountToPay += Integer.parseInt(itemAmount.get(items.get(i).getID()).get(3))
							* items.get(i).getPrice();
				if (column == 1) {
					column = 0;
					row++;
				}

				grid.add(anchorPane, column++, row);// (child,column,row)
				GridPane.setMargin(anchorPane, new Insets(10));

				// width

				grid.setMinWidth(Region.USE_COMPUTED_SIZE);
				grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
				grid.setMaxWidth(Region.USE_PREF_SIZE);

				// height
				grid.setMinHeight(Region.USE_COMPUTED_SIZE);
				grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
				grid.setMaxHeight(Region.USE_PREF_SIZE);
			}
			for (int i = 0; i < customItemInCart.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/ItemInCart.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				ItemInCartController itemInCartController = fxmlLoader.getController();
				itemInCartController.setData(customItemInCart.get(i));
				amountToPay += Double.parseDouble(customItemInCart.get(i)[2]);
				if (column == 1) {
					column = 0;
					row++;
				}

				grid.add(anchorPane, column++, row);// (child,column,row)
				GridPane.setMargin(anchorPane, new Insets(10));

				// width

				grid.setMinWidth(Region.USE_COMPUTED_SIZE);
				grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
				grid.setMaxWidth(Region.USE_PREF_SIZE);

				// height
				grid.setMinHeight(Region.USE_COMPUTED_SIZE);
				grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
				grid.setMaxHeight(Region.USE_PREF_SIZE);
			}
			totalItemPrice.setText("\u20AA" + Double.toString(amountToPay));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
