package controllers;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CatalogController {

    @FXML
    private ImageView CartImage;

    @FXML
    private ImageView ClockImage;

    @FXML
    private ImageView DeliveryImage;

    @FXML
    private Button Back;

    @FXML
    private Pane chosenFlowerCart;

    @FXML
    private ImageView flowerImage;

    @FXML
    private Label flowerName;

    @FXML
    private Label flowerPrice;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    void btnBack(MouseEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CustomerScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage customerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		customerStage.setTitle("Customer");
		customerStage.setScene(scene);
		customerStage.show();
		customerStage.centerOnScreen();
    }

    @FXML
    void initialize() {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'Catalog.fxml'.";
        assert chosenFlowerCart != null : "fx:id=\"chosenFlowerCart\" was not injected: check your FXML file 'Catalog.fxml'.";
        assert flowerImage != null : "fx:id=\"flowerImage\" was not injected: check your FXML file 'Catalog.fxml'.";
        assert flowerName != null : "fx:id=\"flowerName\" was not injected: check your FXML file 'Catalog.fxml'.";
        assert flowerPrice != null : "fx:id=\"flowerPrice\" was not injected: check your FXML file 'Catalog.fxml'.";
        assert grid != null : "fx:id=\"grid\" was not injected: check your FXML file 'Catalog.fxml'.";
        assert scroll != null : "fx:id=\"scroll\" was not injected: check your FXML file 'Catalog.fxml'.";
        Image flower = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/clipart704080.png")));
        flowerImage.setImage(flower);
        Image clockImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/pngaaa.com-372753.png")));
        ClockImage.setImage(clockImage);
        Image cartImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/img_293282.png")));
        CartImage.setImage(cartImage);
        Image deliveryImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/delivery.png")));
        DeliveryImage.setImage(deliveryImage);



    }

}
