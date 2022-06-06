package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.SurveyAnswer;

public class AnalyseSurveyController {

	/**
	 * @author Evgeny
	 * Customer specialist can view all surveys and analyse them here.
	 */
	  @FXML
	    private Text AccountType;

	    @FXML
	    private Button Back;

	    @FXML
	    private TextField IdText;

	    @FXML
	    private VBox OrdersLayout;

	    @FXML
	    private ImageView Search;

	    @FXML
	    private ImageView avatarImg;

	    @FXML
	    private Button createReport;

	    @FXML
	    private TextField createReportNumber;

	    @FXML
	    private Label lblStartMsg;

	    @FXML
	    private Label lblZerLi;

	    @FXML
	    private Label price;

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

	    @FXML
	    private Label surveyID;

	    @FXML
	    private Text userName;

	public static int[] averageScore;
	// [0]:report number, [1-6]:average scores of questions in said report.

	/**
	 * closes current screen and opens the customer specialist login screen.
	 * @param event
	 */
	@FXML
	void btnBack(MouseEvent event) {
		try {
			Parent parent = FXMLLoader
					.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerSpecialistScreen.fxml")));
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			loginStage.setTitle("Customer Specialist Screen");
			loginStage.setScene(scene);
			loginStage.show();
			loginStage.centerOnScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new analysed survey and save it in the DB.
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	@FXML
	void btnCreateReport(MouseEvent event) {
		if (createReportNumber.getText().isEmpty())
			return;
		int reportNumber = Integer.parseInt(createReportNumber.getText());
		// chat-send to server the report number
		// take it from the next screen
		List<SurveyAnswer> surveyAnswer = new ArrayList<>();
		chat.accept(new Message(MessageType.GET_SURVEY_ANSWERS, null));
		surveyAnswer = (List<SurveyAnswer>) AnalyzeMessageFromServer.getData();
		averageScore = new int[7]; // average score of all questions for survey 'reportNumber'.
		int counter = 0;
		averageScore[0] = reportNumber;
		for (SurveyAnswer sAnswer : surveyAnswer) {
			if (reportNumber == sAnswer.getSurveyNumber()) {
				averageScore[1] += sAnswer.getQ1();
				averageScore[2] += sAnswer.getQ2();
				averageScore[3] += sAnswer.getQ3();
				averageScore[4] += sAnswer.getQ4();
				averageScore[5] += sAnswer.getQ5();
				averageScore[6] += sAnswer.getQ6();
				counter++;
			}
		}

		for (int i = 1; i <= 6; i++) {
			if (averageScore[i] == 0) {
				JOptionPane.showMessageDialog(null, "Invalid survey number!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			averageScore[i] /= counter;
		}

		try {
			Parent parent = FXMLLoader
					.load(Objects.requireNonNull(getClass().getResource("/fxml/SurveyReportScreen.fxml")));
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			loginStage.setTitle("Survey Report Screen");
			loginStage.setScene(scene);
			loginStage.show();
			loginStage.centerOnScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Searches report by survey number and shows all matching surveys in the table.
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	@FXML
	void btnSearch(MouseEvent event) {
		OrdersLayout.getChildren().clear();
		String surveyID = IdText.getText();
		if(surveyID.isEmpty())
			initialize();
		List<SurveyAnswer> surveyAnswer = new ArrayList<>();
		chat.accept(new Message(MessageType.GET_SURVEY_ANSWERS, null));
		try {
			surveyAnswer = (List<SurveyAnswer>) AnalyzeMessageFromServer.getData();
			for (int i = 0; i < surveyAnswer.size(); i++) {
				if (surveyID.equals(Integer.toString(surveyAnswer.get(i).getSurveyNumber()))) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("/fxml/SingleSurveyAnswer.fxml"));
					HBox hBox = fxmlLoader.load();
					SingleSurveyController singleSurveyController = fxmlLoader.getController();
					singleSurveyController.setData(surveyAnswer.get(i));
					OrdersLayout.getChildren().add(hBox);
				}
			}
		} catch (Exception e1) {
			initialize();
			e1.printStackTrace();
		}
	}

	/**
	 * Initializes the table to show all surveys.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		userName.setText(LoginScreenController.user.getUsername());
		OrdersLayout.getChildren().clear();
		List<SurveyAnswer> surveyAnswer = new ArrayList<>();
		this.AccountType.setText("Customer Service Specialist"); // accountType - may be handled from DB
		this.userName.setText(LoginScreenController.user.getUsername()); // userName
		chat.accept(new Message(MessageType.GET_SURVEY_ANSWERS, null));
		try {
			surveyAnswer = (List<SurveyAnswer>) AnalyzeMessageFromServer.getData();
			for (int i = 0; i < surveyAnswer.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/SingleSurveyAnswer.fxml"));
				HBox hBox = fxmlLoader.load();
				SingleSurveyController singleSurveyController = fxmlLoader.getController();
				singleSurveyController.setData(surveyAnswer.get(i));
				OrdersLayout.getChildren().add(hBox);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
