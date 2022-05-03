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
	private TextArea orderDetails;

	@FXML
	private Label Title;

	@FXML
	void initialize() {
		String data = AnalyzeMessageFromServer.getData();
		if (!data.equals("ERROR")) {
			String[] temporal = data.split("#", 2);
			orderDetails.appendText(temporal[0] + "\n");
			data = temporal[1];
			String temp = "";
			while (!data.equals("@")) {
				String[] orderNumber = data.split("#", 2);
				for (int i = 0; i < orderNumber[0].length(); i++) {
					if (orderNumber[0].charAt(i) == (',') || orderNumber[0].charAt(i) == ('#') || orderNumber[0].charAt(i) == ('[') || orderNumber[0].charAt(i) == (']')) {
						orderDetails.appendText(temp + "\n");
						temp = "";
					} else {
						temp += orderNumber[0].charAt(i);
					}
				}
				data = orderNumber[1];
			}
		}
		assert orderDetails != null
				: "fx:id=\"orderDetails\" was not injected: check your FXML file 'OrderDetails.fxml'.";
		assert Title != null : "fx:id=\"Title\" was not injected: check your FXML file 'OrderDetails.fxml'.";

	}
}
