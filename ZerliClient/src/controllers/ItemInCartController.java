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

/**
 * @author Evgeny
 * Single item used in cart and in the custom bouquet.
 */
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

	@FXML
	private Button btnDelete;

	Item item;
	public static ArrayList<Item> originalSelectedProducts;
	public static Map<Integer, ArrayList<String>> originalItemToAmounts;
	// temporary map and array for the cart. (this is what is being sent to the
	// checkout)
	public static Map<Integer, ArrayList<String>> cartItemToAmountPremade;
	public static ArrayList<Item> cartSelectedProducts;

	static int originalBouquetCounter;
	private int bouquetNumber = 1;

	/**
	 * Sets the custom item data and amount in the custom bouquet.
	 * @param item chosen item
	 * @param amount amount of selected item
	 */
	public void setData(Item item, String amount) {
		this.item = item;
		amountLabel.setText(amount);
		if (!item.isOnSale()) {
			priceLabel.setText("\u20AA" + item.getPrice()); // shekel unicode
			totalPriceLabel.setText("\u20AA" + Double.toString(Integer.parseInt(amount) * item.getPrice()));
		} else {
			priceLabel.setText("\u20AA" + item.getSalePrice()); // shekel unicode
			totalPriceLabel.setText("\u20AA" + Double.toString(Integer.parseInt(amount) * item.getSalePrice()));
		}
		nameLabel.setText(item.getName());

		Image image = new Image((Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc()))));
		img.setImage(image);
	}

	/**
	 * Sets the custom item data in the cart
	 * @param customItemData data for the custom item shown below
	 */
	public void setData(String[] customItemData) {
		bouquetNumber = Integer.parseInt(customItemData[3]);
		amountLabel.setText("1");
		priceLabel.setText("\u20AA" + customItemData[2].toString());
		totalPriceLabel.setText("\u20AA" + customItemData[2]);
		nameLabel.setText(customItemData[0] + ": " + customItemData[1]);
		Image image = new Image((Objects.requireNonNull(getClass().getResourceAsStream("/images/SA2.png"))));
		img.setImage(image);
	}

	/**
	 * decrements the amount of the selected item.
	 * @param event
	 */
	@FXML
	void btnMin(MouseEvent event) {
		int amount = Integer.valueOf(amountLabel.getText());
		int indexToDelete = 0;
		boolean alreadyChanged = false;
		if (amount > 0) {
			amount--;
			amountLabel.setText(Integer.toString(amount));
			ArrayList<String> updateItemAmount = new ArrayList<String>(); /* new array list to update amount */
			/* Only done for the Custom Catalog */
			if (!(item == null) && item.getType().equals("Self Assembly")) {
				updateItemAmount.add(Integer.toString(item.getID()));
				updateItemAmount.add(priceLabel.getText().substring(1));
				updateItemAmount.add(item.getImgSrc());
				updateItemAmount.add(Integer.toString(amount));
				customItemToAmount.put(item.getID(), updateItemAmount);
			} else if (!(item == null) && (item.getType().equals("Premade"))) {
				ArrayList<String> tempItemPremade = CatalogController.itemToAmount.get(item.getID());
				int tempAmount = Integer.parseInt(tempItemPremade.get(3));
				tempItemPremade.set(3, Integer.toString(tempAmount - 1));
				CatalogController.itemToAmount.put(item.getID(), tempItemPremade);
				// Custom bouquet - deleting item from cart when amount is 0
			}
			if (item == null && amount == 0) {
				for (String[] bouquet : CartController.customItemInCart)
					if (bouquet[3].equals(Integer.toString(bouquetNumber))) {
						double totalPriceTemp = Double
								.parseDouble(CartController.totalItemsPrice.getText().substring(1));
						totalPriceTemp -= Double.parseDouble((bouquet[2]));
						CartController.totalItemsPrice.setText("\u20AA" + Double.toString(totalPriceTemp));
						CartController.staticGrid.getChildren().remove(CartController.selectedProductsPremade.size()
								+ CartController.customItemInCart.indexOf(bouquet));
						CartController.customItemInCart.remove(bouquet);
						CustomCatalogController.bouquetCounter--;
						alreadyChanged = true;
						return;
					}
			}
			// Premade catalog - deleting item from cart when amount is 0
			if (!(item == null) && item.getType().equals("Premade") && amount == 0) {
				indexToDelete = CartController.selectedProductsPremade.indexOf(item);
				double totalPriceTemp = Double.parseDouble(CartController.totalItemsPrice.getText().substring(1));
				totalPriceTemp -= (Double.parseDouble(priceLabel.getText().substring(1)));
				CartController.totalItemsPrice.setText("\u20AA" + Double.toString(totalPriceTemp));
				CartController.selectedProductsPremade.remove(item);
				CartController.itemToAmountPremade.remove(item.getID());
				CartController.staticGrid.getChildren().remove(indexToDelete);
				CatalogController.premadeBouquetNumber--;
				alreadyChanged = true;
			}
		}
		if (amount == 0) {
			MinBtn.setDisable(true);
			/* Remove empty items from product array list and amount map */
			int index = 0;
			if (!(item == null) && item.getType().equals("Self Assembly")) {
				try {
					for (Item i : customSelectedProducts)
						if (i.equals(item)) {
							customSelectedProducts.remove(index);
							customItemToAmount.remove(i.getID());
							break;
						} else
							index++;
				} catch (NullPointerException e) {
				}
				;
			} else if (!(item == null) && (item.getType().equals("Premade"))) {
				try {
					for (Item i : CatalogController.selectedProducts)
						if (i.equals(item)) {
							CatalogController.selectedProducts.remove(index);
							CatalogController.itemToAmount.remove(i.getID());
							break;
						} else
							index++;
				} catch (NullPointerException e) {
				}
				;
			}
		}
		/*
		 * setting single total item price label and total items pricelabel
		 */
		if (amount < 20)
			PlusBtn.setDisable(false);
		if (!alreadyChanged) {
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
				// CustomCatalogController.itemToAmount
				// CustomCatalogController.selectedProducts
				/* Only done for the cart */
			} else {
				totalPriceTemp = Double.parseDouble(CartController.totalItemsPrice.getText().substring(1));
				totalPriceTemp -= price;
				CartController.totalItemsPrice.setText("\u20AA" + Double.toString(totalPriceTemp));
			}
		}
	}

	/**
	 * Increments the amount of the selected item.
	 * @param event
	 */
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
				updateItem.add(priceLabel.getText().substring(1));
				updateItem.add(item.getImgSrc());
				updateItem.add(Integer.toString(amount));
				customItemToAmount.put(item.getID(), updateItem);
			} else if (!(item == null) && (item.getType().equals("Premade"))) {

				ArrayList<String> tempItemPremade = CatalogController.itemToAmount.get(item.getID());
				int tempAmount = Integer.parseInt(tempItemPremade.get(3));
				tempItemPremade.set(3, Integer.toString(tempAmount + 1));
				CatalogController.itemToAmount.put(item.getID(), tempItemPremade);
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
		// update temp cart struct(map / arraylist) to send to checkout.

	}

	/**
	 * Removes the selected item from the cart / custom bouquet.
	 * @param event
	 * @throws InterruptedException
	 */
	@FXML
	void clkDelete(MouseEvent event) throws InterruptedException {
		/* Cart */
		// Self assembly catalog
		int indexToDelete = 1;
		if (item == null) {
			for (String[] bouquet : CartController.customItemInCart)
				if (bouquet[3].equals(Integer.toString(bouquetNumber))) {
					indexToDelete = Integer.parseInt(bouquet[3]);
					double totalPriceTemp = Double.parseDouble(CartController.totalItemsPrice.getText().substring(1));
					totalPriceTemp -= Double.parseDouble((bouquet[2])) * (Double.parseDouble(amountLabel.getText()));
					CartController.totalItemsPrice.setText("\u20AA" + Double.toString(totalPriceTemp));
					CartController.staticGrid.getChildren().remove(CartController.selectedProductsPremade.size()
							+ CartController.customItemInCart.indexOf(bouquet));
					CartController.customItemInCart.remove(bouquet);
					CustomCatalogController.bouquetCounter--;
					return;
				}
		}
		// Premade catalog
		else if (item.getType().equals("Premade")) {
			indexToDelete = CartController.selectedProductsPremade.indexOf(item);
			double totalPriceTemp = Double.parseDouble(CartController.totalItemsPrice.getText().substring(1));
			totalPriceTemp -= (Double.parseDouble(priceLabel.getText().substring(1))
					* (Double.parseDouble(amountLabel.getText())));
			CartController.totalItemsPrice.setText("\u20AA" + Double.toString(totalPriceTemp));
			CartController.selectedProductsPremade.remove(item);
			CartController.itemToAmountPremade.remove(item.getID());
			CartController.staticGrid.getChildren().remove(indexToDelete);
			CatalogController.premadeBouquetNumber--;
		} else if (item.getType().equals("Self Assembly")) {

			customItemToAmount.remove(item.getID());
			double totalPriceTemp = Double
					.parseDouble(CustomItemViewController.staticTotalItemPrice.getText().substring(1));
			totalPriceTemp -= (Double.parseDouble(priceLabel.getText().substring(1))
					* (Double.parseDouble(amountLabel.getText())));
			CustomItemViewController.staticTotalItemPrice.setText("\u20AA" + Double.toString(totalPriceTemp));
			CustomItemViewController.staticGrid.getChildren().remove(customSelectedProducts.indexOf(item));
			customSelectedProducts.remove(item);
			if (customItemToAmount.isEmpty())
				CustomCatalogController.bouquetCounter--;
		}

	}

	/**
	 * Data initializatin
	 */
	@FXML
	void initialize() {
		/*
		 * Save original values on initialization in case 'Close' was clicked instead of
		 * 'Save'
		 */
		originalBouquetCounter = CustomCatalogController.bouquetCounter;
		originalSelectedProducts = new ArrayList<Item>(customSelectedProducts);
		originalItemToAmounts = new HashMap<Integer, ArrayList<String>>(customItemToAmount);
	}
}
