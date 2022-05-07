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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChooseStoreController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private Button done;

    @FXML
    private ComboBox<?> chooseStore;

    @FXML
    private ComboBox<?> chooseArea;

    @FXML
    private CheckBox delivery;

    @FXML
    private CheckBox pickUp;

    @FXML
    private ImageView StoreImage;

    @FXML
    void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CheckoutScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage checkoutStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		checkoutStage.setTitle("Checkout");
		checkoutStage.setScene(scene);
		checkoutStage.show();
		checkoutStage.centerOnScreen();
    }

    @FXML
    void btnDone(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/DeliveryDetails.fxml")));
		Scene scene = new Scene(parent);
		Stage deliveryDetailsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		deliveryDetailsStage.setTitle("Delivery Details");
		deliveryDetailsStage.setScene(scene);
		deliveryDetailsStage.show();
		deliveryDetailsStage.centerOnScreen();
    }

    @FXML
    void checkBoxDelivery(MouseEvent event) {

    }

    @FXML
    void checkBoxPickUp(MouseEvent event) {

    }

    @FXML
    void comboChooseArea(MouseEvent event) {

    }

    @FXML
    void comboChooseStore(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'ChooseStore.fxml'.";
        assert done != null : "fx:id=\"done\" was not injected: check your FXML file 'ChooseStore.fxml'.";
        assert chooseStore != null : "fx:id=\"chooseStore\" was not injected: check your FXML file 'ChooseStore.fxml'.";
        assert chooseArea != null : "fx:id=\"chooseArea\" was not injected: check your FXML file 'ChooseStore.fxml'.";
        assert delivery != null : "fx:id=\"delivery\" was not injected: check your FXML file 'ChooseStore.fxml'.";
        assert pickUp != null : "fx:id=\"pickUp\" was not injected: check your FXML file 'ChooseStore.fxml'.";

        Image storeimage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/choosestore.jpg")));
        StoreImage.setImage(storeimage);
    }
}
