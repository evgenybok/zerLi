package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Complain;
import logic.SingleComplaint;

/**
 * @author Evgeny Customer service worker can fill a complaint made by a
 *         customer.
 */
public class CreateComplaintController {
	@FXML
	private TextField DescriptionField;

	@FXML
	private TextField OrderIdField;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button Back;
	@FXML
	private TextField userNameField;
	@FXML
	private Button Submit;

	@FXML
	private Text accountStatus;

	@FXML
	private Text accountType;

	/**
	 * Sends the user back to the customer service main screen
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		chat.accept(new Message(MessageType.LOGOUT, LoginScreenController.user));
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerService.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage complaintScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
		complaintScreen.setTitle("Create Complaint Screen");
		complaintScreen.setScene(scene);
		complaintScreen.show();
		complaintScreen.centerOnScreen();
	}

	/**
	 * Sumbits written complaint and adds it to the DB with the Pending status.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@FXML
	void btnSubmit(MouseEvent event) throws IOException {
		checkIfFieldIsEmpty();
		String user_id = userNameField.getText();
		String orderid = OrderIdField.getText();
		if ((checkIfHaveExistComplaint(orderid)) == true) {
			JOptionPane.showMessageDialog(null, "There is exist Complaint", "Info", JOptionPane.INFORMATION_MESSAGE);
		} else {
			if ((checkIfThisUserHaveChoosenOrder(user_id, orderid)) == false) {
				JOptionPane.showMessageDialog(null, "One or more fields are worng", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				int order_id = Integer.parseInt(orderid);
				String description = DescriptionField.getText();
				String handelerIdString = LoginScreenController.user.getID();
				double refund = 0.00;
				String complainStatus = "Pending";
				LocalDateTime date=LocalDateTime.now();
				chat.accept(new Message(MessageType.GET_STORE_ID_BY_ORDER_ID, orderid + "@" + user_id));
				String storeID=(String) AnalyzeMessageFromServer.getData();
				// now query for insert
				Complain complain = new Complain(handelerIdString, user_id, order_id, description, complainStatus,
						refund,storeID,date,false);
				try {
					chat.accept(new Message(MessageType.INSERT_NEW_COMPLAIN, complain));
					if (AnalyzeMessageFromServer.getData().equals(null))
						return;

				} catch (Exception e) {
					return;
				}
				;
				JOptionPane.showMessageDialog(null, "The Complaint Continue For Treatment", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				Parent parent = FXMLLoader
						.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerService.fxml")));
				parent.getStylesheets().add("css/styleNew.css");
				Scene scene = new Scene(parent);
				Stage csScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
				csScreen.setTitle("Customer Service Screen");
				csScreen.setScene(scene);
				csScreen.show();
				csScreen.centerOnScreen();
			}
		}

	}

	/**
	 * Gets user id number with the given order number
	 * 
	 * @param OrderNumber
	 * @return
	 */
	public String getUserID(String OrderNumber) {
		try {
			chat.accept(new Message(MessageType.GET_USERID_BY_ORDERID, OrderNumber));
			if (AnalyzeMessageFromServer.getData().equals(null))
				return null;

		} catch (Exception e) {
			return null;
		}
		;
		String str = (String) AnalyzeMessageFromServer.getData();
		return str;
	}

	/**
	 * Initialize data shown on screen
	 */
	@FXML
	void initialize() {

		this.accountType.setText("Customer Service"); // accountType -
	}

	/**
	 * Checking if fields are empty
	 */
	public void checkIfFieldIsEmpty() {
		if (userNameField.getText().isEmpty() || OrderIdField.getText().isEmpty()
				|| DescriptionField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	/**
	 * Checking if given order has a complaint
	 * 
	 * @param orderid
	 * @return
	 */
	public boolean checkIfHaveExistComplaint(String orderid) {
		try {
			chat.accept(new Message(MessageType.CHECK_EXIST_QOMPLAIN, orderid));
			if (AnalyzeMessageFromServer.getData().equals(null))
				return false;

		} catch (Exception e) {
			return false;
		}
		;
		String str = (String) AnalyzeMessageFromServer.getData();
		if (str.equals("false")) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if there is an order number with given order number and user id.
	 * 
	 * @param userid
	 * @param orderid
	 * @return
	 */
	public boolean checkIfThisUserHaveChoosenOrder(String userid, String orderid) {
		String str = userid + "@" + orderid;
		try {
			chat.accept(new Message(MessageType.CHECK_ORDER_BY_USERID, str));
			if (AnalyzeMessageFromServer.getData().equals(null))
				return false;
		} catch (Exception e) {
			return false;
		}
		;
		String res = (String) AnalyzeMessageFromServer.getData();
		if (res.equals("false"))
			return false;
		return true;
	}
}
