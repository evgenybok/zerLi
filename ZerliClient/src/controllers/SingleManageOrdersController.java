package controllers;

import static controllers.IPScreenController.chat;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.SingleManageOrder;

public class SingleManageOrdersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label OrderId;

    @FXML
    private Label Price;

    @FXML
    private Label Status;

    @FXML
    private Label SupplyType;

    @FXML
    private Label UserId;

    @FXML
    private Label orderDate;
    @FXML
    private Button Approve;
       @FXML
       void btnApprove(MouseEvent event) throws IOException {
       	try {
   			chat.accept(new Message(MessageType.UPDATE_ORDER_STATUS_BY_MANAGER, OrderId.getText()));
   			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
   				return;
   		} catch (Exception e) {
   			return;
   		}
       	JOptionPane.showMessageDialog(null, "Order Status Has Changed Successfully", "Info",JOptionPane.INFORMATION_MESSAGE);
       	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ManageOrders.fxml")));
   		Scene scene = new Scene(parent);
   		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   		customerStage.setTitle("Manager Orders");
   		customerStage.setScene(scene);
   		customerStage.show();
   		customerStage.centerOnScreen();
       }
    public void setData(SingleManageOrder singleManageOrder) {
    	String res = String.valueOf(singleManageOrder.getOrderId());
    	OrderId.setText(res);
    	UserId.setText(singleManageOrder.getUserId());
    	Price.setText(Double.toString(singleManageOrder.getPrice()));
    	orderDate.setText(singleManageOrder.getOrderDate());
    	SupplyType.setText(singleManageOrder.getSupplyType());
    	Status.setText(singleManageOrder.getStatus());
    }
    @FXML
    void initialize() {
    	assert OrderId != null : "fx:id=\"OrderId\" was not injected: check your FXML file 'SingleOrderBranchMn.fxml'.";
        assert Price != null : "fx:id=\"Price\" was not injected: check your FXML file 'SingleOrderBranchMn.fxml'.";
        assert Status != null : "fx:id=\"Status\" was not injected: check your FXML file 'SingleOrderBranchMn.fxml'.";
        assert SupplyType != null : "fx:id=\"SupplyType\" was not injected: check your FXML file 'SingleOrderBranchMn.fxml'.";
        assert UserId != null : "fx:id=\"UserId\" was not injected: check your FXML file 'SingleOrderBranchMn.fxml'.";
        assert orderDate != null : "fx:id=\"orderDate\" was not injected: check your FXML file 'SingleOrderBranchMn.fxml'.";
    }
}

