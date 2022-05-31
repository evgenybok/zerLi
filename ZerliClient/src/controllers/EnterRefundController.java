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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EnterRefundController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Refund;

    @FXML
    void submitFunc(MouseEvent event) throws IOException {
    	String refund = Refund.getText();
    	String us= SingleComplaintController.user_id;
    	String or= SingleComplaintController.order_id;
    	String res = refund + "@" + us + "@" +or ;
    	System.out.println(" res is      " +res);
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
    	/*Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/HandelComplaint.fxml")));
		Scene scene = new Scene(parent);
		Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loginStage.setTitle("Handel Complaint Screen");
		loginStage.setScene(scene);
		loginStage.show();
		loginStage.centerOnScreen();*/
    }
    @FXML
    void initialize() {
        assert Refund != null : "fx:id=\"Refund\" was not injected: check your FXML file 'EnterRefund.fxml'.";
    }

}
