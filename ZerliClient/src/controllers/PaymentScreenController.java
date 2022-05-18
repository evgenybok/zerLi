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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PaymentScreenController {

	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TextField Adress;

	    @FXML
	    private ComboBox<?> Area;

	    @FXML
	    private Button Back;

	    @FXML
	    private TextField CVV;

	    @FXML
	    private TextField CardDate;

	    @FXML
	    private TextField CardNum;

	    @FXML
	    private DatePicker Date;

	    @FXML
	    private Button Pay;

	    @FXML
	    private TextField Phone;

	    @FXML
	    private TextField ReciverName;

	    @FXML
	    private ComboBox<?> StoreName;

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
    void btnPay(MouseEvent event) {

    }

    @FXML
    void initialize() {
       // Image paymentimage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/payment1.jpg")));
        //PaymentImage.setImage(paymentimage);
    	assert Adress != null : "fx:id=\"Adress\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert Area != null : "fx:id=\"Area\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert CVV != null : "fx:id=\"CVV\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert CardDate != null : "fx:id=\"CardDate\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert CardNum != null : "fx:id=\"CardNum\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert Date != null : "fx:id=\"Date\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert Pay != null : "fx:id=\"Pay\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert Phone != null : "fx:id=\"Phone\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert ReciverName != null : "fx:id=\"ReciverName\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert StoreName != null : "fx:id=\"StoreName\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";

    }
}
