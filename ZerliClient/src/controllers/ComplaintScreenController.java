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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ComplaintScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back;

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
    void btnsend(MouseEvent event) throws IOException {
    	
    }

    @FXML
    void initialize() {
    }

}
