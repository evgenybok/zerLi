package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.SingleComplaint;

public class SingleComplaintController {
	public static  String user_id;
	public static  String order_id;
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

    @FXML
    void NotRefund(MouseEvent event) {
    	try {
			chat.accept(new Message(MessageType.UPDATE_STATUS_COMPLAINT,orderID.getText()));
			if (AnalyzeMessageFromServer.getData().equals(null)) 
				return;
		} catch (Exception e) {
			return;
		};
    }
    @FXML
    void RefundFunc(MouseEvent event) {
    	user_id = userID.getText();
    	order_id = orderID.getText();
    	MoveScreen();
    }
    public void setData(SingleComplaint singleComplaint)
    {
    	userID.setText(singleComplaint.getUserid());
    	orderID.setText(singleComplaint.getOrderid());
    	description.setText(singleComplaint.getDescription());
    }
    public void MoveScreen()
    {
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