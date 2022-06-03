package controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import communication.Message;
import communication.MessageType;

import static controllers.IPScreenController.chat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
//import logic.MyFile;

public class SurveyReportController {

    @FXML
    private Label surveyNumber;

    @FXML
    private Label avgQ1;

    @FXML
    private Label avgQ2;

    @FXML
    private Label avgQ3;

    @FXML
    private Label avgQ4;

    @FXML
    private Label avgQ5;

    @FXML
    private Label avgQ6;

    @FXML
    private TextArea textConclusion;

    @FXML
    private Button back;

    @FXML
    private Button save;

    @FXML
    void btnBack(MouseEvent event) {
    	try {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SPAnalyseSurveyScreen.fxml")));
			Scene scene = new Scene(parent);
			Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			loginStage.setTitle("Analyse Survey");
			loginStage.setScene(scene);
			loginStage.show();
			loginStage.centerOnScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void btnSave(MouseEvent event) {
		/*
		 * String report=textConclusion.getText();
		 * 
		 * //String [] n = message.split("\\\\"); MyFile msg= new MyFile(report);
		 * msg.setFileName("AnalysisReprort" + surveyNumber.getText()); //String
		 * LocalfilePath="C:\\Users\\classroom\\Desktop\\" +
		 * ((MyFile)msg).getFileName();
		 * 
		 * //String LocalfilePath="/Reports";
		 * 
		 * //System.out.println(LocalfilePath);
		 * 
		 * try{ File newFile = new File ("/Reports");
		 * 
		 * byte [] mybytearray = new byte [(int)newFile.length()]; FileInputStream fis =
		 * new FileInputStream(newFile); BufferedInputStream bis = new
		 * BufferedInputStream(fis);
		 * 
		 * msg.initArray(mybytearray.length); msg.setSize(mybytearray.length);
		 * 
		 * bis.read(msg.getMybytearray(),0,mybytearray.length); chat.accept(new
		 * Message(MessageType.SAVE_ANALYSIS, report)); } catch (Exception e) {
		 * System.out.println("Error send (Files)msg) to Server"); }
		 */
    	
    	//add name at the end of the report.
    	String conclusion=textConclusion.getText();
    	conclusion+="\n Report analysed by:" + LoginScreenController.user.getUsername()+".";
    }
    
    @FXML
	void initialize() {
    	//AnalyseSurveyController.averageScore[0];
    	surveyNumber.setText(Integer.toString(AnalyseSurveyController.averageScore[0]));
    	avgQ1.setText(Integer.toString(AnalyseSurveyController.averageScore[1]));
    	avgQ2.setText(Integer.toString(AnalyseSurveyController.averageScore[2]));
    	avgQ3.setText(Integer.toString(AnalyseSurveyController.averageScore[3]));
    	avgQ4.setText(Integer.toString(AnalyseSurveyController.averageScore[4]));
    	avgQ5.setText(Integer.toString(AnalyseSurveyController.averageScore[5]));
    	avgQ6.setText(Integer.toString(AnalyseSurveyController.averageScore[6]));
	}

}
