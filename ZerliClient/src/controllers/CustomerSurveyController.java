package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerSurveyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text AccountStatus;

    @FXML
    private Text AccountType;

    @FXML
    private ImageView PersonImage;

    @FXML
    private Text UserName;

    @FXML
    private Button back;

    @FXML
    private Label lblStartMsg;

    @FXML
    private Label lblZerLi;
    
    @FXML
    private ImageView survImage;
   
    @FXML
    void btnBack(MouseEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
    }

    @FXML
    void initialize() {
        assert AccountStatus != null : "fx:id=\"AccountStatus\" was not injected: check your FXML file 'CustomerSurvey.fxml'.";
        assert AccountType != null : "fx:id=\"AccountType\" was not injected: check your FXML file 'CustomerSurvey.fxml'.";
        assert PersonImage != null : "fx:id=\"PersonImage\" was not injected: check your FXML file 'CustomerSurvey.fxml'.";
        assert UserName != null : "fx:id=\"UserName\" was not injected: check your FXML file 'CustomerSurvey.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'CustomerSurvey.fxml'.";
        assert lblStartMsg != null : "fx:id=\"lblStartMsg\" was not injected: check your FXML file 'CustomerSurvey.fxml'.";
        assert lblZerLi != null : "fx:id=\"lblZerLi\" was not injected: check your FXML file 'CustomerSurvey.fxml'.";
        Image personImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Avatar.png")));
    	PersonImage.setImage(personImage);
    	Image surveyImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-survey-100.png")));
    	survImage.setImage(surveyImage);
    }

}
