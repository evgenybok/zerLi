
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

/**
 * @author Evgeny
 * Greeting screen when opening the user opens the program
 */
public class WelcomeScreenController {

	@FXML
	private Button getStarted;

	/**
	 * Opens the login screen
	 * @param event
	 */
	@FXML
	void btnGetStarted(MouseEvent event) {

		try {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LoginScreen.fxml")));
			parent.getStylesheets().clear();
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage LoginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			LoginStage.setTitle("LoginScreen");
			LoginStage.setScene(scene);
			LoginStage.show();
			LoginStage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
