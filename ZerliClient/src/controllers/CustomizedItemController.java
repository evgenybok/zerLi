package controllers;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.Item;

public class CustomizedItemController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label amountLabel;

	@FXML
	private ImageView img;

	@FXML
	private Label nameLabel;

	@FXML
	private Label priceLabel;

	@FXML
	private Label totalPriceLabel;

	public void setData(Item item, String amount) {
		amountLabel.setText(amount);
		priceLabel.setText("¤" + item.getPrice());
		nameLabel.setText(item.getName());
		totalPriceLabel.setText(Double.toString(Integer.parseInt(amount) * item.getPrice()));
		Image image = new Image((Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc()))));
		img.setImage(image);
	}

	@FXML
	void initialize() {
		assert amountLabel != null
				: "fx:id=\"amountLabel\" was not injected: check your FXML file 'CustomizedItem.fxml'.";
		assert img != null : "fx:id=\"img\" was not injected: check your FXML file 'CustomizedItem.fxml'.";
		assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'CustomizedItem.fxml'.";
		assert priceLabel != null
				: "fx:id=\"priceLabel\" was not injected: check your FXML file 'CustomizedItem.fxml'.";
		assert totalPriceLabel != null
				: "fx:id=\"totalPriceLabel\" was not injected: check your FXML file 'CustomizedItem.fxml'.";

	}

}
