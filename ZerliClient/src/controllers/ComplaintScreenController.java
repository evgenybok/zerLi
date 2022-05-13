package controllers;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ComplaintScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField desTxt;

    @FXML
    private ImageView image;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button sendbtn;

    @FXML
    private TextField subTxt;

    @FXML
    void btnsend(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert desTxt != null : "fx:id=\"desTxt\" was not injected: check your FXML file 'CustomerComplaintScreen.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'CustomerComplaintScreen.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'CustomerComplaintScreen.fxml'.";
        assert sendbtn != null : "fx:id=\"sendbtn\" was not injected: check your FXML file 'CustomerComplaintScreen.fxml'.";
        assert subTxt != null : "fx:id=\"subTxt\" was not injected: check your FXML file 'CustomerComplaintScreen.fxml'.";
        //Image homeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ComplaintImage.jpeg")));
        //image.setImage(homeImage);
    }

}
