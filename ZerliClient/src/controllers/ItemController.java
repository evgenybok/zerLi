package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.Item;

import java.util.Objects;

public class ItemController {

	@FXML
	private ImageView img;

	@FXML
	private Label nameLabel;

	@FXML
	private Label priceLabel;

	@FXML
	private Label serial;

	@FXML
	private Label lblOnSale;

	@FXML
	private Label lblSalePrice;

	@FXML
	private Label lblSlash;

	@FXML
	private ImageView saleImg;

	public void setData(Item item) {

		try {
			priceLabel.setText("\u20AA" + item.getPrice());
			nameLabel.setText(item.getName());
			Image image = new Image((Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc()))));
			img.setImage(image);
			serial.setText(Integer.toString(item.getID()));
			if (item.isOnSale()) {
				Image saleImage = new Image((Objects.requireNonNull(getClass().getResourceAsStream("/images/sale1.png"))));
				saleImg.setImage(saleImage);
				saleImg.setVisible(true);
				lblSalePrice.setText("\u20AA" + Double.toString(item.getSalePrice()));
				lblSalePrice.setVisible(true);
				lblSlash.setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
