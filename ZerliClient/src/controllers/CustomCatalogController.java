package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
	private Button viewCustomizedBouquet;

	public static ArrayList<Item> selectedProducts = new ArrayList<Item>();
	public static double totalPrice = 0;

	public static ArrayList<String[]> customItemInCart = new ArrayList<String[]>();
	public static Map<Integer, ArrayList<String>> itemToAmount = new HashMap<Integer, ArrayList<String>>();
	private int counter = 0;

	@FXML
	void btnAddToCart(MouseEvent event) {
		if (!selectedProducts.isEmpty()) {
			StringBuilder customItemDetails = new StringBuilder();
			for (int i = 0; i < selectedProducts.size(); i++) {
				customItemDetails.append(selectedProducts.get(i).getName() + "("
						+ itemToAmount.get(selectedProducts.get(i).getID()).get(3) + "), ");

			}
			if (customName.getText().equals("")) {
				String[] data = new String[] { "MyBouquet" + Integer.toString(counter + 1),
						customItemDetails.toString(), Double.toString(totalPrice) };
				customItemInCart.add(counter, data);
			} else {
				String[] data = new String[] { customName.getText(), customItemDetails.toString(),
						Double.toString(totalPrice) };
				customItemInCart.add(counter, data);
			}

			counter++;
			selectedProducts.clear();
			itemToAmount.clear();
			;
			totalPrice = 0;
			customName.setText("");
			JOptionPane.showMessageDialog(null, "Added the customized bouquet to the cart!", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "You did not add items for the customized bouquet!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@SuppressWarnings({ "unchecked" })
	@FXML
	void btnAddToCustomArrangement(MouseEvent event) {
		if (AmountLabel.getText().equals("0")) {
			JOptionPane.showMessageDialog(null, "You can not add 0 items to the customized bouquet!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			ArrayList<Item> items = new ArrayList<>();
			chat.accept(new Message(MessageType.GET_SELFASSEMBLY_ITEMS, null));
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
					totalPrice += Integer.parseInt(itemToAmount.get(selectedProducts.get(i).getID()).get(3))
							* selectedProducts.get(i).getPrice();
				}
			}
			JOptionPane.showMessageDialog(null, "Added the items to the customized bouquet!", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			AmountLabel.setText("0");
		}
	}

	@FXML
	void btnBack(MouseEvent event) throws IOException {
		selectedProducts.clear();
		itemToAmount.clear();
		;
		totalPrice = 0;
		customName.setText("");

		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}

	private void setSelectedItem(Item item) {
		flowerName.setText(item.getName());
		flowerPrice.setText("\u20AA" + item.getPrice()); // unicode for shekel
		Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc())));
		flowerImage.setImage(image);
		serialID.setText(Integer.toString(item.getID()));
	}

	@FXML
	void btnPlus(MouseEvent event) {
		int amount = Integer.valueOf(AmountLabel.getText());
		if (amount < 50) {
			amount++;
			AmountLabel.setText(Integer.toString(amount));
		} else {
			amount = 0;
			AmountLabel.setText(Integer.toString(amount));
		}

	}

	@FXML
	void btnMin(MouseEvent event) {
		int amount = Integer.valueOf(AmountLabel.getText());
		if (amount > 0) {
			amount--;
			AmountLabel.setText(Integer.toString(amount));
		} else {
			amount = 50;
			AmountLabel.setText(Integer.toString(amount));
		}
	}

	@FXML
	void btnMyCart(MouseEvent event) {
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

	@FXML
	void btnViewCustomizedBouquet(MouseEvent event) {
		try {
			if (selectedProducts.isEmpty())
				JOptionPane.showMessageDialog(null, "You did not add items for the customized bouquet!", "Error",
						JOptionPane.ERROR_MESSAGE);
			else {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/CustomItemViewScreen.fxml")));
				Parent root1 = (Parent) fxmlLoader.load();
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

	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {
		int column = 0;
		int row = 1;

		Image clockImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Clock.png")));
		ClockImage.setImage(clockImage);
		Image cartImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Cart.png")));
		CartImage.setImage(cartImage);
		Image deliveryImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/delivery.png")));
		DeliveryImage.setImage(deliveryImage);
		ArrayList<Item> items = new ArrayList<>();
		chat.accept(new Message(MessageType.GET_SELFASSEMBLY_ITEMS, null));
		items = (ArrayList<Item>) AnalyzeMessageFromServer.getData();

		if (items.size() > 0) {
			setSelectedItem(items.get(0));
		}

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

}