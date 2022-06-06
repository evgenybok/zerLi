package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * @author Evgeny
 * Customer service user can refund a customer after they issued a complaint.
 */
public class EnterRefundController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Refund;

    /**
     * Updates the refund amount on the selected customer screen and saves refund in DB.
     * @param event
     * @throws IOException
     */
    @FXML
    void submitFunc(MouseEvent event) throws IOException {
    	String refund = Refund.getText();
    	String us= SingleComplaintController.user_id;
    	String or= SingleComplaintController.order_id;
    	String res = refund + "@" + us + "@" +or ;
    	try {
			chat.accept(new Message(MessageType.UPDATE_REFUND, res));
			if (AnalyzeMessageFromServer.getData().equals(null)) 
				return;
		} catch (Exception e) {
			return;
		};
		try {
			chat.accept(new Message(MessageType.UPDATE_STATUS_COMPLAINT,or));
			if (AnalyzeMessageFromServer.getData().equals(null)) 
				return;
		} catch (Exception e) {
			return;
		};
		JOptionPane.showMessageDialog(null, "The Treatment Handle Succsessfully", "Info",JOptionPane.INFORMATION_MESSAGE);
		((Node)event.getSource()).getScene().getWindow().hide();
		HandelComplaintController handelComplaintController = new HandelComplaintController();
		handelComplaintController.initialize();
    }
}
