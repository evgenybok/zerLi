package controllers;

import java.util.Objects;

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
import javafx.stage.Stage;

/**
 * @author Evgeny
 * Opens the screen to confirm the end of the order and payment.
 */
public class OrderCompleteController {

	@FXML
	private Button btnProceed;

	@FXML
	private Label confirmationTxt;

	@FXML
	private ImageView zerliImg;

	/**
	 * Opens the customer screen 
	 * @param event
	 */
	@FXML
	void clkProceed(MouseEvent event) {

		try {
			Parent parent = FXMLLoader
					.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerScreen.fxml")));
			parent.getStylesheets().add("css/styleNew.css");
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

	/**
	 * Initializes data shown on screen
	 */
	@FXML
	void initialize() {
		Image zerliImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo.jpg")));
		zerliImg.setImage(zerliImage);
		String completeMsg = ("Hey " + LoginScreenController.user.getFirstName() + " "
				+ LoginScreenController.user.getLastName()
				+ ",Your Order is Confirmed! We'll send you order confirmation email to "
				+ LoginScreenController.user.getEmail() + "  and SMS to " + PaymentScreenController.phoneNumber
				+ " as soon as the branch manager will approve your order.");
		confirmationTxt.setText(completeMsg);
	}
}