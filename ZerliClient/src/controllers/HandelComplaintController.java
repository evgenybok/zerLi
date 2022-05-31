package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.SingleComplaint;
import logic.SingleOrder;

public class HandelComplaintController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back;

    @FXML
    private TextField IdSearch;

    @FXML
    private Text accountStatus;

    @FXML
    private Text accountType;

    @FXML
    private Text userName;
    @FXML
    private VBox ComplaintLayout;

    @FXML
    void btnBack(MouseEvent event) throws IOException {
    	((Node)event.getSource()).getScene().getWindow().hide();
 		chat.accept(new Message(MessageType.LOGOUT,LoginScreenController.user));
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerService.fxml")));
		Scene scene = new Scene(parent);
		Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loginStage.setTitle("Create Survey Screen");
		loginStage.setScene(scene);
		loginStage.show();
		loginStage.centerOnScreen();
    }

    @FXML
    void initialize() {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'HandelComplaint.fxml'.";
        assert IdSearch != null : "fx:id=\"IdSearch\" was not injected: check your FXML file 'HandelComplaint.fxml'.";
        assert accountStatus != null : "fx:id=\"accountStatus\" was not injected: check your FXML file 'HandelComplaint.fxml'.";
        assert accountType != null : "fx:id=\"accountType\" was not injected: check your FXML file 'HandelComplaint.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'HandelComplaint.fxml'.";
        this.accountStatus.setText("CONFIRMED"); // accountStatus - need to be handled from DB
		this.accountType.setText("Customer Service"); // accountType - may be handled from DB
    	this.userName.setText(LoginScreenController.user.getUsername()); //userName
    	List<SingleComplaint> complaint = new ArrayList<>();
		chat.accept(new Message(MessageType.GET_COMPLAINTS, LoginScreenController.user.getID()));
		complaint = (ArrayList<SingleComplaint>) AnalyzeMessageFromServer.getData();
		try {

			for (int i = 0; i < complaint.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleComplaintScreen.fxml"));
				HBox hBox = fxmlLoader.load();
				SingleComplaintController singleComplaintController = fxmlLoader.getController();
				singleComplaintController.setData(complaint.get(i));
				ComplaintLayout.getChildren().add(hBox);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    @FXML
    void Serach(MouseEvent event) {
    	ArrayList<SingleComplaint> complain = new ArrayList<>();
		String userid = IdSearch.getText();
		ComplaintLayout.getChildren().clear();
		if(IdSearch.getText().isEmpty())
		{
			initialize();
		}
		try {
			chat.accept(new Message(MessageType.GET_COMPLAINT_BY_ID, userid));
			if (AnalyzeMessageFromServer.getData().equals(null)) 
				return;
			
		} catch (Exception e) {
			return;
		}
		;
		complain = (ArrayList<SingleComplaint>) AnalyzeMessageFromServer.getData();
		try {

			for (int i = 0; i < complain.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleComplaintScreen.fxml"));
				HBox hBox = fxmlLoader.load();
				SingleComplaintController singleComplaintController = fxmlLoader.getController();
				singleComplaintController.setData(complain.get(i)); 
				ComplaintLayout.getChildren().add(hBox);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
}

