package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.JOptionPane;

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
	private Label serialID;

	@FXML
	private ScrollPane scroll;

	@FXML
	private Button btnPriceRange;

	@FXML
	private TextField txtFrom;

	@FXML
	private TextField txtTo;

	@FXML
	private Button btnColor;

	@FXML
	private TextField txtColor;

	static Button addToCart;

	public static ArrayList<Item> selectedProducts = new ArrayList<Item>();
	public double totalPrice;

	// A map that wires an item for the selected amount. ( map.get(item)==amount )
	public static Map<Integer, ArrayList<String>> itemToAmount = new HashMap<Integer, ArrayList<String>>();

	ArrayList<Item> selectedItems = new ArrayList<>();
	ArrayList<Item> items = new ArrayList<>();

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
		if (amount < 20) {
			amount++;
			AmountLabel.setText(Integer.toString(amount));
		} else {
			amount = 0;
			AmountLabel.setText(Integer.toString(amount));
		}
		if (amount > 0 && !CustomerScreenController.accountStatus.equals("Frozen"))
			AddToCartBtn.setDisable(false);
	}

	@FXML
	void btnMin(MouseEvent event) {
		int amount = Integer.valueOf(AmountLabel.getText());
		if (amount > 0) {
			amount--;
			AmountLabel.setText(Integer.toString(amount));
		} else {
			amount = 20;
			AmountLabel.setText(Integer.toString(amount));
			if (!CustomerScreenController.accountStatus.equals("Frozen"))
				AddToCartBtn.setDisable(true);
		}
	}

	@FXML
	void btnMyCart(MouseEvent event) throws IOException {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/CartScreen.fxml")));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage cartDetailsScreen = new Stage();
			cartDetailsScreen.initModality(Modality.APPLICATION_MODAL);
			cartDetailsScreen.setTitle("Cart Details");
			cartDetailsScreen.setScene((new Scene(root1)));
			cartDetailsScreen.show();
			cartDetailsScreen.centerOnScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void setSelectedItem(Item item) {
		flowerName.setText(item.getName());
		flowerPrice.setText("\u20AA" + item.getPrice());
		Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc())));
		flowerImage.setImage(image);
		serialID.setText(Integer.toString(item.getID()));
	}

	@SuppressWarnings("unchecked")
	@FXML
	void btnAddToCart(MouseEvent event) throws IOException {
		if (AmountLabel.getText().equals("0")) {
			JOptionPane.showMessageDialog(null, "You can not add 0 items to the cart!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			ArrayList<Item> items = new ArrayList<>();
			chat.accept(new Message(MessageType.GET_PREMADE_ITEMS, null));
			items = (ArrayList<Item>) AnalyzeMessageFromServer.getData();

			boolean flag = false;
			for (Item item : items) {
				if (Integer.toString(item.getID()).equals(serialID.getText())) {
					try {
						for (int k = 0; k < selectedProducts.size(); k++) {
							Item temp = selectedProducts.get(k);
							if (temp.getID() == item.getID()) {
								flag = true;
							}
						}
					} catch (Exception e) {
					}
					;
					if (!flag) {
						selectedProducts.add(item);
						flag = false;
					}
				}
			}
			boolean amountChanged = false;
			for (int i = 0; i < selectedProducts.size(); i++) {
				if (selectedProducts.get(i).getID() == Integer.parseInt(serialID.getText())) {
					ArrayList<String> details = new ArrayList<String>();
					details.add(items.get(i).getName());
					details.add(Double.toString(items.get(i).getPrice()));
					details.add(items.get(i).getImgSrc());
					if (itemToAmount.containsKey(Integer.parseInt(serialID.getText()))) {
						if (!amountChanged) {
							String tempAmount = itemToAmount.get(Integer.parseInt(serialID.getText())).get(3)
									.toString();
							Integer newAmount = Integer.parseInt(tempAmount) + Integer.parseInt(AmountLabel.getText());
							details.add(newAmount.toString());
							amountChanged = true;
						}
					} else
						details.add(AmountLabel.getText());
					itemToAmount.put(Integer.parseInt(serialID.getText()), details);
				}
			}
			AddToCartBtn.setDisable(true);
			JOptionPane.showMessageDialog(null, "Added the bouquet(s) to the cart!", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			AmountLabel.setText("0");
		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {
		grid.getChildren().clear();
		addToCart = AddToCartBtn;
		AddToCartBtn.setDisable(true);
		if (CustomerScreenController.accountStatus.equals("Frozen")) {
			// AddToCartBtn.setDisable(true);
			CartImage.setDisable(true);
		}
		int column = 0;
		int row = 1;

		Image clockImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Clock.png")));
		ClockImage.setImage(clockImage);
		Image cartImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Cart.png")));
		CartImage.setImage(cartImage);
		Image deliveryImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/delivery.png")));
		DeliveryImage.setImage(deliveryImage);

		chat.accept(new Message(MessageType.GET_PREMADE_ITEMS, null));
		items = (ArrayList<Item>) AnalyzeMessageFromServer.getData();
		if (items.size() > 0) {
			setSelectedItem(items.get(0));
		}
		if (selectedItems.isEmpty())
			selectedItems = new ArrayList<Item>(items);
		try {

			for (int i = 0; i < selectedItems.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/Item.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				ItemController itemController = fxmlLoader.getController();
				itemController.setData(selectedItems.get(i));

				if (column == 3) {
					column = 0;
					row++;
				}

				anchorPane.setId(Integer.toString(selectedItems.get(i).getID()));
				for (Item item : selectedItems) {
					if (Integer.toString(item.getID()).equals(anchorPane.getId())) {
						anchorPane.setOnMouseClicked(evt -> {
							flowerImage.setImage(new Image(Objects
									.requireNonNull(getClass().getResourceAsStream(item.getImgSrc().toString()))));
							flowerName.setText(item.getName());
							flowerPrice.setText("\u20AA" + item.getPrice());
							serialID.setText(Integer.toString(item.getID()));
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
	}

	@FXML
	void clkColor(MouseEvent event) {
		String color;
		ArrayList<Item> tempSelectedItems = new ArrayList<Item>();
		if (txtColor.getText().isEmpty()) {
			double from, to;
			if (txtFrom.getText().isEmpty())
				from = 0;
			else
				from = Double.parseDouble(txtFrom.getText());
			if (txtTo.getText().isEmpty())
				to = Integer.MAX_VALUE;
			else
				to = Double.parseDouble(txtTo.getText());
			for (Item item : items) {
				if (item.getPrice() >= from && item.getPrice() <= to && item.getType().equals("Premade"))
					tempSelectedItems.add(item);
			}
			selectedItems = new ArrayList<Item>(tempSelectedItems);
			initialize();
			return;
		}
		color = txtColor.getText();
		if (selectedItems.isEmpty()) {
			for (Item item : items) {
				if (color.equals(item.getColor()))
					tempSelectedItems.add(item);
			}
		} else {
			for (Item item : selectedItems) {
				if (color.equals(item.getColor()))
					tempSelectedItems.add(item);
			}
		}
		if (!tempSelectedItems.isEmpty()) {
			selectedItems = new ArrayList<Item>(tempSelectedItems);
			initialize();
		} else
			JOptionPane.showMessageDialog(null, "No bouquets found with the color " + color + ".", "Info",
					JOptionPane.INFORMATION_MESSAGE);

	}

	@FXML
	void clkPriceRange(MouseEvent event) {
		ArrayList<Item> tempSelectedItems = new ArrayList<Item>();
		double from, to;
		if (txtFrom.getText().isEmpty())
			from = 0;
		else
			from = Double.parseDouble(txtFrom.getText());
		if (txtTo.getText().isEmpty())
			to = Integer.MAX_VALUE;
		else
			to = Double.parseDouble(txtTo.getText());
		if (!(txtColor.getText().isEmpty())) {
			for (Item item : items) {
				if (item.getPrice() >= from && item.getPrice() <= to && item.getType().equals("Premade")
						&& txtColor.getText().equals(item.getColor())) {
					tempSelectedItems.add(item);
				}
			}
		} else {
			for (Item item : items) {
				if (item.getPrice() >= from && item.getPrice() <= to && item.getType().equals("Premade")) {
					tempSelectedItems.add(item);
				}
			}
		}
		if (!tempSelectedItems.isEmpty()) {
			selectedItems = new ArrayList<Item>(tempSelectedItems);
			initialize();
		}
	}
}
