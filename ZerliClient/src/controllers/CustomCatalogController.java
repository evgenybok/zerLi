package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

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

/**
 * @author Evgeny Shows the custom catalog, here the user can choose which items
 *         to add to the cart.
 */
public class CustomCatalogController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField AmountLabel;

	@FXML
	private Button Back;

	@FXML
	private ImageView CartImage;

	@FXML
	private ImageView ClockImage;

	@FXML
	private ImageView DeliveryImage;

	@FXML
	private Button MinBtn;

	@FXML
	private Button PlusBtn;

	@FXML
	private Button addToCart;

	@FXML
	private Pane chosenFlowerCart;

	@FXML
	private TextField customName;

	@FXML
	private ImageView flowerImage;

	@FXML
	private Label flowerName;

	@FXML
	private Label flowerPrice;

	@FXML
	private GridPane grid;

	@FXML
	private Button itemsInCustomBouquet;

	@FXML
	private ScrollPane scroll;

	@FXML
	private Label serialID;

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

	@FXML
	private ImageView saleImg;

	@FXML
	private Label lblSalePrice;

	@FXML
	private Label lblMyCart;

	@FXML
	private Button viewCustomizedBouquet;

	@FXML
	private Label msgLabel;

	static Button staticViewCustomizedBouquet;

	static Button staticAddToCart;

	public static Stage customCatalogStage; // *********************

	public static ArrayList<Item> selectedProducts = new ArrayList<Item>();
	private ArrayList<Item> catalogProducts = new ArrayList<Item>();
	public static double totalPrice = 0;

	public static ArrayList<String[]> customItemInCart = new ArrayList<String[]>();
	public static Map<Integer, ArrayList<String>> itemToAmount = new HashMap<Integer, ArrayList<String>>();
	static int bouquetCounter = 0;
	private int bouquetNumber = 1;
	private ArrayList<Item> items;

	/**
	 * Button to add the selected product and amount to the cart.
	 * 
	 * @param event
	 */
	@FXML
	void btnAddToCart(MouseEvent event) {
		if (!selectedProducts.isEmpty()) {
			StringBuilder customItemDetails = new StringBuilder();
			for (int i = 0; i < selectedProducts.size(); i++) {
				customItemDetails.append(selectedProducts.get(i).getName() + "("
						+ itemToAmount.get(selectedProducts.get(i).getID()).get(3) + "), ");

			}
			if (customName.getText().equals("")) {
				String[] data = new String[] { "MyBouquet" + Integer.toString(bouquetCounter + 1),
						customItemDetails.toString(), Double.toString(totalPrice), Integer.toString(bouquetNumber++) };
				customItemInCart.add(bouquetCounter, data);
			} else {
				String[] data = new String[] { customName.getText(), customItemDetails.toString(),
						Double.toString(totalPrice), Integer.toString(bouquetNumber++) };
				customItemInCart.add(bouquetCounter, data);
			}

			bouquetCounter++;
			selectedProducts.clear();
			itemToAmount.clear();
			;
			totalPrice = 0;
			customName.setText("");
			if (!CustomerScreenController.accountStatus.equals("Frozen")) {
				viewCustomizedBouquet.setDisable(true);
				addToCart.setDisable(true);
			}
			msgLabel.setText("The item was added to the cart");
			msgLabel.setVisible(true);
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					msgLabel.setVisible(false);
				}
			}, 2000);
		} else {
			msgLabel.setText("The item was added to the cart");
			msgLabel.setVisible(true);
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					msgLabel.setVisible(false);
				}
			}, 2000);
		}
	}

	/**
	 * Adds the custom items to a bouquet made of various items which can later be
	 * named and added to the cart
	 * 
	 * @param event
	 */
	// @SuppressWarnings({ "unchecked" })
	@FXML
	void btnAddToCustomArrangement(MouseEvent event) {
		if (AmountLabel.getText().equals("0")) {
			msgLabel.setText("Cannot add 0 items!");
			msgLabel.setVisible(true);
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					msgLabel.setVisible(false);
				}
			}, 2000);
		} else {

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
					details.add(selectedProducts.get(i).getName());
					if (selectedProducts.get(i).isOnSale()) {
						details.add(Double.toString(selectedProducts.get(i).getSalePrice()));
					} else
						details.add(Double.toString(selectedProducts.get(i).getPrice()));
					details.add(selectedProducts.get(i).getImgSrc());
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
					if (selectedProducts.get(i).isOnSale()) {
						totalPrice += Integer.parseInt(itemToAmount.get(selectedProducts.get(i).getID()).get(3))
								* selectedProducts.get(i).getSalePrice();
					} else
						totalPrice += Integer.parseInt(itemToAmount.get(selectedProducts.get(i).getID()).get(3))
								* selectedProducts.get(i).getPrice();
				}
			}
			if (!selectedProducts.isEmpty() && !CustomerScreenController.accountStatus.equals("Frozen")) {
				viewCustomizedBouquet.setDisable(false);
				addToCart.setDisable(false);
			} else {
				viewCustomizedBouquet.setDisable(true);
				addToCart.setDisable(true);
			}

			msgLabel.setText("Added the items!");
			msgLabel.setVisible(true);
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					msgLabel.setVisible(false);
				}
			}, 2000);
			AmountLabel.setText("0");
		}
	}

	/**
	 * closes current screen and opens the customer screen.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnBack(MouseEvent event) throws IOException {
		selectedProducts.clear();
		itemToAmount.clear();
		;
		totalPrice = 0;
		customName.setText("");

		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerScreen.fxml")));
		parent.getStylesheets().clear();
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}

	/**
	 * Sets selected item field with the data from given item.
	 * 
	 * @param item
	 */
	private void setSelectedItem(Item item) {
		flowerName.setText(item.getName());
		flowerPrice.setText("\u20AA" + item.getPrice()); // unicode for shekel
		Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc())));
		flowerImage.setImage(image);
		serialID.setText(Integer.toString(item.getID()));
	}

	/**
	 * increments the amount of currently shown item to add to the cart.
	 * 
	 * @param event
	 */
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
	}

	/**
	 * decrements the amount of currently shown item to add to the cart.
	 * 
	 * @param event
	 */
	@FXML
	void btnMin(MouseEvent event) {
		int amount = Integer.valueOf(AmountLabel.getText());
		if (amount > 0) {
			amount--;
			AmountLabel.setText(Integer.toString(amount));
		} else {
			amount = 20;
			AmountLabel.setText(Integer.toString(amount));
		}
	}

	/**
	 * Opens the cart screen to show the user which items are inside his cart.
	 * 
	 * @param event
	 */
	@FXML
	void btnMyCart(MouseEvent event) {
		try {
			customCatalogStage = (Stage) addToCart.getScene().getWindow(); // *********************

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/CartScreen.fxml")));
			Parent root1 = (Parent) fxmlLoader.load();
			root1.getStylesheets().add("css/styleNew.css");
			root1.getStylesheets().add("css/transTextArea.css");
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

	/**
	 * Opens a new screen in which the user can view his customized bouquet.
	 * 
	 * @param event
	 */
	@FXML
	void btnViewCustomizedBouquet(MouseEvent event) {
		try {
			if (selectedProducts.isEmpty())
				return;
			else {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/CustomItemViewScreen.fxml")));
				Parent root1 = (Parent) fxmlLoader.load();
				root1.getStylesheets().add("css/styleNew.css");
				root1.getStylesheets().add("css/transTextArea.css");
				Stage customizedItemDetailsScreen = new Stage();
				customizedItemDetailsScreen.initModality(Modality.APPLICATION_MODAL);
				customizedItemDetailsScreen.setTitle("Customized Item Details");
				customizedItemDetailsScreen.setScene((new Scene(root1)));
				customizedItemDetailsScreen.show();
				customizedItemDetailsScreen.centerOnScreen();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Search by color - filters the catalog to match the selected color input.
	 * 
	 * @param event
	 */
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
			chat.accept(new Message(MessageType.GET_SELFASSEMBLY_ITEMS, null));
			items = (ArrayList<Item>) AnalyzeMessageFromServer.getData();
			for (Item item : items) {
				if (item.getPrice() >= from && item.getPrice() <= to && item.getType().equals("Self Assembly"))
					tempSelectedItems.add(item);
			}
			catalogProducts = new ArrayList<Item>(tempSelectedItems);
			initialize();
			return;
		}
		color = txtColor.getText();
		color.toLowerCase();
		color = color.substring(0, 1).toUpperCase() + color.substring(1);
		if (catalogProducts.isEmpty()) {
			for (Item item : items) {
				if (color.equals(item.getColor()))
					tempSelectedItems.add(item);
			}
		} else {
			for (Item item : catalogProducts) {
				if (color.equals(item.getColor()))
					tempSelectedItems.add(item);
			}
		}
		if (tempSelectedItems.isEmpty()) {
			for (Item item : items) {
				if (color.equals(item.getColor()))
					tempSelectedItems.add(item);
			}
		}
		if (!tempSelectedItems.isEmpty()) {
			catalogProducts = new ArrayList<Item>(tempSelectedItems);
			initialize();
		} else
			JOptionPane.showMessageDialog(null, "No items found with the color " + color + ".", "Info",
					JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Serach by price - filters the current catalog to match the selected price
	 * range.
	 * 
	 * @param event
	 */
	@FXML
	void clkPriceRange(MouseEvent event) {
		ArrayList<Item> tempSelectedItems = new ArrayList<Item>();
		double from, to;
		String color = null;
		if (!txtColor.getText().isEmpty()) {
			color = txtColor.getText();
			color.toLowerCase();
			color = color.substring(0, 1).toUpperCase() + color.substring(1);
		}
		if (txtFrom.getText().isEmpty())
			from = 0;
		else
			from = Double.parseDouble(txtFrom.getText());
		if (txtTo.getText().isEmpty())
			to = Integer.MAX_VALUE;
		else
			to = Double.parseDouble(txtTo.getText());
		if (from > to || to < from)
			JOptionPane.showMessageDialog(null, "Invalid price range entered", "Error", JOptionPane.ERROR_MESSAGE);
		if (!(txtColor.getText().isEmpty())) {
			for (Item item : items) {
				if (item.getPrice() >= from && item.getPrice() <= to && item.getType().equals("Self Assembly")
						&& color.equals(item.getColor())) {
					tempSelectedItems.add(item);
				}
			}
		} else {
			for (Item item : items) {
				if (item.getPrice() >= from && item.getPrice() <= to && item.getType().equals("Self Assembly")) {
					tempSelectedItems.add(item);
				}
			}
		}
		if (!tempSelectedItems.isEmpty()) {
			catalogProducts = new ArrayList<Item>(tempSelectedItems);
			initialize();
		}
	}

	/**
	 * Initialization: shows all of the avaliable items from the DB that are self
	 * assembly (custom) in the table.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {
		msgLabel.setVisible(false);
		grid.getChildren().clear();
		staticAddToCart = addToCart;
		staticViewCustomizedBouquet = viewCustomizedBouquet;
		if (selectedProducts.isEmpty()) {
			addToCart.setDisable(true);
			viewCustomizedBouquet.setDisable(true);
		}
		if (CustomerScreenController.accountStatus.equals("Frozen")) {
			itemsInCustomBouquet.setDisable(true);
			addToCart.setDisable(true);
			CartImage.setDisable(true);
			lblMyCart.setDisable(true);
		}
		int column = 0;
		int row = 1;

		Image clockImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Clock.png")));
		ClockImage.setImage(clockImage);
		Image cartImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Cart.png")));
		CartImage.setImage(cartImage);
		Image deliveryImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/delivery.png")));
		DeliveryImage.setImage(deliveryImage);
		chat.accept(new Message(MessageType.GET_SELFASSEMBLY_ITEMS, null));
		items = (ArrayList<Item>) AnalyzeMessageFromServer.getData();

		if (items.size() > 0) {
			setSelectedItem(items.get(0));
		}
		if (catalogProducts.isEmpty()) {
			catalogProducts = new ArrayList<Item>(items);
		}
		try {

			for (int i = 0; i < catalogProducts.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/Item.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				ItemController itemController = fxmlLoader.getController();
				itemController.setData(catalogProducts.get(i));

				if (column == 3) {
					column = 0;
					row++;

				}

				anchorPane.setId(Integer.toString(catalogProducts.get(i).getID()));
				flowerImage.setImage(new Image(Objects.requireNonNull(
						getClass().getResourceAsStream(catalogProducts.get(i).getImgSrc().toString()))));
				flowerName.setText(catalogProducts.get(i).getName());
				flowerPrice.setText("\u20AA" + catalogProducts.get(i).getPrice());
				serialID.setText(Integer.toString(catalogProducts.get(i).getID()));
				if (catalogProducts.get(i).isOnSale()) {
					lblSalePrice.setText("\u20AA" + catalogProducts.get(i).getSalePrice());
					lblSalePrice.setVisible(true);
					Image saleImage = new Image(
							(Objects.requireNonNull(getClass().getResourceAsStream("/images/sale1.png"))));
					saleImg.setImage(saleImage);
					saleImg.setVisible(true);
				} else {
					lblSalePrice.setVisible(false);
					saleImg.setVisible(false);
				}
				for (Item item : catalogProducts) {
					if (Integer.toString(item.getID()).equals(anchorPane.getId())) {
						anchorPane.setOnMouseClicked(evt -> {
							flowerImage.setImage(new Image(Objects
									.requireNonNull(getClass().getResourceAsStream(item.getImgSrc().toString()))));
							flowerName.setText(item.getName());
							flowerPrice.setText("\u20AA" + item.getPrice());
							serialID.setText(Integer.toString(item.getID()));
							if (item.isOnSale()) {
								lblSalePrice.setText("\u20AA" + item.getSalePrice());
								lblSalePrice.setVisible(true);
								Image saleImage = new Image(
										(Objects.requireNonNull(getClass().getResourceAsStream("/images/sale1.png"))));
								saleImg.setImage(saleImage);
								saleImg.setVisible(true);
							} else {
								lblSalePrice.setVisible(false);
								saleImg.setVisible(false);
							}
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

}