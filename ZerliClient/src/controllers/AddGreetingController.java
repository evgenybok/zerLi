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
import javafx.scene.input.MouseEvent;
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
	    void btnDone(MouseEvent event) throws IOException {
	    	if(txtGreeting.getText().isEmpty())
	    	{
	    		Greeting=null;
	    	}
	    	else
	    	{
	    		Greeting=txtGreeting.getText();
	    		
	    	}
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
	        // Image greetingimage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Greeting.jpg")));
	        // GreetingImage.setImage(greetingimage);
	    }
}
