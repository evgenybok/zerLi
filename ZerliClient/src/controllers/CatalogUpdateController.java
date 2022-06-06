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
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logic.Item;

/**
 * @author Evgeny Marketing user can update the catalog, change price of item,
 *         move item to another catalog, put the item on sale and remove the
 *         item.
 */
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

	static ArrayList<Item> premadeItems = new ArrayList<>();
	static ArrayList<Item> customItems = new ArrayList<>();
	ArrayList<Item> selectedItems = new ArrayList<>();
	Integer[][] premadeID;
	Integer[][] customID;
	public static Item currentItem;

	/**
	 * Adds a new item to the selected catalog
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnAddNewItem(MouseEvent event) throws IOException {
		currentItem = null;
		((Node) event.getSource()).getScene().getWindow().hide();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/ItemEdit.fxml")));
		Parent root1 = (Parent) fxmlLoader.load();
		root1.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(root1);
		Stage itemEditStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		itemEditStage.setTitle("Item Edit");
		itemEditStage.setScene((scene));
		itemEditStage.show();
		itemEditStage.centerOnScreen();
	}

	/**
	 * Sends the user back to the marketing department screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnBack(MouseEvent event) throws IOException {
		if (LoginScreenController.user.getRole().equals("Marketing")) {
			((Node) event.getSource()).getScene().getWindow().hide();
			Parent parent = FXMLLoader
					.load(Objects.requireNonNull(getClass().getResource("/fxml/MarketingDepartment.fxml")));
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage catalogEditScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
			catalogEditScreen.setTitle("Home Screen");
			catalogEditScreen.setScene(scene);
			catalogEditScreen.show();
			catalogEditScreen.centerOnScreen();
		}
		else if (LoginScreenController.user.getRole().equals("worker")){
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/WorkerScreen.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage workerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		workerStage.setTitle("Worker Screen");
		workerStage.setScene(scene);
		workerStage.show();
		workerStage.centerOnScreen();
		}
		
	}

	/**
	 * Opens the selected item in the custom grid when it is clicked and opens its
	 * specifics to edit.
	 * @param event
	 * @throws IOException
	 */
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
			((Node) event.getSource()).getScene().getWindow().hide();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/ItemEdit.fxml")));
			Parent root1 = (Parent) fxmlLoader.load();
			root1.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(root1);
			Stage itemEditStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			itemEditStage.setTitle("Item Edit");
			itemEditStage.setScene((scene));
			itemEditStage.show();
			itemEditStage.centerOnScreen();
		}
	}

	/**
	 * Opens the selected item in the premade grid when it is clicked and opens its
	 * specifics to edit.
	 * @param event
	 * @throws IOException
	 */
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
			((Node) event.getSource()).getScene().getWindow().hide();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/ItemEdit.fxml")));
			Parent root1 = (Parent) fxmlLoader.load();
			root1.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(root1);
			Stage itemEditStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			itemEditStage.setTitle("Item Edit");
			itemEditStage.setScene((scene));
			itemEditStage.show();
			itemEditStage.centerOnScreen();

		}
	}

	/**
	 * Initialization of data on the screen.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {
		if (LoginScreenController.user.getRole().equals("Marketing")) {
			addNewItem.setVisible(false);
		}
		// premade grid
		premadeGrid.getChildren().clear();
		int column = 0;
		int row = 1;

		chat.accept(new Message(MessageType.GET_PREMADE_ITEMS, null));
		premadeItems = (ArrayList<Item>) AnalyzeMessageFromServer.getData();
		premadeID = new Integer[premadeItems.size() + 1 / 2][2];

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
		customID = new Integer[customItems.size() + 1 / 2][2];

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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
