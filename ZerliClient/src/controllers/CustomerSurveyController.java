package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JOptionPane;

import clientanalyze.AnalyzeMessageFromServer;
import communication.Message;
import communication.MessageType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Evgeny
 * Allows the store worker to fill the input the suvery feedback received from the customer
 */
public class CustomerSurveyController {


    @FXML
    private Text AccountType;

    @FXML
    private ComboBox<String> Combo1;

    @FXML
    private ComboBox<String> Combo2;

    @FXML
    private ComboBox<String> Combo3;

    @FXML
    private ComboBox<String> Combo4;

    @FXML
    private ComboBox<String> Combo5;

    @FXML
    private ComboBox<String> Combo6;

    @FXML
    private Label EnumNum;

    @FXML
    private ImageView PersonImage;

    @FXML
    private Label Question1;

    @FXML
    private Label Question2;

    @FXML
    private Label Question3;

    @FXML
    private Label Question4;

    @FXML
    private Label Question5;

    @FXML
    private Label Question6;

    @FXML
    private Text UserName;

    @FXML
    private Button back;

    @FXML
    private Label lblStartMsg;

    @FXML
    private Label lblZerLi;

    @FXML
    private Button submit;

    @FXML
    private ImageView survImage;
    @FXML
    private ComboBox<String> surveyNum;
    


    /**
     * Sends the user back to the customer screen
     * @param event
     * @throws IOException
     */
    @FXML
    void btnBack(MouseEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/WorkerScreen.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage workerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		workerStage.setTitle("Worker Screen");
		workerStage.setScene(scene);
		workerStage.show();
		workerStage.centerOnScreen();
    }

    /**
     * Submits customer's answers to the survey and saves them in the DB
     * @param event
     * @throws IOException
     */
    @FXML
    void btnSubmit(MouseEvent event) throws IOException {
    	String ans =EnumNum.getText() +"@"+ Combo1.getValue() + "@"+Combo2.getValue()+ "@"+Combo3.getValue()+ "@"+Combo4.getValue()
    	+ "@"+Combo5.getValue()+ "@"+Combo6.getValue();
    	try {
			chat.accept(new Message(MessageType.UPDATE_SURVEY_ANS,ans));
			if (AnalyzeMessageFromServer.getData().equals(null)) { // Incorrect username / password
				JOptionPane.showMessageDialog(null, "An error occured, try again.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

		} catch (Exception e) {
			return;
		}
    	JOptionPane.showMessageDialog(null, "Answers Successfully Saved, Thank you", "Info",JOptionPane.INFORMATION_MESSAGE);
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerSurvey.fxml")));
		Scene scene = new Scene(parent);
		Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loginStage.setTitle("Survey Answers");
		loginStage.setScene(scene);
		loginStage.show();
		loginStage.centerOnScreen();
    }


    /**
     * Data initialization shown on screen
     */
    @FXML
    void initialize() {
    	this.UserName.setText(LoginScreenController.user.getUsername()); // userName
        Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
    	PersonImage.setImage(personImage);
    	Image surveyimage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/satiComplaint.png")));
    	survImage.setImage(surveyimage);
        Combo1.setItems(FXCollections.observableArrayList("1", "2","3","4","5","6","7","8","9","10"));
        Combo2.setItems(FXCollections.observableArrayList("1", "2","3","4","5","6","7","8","9","10"));
        Combo3.setItems(FXCollections.observableArrayList("1", "2","3","4","5","6","7","8","9","10"));
        Combo4.setItems(FXCollections.observableArrayList("1", "2","3","4","5","6","7","8","9","10"));
        Combo5.setItems(FXCollections.observableArrayList("1", "2","3","4","5","6","7","8","9","10"));
        Combo6.setItems(FXCollections.observableArrayList("1", "2","3","4","5","6","7","8","9","10"));
        ArrayList<String> surveyNumList = new ArrayList<>();
        surveyNumList = getSurveyNumber();
        surveyNum.setItems(FXCollections.observableArrayList(surveyNumList));
       // EnumNum.setText(res);

    }
    /**
     * @return number of the next survey number.
     */
    public ArrayList<String> getSurveyNumber(){
    	ArrayList<String> list = new ArrayList<>();
    	try {
			chat.accept(new Message(MessageType.GET_SURVEY_NUMBERS_COMBO,null));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return null;

		} catch (Exception e) {
			return null;
		}
    	list = (ArrayList<String>)AnalyzeMessageFromServer.getData();
    	return list;
    }
    
    	
    /**
     * @return list of survey questions retrieved from the DB.
     */
    public ArrayList<String> setQuestion(){
		ArrayList<String> list = new ArrayList<>();
		try {
			chat.accept(new Message(MessageType.GET_SURVEY_QUS_BY_SURVEYNUM,surveyNum.getValue()));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return null;
		} catch (Exception e) {
			return null;
		};
		list = (ArrayList<String>)AnalyzeMessageFromServer.getData();
		
		return list;
    }
    @FXML
    public void SelectSurveyNumber(ActionEvent event) {
      try {
    		ArrayList<String> list = setQuestion();
    		   Question1.setText(list.get(0));
    		   Question2.setText(list.get(1));
    		   Question3.setText(list.get(2));
    		   Question4.setText(list.get(3));
    		   Question5.setText(list.get(4));
    		   Question6.setText(list.get(5));
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
}
