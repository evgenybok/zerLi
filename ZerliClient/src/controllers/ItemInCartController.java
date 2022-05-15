package controllers;

import java.net.URL;
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

public class ItemInCartController {

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
    private Button deleteItem;

	public void setData(Item item, String amount) {
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
    void btnDeleteItem(MouseEvent event) {

    }

	@FXML
	void initialize() {
		nameLabel.getStyleClass().add("images/css/transTextArea.css");
        assert amountLabel != null : "fx:id=\"amountLabel\" was not injected: check your FXML file 'ItemInCart.fxml'.";
        assert deleteItem != null : "fx:id=\"deleteItem\" was not injected: check your FXML file 'ItemInCart.fxml'.";
        assert img != null : "fx:id=\"img\" was not injected: check your FXML file 'ItemInCart.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'ItemInCart.fxml'.";
        assert priceLabel != null : "fx:id=\"priceLabel\" was not injected: check your FXML file 'ItemInCart.fxml'.";
        assert totalPriceLabel != null : "fx:id=\"totalPriceLabel\" was not injected: check your FXML file 'ItemInCart.fxml'.";

	}

}
