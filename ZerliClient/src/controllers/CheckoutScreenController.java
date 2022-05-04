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
		Parent root1=(Parent) fxmlLoader.load();
		Stage stage=new Stage();
		stage.setTitle("Add Greeting");
		stage.setScene((new Scene(root1)));
		stage.show();
    }

    @FXML
    void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CatalogTypeScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage LoginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		LoginStage.setTitle("Catalog Type");
		LoginStage.setScene(scene);
		LoginStage.show();
    }

    @FXML
    void btnChooseStore(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ChooseStore.fxml")));
		Scene scene = new Scene(parent);
		Stage LoginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		LoginStage.setTitle("Choose Store");
		LoginStage.setScene(scene);
		LoginStage.show();
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
