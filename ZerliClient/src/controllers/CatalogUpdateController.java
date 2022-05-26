package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import static controllers.IPScreenController.chat;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.Item;

public class CatalogUpdateController {

	@FXML
	private Button Edit;

	@FXML
	private Button addNewItem;

	@FXML
	private Button back;

	@FXML
	private GridPane customGrid;

	@FXML
	private ScrollPane customScroll;

	@FXML
	private GridPane premadeGrid;

	@FXML
	private ScrollPane premadeScroll;

	ArrayList<Item> premadeItems = new ArrayList<>();
	ArrayList<Item> customItems = new ArrayList<>();
	ArrayList<Item> selectedItems = new ArrayList<>();
	Integer[][] premadeID;
	Integer[][] customID;
	public static Item currentItem;

	@FXML
	void btnAddNewItem(MouseEvent event) throws IOException {
		currentItem = null;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/ItemEdit.fxml")));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage itemEditStage = new Stage();
		itemEditStage.initModality(Modality.APPLICATION_MODAL);
		itemEditStage.setTitle("Item Edit");
		itemEditStage.setScene((new Scene(root1)));
		itemEditStage.show();
		itemEditStage.centerOnScreen();
	}

	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/WorkerScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage workerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		workerStage.setTitle("Home Screen");
		workerStage.setScene(scene);
		workerStage.show();
		workerStage.centerOnScreen();
	}


	@FXML
	void clickCustomGrid(MouseEvent event) throws IOException {
		Node clickedNode = event.getPickResult().getIntersectedNode();
		if (clickedNode != customGrid) {
			// click on descendant node
			Node parent = clickedNode.getParent();
			while (parent != customGrid) {
				clickedNode = parent;
				parent = clickedNode.getParent();
			}
			Integer colIndex = GridPane.getColumnIndex(clickedNode);
			Integer rowIndex = GridPane.getRowIndex(clickedNode);
			for (int i = 0; i < customItems.size(); i++) {
				if (customItems.get(i).getID() == customID[rowIndex - 1][colIndex])
					currentItem = customItems.get(i);
			}
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/ItemEdit.fxml")));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage itemEditStage = new Stage();
			itemEditStage.initModality(Modality.APPLICATION_MODAL);
			itemEditStage.setTitle("Item Edit");
			itemEditStage.setScene((new Scene(root1)));
			itemEditStage.show();
			itemEditStage.centerOnScreen();
		}
	}

	public void clickPremadeGrid(MouseEvent event) throws IOException {
		Node clickedNode = event.getPickResult().getIntersectedNode();
		if (clickedNode != premadeGrid) {
			// click on descendant node
			Node parent = clickedNode.getParent();
			while (parent != premadeGrid) {
				clickedNode = parent;
				parent = clickedNode.getParent();
			}
			Integer colIndex = GridPane.getColumnIndex(clickedNode);
			Integer rowIndex = GridPane.getRowIndex(clickedNode);
			for (int i = 0; i < premadeItems.size(); i++) {
				if (premadeItems.get(i).getID() == premadeID[rowIndex - 1][colIndex])
					currentItem = premadeItems.get(i);
			}
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/ItemEdit.fxml")));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage itemEditStage = new Stage();
			itemEditStage.initModality(Modality.APPLICATION_MODAL);
			itemEditStage.setTitle("Item Edit");
			itemEditStage.setScene((new Scene(root1)));
			itemEditStage.show();
			itemEditStage.centerOnScreen();

		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {

		// premade grid
		premadeGrid.getChildren().clear();
		int column = 0;
		int row = 1;

		chat.accept(new Message(MessageType.GET_PREMADE_ITEMS, null));
		premadeItems = (ArrayList<Item>) AnalyzeMessageFromServer.getData();
		premadeID = new Integer[premadeItems.size()+1 / 2][2];

		try {

			for (int i = 0; i < premadeItems.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/Item.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				ItemController itemController = fxmlLoader.getController();
				itemController.setData(premadeItems.get(i));

				if (column == 2) {
					column = 0;
					row++;
				}
				premadeID[row - 1][column] = premadeItems.get(i).getID();
				premadeGrid.add(anchorPane, column++, row);// (child,column,row)

				GridPane.setMargin(anchorPane, new Insets(10));

				// width

				premadeGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
				premadeGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
				premadeGrid.setMaxWidth(Region.USE_PREF_SIZE);

				// height
				premadeGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
				premadeGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
				premadeGrid.setMaxHeight(Region.USE_PREF_SIZE);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// custom grid
		customGrid.getChildren().clear();
		int column1 = 0;
		int row1 = 1;

		chat.accept(new Message(MessageType.GET_SELFASSEMBLY_ITEMS, null));
		customItems = (ArrayList<Item>) AnalyzeMessageFromServer.getData();
		customID = new Integer[customItems.size()+1 / 2][2];

		try {

			for (int i = 0; i < customItems.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/Item.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				ItemController itemController = fxmlLoader.getController();
				itemController.setData(customItems.get(i));

				if (column1 == 2) {
					column1 = 0;
					row1++;
				}
				customID[row1 - 1][column1] = customItems.get(i).getID();
				customGrid.add(anchorPane, column1++, row1);// (child,column,row)
				GridPane.setMargin(anchorPane, new Insets(10));

				// width

				customGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
				customGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
				customGrid.setMaxWidth(Region.USE_PREF_SIZE);

				// height
				customGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
				customGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
				customGrid.setMaxHeight(Region.USE_PREF_SIZE);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
