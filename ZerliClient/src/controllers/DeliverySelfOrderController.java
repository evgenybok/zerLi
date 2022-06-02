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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DeliverySelfOrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back;

    @FXML
    private VBox DeliveryLayout;

    @FXML
    private Text accountType;

    @FXML
    private TextField orderIDtext;

    @FXML
    private TextField storeText;

    @FXML
    private Text userName;

    @FXML
    void SearchByOrderID(MouseEvent event) {

    }

    @FXML
    void SearchByStoreID(MouseEvent event) {

    }

    @FXML
    void btnBack(MouseEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/DeliveryLoginScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Delivery Screen");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
    }

    @FXML
    void initialize() {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'DeliverySelfOrder.fxml'.";
        assert DeliveryLayout != null : "fx:id=\"DeliveryLayout\" was not injected: check your FXML file 'DeliverySelfOrder.fxml'.";
        assert accountType != null : "fx:id=\"accountType\" was not injected: check your FXML file 'DeliverySelfOrder.fxml'.";
        assert orderIDtext != null : "fx:id=\"orderIDtext\" was not injected: check your FXML file 'DeliverySelfOrder.fxml'.";
        assert storeText != null : "fx:id=\"storeText\" was not injected: check your FXML file 'DeliverySelfOrder.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'DeliverySelfOrder.fxml'.";

    }

}