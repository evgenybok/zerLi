package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.SingleManageOrder;
import logic.SingleOrder;
import logic.SingleUser;

/**
 * @author Evgeny
 * Manager can view the orders of his store and approve pending orders.
 */
public class ManagerOrdersController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button Back;

	@FXML
	private TextField IdText;

	@FXML
	private Label OrderId;

	@FXML
	private VBox OrdersLayout;

	@FXML
	private Label Price;

	@FXML
	private ImageView Search;

	@FXML
	private Label Status;

	@FXML
	private Label SupplyType;

	@FXML
	private Label UserId;

	@FXML
	private Text accountType;

	@FXML
	private Label orderDate;

	@FXML
	private Label refund1;

	@FXML
	private Text userName;

	/**
	 * Sends the manager back to the branch manager screen.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/branchManager.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage managerScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
		managerScreen.setTitle("Branch Manager Screen");
		managerScreen.setScene(scene);
		managerScreen.show();
		managerScreen.centerOnScreen();
	}

	/**
	 * Searches for a specific order based on user input and shows it in the table
	 * @param event
	 */
	@FXML
	void btnSearch(MouseEvent event) {
		ArrayList<SingleManageOrder> order = new ArrayList<>();
		String idSearch = IdText.getText();
		OrdersLayout.getChildren().clear();
		if (idSearch.isEmpty()) {
			initialize();
		}
		try {
			chat.accept(new Message(MessageType.GET_ORDER_BY_ORDER_ID, idSearch));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return;
		} catch (Exception e) {
			return;
		}
		order = (ArrayList<SingleManageOrder>) AnalyzeMessageFromServer.getData();
		try {
			for (int i = 0; i < order.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleOrderBranchMn.fxml"));
				HBox hBox = fxmlLoader.load();
				SingleManageOrdersController singleManageOrdersController = fxmlLoader.getController();
				singleManageOrdersController.setData(order.get(i));
				OrdersLayout.getChildren().add(hBox);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of data on screen
	 */
	@FXML
	void initialize() {

		InsertToTable();
	}

	/**
	 * Inserts the needed data from the DB to the table.
	 */
	public void InsertToTable() {
		ArrayList<SingleManageOrder> list = new ArrayList<>();
		chat.accept(new Message(MessageType.GET_MANAGER_ORDERS, null));
		list = (ArrayList<SingleManageOrder>) AnalyzeMessageFromServer.getData();
		// System.out.println(list.get(0).toString());
		try {
			for (int i = 0; i < list.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleOrderBranchMn.fxml"));
				HBox hBox = fxmlLoader.load();
				SingleManageOrdersController singleManageOrdersController = fxmlLoader.getController();
				singleManageOrdersController.setData(list.get(i));
				OrdersLayout.getChildren().add(hBox);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
