package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Item;

public class CartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button close;

    @FXML
    private Text customizedName;

    @FXML
    private GridPane grid;

    @FXML
    private Label lblCustomizedName;

    @FXML
    private Label lblTotalPrice;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label titleAmount;

    @FXML
    private Label titleImage;

    @FXML
    private Label titleName;

    @FXML
    private Label titlePrice;

    @FXML
    private Label titleTotalPrice;

    @FXML
    private Text totalItemPrice;
	
	public static ArrayList<Item> selectedProducts = CustomCatalogController.selectedProducts;
	public Map<Integer, ArrayList<String>> itemToAmount = CustomCatalogController.itemToAmount;

    @FXML
    void btnClose(MouseEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

	@FXML
	void initialize() {
		int column = 0;
		int row = 1;
		ArrayList<Item> items = new ArrayList<>();
		items = selectedProducts;
		try {
			grid.getChildren().clear();

			for (int i = 0; i < items.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/CustomizedItem.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();
				
				CustomizedItemController CustomizedItemController = fxmlLoader.getController();
				CustomizedItemController.setData(items.get(i), itemToAmount.get(items.get(i).getID()).get(3));

				if (column == 1) {
					column = 0;
					row++;
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
        assert customizedName != null : "fx:id=\"customizedName\" was not injected: check your FXML file 'CartScreen.fxml'.";
        assert grid != null : "fx:id=\"grid\" was not injected: check your FXML file 'CartScreen.fxml'.";
        assert lblCustomizedName != null : "fx:id=\"lblCustomizedName\" was not injected: check your FXML file 'CartScreen.fxml'.";
        assert lblTotalPrice != null : "fx:id=\"lblTotalPrice\" was not injected: check your FXML file 'CartScreen.fxml'.";
        assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file 'CartScreen.fxml'.";
        assert titleAmount != null : "fx:id=\"titleAmount\" was not injected: check your FXML file 'CartScreen.fxml'.";
        assert titleImage != null : "fx:id=\"titleImage\" was not injected: check your FXML file 'CartScreen.fxml'.";
        assert titleName != null : "fx:id=\"titleName\" was not injected: check your FXML file 'CartScreen.fxml'.";
        assert titlePrice != null : "fx:id=\"titlePrice\" was not injected: check your FXML file 'CartScreen.fxml'.";
        assert titleTotalPrice != null : "fx:id=\"titleTotalPrice\" was not injected: check your FXML file 'CartScreen.fxml'.";
        assert totalItemPrice != null : "fx:id=\"totalItemPrice\" was not injected: check your FXML file 'CartScreen.fxml'.";

	}
}
