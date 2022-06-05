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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddGreetingController {
	public static String Greeting;
	@FXML
	private Button Back;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button done;

	@FXML
	private TextArea txtGreeting;

    @FXML
    private ImageView greetingImg;
    
	@FXML
	void btnDone(MouseEvent event) throws IOException {
			Greeting = txtGreeting.getText();
		((Node) event.getSource()).getScene().getWindow().hide();
		try {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CartScreen.fxml")));
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage deliveryDetailsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			deliveryDetailsStage.initModality(Modality.APPLICATION_MODAL);
			deliveryDetailsStage.setTitle("Shopping Cart");
			deliveryDetailsStage.setScene(scene);
			deliveryDetailsStage.show();
			deliveryDetailsStage.centerOnScreen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		try {
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CartScreen.fxml")));
			parent.getStylesheets().add("css/styleNew.css");
			Scene scene = new Scene(parent);
			Stage deliveryDetailsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			deliveryDetailsStage.initModality(Modality.APPLICATION_MODAL);
			deliveryDetailsStage.setTitle("Shopping Cart");
			deliveryDetailsStage.setScene(scene);
			deliveryDetailsStage.show();
			deliveryDetailsStage.centerOnScreen();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@FXML
	void initialize() {
		Image greetingImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/GreetingImage.png")));
		greetingImg.setImage(greetingImage);
		txtGreeting.setText(Greeting);
		// Image greetingimage = new
		// Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Greeting.jpg")));
		// GreetingImage.setImage(greetingimage);
	}
}
