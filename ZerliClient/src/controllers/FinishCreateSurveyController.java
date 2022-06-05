package controllers;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FinishCreateSurveyController {

	@FXML
	private Button backBtn;

	@FXML
	void MoveToMenuScreen(MouseEvent event) throws IOException {
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerService.fxml")));
		parent.getStylesheets().add("css/styleNew.css");
		Scene scene = new Scene(parent);
		Stage csScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
		csScreen.setTitle("Customer Service Screen");
		csScreen.setScene(scene);
		csScreen.show();
		csScreen.centerOnScreen();
	}

}
