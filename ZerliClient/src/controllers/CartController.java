package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
import javafx.stage.Stage;
import logic.Item;

public class CartController {
	  @FXML
	    private Button AddGreeting;
	@FXML
	private Button close;

	@FXML
	private GridPane grid;

	@FXML
	private Label lblTotalPrice;

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
	public static Label titleTotalPrice;

	@FXML
	private Text totalItemPrice;
	
	static Text totalItemsPrice;
	static GridPane staticGrid;

    @FXML
    private Button checkOut;


	public static ArrayList<String[]> customItemInCart = CustomCatalogController.customItemInCart;
	public static ArrayList<Item> selectedProductsPremade = CatalogController.selectedProducts;
	public static Map<Integer, ArrayList<String>> itemToAmountPremade = CatalogController.itemToAmount;
	public double amountToPay=0;
	
    @FXML
    void btnCheckout(MouseEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/PaymentScreenNew.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Checkout");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
    }
    @FXML
    void btnGreeting(MouseEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/AddGreeting.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Greeting");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
    }
	@FXML
	void btnClose(MouseEvent event) {
		Stage stage = (Stage) close.getScene().getWindow();
		stage.close();
	}

	
	@FXML
	void initialize() {
		staticGrid=grid;
		totalItemsPrice=totalItemPrice;
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
				amountToPay += Integer.parseInt(itemAmount.get(items.get(i).getID()).get(3)) * items.get(i).getPrice();

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
			for(int i = 0; i < customItemInCart.size(); i++) {
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
			totalItemPrice.setText("\u20AA" +Double.toString(amountToPay));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
