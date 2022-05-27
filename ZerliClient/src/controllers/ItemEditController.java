package controllers;

import java.io.IOException;
import java.util.Objects;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.Item;

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

	private Item currentItem = CatalogUpdateController.currentItem;

	@FXML
	void btnClose(MouseEvent event) throws IOException {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
	}

	@FXML
	void btnSave(MouseEvent event) {
		saveMsg.setText("Item Saved Successfully!");
		saveMsg.setVisible(true);
	}

	@FXML
	void initialize() {
		if(currentItem==null) {
			id.setText("");
			imagePath.setText("");
			name.setText("");
			price.setText("");
			color.setText("");
			checkBoxSale.setSelected(false);
			salePrice.setText("");
		}
		else{
			id.setText(Integer.toString(currentItem.getID()));
			imagePath.setText(currentItem.getImgSrc());
			name.setText(currentItem.getName());
			price.setText(Double.toString(currentItem.getPrice()));
			color.setText(currentItem.getColor());
			/* sale checkbox and sale price 
			 * if(currentItem.get) salePrice.setText("SALE");
			 */
		}

	}

}
