package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.SingleOrder;

public class BMViewOrdersController {

	@FXML
	private Label lblZerLi;

	@FXML
	private Label lblStartMsg;

	@FXML
	private Text UserName;

	@FXML
	private ImageView PersonImage;

	@FXML
	private Text AccountType;

	@FXML
	private Button Back;

	@FXML
	private TextField IdText;

	@FXML
	private ImageView Search;

	@FXML
	private Button cancelOrder;

	@FXML
	private TextField cancelOrderNumber;

	@FXML
	private Button approveOrder;

	@FXML
	private TextField approveOrderNumber;

	@FXML
	private Button viewOrder;

	@FXML
	private TextField viewOrderNumber;

	@FXML
	private Label orderNum;

	@FXML
	private Label price;

	@FXML
	private Label shop;

	@FXML
	private Label orderDate;

	@FXML
	private Label deliveryDate;

	@FXML
	private Label supplyType;

	@FXML
	private Label status;

	@FXML
	private Label refund;

	@FXML
	private VBox OrdersLayout;

	List<SingleOrder> Orders = new ArrayList<SingleOrder>();

	@FXML
	void btnApproveOrder(MouseEvent event) {

	}

	@FXML
	void btnBack(MouseEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/branchManager.fxml")));
			Scene scene = new Scene(parent);
			Stage ordersStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			parent.getStylesheets().add("css/styleNew.css");
			ordersStage.setTitle("View Orders");
			ordersStage.setScene(scene);
			ordersStage.show();
			ordersStage.centerOnScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * Add conditions when to cancel
	 */

	@SuppressWarnings("unchecked")
	@FXML
	void btnCancelOrder(MouseEvent event) {
		String orderID = cancelOrderNumber.getText();
		List<SingleOrder> orders = new ArrayList<SingleOrder>();
		OrdersLayout.getChildren().clear();
		if (cancelOrderNumber.getText().isEmpty()) {
			initialize();
			return;
		}
		chat.accept(new Message(MessageType.GET_STORE_ORDERS, LoginScreenController.user.getID().toString()));
		try {
			orders = (ArrayList<SingleOrder>) AnalyzeMessageFromServer.getData();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 0; i < orders.size(); i++) {
			if (Integer.parseInt(cancelOrderNumber.getText()) == Orders.get(i).getOrderNumber()
					&& Orders.get(i).getStatus().equals("Cancelled")) {
				JOptionPane.showMessageDialog(null, "This order is already cancelled!!!", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (Integer.parseInt(cancelOrderNumber.getText()) == Orders.get(i).getOrderNumber()
					&& Orders.get(i).getStatus().equals("Delivered")) {
				JOptionPane.showMessageDialog(null, "This order is already delivered!!!", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		for (SingleOrder sOrder : orders) {
			if (Integer.toString(sOrder.getOrderNumber()).equals(orderID)) {
				chat.accept(new Message(MessageType.CANCEL_ORDER, sOrder));
				initialize();
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "Order does not exist", "Notice", JOptionPane.INFORMATION_MESSAGE);
		initialize();
		return;
	}

	@SuppressWarnings("unchecked")
	@FXML
	void btnSearch(MouseEvent event) {
		String OrderId = IdText.getText();
		boolean orderExists = false;
		OrdersLayout.getChildren().clear();
		if (IdText.getText().isEmpty()) {
			orderExists = true;
			initialize();
		}

		chat.accept(new Message(MessageType.GET_STORE_ORDERS, LoginScreenController.user.getID().toString()));
		try {
			Orders = (ArrayList<SingleOrder>) AnalyzeMessageFromServer.getData();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (SingleOrder sOrder : Orders) {
			if (Integer.toString(sOrder.getOrderNumber()).equals(OrderId) && !orderExists) {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/SingleOrder.fxml"));
					HBox hBox = fxmlLoader.load();
					SingleOrderController singleOrderController = fxmlLoader.getController();
					singleOrderController.setData(sOrder);
					OrdersLayout.getChildren().add(hBox);
					orderExists = true;
				} catch (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
				}
			}
		}

		if (!orderExists)

		{
			JOptionPane.showMessageDialog(null, "Order does not exist", "Notice", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

	}

	@FXML
	void btnViewOrder(MouseEvent event) {

	}

	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {
		UserName.setText(LoginScreenController.user.getUsername());
		OrdersLayout.getChildren().clear();
		chat.accept(new Message(MessageType.GET_STORE_ORDERS, LoginScreenController.user.getID().toString()));
		Orders = (ArrayList<SingleOrder>) AnalyzeMessageFromServer.getData();
		try {
			for (int i = 0; i < Orders.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleOrder.fxml"));
				HBox hBox = fxmlLoader.load();
				SingleOrderController singleOrderController = fxmlLoader.getController();
				singleOrderController.setData(Orders.get(i));
				OrdersLayout.getChildren().add(hBox);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
