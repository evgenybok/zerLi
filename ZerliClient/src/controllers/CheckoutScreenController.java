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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CheckoutScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> checkoutTable;

    @FXML
    private Button chooseStore;

    @FXML
    private Button addGreeting;

    @FXML
    private Button back;

    @FXML
    private Label totalSum;

    @FXML
    private Button remove;

    @FXML
    void btnAddGreeting(MouseEvent event) throws IOException {
		FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(("/fxml/AddGreeting.fxml")));
		Parent root=(Parent) fxmlLoader.load();
		Stage addGreetingStage=new Stage();
		addGreetingStage.setTitle("Add Greeting");
		addGreetingStage.setScene((new Scene(root)));
		addGreetingStage.show();
		addGreetingStage.centerOnScreen();
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
    void btnChooseStore(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ChooseStore.fxml")));
		Scene scene = new Scene(parent);
		Stage chooseStoreStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		chooseStoreStage.setTitle("Choose Store");
		chooseStoreStage.setScene(scene);
		chooseStoreStage.show();
		chooseStoreStage.centerOnScreen();
    }

    @FXML
    void btnRemove(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert checkoutTable != null : "fx:id=\"checkoutTable\" was not injected: check your FXML file 'CheckoutScreen.fxml'.";
        assert chooseStore != null : "fx:id=\"chooseStore\" was not injected: check your FXML file 'CheckoutScreen.fxml'.";
        assert addGreeting != null : "fx:id=\"addGreeting\" was not injected: check your FXML file 'CheckoutScreen.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'CheckoutScreen.fxml'.";
        assert totalSum != null : "fx:id=\"totalSum\" was not injected: check your FXML file 'CheckoutScreen.fxml'.";
        assert remove != null : "fx:id=\"remove\" was not injected: check your FXML file 'CheckoutScreen.fxml'.";

    }
}
