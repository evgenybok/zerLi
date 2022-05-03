package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import clientanalyze.AnalyzeMessageFromServer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class OrderDetailsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Title;

    @FXML
    private TextArea orderDetails;

    @FXML
    void initialize() {
    	String data = AnalyzeMessageFromServer.getData();
		if (!data.equals("ERROR")) {
			data.split("#", 3);
			orderDetails.appendText(data);
        assert Title != null : "fx:id=\"Title\" was not injected: check your FXML file 'OrderDetails.fxml'.";
        assert orderDetails != null : "fx:id=\"orderDetails\" was not injected: check your FXML file 'OrderDetails.fxml'.";

    }
}
}
