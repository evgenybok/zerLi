package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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

public class CreateSurveyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back;

    @FXML
    private TextField Ques1;

    @FXML
    private TextField Ques2;

    @FXML
    private TextField Ques3;

    @FXML
    private TextField Ques4;

    @FXML
    private TextField Ques5;

    @FXML
    private TextField Ques6;

    @FXML
    private Text accountStatus;

    @FXML
    private Text accountType;

    @FXML
    private Text userName;

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
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'CreateSurveyScreen.fxml'.";
        assert Ques1 != null : "fx:id=\"Ques1\" was not injected: check your FXML file 'CreateSurveyScreen.fxml'.";
        assert Ques2 != null : "fx:id=\"Ques2\" was not injected: check your FXML file 'CreateSurveyScreen.fxml'.";
        assert Ques3 != null : "fx:id=\"Ques3\" was not injected: check your FXML file 'CreateSurveyScreen.fxml'.";
        assert Ques4 != null : "fx:id=\"Ques4\" was not injected: check your FXML file 'CreateSurveyScreen.fxml'.";
        assert Ques5 != null : "fx:id=\"Ques5\" was not injected: check your FXML file 'CreateSurveyScreen.fxml'.";
        assert Ques6 != null : "fx:id=\"Ques6\" was not injected: check your FXML file 'CreateSurveyScreen.fxml'.";
        assert accountStatus != null : "fx:id=\"accountStatus\" was not injected: check your FXML file 'CreateSurveyScreen.fxml'.";
        assert accountType != null : "fx:id=\"accountType\" was not injected: check your FXML file 'CreateSurveyScreen.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'CreateSurveyScreen.fxml'.";
        this.accountStatus.setText("CONFIRMED"); // accountStatus - need to be handled from DB
		this.accountType.setText("Customer Service"); // accountType - may be handled from DB
    	this.userName.setText(LoginScreenController.user.getUsername()); //userName
    }

}
