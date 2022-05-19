package controllers;

import java.net.URL;
import java.util.ArrayList;
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
	public static ArrayList<Item> updatedItems;
	public static Map<Integer, ArrayList<String>> itemAmounts;

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
		priceLabel.setText("-");
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
			ArrayList<String> updateItem = new ArrayList<String>();
			updateItem.add(Integer.toString(item.getID()));
			updateItem.add(Double.toString(item.getPrice()));
			updateItem.add(item.getImgSrc());
			updateItem.add(Integer.toString(amount));
			itemAmounts.put(item.getID(), updateItem);
		}
		if (amount == 0) {
			MinBtn.setDisable(true);
			int index = 0;
			for (Item i : updatedItems)
				if (i.equals(item)) {
					updatedItems.remove(index);
					break;
				} else
					index++;
		}
		if (amount < 20)
			PlusBtn.setDisable(false);
		String priceText = priceLabel.getText();
		String temp = priceText.substring(1);
		double price = Double.valueOf(temp);
		totalPriceLabel.setText(Double.toString(price * Double.parseDouble(amountLabel.getText())));
		double totalPriceTemp = Double.parseDouble(totalPriceText.getText());
		totalPriceTemp -= price;
		totalPriceText.setText(Double.toString(totalPriceTemp));
	}

	@FXML
	void btnPlus(MouseEvent event) {
		int amount = Integer.valueOf(amountLabel.getText());
		if (amount < 20) {
			amount++;
			amountLabel.setText(Integer.toString(amount));
			ArrayList<String> updateItem = new ArrayList<String>();
			updateItem.add(Integer.toString(item.getID()));
			updateItem.add(Double.toString(item.getPrice()));
			updateItem.add(item.getImgSrc());
			updateItem.add(Integer.toString(amount));
			itemAmounts.put(item.getID(), updateItem);
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
		totalPriceLabel.setText(Double.toString(price * Double.parseDouble(amountLabel.getText())));
		double totalPriceTemp = Double.parseDouble(totalPriceText.getText());
		totalPriceTemp += price;
		totalPriceText.setText(Double.toString(totalPriceTemp));
	}

	Map<Integer, ArrayList<String>> getAmounts() {
		return itemAmounts;
	}

	ArrayList<Item> getSelected() {
		return updatedItems;
	}

	@FXML
	void initialize() {
		updatedItems = selectedProducts;
		itemAmounts = itemToAmount;
	}

}
