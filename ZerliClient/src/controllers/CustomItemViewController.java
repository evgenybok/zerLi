package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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

/**
 * @author Evgeny
 * View custom bouquet screen where user can view and organaize what the bouquet is made of.
 */
public class CustomItemViewController{

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

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
	private Label titleTotalPrice;

	@FXML
	private Text totalItemPrice;

	@FXML
	private Button btnSave;

    @FXML
    private Label msgLabel;

	public static Text totalPriceText;
	static GridPane staticGrid;
	static Text staticTotalItemPrice;

	static String totalPriceTemp;
	public static ArrayList<Item> customSelectedProducts = CustomCatalogController.selectedProducts;
	public static Map<Integer, ArrayList<String>> customItemToAmount = CustomCatalogController.itemToAmount;
	static double totalPrice;

	/**
	 * Closes custom bouquet screen without saving.
	 * @param event
	 */
	@FXML
	void btnClose(MouseEvent event) {
		customItemToAmount = new HashMap<Integer, ArrayList<String>>(ItemInCartController.originalItemToAmounts);
		CustomCatalogController.itemToAmount = new HashMap<Integer, ArrayList<String>>(ItemInCartController.originalItemToAmounts);
		customSelectedProducts = new ArrayList<Item>(ItemInCartController.originalSelectedProducts);
		CustomCatalogController.selectedProducts = new ArrayList<Item>(ItemInCartController.originalSelectedProducts);
		totalPriceTemp=totalPriceTemp.substring(1);
		totalPrice=Double.parseDouble(totalPriceTemp);
		CustomCatalogController.bouquetCounter=ItemInCartController.originalBouquetCounter;
		Stage stage = (Stage) close.getScene().getWindow();
		stage.close();
	}

	/**
	 * Saves custom bouquet, updates it and closes screen.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void clkSave(MouseEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/ItemInCart.fxml"));
		fxmlLoader.load();
		totalPrice = 0;
		for (ArrayList<String> entry : customItemToAmount.values()) {
			double price = Double.parseDouble(entry.get(1));
			int amount = Integer.parseInt(entry.get(3));
			totalPrice += price * amount;
		}
		CustomCatalogController.totalPrice = totalPrice;
		if (customSelectedProducts.isEmpty())
		{
			CustomCatalogController.staticViewCustomizedBouquet.setDisable(true);
			CustomCatalogController.staticAddToCart.setDisable(true);
		}

		msgLabel.setVisible(true);
		new java.util.Timer().schedule( new java.util.TimerTask() {
			@Override
			public void run() {
				msgLabel.setVisible(false);
			}
		}, 2000);
	}

	/**
	 * Initialization of items in custom bouquet.
	 */
	@FXML
	void initialize() {
		msgLabel.setVisible(false);
		staticGrid=grid;
		staticTotalItemPrice=totalItemPrice;
		totalPrice = CustomCatalogController.totalPrice;
		totalItemPrice.setText("\u20AA" + Double.toString(totalPrice));
		totalPriceTemp=totalItemPrice.getText();
		totalPriceText = totalItemPrice;
		customItemToAmount=CustomCatalogController.itemToAmount;
		customSelectedProducts=CustomCatalogController.selectedProducts;
		int column = 0;
		int row = 1;
		ArrayList<Item> items = new ArrayList<>();
		items = customSelectedProducts;
		try {
			grid.getChildren().clear();
			for (int i = 0; i < items.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/ItemInCart.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				ItemInCartController itemInCartController = fxmlLoader.getController();
				itemInCartController.setData(items.get(i), customItemToAmount.get(items.get(i).getID()).get(3));

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

				// height grid.setMinHeight(Region.USE_COMPUTED_SIZE);
				grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
				grid.setMaxHeight(Region.USE_PREF_SIZE);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
