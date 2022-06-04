package controllers;

import java.net.URL;

import javax.swing.JOptionPane;

import clientanalyze.AnalyzeMessageFromServer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OrderDetailsController {

	@FXML
	private URL location;

	@FXML
	private TextArea txtOrderDetails;

	@FXML
	private Label lblTitle;

    @FXML
    private Button btnBack;

    @FXML
    private Label lblOrderNumber;

    @FXML
    void btnBack(MouseEvent event) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }
	@FXML
	void initialize() {
		String data = (String) AnalyzeMessageFromServer.getData();
		if (!data.equals("ERROR")) {
			String[] temporal = data.split("#", 2);
			lblOrderNumber.setText(temporal[0]);	//order number
			data = temporal[1];
			String temp = "";
			while (!data.equals("@")) {
				String[] orderNumber = data.split("#", 2);
				for (int i = 0; i < orderNumber[0].length(); i++) {
					if (orderNumber[0].charAt(i) == (',') || orderNumber[0].charAt(i) == ('#') || orderNumber[0].charAt(i) == ('[') || orderNumber[0].charAt(i) == (']')) {
						txtOrderDetails.appendText(temp + "\n");
						temp = "";
					} else {
						temp += orderNumber[0].charAt(i);
					}
				}
				data = orderNumber[1];
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Could not open order", "Error", JOptionPane.WARNING_MESSAGE);

	}
}
