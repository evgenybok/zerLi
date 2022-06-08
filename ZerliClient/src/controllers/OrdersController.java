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
import javafx.scene.image.Image;
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
	private Text AccountStatus;

	@FXML
	private Text AccountType;

	@FXML
	private Button Back;

	@FXML
	private TextField IdText;

	@FXML
	private VBox OrdersLayout;

    @FXML
    private ImageView searchImg;
    
	@FXML
	private ImageView avatarImg;

	@FXML
	private Button cancelOrder;

	@FXML
	private TextField cancelOrderNumber;

	@FXML
	private Label deliveryDate1;

	@FXML
	private Label lblStartMsg;

	@FXML
	private Label lblZerLi;

	@FXML
	private Label orderDate1;

	@FXML
	private Label orderNum1;

	@FXML
	private Label price1;

	@FXML
	private Label refund1;

	@FXML
	private Label shop1;

	@FXML
	private Label status1;

	@FXML
	private Label supplyType1;

	@FXML
	private Text userName;

    @FXML
    private Label msgLabel;


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
				msgLabel.setText("This order is already cancelled!");
				msgLabel.setVisible(true);
				new java.util.Timer().schedule( new java.util.TimerTask() {
					@Override
					public void run() {
						msgLabel.setVisible(false);
					}
				}, 2000);
				return;
			} else if (Integer.parseInt(cancelOrderNumber.getText()) == Orders.get(i).getOrderNumber()
					&& Orders.get(i).getStatus().equals("Delivered")) {
				msgLabel.setText("This order is already delivered!");
				msgLabel.setVisible(true);
				new java.util.Timer().schedule( new java.util.TimerTask() {
					@Override
					public void run() {
						msgLabel.setVisible(false);
					}
				}, 2000);
				return;
			}
			else if (Integer.parseInt(cancelOrderNumber.getText()) == Orders.get(i).getOrderNumber()
					&& Orders.get(i).getStatus().equals("Compensation")) {
				msgLabel.setText("You have received a compensation for this order!");
				msgLabel.setVisible(true);
				new java.util.Timer().schedule( new java.util.TimerTask() {
					@Override
					public void run() {
						msgLabel.setVisible(false);
					}
				}, 2000);
				return;
			} else if (Integer.parseInt(cancelOrderNumber.getText()) == Orders.get(i).getOrderNumber()
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
						msgLabel.setText("The option to cancel this order has expired!");
						msgLabel.setVisible(true);
						new java.util.Timer().schedule( new java.util.TimerTask() {
							@Override
							public void run() {
								msgLabel.setVisible(false);
							}
						}, 2000);
						return;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				chat.accept(new Message(MessageType.CANCEL_ORDER, toCancel));
				msgLabel.setText("Order Cancelled Successfully!");
				msgLabel.setVisible(true);
				new java.util.Timer().schedule( new java.util.TimerTask() {
					@Override
					public void run() {
						msgLabel.setVisible(false);
					}
				}, 2000);
				flagExists = true;
				initialize();
			}
		}
		if (flagExists == false)
		{
			msgLabel.setText("Wrong Order Number!!!");
			msgLabel.setVisible(true);
			new java.util.Timer().schedule( new java.util.TimerTask() {
				@Override
				public void run() {
					msgLabel.setVisible(false);
				}
			}, 2000);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of data on screen.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {
		msgLabel.setVisible(false);
		Image searchImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-search-50.png")));
		searchImg.setImage(searchImage);
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		userName.setText(LoginScreenController.user.getUsername());
		chat.accept(new Message(MessageType.GET_ACCOUNT_DETAILS, LoginScreenController.user.getID()));
		ArrayList<Account> account = (ArrayList<Account>) AnalyzeMessageFromServer.getData();
		AccountStatus.setText(account.get(0).getStatus());
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
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
