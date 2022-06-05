package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import logic.SurveyAnswer;

/**
 * @author Evgeny
 * Sets data for a single survey later used when showing all surveys.
 */
public class SingleSurveyController {

	@FXML
	private Label userID;

	@FXML
	private Label surveyNumber;

	@FXML
	private Label question1;

	@FXML
	private Label question2;

	@FXML
	private Label question3;

	@FXML
	private Label question4;

	@FXML
	private Label question5;

	@FXML
	private Label question6;

	/**
	 * Sets data for a single survey.
	 * @param surveyAnswer
	 */
	public void setData(SurveyAnswer surveyAnswer) {
		userID.setText(Integer.toString(surveyAnswer.getID()));
		surveyNumber.setText(Integer.toString(surveyAnswer.getSurveyNumber()));
		question1.setText(Integer.toString(surveyAnswer.getQ1()));
		question2.setText(Integer.toString(surveyAnswer.getQ2()));
		question3.setText(Integer.toString(surveyAnswer.getQ3()));
		question4.setText(Integer.toString(surveyAnswer.getQ4()));
		question5.setText(Integer.toString(surveyAnswer.getQ5()));
		question6.setText(Integer.toString(surveyAnswer.getQ6()));
	}

}
