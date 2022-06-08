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

/**
 * @author Evgeny A single order for the delivery user to view, delivery user
 *         can approve orders with status Pending
 */
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

	/**
	 * Delivery user can approve pending orders.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnApprove(MouseEvent event) throws IOException {
		if (Status.getText().equals("Pending")) {
			try {
				chat.accept(new Message(MessageType.UPDATE_ORDER_STATUS_BY_MANAGER, OrderId.getText()));
				if (AnalyzeMessageFromServer.getData().equals(null))
					return;
			} catch (Exception e) {
				return;
			}
		} else if (Status.getText().equals("Cancel Request")) {
			try {
				chat.accept(new Message(MessageType.CANCEL_ORDER_STATUS_BY_MANAGER, OrderId.getText()));
				if (AnalyzeMessageFromServer.getData().equals(null))
					return;
			} catch (Exception e) {
				return;
			}
		} else {
			ManagerOrdersController.staticMsgLabel.setText("Cannot change the order's status!");
			ManagerOrdersController.staticMsgLabel.setVisible(true);
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					ManagerOrdersController.staticMsgLabel.setVisible(false);
				}
			}, 2000);
		}

		if (Status.getText().equals("Pending")) {
			ManagerOrdersController.staticMsgLabel.setText("Order has been approved");
			ManagerOrdersController.staticMsgLabel.setVisible(true);
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					ManagerOrdersController.staticMsgLabel.setVisible(false);
				}
			}, 2000);
		} else if (Status.getText().equals("Cancel Request")) {
			ManagerOrdersController.staticMsgLabel.setText("Order has been cancelled");
			ManagerOrdersController.staticMsgLabel.setVisible(true);
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					ManagerOrdersController.staticMsgLabel.setVisible(false);
				}
			}, 2000);
		}
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ManageOrders.fxml")));
		parent.getStylesheets().add("/css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage manageOrdersScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
		manageOrdersScreen.setTitle("Manager Orders");
		manageOrdersScreen.setScene(scene);
		manageOrdersScreen.show();
		manageOrdersScreen.centerOnScreen();
	}

	/**
	 * Sets data to show on screen
	 * 
	 * @param singleManageOrder
	 */
	public void setData(SingleManageOrder singleManageOrder) {
		String res = String.valueOf(singleManageOrder.getOrderId());
		OrderId.setText(res);
		UserId.setText(singleManageOrder.getUserId());
		Price.setText(Double.toString(singleManageOrder.getPrice()));
		orderDate.setText(singleManageOrder.getOrderDate());
		SupplyType.setText(singleManageOrder.getSupplyType());
		Status.setText(singleManageOrder.getStatus());
	}
}
