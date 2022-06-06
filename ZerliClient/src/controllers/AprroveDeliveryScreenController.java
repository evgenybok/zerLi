package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.SingleSelfDelivery;

/**
 * @author Evgeny
 * Confirmation screen for the chosen delivery to approve.
 */
public class AprroveDeliveryScreenController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button NoBtn;

	@FXML
	private Button yesBtn;

	/**
	 * When choosing not to confirm the delivery
	 * @param event
	 */
	@FXML
	void NoClick(MouseEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	/**
	 * when choosing to confirm the delivery, updates DB
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void YesClick(MouseEvent event) throws IOException {

		String order_id = SingleDeliveryController.order_id;
		try {
			chat.accept(new Message(MessageType.UPDATE_DELIVERY_STATUS, order_id));
			if (AnalyzeMessageFromServer.getData().equals(null)) {
				return;
			}
		} catch (Exception e) {
			return;
		}
		;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String currdate = formatter.format(date);
		String handleid = LoginScreenController.user.getID();
		int orderid = Integer.parseInt(order_id);
		String customerDate = SingleDeliveryController.customersupplydate;
		difBetweenDate(customerDate, currdate, orderid);
		SingleSelfDelivery singleSelfDelivery = new SingleSelfDelivery(orderid, handleid, customerDate, currdate);
		try {
			chat.accept(new Message(MessageType.INSERT_TO_DELIVERY_TABLE, singleSelfDelivery));
			if (AnalyzeMessageFromServer.getData().equals(null))
				return;
		} catch (Exception e) {
			return;
		}
		;
		((Node) event.getSource()).getScene().getWindow().hide();
		JOptionPane.showMessageDialog(null, "The Delivery was Approved", "Info", JOptionPane.INFORMATION_MESSAGE);
		DeliveryLoginScreenController.acceptStage.hide();
		Parent parent = FXMLLoader
				.load(Objects.requireNonNull(getClass().getResource("/fxml/DeliveryLoginScreen.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Delivery Screen");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
	}

	/**
	 * Calculates deserved refund
	 * @param supply supply time
	 * @param curr current time
	 * @param orderid order ID number
	 */
	public void difBetweenDate(String supply, String curr, int orderid) {
		Date time1;
		try {
			time1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(supply);
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(time1);

			Date time2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(curr);
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(time2);

			Date x = calendar2.getTime();
			// supply time between 3hours to 1hour - 50% refund
			if (x.after(calendar1.getTime())) {
				try {
					String s = String.valueOf(orderid);
					chat.accept(new Message(MessageType.UPDATE_REFUND_BY_ORDERID, s));
					if (AnalyzeMessageFromServer.getData().equals(null))
						return;

				} catch (Exception e) {
					return;
				}
				;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}