package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.Objects;

import javax.swing.JOptionPane;

import communication.Message;
import communication.MessageType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.Item;

/**
 * @author Evgeny
 * Allows the user to edit the opened item's data.
 */
public class ItemEditController {

	@FXML
	private CheckBox checkBoxSale;

	@FXML
	private Button close;

	@FXML
	private TextField color;

	@FXML
	private TextField id;

	@FXML
	private TextField imagePath;

	@FXML
	private TextField name;

	@FXML
	private TextField price;

	@FXML
	private TextField salePrice;

	@FXML
	private Button save;

	@FXML
	private Label saveMsg;

	@FXML
	private ImageView itemImage;

	@FXML
	private CheckBox preCheckBox;

	@FXML
	private CheckBox selfCheckBox;

	@FXML
	private Button delete;

	private Item currentItem = CatalogUpdateController.currentItem;
	private Item updatedItem = new Item(0, null, null, 0, null, null, false, 0);
	boolean newItemFlag = false;

	/**
	 * Closes current screen and opens Catalog Update screen.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnClose(MouseEvent event) throws IOException {
		Parent parent = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/fxml/CatalogUpdate.fxml"))));
		Scene scene = new Scene(parent);
		parent.getStylesheets().add("css/styleNew.css");
		Stage catalogUpdateStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		catalogUpdateStage.setTitle("Catalog Update");
		catalogUpdateStage.setScene(scene);
		catalogUpdateStage.show();
		catalogUpdateStage.centerOnScreen();
	}

	/**
	 * Removes the item from the catalog.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnDelete(MouseEvent event) throws IOException {
		try {
			chat.accept(new Message(MessageType.DELETE_ITEM, currentItem));
		} catch (Exception e) {
			return;
		}
		JOptionPane.showMessageDialog(null, "Item deleted successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
		Parent parent = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/fxml/CatalogUpdate.fxml"))));
		Scene scene = new Scene(parent);
		parent.getStylesheets().add("css/styleNew.css");
		Stage catalogUpdateStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		catalogUpdateStage.setTitle("Catalog Update");
		catalogUpdateStage.setScene(scene);
		catalogUpdateStage.show();
		catalogUpdateStage.centerOnScreen();
	}

	/**
	 * Saves the updated item's data in the DB and catalog.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnSave(MouseEvent event) throws IOException {
		if (newItemFlag == true) {
			for (int i = 0; i < CatalogUpdateController.customItems.size(); i++) {
				if (Integer.toString(CatalogUpdateController.customItems.get(i).getID()).equals(id.getText())) {
					JOptionPane.showMessageDialog(null, "An item with the exact itemID already exists", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				for (int j = 0; j < CatalogUpdateController.premadeItems.size(); j++) {
					if (Integer.toString(CatalogUpdateController.premadeItems.get(j).getID()).equals(id.getText())) {
						JOptionPane.showMessageDialog(null, "An item with the exact itemID already exists", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
			if (preCheckBox.isSelected() && (!(id.getText().startsWith("2")) || !(id.getText().length() == 5))) {
				JOptionPane.showMessageDialog(null, "Premade item ID form '2XXXX'", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (selfCheckBox.isSelected()
					&& (!(id.getText().startsWith("1")) || !(id.getText().length() == 5))) {
				JOptionPane.showMessageDialog(null, "Self Assembly item ID form '1XXXX'", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		try {
			Double.parseDouble(price.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Wrong input in the price field!!!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if ((selfCheckBox.isSelected() ^ preCheckBox.isSelected()) && !imagePath.getText().isEmpty()
				&& !name.getText().isEmpty() && !color.getText().isEmpty()) {
			if (selfCheckBox.isSelected())
				updatedItem.setType("Self Assembly");
			else
				updatedItem.setType("Premade");
			updatedItem.setID(Integer.parseInt(id.getText()));
			updatedItem.setImgSrc(imagePath.getText());
			updatedItem.setName(name.getText());
			updatedItem.setPrice(Double.parseDouble(price.getText()));
			updatedItem.setColor(color.getText());
			updatedItem.setOnSale(checkBoxSale.isSelected());
			if (checkBoxSale.isSelected()) {
				try {
					Double.parseDouble(salePrice.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Wrong input in the sale price field!!!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				updatedItem.setSalePrice(Double.parseDouble(salePrice.getText()));
			}
			if (newItemFlag == false) {
				try {
					chat.accept(new Message(MessageType.UPDATE_CATALOG, updatedItem));
				} catch (Exception e) {
					return;
				}
			} else {
				try {
					chat.accept(new Message(MessageType.ADD_NEW_ITEM, updatedItem));
				} catch (Exception e) {
					return;
				}
			}
			saveMsg.setText("Item Saved Successfully!");
			saveMsg.setVisible(true);
			JOptionPane.showMessageDialog(null, "Item Saved Successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
			Parent parent = FXMLLoader
					.load((Objects.requireNonNull(getClass().getResource("/fxml/CatalogUpdate.fxml"))));
			Scene scene = new Scene(parent);
			parent.getStylesheets().add("css/styleNew.css");
			Stage catalogUpdateStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			catalogUpdateStage.setTitle("Catalog Update");
			catalogUpdateStage.setScene(scene);
			catalogUpdateStage.show();
			catalogUpdateStage.centerOnScreen();

		} else
			JOptionPane.showMessageDialog(null, "Wrong input in one or more fields!!!", "Error",
					JOptionPane.ERROR_MESSAGE);

	}


	/**
	 * Checkbox if the selected item is premade.
	 * @param event
	 */
	@FXML
	void premadeSelected(MouseEvent event) {
		if (preCheckBox.isSelected()) {
			selfCheckBox.setDisable(true);
			selfCheckBox.setSelected(false);
		} else {
			selfCheckBox.setDisable(false);
		}
	}


	/**
	 * Checkbox if the selected item is custom made.
	 * @param event
	 */
	@FXML
	void selfSelected(MouseEvent event) {
		if (selfCheckBox.isSelected()) {
			preCheckBox.setDisable(true);
			preCheckBox.setSelected(false);
		} else {
			preCheckBox.setDisable(false);
		}
	}

	/**
	 * Checkbox if the selected item is on sale.
	 * @param event
	 */
	@FXML
	void onSaleSelected(MouseEvent event) {
		if (checkBoxSale.isSelected()) {
			salePrice.setDisable(false);
		} else
			salePrice.setDisable(true);
	}

	/**
	 * Initialization of data on the screen.
	 */
	@FXML
	void initialize() {
		if(LoginScreenController.user.getRole().equals("worker")) {
			salePrice.setDisable(true);
			checkBoxSale.setDisable(true);
		}
		else {
			delete.setVisible(false);
			imagePath.setDisable(true);
			name.setDisable(true);
			price.setDisable(true);
			color.setDisable(true);
		}
		if (currentItem == null) {
			id.setText("");
			imagePath.setText("");
			name.setText("");
			price.setText("");
			color.setText("");
			checkBoxSale.setSelected(false);
			salePrice.setText("");
			salePrice.setDisable(true);
			newItemFlag = true;
			delete.setVisible(false);
		} else {
			id.setText(Integer.toString(currentItem.getID()));
			id.setDisable(true);
			preCheckBox.setSelected(currentItem.getType().equals("Premade"));
			selfCheckBox.setSelected(currentItem.getType().equals("Self Assembly"));
			preCheckBox.setDisable(true);
			selfCheckBox.setDisable(true);
			imagePath.setText(currentItem.getImgSrc());
			name.setText(currentItem.getName());
			price.setText(Double.toString(currentItem.getPrice()));
			color.setText(currentItem.getColor());
			Image itemImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream(currentItem.getImgSrc())));
			itemImage.setImage(itemImg);
			checkBoxSale.setSelected(currentItem.isOnSale());
			if (checkBoxSale.isSelected())
				salePrice.setText(Double.toString(currentItem.getSalePrice()));
			else salePrice.setDisable(true);
		}

	}

}
