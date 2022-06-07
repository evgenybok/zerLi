package controllers;

import static controllers.IPScreenController.chat;

import java.io.IOException;
import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Survey;

/**
 * @author Evgeny
 * Customer service user can create a survey with questions here.
 */
public class CreateSurveyController {
	public String survey_num;
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
	private Button Submit;

	 @FXML
	private Text SurveyNumber;

	@FXML
	private Text accountType;

	@FXML
	private ImageView avatarImg;

	@FXML
	private Text userName;

	/**
	 * Sends the user back to the customer service screen.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnBack(MouseEvent event) throws IOException {
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerService.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage csScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
		csScreen.setTitle("Customer Service Screen");
		csScreen.setScene(scene);
		csScreen.show();
		csScreen.centerOnScreen();
	}

	/**
	 * Creates a new survey with the entered question on screen.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void CreateSurveyFunc(MouseEvent event) throws IOException {
		if ((CheckIfEmpty()) == false) {
			JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			ArrayList<String> question = new ArrayList<>();
			question.add(Ques1.getText());
			question.add(Ques2.getText());
			question.add(Ques3.getText());
			question.add(Ques4.getText());
			question.add(Ques5.getText());
			question.add(Ques6.getText());
			String surveyCreator = LoginScreenController.user.getID();
			int surveyNumber = Integer.parseInt(survey_num);
			Survey survey = new Survey(surveyNumber, surveyCreator, question);
			// insert query
			if ((insertSurveyToDB(survey)) == true) {
				JOptionPane.showMessageDialog(null, "The Survey Was Added Succsessfully", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				((Node) event.getSource()).getScene().getWindow().hide();
				Parent parent = FXMLLoader
						.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerService.fxml")));
				parent.getStylesheets().add("css/styleNew.css");
				Scene scene = new Scene(parent);
				Stage csScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
				csScreen.setTitle("Customer");
				csScreen.setScene(scene);
				csScreen.show();
				csScreen.centerOnScreen();
			}
		}
	}

	/**
	 * Initializes data shown on screen
	 */
	@FXML
	void initialize() {
		Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
		avatarImg.setImage(personImage);
		this.accountType.setText("Customer Service"); // accountType
		this.userName.setText(LoginScreenController.user.getUsername()); // userName
		survey_num = getSurveyNumber();
		SurveyNumber.setText(survey_num);
	}

	/**
	 * Gets the next serial survey number
	 * @return new survey number
	 */
	public String getSurveyNumber() {
		String temp;
		try {
			chat.accept(new Message(MessageType.GET_SURVEY_NUMBER, null));
			if (AnalyzeMessageFromServer.getData().equals(null)) // Incorrect username / password
				return null;

		} catch (Exception e) {
			return null;
		}
		;
		temp = (String) AnalyzeMessageFromServer.getData();
		return temp;
	}

	/**
	 * Inserts the filled survey to the DB
	 * @param survey given survey to insert
	 * @return true if successful, false otherwise.
	 */
	public boolean insertSurveyToDB(Survey survey) {
		try {
			chat.accept(new Message(MessageType.INSERT_NEW_SURVEY, survey));
			if (AnalyzeMessageFromServer.getData().equals(null))
				return false;

		} catch (Exception e) {
			return false;
		}
		;
		String str = (String) AnalyzeMessageFromServer.getData();
		if (str.equals("false"))
			return false;
		return true;
	}

	/**
	 * Check if any of the question fields are empty.
	 * @return false if fields are empty, true otherwise
	 */
	public boolean CheckIfEmpty() {
		if (Ques1.getText().isEmpty() || Ques2.getText().isEmpty() || Ques3.getText().isEmpty()
				|| Ques4.getText().isEmpty() || Ques5.getText().isEmpty() || Ques6.getText().isEmpty())
			return false;
		return true;
	}
}
