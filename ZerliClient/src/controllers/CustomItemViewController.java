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

	public static Text totalPriceText;

	public static ArrayList<Item> selectedProducts = CustomCatalogController.selectedProducts;
	public static Map<Integer, ArrayList<String>> itemToAmount = CustomCatalogController.itemToAmount;
	static double totalPrice;

	@FXML
	void btnClose(MouseEvent event) {
		itemToAmount = new HashMap<Integer, ArrayList<String>>(ItemInCartController.originalItemToAmounts);
		CustomCatalogController.itemToAmount = itemToAmount;
		selectedProducts = new ArrayList<Item>(ItemInCartController.originalSelectedProducts);
		CustomCatalogController.selectedProducts = selectedProducts;
		Stage stage = (Stage) close.getScene().getWindow();
		stage.close();
	}

	@FXML
	void clkSave(MouseEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/ItemInCart.fxml"));
		fxmlLoader.load();
		totalPrice = 0;
		for (ArrayList<String> entry : itemToAmount.values()) {
			double price = Double.parseDouble(entry.get(1));
			int amount = Integer.parseInt(entry.get(3));
			totalPrice += price * amount;
		}
		CustomCatalogController.totalPrice = totalPrice;
		if (selectedProducts.isEmpty())
		{
			CustomCatalogController.staticViewCustomizedBouquet.setDisable(true);
			CustomCatalogController.staticAddToCart.setDisable(true);
		}
		Stage stage = (Stage) btnSave.getScene().getWindow();
		stage.close();
		JOptionPane.showMessageDialog(null, "Cart updated successfuly!", "Update", JOptionPane.PLAIN_MESSAGE);
	}

	@FXML
	void initialize() {
		totalPrice = CustomCatalogController.totalPrice;
		totalItemPrice.setText("\u20AA" + Double.toString(totalPrice));
		totalPriceText = totalItemPrice;
		int column = 0;
		int row = 1;
		ArrayList<Item> items = new ArrayList<>();
		items = selectedProducts;
		try {
			grid.getChildren().clear();
			for (int i = 0; i < items.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/ItemInCart.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				ItemInCartController itemInCartController = fxmlLoader.getController();
				itemInCartController.setData(items.get(i), itemToAmount.get(items.get(i).getID()).get(3));

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
