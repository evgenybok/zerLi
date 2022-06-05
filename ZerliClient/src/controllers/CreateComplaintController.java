package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Complain;

public class CreateComplaintController {
	@FXML
	private Button Back;

	@FXML
	private TextField DescriptionField;

	@FXML
	private TextField OrderIdField;

	@FXML
	private Button Submit;

	@FXML
	private Text accountType;

	@FXML
	private ImageView avatarImg;

	@FXML
	private Text userName;

	@FXML
	private TextField userNameField;

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
				String complainStatus = "WaitForHandle";
				// now query for insert
				Complain complain = new Complain(handelerIdString, user_id, order_id, description, complainStatus,
						refund);
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

	@FXML
	void initialize() {
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		userName.setText(LoginScreenController.user.getUsername());
		this.accountType.setText("Customer Service"); // accountType -
	}

	public void checkIfFieldIsEmpty() {
		if (userNameField.getText().isEmpty() || OrderIdField.getText().isEmpty()
				|| DescriptionField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

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
