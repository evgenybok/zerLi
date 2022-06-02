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
	void btnSubmit(MouseEvent event) throws IOException {
		checkIfFieldIsEmpty();
		String user_id = userNameField.getText();
		String orderid = OrderIdField.getText();
		if((checkIfHaveExistComplaint(orderid))==true)
		{
			JOptionPane.showMessageDialog(null, "There is exist Complaint", "Info",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			if((checkIfThisUserHaveChoosenOrder(user_id,orderid))==false)
			{
				JOptionPane.showMessageDialog(null, "One or more fields are worng", "Info",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				int order_id = Integer.parseInt(orderid);
				String description = DescriptionField.getText();
				String handelerIdString = LoginScreenController.user.getID();
				double refund = 0.00;
				String complainStatus = "WaitForHandle";
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
				JOptionPane.showMessageDialog(null, "The Complaint Continue For Treatment", "Info",JOptionPane.INFORMATION_MESSAGE);
				Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerService.fxml")));
				Scene scene = new Scene(parent);
				Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				loginStage.setTitle("Customer Service Screen");
				loginStage.setScene(scene);
				loginStage.show();
				loginStage.centerOnScreen();
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
	public  void checkIfFieldIsEmpty()
	{
		if(userNameField.getText().isEmpty()||OrderIdField.getText().isEmpty()||DescriptionField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "One or more fields are empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	public  boolean checkIfHaveExistComplaint(String orderid)
	{
		try {
			chat.accept(new Message(MessageType.CHECK_EXIST_QOMPLAIN,orderid));
			if (AnalyzeMessageFromServer.getData().equals(null))
				return false;

		} catch (Exception e) {
			return false;
		}
		;
		String str = (String) AnalyzeMessageFromServer.getData();
		if(str.equals("false"))
		{
			return false;
		}
		return true;
	}
	public  boolean checkIfThisUserHaveChoosenOrder(String userid,String orderid)
	{
		String str= userid + "@" + orderid;
		try {
			chat.accept(new Message(MessageType.CHECK_ORDER_BY_USERID, str));
			if (AnalyzeMessageFromServer.getData().equals(null))
					return false;
		} catch (Exception e) {
			return false;
		}
		;
		String res=(String) AnalyzeMessageFromServer.getData();
		if(res.equals("false"))
			return false;
		return true;
	}
}
