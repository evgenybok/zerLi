package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;

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
import javafx.stage.StageStyle;
import logic.Item;

/**
 * @author Evgeny
 * Shows the premade catalog, here the user can choose which items to add to the cart.
 */
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

	@FXML
	private ImageView saleImg;

	@FXML
	private Label lblSalePrice;
	
    @FXML
    private Label lblMyCart;

    @FXML
    private Label msgLabel;
    
	@FXML
	public static Stage premadeCatalogStage;

	static Button addToCart;

	public static ArrayList<Item> selectedProducts = new ArrayList<Item>();
	public double totalPrice;

	// A map that wires an item for the selected amount. ( map.get(item)==amount )
	public static Map<Integer, ArrayList<String>> itemToAmount = new HashMap<Integer, ArrayList<String>>();

	ArrayList<Item> selectedItems = new ArrayList<>();
	ArrayList<Item> items = new ArrayList<>();
	static int premadeBouquetNumber = 0;


	@FXML
    void buttonClick(MouseEvent event)
    {
        if(event.getClickCount()==1)
        {

            for(Node ap : grid.getChildren())
            {
            if(ap.isFocused() || scroll.isFocused())
            {
                msgLabel.setVisible(true);
            }
            }
        }
    }
	/**
	 * closes current screen and opens the customer screen.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerScreen.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}

	/**
	 * increments the amount of currently shown item to add to the cart.
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
		if (amount != 0)
			AddToCartBtn.setDisable(false);
		else
			AddToCartBtn.setDisable(true);
		if (amount > 0)
			AddToCartBtn.setDisable(false);
		if (CustomerScreenController.accountStatus.equals("Frozen"))
			AddToCartBtn.setDisable(true);
	}

	/**
	 * decrements the amount of currently shown item to add to the cart.
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
		if (amount != 0)
			AddToCartBtn.setDisable(false);
		else
			AddToCartBtn.setDisable(true);
		if (CustomerScreenController.accountStatus.equals("Frozen"))
			AddToCartBtn.setDisable(true);
	}

	/**
	 * Opens the cart screen to show the user which items are inside his cart.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnMyCart(MouseEvent event) throws IOException {
		premadeCatalogStage = (Stage) AddToCartBtn.getScene().getWindow();

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/CartScreen.fxml")));
			Parent root1 = (Parent) fxmlLoader.load();
			root1.getStylesheets().add("css/styleNew.css");
			root1.getStylesheets().add("css/transTextArea.css");
			Stage cartDetailsScreen = new Stage();
			cartDetailsScreen.initModality(Modality.APPLICATION_MODAL);
			cartDetailsScreen.initStyle(StageStyle.UNDECORATED);
			cartDetailsScreen.setTitle("Cart Details");
			cartDetailsScreen.setScene((new Scene(root1)));
			cartDetailsScreen.show();
			cartDetailsScreen.centerOnScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Sets selected item's data according to the item data taken from the DB.
	 * @param item
	 */
	private void setSelectedItem(Item item) {
		flowerName.setText(item.getName());
		flowerPrice.setText("\u20AA" + item.getPrice());
		Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc())));
		flowerImage.setImage(image);
		serialID.setText(Integer.toString(item.getID()));
	}

	
	/**
	 * Button to add the selected product and amount to the cart.
	 * @param event
	 * @throws IOException
	 */
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
			premadeBouquetNumber++;
			AmountLabel.setText("0");
			msgLabel.setVisible(true);
			new java.util.Timer().schedule( new java.util.TimerTask() {
				@Override
				public void run() {
					msgLabel.setVisible(false);
				}
			}, 2000);
		}
		
	}

	/**
	 * Search by color - filters the catalog to match the selected color input.
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
			for (Item item : items) {
				if (item.isOnSale()) {
					if (item.getSalePrice() >= from && item.getSalePrice() <= to && item.getType().equals("Premade"))
						tempSelectedItems.add(item);
				} else {
					if (item.getPrice() >= from && item.getPrice() <= to && item.getType().equals("Premade"))
						tempSelectedItems.add(item);
				}
			}
			selectedItems = new ArrayList<Item>(tempSelectedItems);
			initialize();
			return;
		}
		color = txtColor.getText();
		color.toLowerCase();
		color = color.substring(0, 1).toUpperCase() + color.substring(1);
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

	/**
	 * Serach by price - filters the current catalog to match the selected price range.
	 * @param event
	 */
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
			String color = txtColor.getText();
			color.toLowerCase();
			color = color.substring(0, 1).toUpperCase() + color.substring(1);
			chat.accept(new Message(MessageType.GET_PREMADE_ITEMS, null));
			items = (ArrayList<Item>) AnalyzeMessageFromServer.getData();
			for (Item item : items) {
				if (item.isOnSale()) {
					if (item.getSalePrice() >= from && item.getSalePrice() <= to && item.getType().equals("Premade")
							&& color.equals(item.getColor())) {
						tempSelectedItems.add(item);
					}
				} else {
					if (item.getPrice() >= from && item.getPrice() <= to && item.getType().equals("Premade")
							&& color.equals(item.getColor())) {
						tempSelectedItems.add(item);
					}
				}
			}
		} else {
			for (Item item : items) {
				if (item.isOnSale()) {
					if (item.getSalePrice() >= from && item.getSalePrice() <= to && item.getType().equals("Premade")) {
						tempSelectedItems.add(item);
					}
				} else {
					if (item.getPrice() >= from && item.getPrice() <= to && item.getType().equals("Premade")) {
						tempSelectedItems.add(item);
					}
				}
			}
		}
		if (!tempSelectedItems.isEmpty()) {
			selectedItems = new ArrayList<Item>(tempSelectedItems);
			initialize();
		}
	}

	/**
	 * Initialization: shows all of the avaliable items from the DB that are premade in the table.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {
		msgLabel.setVisible(false);
		grid.getChildren().clear();
		addToCart = AddToCartBtn;
		AddToCartBtn.setDisable(true);
		if (CustomerScreenController.accountStatus.equals("Frozen")) {
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
				flowerImage.setImage(new Image(Objects
						.requireNonNull(getClass().getResourceAsStream(selectedItems.get(i).getImgSrc().toString()))));
				flowerName.setText(selectedItems.get(i).getName());
				flowerPrice.setText("\u20AA" + selectedItems.get(i).getPrice());
				serialID.setText(Integer.toString(selectedItems.get(i).getID()));
				if (selectedItems.get(i).isOnSale()) {
					lblSalePrice.setText("\u20AA" + selectedItems.get(i).getSalePrice());
					lblSalePrice.setVisible(true);
					Image saleImage = new Image(
							(Objects.requireNonNull(getClass().getResourceAsStream("/images/sale1.png"))));
					saleImg.setImage(saleImage);
					saleImg.setVisible(true);
				} else {
					lblSalePrice.setVisible(false);
					saleImg.setVisible(false);
				}
				for (Item item : selectedItems) {
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
