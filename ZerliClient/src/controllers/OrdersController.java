package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import logic.Account;
import logic.SingleOrder;

/**
 * @author Evgeny
 * Here the user can view and manage his orders.
 */
public class OrdersController {

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
	private Text AccountStatus;

	@FXML
	private Button Back;

	@FXML
	private VBox OrdersLayout;

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
	private TextField IdText;
	@FXML
	private ImageView Search;

	@FXML
	private Button cancelOrder;

	@FXML
	private TextField cancelOrderNumber;

	List<SingleOrder> Orders = new ArrayList<SingleOrder>();
	SingleOrder toCancel = new SingleOrder(0, 0, null, null, null, null, 0, null);


	/**
	 * Sends the user back to the customer screen.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerScreen.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}

	/**
	 * User can cancel selected order only if it is in status "Approved"
	 * User gets compensated based on the time left for delivery.
	 * @param event
	 */
	@FXML
	void btnCancelOrder(MouseEvent event) {
		boolean flagExists = false;
		for (int i = 0; i < Orders.size(); i++) {
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

			else if (Integer.parseInt(cancelOrderNumber.getText()) == Orders.get(i).getOrderNumber()
					&& (Orders.get(i).getStatus().equals("Approved") || Orders.get(i).getStatus().equals("Pending"))) {
				toCancel = Orders.get(i);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				String string1 = formatter.format(date);
				try {
					// this time 3 hours ago
					Date time3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(string1);
					Calendar calendar1 = Calendar.getInstance();
					calendar1.setTime(time3);
					calendar1.add(Calendar.HOUR_OF_DAY, 3);

					// this time 1 hour ago
					Date time1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(string1);
					Calendar calendar2 = Calendar.getInstance();
					calendar2.setTime(time1);
					calendar2.add(Calendar.HOUR_OF_DAY, 1);

					// supply time
					Date timeSupply = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toCancel.getSupplyDate());
					Calendar calendar3 = Calendar.getInstance();
					calendar3.setTime(timeSupply);

					Date x = calendar3.getTime();
					// supply time between 3hours to 1hour - 50% refund
					if (x.after(calendar2.getTime()) && x.before(calendar1.getTime())) {
						toCancel.setRefund((toCancel.getPrice()) * 0.5);

					}
					// supply time before 3hours - full refund
					else if (x.after(calendar1.getTime())) {
						toCancel.setRefund((toCancel.getPrice()) * 1);

					}
					// supply time after 1hour - no refund
					else if (x.before(calendar2.getTime()) && x.after(date)) {
						toCancel.setRefund((toCancel.getPrice()) * 0);

					} else {
						JOptionPane.showMessageDialog(null,
								"The option to cancel this order has expired, in case you did not receive the order - a refund will be issued to your account",
								"Info", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				chat.accept(new Message(MessageType.CANCEL_ORDER, toCancel));
				JOptionPane.showMessageDialog(null, "Order Cancelled Successfully!", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				flagExists = true;
				initialize();
			}
		}
		if (flagExists == false) {
			JOptionPane.showMessageDialog(null, "Wrong Order Number!!!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	/**
	 * Allows the user to search for a specific order.
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	@FXML
	void btnSearch(MouseEvent event) {
		ArrayList<SingleOrder> order = new ArrayList<>();
		String OrderId = IdText.getText();
		OrderId = OrderId + "@" + LoginScreenController.user.getID();
		OrdersLayout.getChildren().clear();
		if (IdText.getText().isEmpty()) {
			initialize();
		}
		try {
			chat.accept(new Message(MessageType.GET_ORDER_BY_ID, OrderId));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return;

		} catch (Exception e) {
			return;
		}
		;
		order = (ArrayList<SingleOrder>) AnalyzeMessageFromServer.getData();
		try {

			for (int i = 0; i < order.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleOrder.fxml"));
				HBox hBox = fxmlLoader.load();
				SingleOrderController singleOrderController = fxmlLoader.getController();
				singleOrderController.setData(order.get(i));
				OrdersLayout.getChildren().add(hBox);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of data on screen.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {
		chat.accept(new Message(MessageType.GET_ACCOUNT_DETAILS, LoginScreenController.user.getID()));
		ArrayList<Account> account = (ArrayList<Account>) AnalyzeMessageFromServer.getData();
		AccountStatus.setText(account.get(0).getStatus());
		UserName.setText(LoginScreenController.user.getUsername());
		OrdersLayout.getChildren().clear();
		chat.accept(new Message(MessageType.GET_ORDERS, LoginScreenController.user.getID()));
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
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
