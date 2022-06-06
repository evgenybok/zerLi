package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.SingleComplaint;

/**
 * @author Evgeny Single complaint for the table in the customer service handle
 *         complaints screen.
 */
public class SingleComplaintController {
	public static String user_id;
	public static String order_id;
	@FXML
	private Button NotRefund;

	@FXML
	private Label description;

	@FXML
	private Label orderID;

	@FXML
	private Button refund;

	@FXML
	private Label userID;

	/**
	 * customer service user decides not to refund the selected client.
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	@FXML
	void NotRefund(MouseEvent event) {
		try {
			chat.accept(new Message(MessageType.UPDATE_STATUS_COMPLAINT, orderID.getText()));
			List<SingleComplaint> complaint = new ArrayList<>();
			chat.accept(new Message(MessageType.GET_COMPLAINTS, LoginScreenController.user.getID()));
			complaint = (List<SingleComplaint>) AnalyzeMessageFromServer.getData();
			HandelComplaintController.staticComplaintLayout.getChildren().clear();
			if (!(complaint == null)) {
				try {
					for (int i = 0; i < complaint.size(); i++) {
						FXMLLoader fxmlLoader = new FXMLLoader();
						fxmlLoader.setLocation(getClass().getResource("/fxml/SingleComplaintScreen.fxml"));
						HBox hBox = fxmlLoader.load();
						SingleComplaintController singleComplaintController = fxmlLoader.getController();
						singleComplaintController.setData(complaint.get(i));
						HandelComplaintController.staticComplaintLayout.getChildren().add(hBox);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (AnalyzeMessageFromServer.getData().equals(null))
				return;
		} catch (Exception e) {
			return;
		}
		;
	}

	/**
	 * customer service user decides to refund the selected client, moves to refund
	 * amount screen.
	 * @param event
	 */
	@FXML
	void RefundFunc(MouseEvent event) {
		user_id = userID.getText();
		order_id = orderID.getText();
		List<SingleComplaint> complaint = new ArrayList<>();
		chat.accept(new Message(MessageType.GET_COMPLAINTS, LoginScreenController.user.getID()));
		complaint = (List<SingleComplaint>) AnalyzeMessageFromServer.getData();
		HandelComplaintController.staticComplaintLayout.getChildren().clear();
		if (!(complaint == null)) {
			try {
				for (int i = 0; i < complaint.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/SingleComplaintScreen.fxml"));
					HBox hBox = fxmlLoader.load();
					SingleComplaintController singleComplaintController = fxmlLoader.getController();
					singleComplaintController.setData(complaint.get(i));
					HandelComplaintController.staticComplaintLayout.getChildren().add(hBox);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		MoveScreen();
	}

	/**
	 * Sets the data to show on screen.
	 * @param singleComplaint
	 */
	public void setData(SingleComplaint singleComplaint) {
		userID.setText(singleComplaint.getUserid());
		orderID.setText(singleComplaint.getOrderid());
		description.setText(singleComplaint.getDescription());
	}

	/**
	 * opens new screen to enter the refund amount.
	 */
	public void MoveScreen() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("/fxml/EnterRefund.fxml")));
			Parent root1 = (Parent) fxmlLoader.load();
			root1.getStylesheets().add("/css/styleNew.css");
			Stage EnterRefundStage = new Stage();
			EnterRefundStage.initModality(Modality.APPLICATION_MODAL);
			EnterRefundStage.setTitle("Add Refund");
			EnterRefundStage.setScene((new Scene(root1)));
			EnterRefundStage.show();
			EnterRefundStage.centerOnScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}