package controllers;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OrderCompleteController {

	@FXML
	private Button btnProceed;

	@FXML
	void clkProceed(MouseEvent event) {

		try {
			Parent parent = FXMLLoader
					.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerScreen.fxml")));
			Scene scene = new Scene(parent);
			Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			customerStage.setTitle("Customer");
			customerStage.setScene(scene);
			customerStage.show();
			customerStage.centerOnScreen();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@FXML
	void initialize() {
	}
}