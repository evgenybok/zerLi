package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.SingleOrder;
import logic.SurveyAnswer;

public class AnalyseSurveyController {

	@FXML
	private Label lblZerLi;

	@FXML
	private Label lblStartMsg;

	@FXML
	private Text UserName;

	@FXML
	private ImageView PersonImage;

	@FXML
	private Text AccountType;

	@FXML
	private Button Back;

	@FXML
	private TextField IdText;

	@FXML
	private ImageView Search;

	@FXML
	private Button createReport;

	@FXML
	private TextField createReportNumber;

	@FXML
	private Label surveyID;

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
	private VBox OrdersLayout;

	@FXML
	void btnBack(MouseEvent event) {
		try {
			Parent parent = FXMLLoader
					.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerSpecialistScreen.fxml")));
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

	@FXML
	void btnCreateReport(MouseEvent event) {

	}

	@FXML
	void btnSearch(MouseEvent event) {

	}

	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {
		List<SurveyAnswer> surveyAnswer = new ArrayList<>();
		this.AccountType.setText("Customer Service Specialist"); // accountType - may be handled from DB
		this.UserName.setText(LoginScreenController.user.getUsername()); // userName
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
