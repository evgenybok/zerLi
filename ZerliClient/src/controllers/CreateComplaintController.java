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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Complain;

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

	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		chat.accept(new Message(MessageType.LOGOUT, LoginScreenController.user));
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerService.fxml")));
		Scene scene = new Scene(parent);
		Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loginStage.setTitle("Create Complaint Screen");
		loginStage.setScene(scene);
		loginStage.show();
		loginStage.centerOnScreen();
	}

	@FXML
	void btnSubmit(MouseEvent event) {
		String user_name = userNameField.getText();
		
		String orderid = OrderIdField.getText();
		System.out.println(orderid);
		int order_id = Integer.parseInt(orderid);
		System.out.println(order_id);
		String description = DescriptionField.getText();
		System.out.println(description);
		String handelerIdString = LoginScreenController.user.getID();
		String refund = null;
		String complainStatus = "WaitForHandle";
		String user_id = getUserID(orderid);
		// now query for insert
		Complain complain = new Complain(handelerIdString, user_id, order_id, description, complainStatus, refund);
		try {
			chat.accept(new Message(MessageType.INSERT_NEW_COMPLAIN, complain));
			if (AnalyzeMessageFromServer.getData().equals(null))
				return;

		} catch (Exception e) {
			return;
		}
		;

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
		assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'CreateComplaint.fxml'.";
		assert DescriptionField != null
				: "fx:id=\"DescriptionField\" was not injected: check your FXML file 'CreateComplaint.fxml'.";
		assert OrderIdField != null : "fx:id=\"OrderIdField \" was not injected: check your FXML file 'CreateComplaint.fxml'.";
		assert Submit != null : "fx:id=\"Submit\" was not injected: check your FXML file 'CreateComplaint.fxml'.";
		assert userNameField != null
				: "fx:id=\"userNameField\" was not injected: check your FXML file 'CreateComplaint.fxml'.";
		assert accountStatus != null
				: "fx:id=\"accountStatus\" was not injected: check your FXML file 'CreateComplaint.fxml'.";
		assert accountType != null
				: "fx:id=\"accountType\" was not injected: check your FXML file 'CreateComplaint.fxml'.";
		assert userNameField != null
				: "fx:id=\"userName\" was not injected: check your FXML file 'CreateComplaint.fxml'.";
		this.accountStatus.setText("CONFIRMED"); // accountStatus - need to be handled from DB
		this.accountType.setText("Customer Service"); // accountType - may be handled from DB
	}

}
