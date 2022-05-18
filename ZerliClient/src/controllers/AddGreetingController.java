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
import javafx.stage.Stage;

public class AddGreetingController {

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
	    void btnDone(MouseEvent event) {

	    }
	    @FXML
	    void btnBack(MouseEvent event) throws IOException {
	    	((Node) event.getSource()).getScene().getWindow().hide();
			Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CartScreen.fxml")));
			Scene scene = new Scene(parent);
			Stage deliveryDetailsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			deliveryDetailsStage.setTitle("Shopping Cart");
			deliveryDetailsStage.setScene(scene);
			deliveryDetailsStage.show();
			deliveryDetailsStage.centerOnScreen();
	    }

	    @FXML
	    void initialize() {
	        assert done != null : "fx:id=\"done\" was not injected: check your FXML file 'AddGreeting.fxml'.";
	        assert txtGreeting != null : "fx:id=\"txtGreeting\" was not injected: check your FXML file 'AddGreeting.fxml'.";
	        // Image greetingimage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Greeting.jpg")));
	        // GreetingImage.setImage(greetingimage);
	    }
}
