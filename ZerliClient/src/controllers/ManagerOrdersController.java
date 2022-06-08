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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.SingleManageOrder;

/**
 * @author Evgeny
 * Manager can view the orders of his store and approve pending orders.
 */
public class ManagerOrdersController {

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

    @FXML
    private ImageView addImg;

    @FXML
    private Label msgLabel;
    
    public static Label staticMsgLabel;
	/**
	 * Sends the manager back to the branch manager screen.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private ImageView avatarImg;

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
	@SuppressWarnings("unchecked")
	@FXML
	void btnSearch(MouseEvent event) {
		ArrayList<SingleManageOrder> order = new ArrayList<>();
		String idSearch = IdText.getText();
		String userID = LoginScreenController.user.getID();
		String[] details = new String[2];
		details[0] = idSearch;
		details[1] = userID;
		OrdersLayout.getChildren().clear();
		if (idSearch.isEmpty()) {
			initialize();
		}
		try {
			chat.accept(new Message(MessageType.GET_ORDER_BY_ORDER_ID, details));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return;
		} catch (Exception e) {
			return;
		}
		order = (ArrayList<SingleManageOrder>) AnalyzeMessageFromServer.getData();
		try {
			if(!(order==null)) {
			for (int i = 0; i < order.size(); i++) {
				if(order.get(i).getStatus().equals("Pending")||order.get(i).getStatus().equals("Cancel Request"))
				{
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleOrderBranchMn.fxml"));
				HBox hBox = fxmlLoader.load();
				SingleManageOrdersController singleManageOrdersController = fxmlLoader.getController();
				singleManageOrdersController.setData(order.get(i));
				OrdersLayout.getChildren().add(hBox);
				}
			}
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
		staticMsgLabel=msgLabel;
		msgLabel.setVisible(false);
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		userName.setText(LoginScreenController.user.getUsername());
		InsertToTable();
	}

	/**
	 * Inserts the needed data from the DB to the table.
	 */
	@SuppressWarnings("unchecked")
	public void InsertToTable() {
		ArrayList<SingleManageOrder> list = new ArrayList<>();
		chat.accept(new Message(MessageType.GET_MANAGER_ORDERS, LoginScreenController.user.getID().toString()));
		list = (ArrayList<SingleManageOrder>) AnalyzeMessageFromServer.getData();
		try {
			if(!(list==null)) {
			for (int i = 0; i < list.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleOrderBranchMn.fxml"));
				HBox hBox = fxmlLoader.load();
				SingleManageOrdersController singleManageOrdersController = fxmlLoader.getController();
				singleManageOrdersController.setData(list.get(i));
				OrdersLayout.getChildren().add(hBox);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
