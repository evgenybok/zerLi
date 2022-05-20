package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logic.Item;

public class ItemInCartController extends CustomItemViewController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label amountLabel;

	@FXML
	private ImageView img;

	@FXML
	private TextArea nameLabel;

	@FXML
	private Label priceLabel;

	@FXML
	private Label totalPriceLabel;

	@FXML
	private Button PlusBtn;

	@FXML
	private Button MinBtn;

	Item item;
	public static ArrayList<Item> originalSelectedProducts;
	public static Map<Integer, ArrayList<String>> originalItemToAmounts;

	public void setData(Item item, String amount) {
		this.item = item;
		amountLabel.setText(amount);
		priceLabel.setText("\u20AA" + item.getPrice()); // shekel unicode
		nameLabel.setText(item.getName());
		totalPriceLabel.setText("\u20AA" + Double.toString(Integer.parseInt(amount) * item.getPrice()));
		Image image = new Image((Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc()))));
		img.setImage(image);
	}

	public void setData(String[] customItemData) {
		amountLabel.setText("1");
		priceLabel.setText("\u20AA" + customItemData[customItemData.length - 1].toString());
		nameLabel.setText(customItemData[0] + ": " + customItemData[1]);
		totalPriceLabel.setText("\u20AA" + customItemData[2]);
		Image image = new Image((Objects.requireNonNull(getClass().getResourceAsStream("/images/SA2.png"))));
		img.setImage(image);
	}

	@FXML
	void btnMin(MouseEvent event) {
		int amount = Integer.valueOf(amountLabel.getText());
		if (amount > 0) {
			amount--;
			amountLabel.setText(Integer.toString(amount));
			ArrayList<String> updateItemAmount = new ArrayList<String>(); /* new array list to update amount */
			/* Only done for the Custom Catalog */
			if (!(item == null)) {
				updateItemAmount.add(Integer.toString(item.getID()));
				updateItemAmount.add(Double.toString(item.getPrice()));
				updateItemAmount.add(item.getImgSrc());
				updateItemAmount.add(Integer.toString(amount));
				itemToAmount.put(item.getID(), updateItemAmount);
			}
		}
		if (amount == 0) {
			MinBtn.setDisable(true);
			/* Remove empty items from product array list and amount map */
			int index = 0;
			try {
				for (Item i : selectedProducts)
					if (i.equals(item)) {
						selectedProducts.remove(index);
						itemToAmount.remove(i.getID());
						break;
					} else
						index++;
			} catch (NullPointerException e) {
			}
			;
		}
		/*
		 * setting single total item price label and total items pricelabel
		 */
		if (amount < 20)
			PlusBtn.setDisable(false);
		String priceText = priceLabel.getText();
		String temp = priceText.substring(1);
		double price = Double.valueOf(temp);
		totalPriceLabel.setText("\u20AA" + Double.toString(price * Double.parseDouble(amountLabel.getText())));
		double totalPriceTemp;
		/* Only done for the Custom Catalog */
		if (!(item == null) && (item.getType().equals("Self Assembly"))) {
			totalPriceTemp = Double.parseDouble(totalPriceText.getText().substring(1));
			totalPriceTemp -= price;
			totalPriceText.setText("\u20AA" + Double.toString(totalPriceTemp));
			/* Done only for the cart */
		} else {
			totalPriceTemp = Double.parseDouble(CartController.totalItemsPrice.getText().substring(1));
			totalPriceTemp -= price;
			CartController.totalItemsPrice.setText("\u20AA" + Double.toString(totalPriceTemp));
		}
	}

	@FXML
	/* Plus button for amounts for the Custom catalog and the Cart */
	void btnPlus(MouseEvent event) {
		int amount = Integer.valueOf(amountLabel.getText());
		if (amount < 20) {
			amount++;

			amountLabel.setText(Integer.toString(amount));
			ArrayList<String> updateItem = new ArrayList<String>();
			/* Only done for the Custom Catalog */
			if (!(item == null) && (item.getType().equals("Self Assembly"))) {
				updateItem.add(Integer.toString(item.getID()));
				updateItem.add(Double.toString(item.getPrice()));
				updateItem.add(item.getImgSrc());
				updateItem.add(Integer.toString(amount));
				itemToAmount.put(item.getID(), updateItem);
			}
		}
		if (amount > 0)
			MinBtn.setDisable(false);
		if (amount == 20) {
			PlusBtn.setDisable(true);
		} else
			PlusBtn.setDisable(false);

		String priceText = priceLabel.getText();
		String temp = priceText.substring(1);
		double price = Double.valueOf(temp);
		totalPriceLabel.setText("\u20AA" + Double.toString(price * Double.parseDouble(amountLabel.getText())));
		double totalPriceTemp;
		/* Only done for the Custom Catalog */
		if (!(item == null) && (item.getType().equals("Self Assembly"))) {
			totalPriceTemp = Double.parseDouble(totalPriceText.getText().substring(1));
			totalPriceTemp += price;
			totalPriceText.setText("\u20AA" + Double.toString(totalPriceTemp));
			/* Done only for the cart */
		} else {
			totalPriceTemp = Double.parseDouble(CartController.totalItemsPrice.getText().substring(1));
			totalPriceTemp += price;
			CartController.totalItemsPrice.setText("\u20AA" + Double.toString(totalPriceTemp));
		}
	}

	@FXML
	void initialize() {
		/*
		 * Save original values on initialization in case 'Close' was clicked instead of
		 * 'Save'
		 */
		originalSelectedProducts = new ArrayList<Item>(selectedProducts);
		originalItemToAmounts = new HashMap<Integer, ArrayList<String>>(itemToAmount);
	}

}
