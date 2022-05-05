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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DeliveryDetailsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button done;

    @FXML
    private Button back;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtName;

    @FXML
    private DatePicker deliveryDate;

    @FXML
    void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ChooseStore.fxml")));
		Scene scene = new Scene(parent);
		Stage chooseStoreStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		chooseStoreStage.setTitle("Choose Store");
		chooseStoreStage.setScene(scene);
		chooseStoreStage.show();
		chooseStoreStage.centerOnScreen();
    }

    @FXML
    void btnDone(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/PaymentScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage paymentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		paymentStage.setTitle("Payment");
		paymentStage.setScene(scene);
		paymentStage.show();
		paymentStage.centerOnScreen();
    }

    @FXML
    void initialize() {
        assert done != null : "fx:id=\"done\" was not injected: check your FXML file 'DeliveryDetails.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'DeliveryDetails.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'DeliveryDetails.fxml'.";
        assert txtPhone != null : "fx:id=\"txtPhone\" was not injected: check your FXML file 'DeliveryDetails.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'DeliveryDetails.fxml'.";
        assert deliveryDate != null : "fx:id=\"deliveryDate\" was not injected: check your FXML file 'DeliveryDetails.fxml'.";

    }
}
