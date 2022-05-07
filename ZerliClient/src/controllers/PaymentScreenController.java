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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PaymentScreenController {

    @FXML
    private ImageView PaymentImage;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCardNumber;

    @FXML
    private TextField txtCardDate;

    @FXML
    private TextField txtCVV;

    @FXML
    private Button back;

    @FXML
    private Button finish;

    @FXML
    private TextField txtID;

    @FXML
    void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/DeliveryDetails.fxml")));
		Scene scene = new Scene(parent);
		Stage deliveryDetailsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		deliveryDetailsStage.setTitle("Delivery Details");
		deliveryDetailsStage.setScene(scene);
		deliveryDetailsStage.show();
		deliveryDetailsStage.centerOnScreen();
    }

    @FXML
    void btnFinish(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert txtCardNumber != null : "fx:id=\"txtCardNumber\" was not injected: check your FXML file 'PaymentScreen.fxml'.";
        assert txtCardDate != null : "fx:id=\"txtCardDate\" was not injected: check your FXML file 'PaymentScreen.fxml'.";
        assert txtCVV != null : "fx:id=\"txtCVV\" was not injected: check your FXML file 'PaymentScreen.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'PaymentScreen.fxml'.";
        assert finish != null : "fx:id=\"finish\" was not injected: check your FXML file 'PaymentScreen.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'PaymentScreen.fxml'.";

        Image paymentimage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/payment1.jpg")));
        PaymentImage.setImage(paymentimage);
    }
}
