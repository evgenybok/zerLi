package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import logic.Survey;

public class CreateSurveyController {
	public String survey_num;
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
    private Button Submit;

    @FXML
    private Text SurveyNumber;
    @FXML
    void btnBack(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerService.fxml")));
		Scene scene = new Scene(parent);
		Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loginStage.setTitle("Customer Service Screen");
		loginStage.setScene(scene);
		loginStage.show();
		loginStage.centerOnScreen();
    }
    @FXML
    void CreateSurveyFunc(MouseEvent event) throws IOException 
    {
    	if((CheckIfEmpty())==false)
    	{
    		JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Info",JOptionPane.INFORMATION_MESSAGE);
    	}
    	else
    	{
    			ArrayList<String> question = new ArrayList<>();
            	question.add(Ques1.getText());
            	question.add(Ques2.getText());
            	question.add(Ques3.getText());
            	question.add(Ques4.getText());
            	question.add(Ques5.getText());
            	question.add(Ques6.getText());
            	String surveyCreator = LoginScreenController.user.getID();
            	int surveyNumber= Integer.parseInt(survey_num);
            	Survey survey= new Survey(surveyNumber,surveyCreator,question);
            	//insert query
            	if((insertSurveyToDB(survey))==true)
            	{
            		JOptionPane.showMessageDialog(null, "The Survey Was Added Succsessfully", "Info",JOptionPane.INFORMATION_MESSAGE);
                	((Node) event.getSource()).getScene().getWindow().hide();
                	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerService.fxml")));
                	Scene scene = new Scene(parent);
                	Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                	customerStage.setTitle("Customer");
                	customerStage.setScene(scene);
                	customerStage.show();
                	customerStage.centerOnScreen();
            	}
    	}
    }
    @FXML
    void initialize() {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'CreateSurveyScreen.fxml'.";
        assert Submit != null : "fx:id=\"Submit\" was not injected: check your FXML file 'CreateSurveyScreen.fxml'.";
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
    	survey_num=getSurveyNumber();
    	SurveyNumber.setText(survey_num);
    }
    public String getSurveyNumber()
    {
    	String temp;
    	try {
			chat.accept(new Message(MessageType.GET_SURVEY_NUMBER,null));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return null;

		} catch (Exception e) {
			return null;
		}
		;
		temp = (String)AnalyzeMessageFromServer.getData();
		return temp;
    }  
    public boolean insertSurveyToDB(Survey survey)
    {
    	try {
			chat.accept(new Message(MessageType.INSERT_NEW_SURVEY,survey));
			if (AnalyzeMessageFromServer.getData().equals(null)) 
				return false;

		} catch (Exception e) {
			return false;
		};
		String str = (String) AnalyzeMessageFromServer.getData();
		if(str.equals("false")) return false;
		return true;
    }
    public boolean CheckIfEmpty()
    {
    	if(Ques1.getText().isEmpty()||Ques2.getText().isEmpty()||Ques3.getText().isEmpty()||Ques4.getText().isEmpty()||
    			Ques5.getText().isEmpty()||Ques6.getText().isEmpty())
    			return false;
    	return true;
    	
		/*
		 * { JOptionPane.showMessageDialog(null, "One or more fields are empty!",
		 * "Error", JOptionPane.ERROR_MESSAGE); return false; }
		 
    	return true;
    	*/
    }
}
