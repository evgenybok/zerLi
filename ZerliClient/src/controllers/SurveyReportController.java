package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.Objects;

import communication.Message;
import communication.MessageType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private TextField path;

	@FXML
	void btnBack(MouseEvent event) {
		try {
			Parent parent = FXMLLoader
					.load(Objects.requireNonNull(getClass().getResource("/fxml/SPAnalyseSurveyScreen.fxml")));
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
		String[] report = new String[2];
		String conclusion = textConclusion.getText();
		conclusion += "\n Report analysed by:" + LoginScreenController.user.getUsername() + ".";
		//conclusion = conclusion.replace("\n", "").replace("\r", "");
		report[0] = conclusion;
		String filePath = path.getText();
		report[1] = filePath;
		try {
			chat.accept(new Message(MessageType.SAVE_ANALYSIS, report));
		} catch (Exception e) {
			System.out.println("OH NO");
		}
	}

	@FXML
	void initialize() {
		// AnalyseSurveyController.averageScore[0];
		surveyNumber.setText(Integer.toString(AnalyseSurveyController.averageScore[0]));
		avgQ1.setText(Integer.toString(AnalyseSurveyController.averageScore[1]));
		avgQ2.setText(Integer.toString(AnalyseSurveyController.averageScore[2]));
		avgQ3.setText(Integer.toString(AnalyseSurveyController.averageScore[3]));
		avgQ4.setText(Integer.toString(AnalyseSurveyController.averageScore[4]));
		avgQ5.setText(Integer.toString(AnalyseSurveyController.averageScore[5]));
		avgQ6.setText(Integer.toString(AnalyseSurveyController.averageScore[6]));
	}

}
