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

    @FXML
    void btnBack(MouseEvent event) throws IOException {
      	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/branchManager.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
    }

    @FXML
    void btnSearch(MouseEvent event) {
    	ArrayList<SingleManageOrder> order = new ArrayList<>();
    	String idSearch = IdText.getText();
    	OrdersLayout.getChildren().clear();
    	if(idSearch.isEmpty())
    	{
    		initialize();
    	}
    	try {
    		chat.accept(new Message(MessageType.GET_ORDER_BY_ORDER_ID, idSearch));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return;
    	}catch(Exception e) {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'ManageOrders.fxml'.";
        assert IdText != null : "fx:id=\"IdText\" was not injected: check your FXML file 'ManageOrders.fxml'.";
        assert OrderId != null : "fx:id=\"OrderId\" was not injected: check your FXML file 'ManageOrders.fxml'.";
        assert OrdersLayout != null : "fx:id=\"OrdersLayout\" was not injected: check your FXML file 'ManageOrders.fxml'.";
        assert Price != null : "fx:id=\"Price\" was not injected: check your FXML file 'ManageOrders.fxml'.";
        assert Search != null : "fx:id=\"Search\" was not injected: check your FXML file 'ManageOrders.fxml'.";
        assert Status != null : "fx:id=\"Status\" was not injected: check your FXML file 'ManageOrders.fxml'.";
        assert SupplyType != null : "fx:id=\"SupplyType\" was not injected: check your FXML file 'ManageOrders.fxml'.";
        assert UserId != null : "fx:id=\"UserId\" was not injected: check your FXML file 'ManageOrders.fxml'.";
        assert accountType != null : "fx:id=\"accountType\" was not injected: check your FXML file 'ManageOrders.fxml'.";
        assert orderDate != null : "fx:id=\"orderDate\" was not injected: check your FXML file 'ManageOrders.fxml'.";
        assert refund1 != null : "fx:id=\"refund1\" was not injected: check your FXML file 'ManageOrders.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'ManageOrders.fxml'.";
        InsertToTable();
    }
    public void InsertToTable()
    {
    	ArrayList<SingleManageOrder> list= new ArrayList<>();
    	chat.accept(new Message(MessageType.GET_MANAGER_ORDERS,null));
    	list = (ArrayList<SingleManageOrder>) AnalyzeMessageFromServer.getData();
    	//System.out.println(list.get(0).toString());
    	try {
			for (int i = 0; i < list.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleOrderBranchMn.fxml"));
				HBox hBox = fxmlLoader.load();
				SingleManageOrdersController singleManageOrdersController = fxmlLoader.getController();
				singleManageOrdersController.setData(list.get(i));
				OrdersLayout.getChildren().add(hBox);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


}
