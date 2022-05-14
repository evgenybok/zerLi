package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.Item;

public class CatalogController {

	@FXML
    private Button AddToCartBtn;
	
	@FXML
	private ImageView CartImage;

	@FXML
	private ImageView ClockImage;
	 
	@FXML
	private TextField AmountLabel;
	@FXML
	private ImageView DeliveryImage;

	@FXML
	private Button Back;

	@FXML
	private Pane chosenFlowerCart;
	
	@FXML
	private Button MinBtn;

	@FXML
	private Button PlusBtn;

	@FXML
	private ImageView flowerImage;

	@FXML
	private Label flowerName;

	@FXML
	private Label flowerPrice;

	@FXML
	private GridPane grid;

	@FXML
	private ScrollPane scroll;
	
	ArrayList<Item> selectedItems = new ArrayList<>();
	

	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}
	
    @FXML
    void btnPlus(MouseEvent event) {
		int amount = Integer.valueOf(AmountLabel.getText());
		if (amount < 50) {
			amount ++;
			AmountLabel.setText(Integer.toString(amount));
		}
		else {
			amount = 0;
			AmountLabel.setText(Integer.toString(amount));
		}
    }
    
    @FXML
    void btnMin(MouseEvent event) {
		int amount = Integer.valueOf(AmountLabel.getText());
		if (amount > 0) {
			amount --;
			AmountLabel.setText(Integer.toString(amount));
		}
		else {
			amount = 50;
			AmountLabel.setText(Integer.toString(amount));
		}
    }
    
    @FXML
    void btnMyCart(MouseEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/CartScreen.fxml")));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage cartStage = new Stage();
		cartStage.initModality(Modality.APPLICATION_MODAL);
		cartStage.setTitle("Cart");
		cartStage.setScene((new Scene(root1)));
		cartStage.show();
		cartStage.centerOnScreen();
    }

	private void setSelectedItem(Item item) {
		flowerName.setText(item.getName());
		flowerPrice.setText("\u20AA" + item.getPrice());
		Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc())));
		flowerImage.setImage(image);
	}
	@FXML
    void btnAddToCart(MouseEvent event) throws IOException {
		
}

	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {
		int column = 0;
		int row = 1;

		Image flower = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/clipart704080.png")));
		flowerImage.setImage(flower);
		Image clockImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Clock.png")));
		ClockImage.setImage(clockImage);
		Image cartImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Cart.png")));
		CartImage.setImage(cartImage);
		Image deliveryImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/delivery.png")));
		DeliveryImage.setImage(deliveryImage);

		ArrayList<Item> items = new ArrayList<>();
		chat.accept(new Message(MessageType.GET_PREMADE_ITEMS, null));
		items = (ArrayList<Item>) AnalyzeMessageFromServer.getData();
		if (items.size() > 0) {
			setSelectedItem(items.get(0));
		}
		selectedItems.equals(items);
		try {

			for (int i = 0; i < items.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/Item.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				ItemController itemController = fxmlLoader.getController();
				itemController.setData(items.get(i));

				if (column == 3) {
					column = 0;
					row++;

				}

				anchorPane.setId(Integer.toString(items.get(i).getID()));
				for (Item item : items) {
					if (Integer.toString(item.getID()).equals(anchorPane.getId())) {
						anchorPane.setOnMouseClicked(evt -> {
							flowerImage.setImage(new Image(Objects
									.requireNonNull(getClass().getResourceAsStream(item.getImgSrc().toString()))));
							flowerName.setText(item.getName());
							flowerPrice.setText("$" + item.getPrice());
						});
					}
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

		} catch (IOException e) {
			e.printStackTrace();
		}
		 assert MinBtn != null : "fx:id=\"MinBtn\" was not injected: check your FXML file 'Catalog.fxml'.";
	        assert PlusBtn != null : "fx:id=\"PlusBtn\" was not injected: check your FXML file 'Catalog.fxml'.";
	        assert AmountLabel != null : "fx:id=\"AmountLabel\" was not injected: check your FXML file 'Catalog.fxml'.";
	        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'Catalog.fxml'.";
		assert chosenFlowerCart != null
				: "fx:id=\"chosenFlowerCart\" was not injected: check your FXML file 'Catalog.fxml'.";
		assert flowerImage != null : "fx:id=\"flowerImage\" was not injected: check your FXML file 'Catalog.fxml'.";
		assert flowerName != null : "fx:id=\"flowerName\" was not injected: check your FXML file 'Catalog.fxml'.";
		assert flowerPrice != null : "fx:id=\"flowerPrice\" was not injected: check your FXML file 'Catalog.fxml'.";
		assert grid != null : "fx:id=\"grid\" was not injected: check your FXML file 'Catalog.fxml'.";
		assert scroll != null : "fx:id=\"scroll\" was not injected: check your FXML file 'Catalog.fxml'.";
	}

}
