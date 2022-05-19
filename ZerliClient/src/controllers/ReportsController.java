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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReportsController  {
	  @FXML
	    private Button View;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back;

    @FXML
    private Text accountType;

    @FXML
    private Text userName;

    @FXML
    void btnBack(MouseEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/branchManager.fxml")));
		Scene scene = new Scene(parent);
		Stage deliveryDetailsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		deliveryDetailsStage.setTitle("Delivery Details");
		deliveryDetailsStage.setScene(scene);
		deliveryDetailsStage.show();
		deliveryDetailsStage.centerOnScreen();
    }
    @FXML
    void btnView(MouseEvent event) {

    }


    @FXML
    void initialize() {
    }

}

