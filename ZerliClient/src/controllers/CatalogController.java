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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CatalogController {

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

}
