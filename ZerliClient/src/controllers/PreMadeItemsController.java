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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PreMadeItemsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> preMadeItemsTable;

    @FXML
    private Button addItem;

    @FXML
    private Button back;

    @FXML
    private ImageView toCart;


    @FXML
    private TextField fromPrice;

    @FXML
    private TextField toPrice;

    @FXML
    private ComboBox<?> color;

    @FXML
    private Button update;

    @FXML
    void btnAddItem(MouseEvent event) {

    }

    @FXML
    void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CatalogTypeScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage catalogTypeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		catalogTypeStage.setTitle("Catalog Type");
		catalogTypeStage.setScene(scene);
		catalogTypeStage.show();
		catalogTypeStage.centerOnScreen();
    }

    @FXML
    void btnUpdate(MouseEvent event) {

    }

    @FXML
    void imgToCart(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CheckoutScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage checkoutStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		checkoutStage.setTitle("Checkout");
		checkoutStage.setScene(scene);
		checkoutStage.show();
		checkoutStage.centerOnScreen();
    }

    @FXML
    void initialize() {
        assert preMadeItemsTable != null : "fx:id=\"preMadeItemsTable\" was not injected: check your FXML file 'PreMadeItems.fxml'.";
        assert addItem != null : "fx:id=\"addItem\" was not injected: check your FXML file 'PreMadeItems.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'PreMadeItems.fxml'.";
        assert toCart != null : "fx:id=\"toCart\" was not injected: check your FXML file 'PreMadeItems.fxml'.";
        assert fromPrice != null : "fx:id=\"fromPrice\" was not injected: check your FXML file 'PreMadeItems.fxml'.";
        assert toPrice != null : "fx:id=\"toPrice\" was not injected: check your FXML file 'PreMadeItems.fxml'.";
        assert color != null : "fx:id=\"color\" was not injected: check your FXML file 'PreMadeItems.fxml'.";
        assert update != null : "fx:id=\"update\" was not injected: check your FXML file 'PreMadeItems.fxml'.";

    }
}
